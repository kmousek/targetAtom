package com.ktds.targetatom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.processor.interceptor.Tracer;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.ktds.targetatom.bean.AuditHandler;
import com.ktds.targetatom.bean.ReferenceLoadBean;
import com.ktds.targetatom.bean.ShutdownConfig;
import com.ktds.targetatom.common.DynamicAddRoute;
import com.ktds.targetatom.common.RouteLoader;
import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.tracer.TraceFormatter;
import com.ktds.targetatom.vo.CamelRouteFileInfo;
import com.ktds.targetatom.vo.CdrReferenceInfo;

@Configuration
@SpringBootApplication
@ImportResource("classpath:spring/camelContextMain.xml")
//@ImportResource("classpath:spring/camelContext.xml")
//@ImportResource("classpath:spring/camelContext_async.xml")
//@ImportResource("file:/home/sangsoo_kim_rnb/processor/spring/camelContext.xml")
@PropertySource(value = {"classpath:application.properties", "classpath:camel-properties.properties"})
public class TargetatomProcessorApplication {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String PROCESS_INSTANCE = "PRC";
	private static ConfigurableApplicationContext context;
	/*@Autowired
	private CamelContext camelContext;
	*/
	@Value("${processor.format}") 
	private String formatName;
	
	@Value("${processor.instance.id}")
	private String instanceId;
	
	@Value("${message.collect.queue.id}")
	private String queueCollectId;
	
//	@Value("${dynamic.route.enabled}")
//	private boolean routeEnabled;
	
//  멀티 Target Directory 사용 시 활용
	@Value("${dss.id}")
	private String dssId;
	
	@Value("${ne.id}")
	private String sNeTypeId;
	
	@Autowired
	AuditHandler auditHandler;
	
	@Autowired
	ReferenceInfoDao referenceInfoDao;
	
	@Autowired
	ReferenceLoadBean referenceLoadBean;
	
	@Autowired
	ShutdownConfig shutdownConfig;
	

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TargetatomProcessorApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		context = app.run(args);
	}
	
	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);
		
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(TargetatomProcessorApplication.class, args.getSourceArgs());
		});
		
		thread.setDaemon(false);
		thread.start();
		
	}
//	@Bean
//	public KieContainer kieContainer() {
//		return KieServices.Factory.get().getKieClasspathContainer();
//	}
	
	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext camelContext) {
			}
			@Override
			public void afterApplicationStart(CamelContext camelContext) {
				Tracer tracer = new Tracer();
				TraceFormatter formatter = new TraceFormatter();
				tracer.setFormatter(formatter);
				tracer.setEnabled(true);
				camelContext.addInterceptStrategy(tracer);
				
				referenceLoadBean.loadReferenceData();
				
				try {
					List<CamelRouteFileInfo> resultSet = referenceInfoDao.getCamelRouteFileList(formatName, PROCESS_INSTANCE);
					
					
					if (resultSet==null) return;
					
					log.debug("resultSet.get(0).getModuleType()  ::: {}", resultSet.get(0).getModuleType());
					
					//if (true) {
					if (resultSet.get(0).getModuleType().toUpperCase().equals("FILE")) {
					//if (routeEnabled == true) {
						List<String> sourceDirectoryList = new ArrayList<String>();
						//Map<String, String> targetDirectoryMap = new HashMap<String, String>();
					
						List<CdrReferenceInfo> referenceInfos = referenceInfoDao.getCdrReferenceInfoList(instanceId);
						Map<String, String> mainTargetDirectoryMap  = new HashMap<String, String>();
						Map<String, String> dropTargetDirectoryMap  = new HashMap<String, String>();
						Map<String, String> errorTargetDirectoryMap = new HashMap<String, String>();
												
						for (int i = 0; i < referenceInfos.size(); i++) {
							
							CdrReferenceInfo referenceInfo = referenceInfos.get(i);
						
							if (referenceInfo.getDssId().equals(dssId)) {
								if (!sourceDirectoryList.contains(referenceInfo.getCdrFileColecDirNm())) {
									sourceDirectoryList.add(referenceInfo.getCdrFileColecDirNm());
								}
							
								if (referenceInfo.getTrmDirNm().contains("DRP")) {
									if (!dropTargetDirectoryMap.containsValue(referenceInfo.getNeId())) {
										dropTargetDirectoryMap.put(referenceInfo.getNeId(), referenceInfo.getTrmDirNm());
									}
								} else if (referenceInfo.getTrmDirNm().contains("ERR")) {
									if (!errorTargetDirectoryMap.containsValue(referenceInfo.getNeId())) {
										errorTargetDirectoryMap.put(referenceInfo.getNeId(), referenceInfo.getTrmDirNm());
									}
								} else {
									if (!mainTargetDirectoryMap.containsValue(referenceInfo.getNeId())) {
										mainTargetDirectoryMap.put(referenceInfo.getNeId(), referenceInfo.getTrmDirNm());
									}
								}
							}

						}
					
						log.debug("mainTargetDirectoryMap  ::: {}", mainTargetDirectoryMap.toString());
						log.debug("dropTargetDirectoryMap  ::: {}", dropTargetDirectoryMap.toString());
						log.debug("errorTargetDirectoryMap ::: {}", errorTargetDirectoryMap.toString());
				
										
						////////////////////////////// AddRoute /////////////////////////////////////
						for (int i = 0; i < sourceDirectoryList.size(); i++) {
							String sourceDirectory = "file:" + sourceDirectoryList.get(i) + "?noop=false&charset=iso-8859-1&readLock=changed&readLockTimeout=5000";
							
							//Queue 처리용
							//camelContext.addRoutes(new InitialRouter(camelContext, sourceDirectory, "jms:queue:" + queueCollectId));
							//File 처리용
							camelContext.addRoutes(new DynamicAddRoute(camelContext, sourceDirectory, "direct:source"));
						
							//targetDirectory.put(referenceInfo.getCdrFileNmngRuleSbst().substring(0, 6), referenceInfo.getTrmDirNm());
						
							//camelContext.addRoutes(new InitialRouter(camelContext, sourceDirectory, "direct:source"));
						}
						auditHandler.setMainTargetDirectoryMap(mainTargetDirectoryMap);
						auditHandler.setDropTargetDirectoryMap(dropTargetDirectoryMap);
						auditHandler.setErrorTargetDirectoryMap(errorTargetDirectoryMap);
					}
					
					//auditHandler.setKieSession(resultSet.get(0).getKieSession());
					
//					String routePath = getRouteFile(camelContext, formatName);			
//					RouteLoader.getInstance().addRoute(camelContext, routePath);
					
					///Test
					//RouteLoader.getInstance().addRoute(camelContext, "file:D:/home/Routes/krRouteProcessor.xml");
					//RouteLoader.getInstance().addRoute(camelContext, resultSet.get(0).getRouteFile());
					//RouteLoader.getInstance().addRoute(camelContext, "spring/routes/kr/krRouteProcessor.xml");
					//RouteLoader.getInstance().addRoute(camelContext, "spring/routes/volte/volteRouteProcessor.xml");
					//RouteLoader.getInstance().addRoute(camelContext, "spring/routes/intcc/intccRouteProcessor.xml");
					log.debug("before : RouteLoader.getInstance().addRoute(camelContext, resultSet.get(0).getRouteFile());");
					RouteLoader.getInstance().addRoute(camelContext, resultSet.get(0).getRouteFile());	
					log.debug("after : RouteLoader.getInstance().addRoute(camelContext, resultSet.get(0).getRouteFile());");
					
				} catch (Exception e) {
					log.error("Exception occured during initialize application after camelContext stated : \n {}", e);
					System.exit(0);
				}	finally {
					log.debug("<-- afterApplicationStart");
				}
				
				try {
					camelContext.startAllRoutes();
					log.info("All Routers are started!!!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}

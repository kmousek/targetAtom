package com.ktds.targetatom.test;
//package com.ktds.targetatom.test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.camel.CamelContext;
//import org.apache.camel.processor.interceptor.Tracer;
//import org.apache.camel.spring.boot.CamelContextConfiguration;
//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ExitCodeGenerator;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.context.annotation.PropertySource;
//
//import com.ktds.targetatom.bean.TerminateBean;
//import com.ktds.targetatom.dao.ReferenceInfoDao;
//
//import com.ktds.targetatom.common.DynamicAddRoute;
//import com.ktds.targetatom.common.RouteLoader;
//import com.ktds.targetatom.tracer.TraceFormatter;
//import com.ktds.targetatom.vo.CdrReferenceInfo;
//
//@Configuration
//@ComponentScan("com.ktds.targetatom")
//@SpringBootApplication
//@ImportResource("classpath:spring/camelContextMain.xml")
////@ImportResource("classpath:spring/camelContext.xml")
////@ImportResource("classpath:spring/camelContext_async.xml")
////@ImportResource("file:/home/sangsoo_kim_rnb/processor/spring/camelContext.xml")
//@PropertySource(value = {"classpath:application.properties", "classpath:camel-properties.properties"})
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//public class DynamicDispatcherRefactoredApplication {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	private CamelContext camelContext;
//
//	@Value("${processor.format}")
//	private String formatName;
//
////	@Value("${processor.instance.id}")
////	private String instanceId;
//	
//	private String instanceId	= "P1_IPTVKR";
//
//	@Value("${dss.id}")
//	private String dssId;
//
//	@Autowired
//	ReferenceInfoDao referenceInfoDao;
//
//	Map<String, String> target_directory = new HashMap<String, String>();
//
//	public static void main(String[] args) {
//		SpringApplication.run(DynamicDispatcherRefactoredApplication.class, args);
//	}
//
//	@Bean
//	public KieContainer kieContainer() {
//		return KieServices.Factory.get().getKieClasspathContainer();
//	}
//
//	@Bean
//	CamelContextConfiguration contextConfiguration() {
//		return new CamelContextConfiguration() {
//			@Override
//			public void beforeApplicationStart(CamelContext camelContext) {
//
//			}
//
//			@Override
//			public void afterApplicationStart(CamelContext camelContex) {
//				log.debug("--> afterApplicationStart");
//				log.trace("   camelContext.name= {}", camelContext.getName());
//				try {
//					Tracer tracer = new Tracer();
//					TraceFormatter formatter = new TraceFormatter();
//					tracer.setFormatter(formatter);
//					tracer.setEnabled(true);
//					camelContext.addInterceptStrategy(tracer);
//
//					List<String> sourceDirectoryList = new ArrayList<String>();
//					//Map<String, String> targetDirectoryMap = new HashMap<String, String>();
//				
//					List<CdrReferenceInfo> referenceInfos = referenceInfoDao.getCdrReferenceInfoList(instanceId);
//				
//					for (int i = 0; i < referenceInfos.size(); i++) {
//						CdrReferenceInfo referenceInfo = referenceInfos.get(i);
//					
//						if (referenceInfo.getDssId().equals(dssId)) {
//							if (!sourceDirectoryList.contains(referenceInfo.getCdrFileColecDirNm())) {
//								sourceDirectoryList.add(referenceInfo.getCdrFileColecDirNm());
//							}
//						}
//
//					}
//									
//					////////////////////////////// AddRoute /////////////////////////////////////
//					for (int i = 0; i < sourceDirectoryList.size(); i++) {
//						String sourceDirectory = "file:" + sourceDirectoryList.get(i) + "?noop=false&charset=iso-8859-1";
//						camelContext.addRoutes(new DynamicAddRoute(camelContext, sourceDirectory, "direct:source"));
//					}
//					
////					List<CdrReferenceInfo> referenceInfos = referenceInfoDao.getCdrReferenceInfoList(instanceId);
////					for (int i = 0; i < referenceInfos.size(); i++) {
////						CdrReferenceInfo referenceInfo = referenceInfos.get(i);
////						String sourceDirectory = "file:" + referenceInfo.getCdrFileColecDirNm() + "?noop=false&charset=iso-8859-1";
////						log.debug("referenceInfo.getTrmDirNm()={}", referenceInfo.getTrmDirNm());
////						target_directory.put(referenceInfo.getCdrFileNmngRuleSbst().substring(0, 6), referenceInfo.getTrmDirNm());
////
////      			camelContext.addRoutes(new InitialRouter(camelContext, sourceDirectory, "direct:source"));
////					}
////					log.debug("route = {}", camelContext.getRoutes());
//
//					String routePath = "spring/routes/DynamicDispatcher.xml";
//
//					RouteLoader.getInstance().addRoute(camelContext, routePath);
//				} catch (Exception e) {
//					log.error("Exception occured during initialize application after camelContext stated : \n {}", e);
//				} finally {
//					log.debug("<-- afterApplicationStart");
//				}
//			}
//		};
//	}
//}

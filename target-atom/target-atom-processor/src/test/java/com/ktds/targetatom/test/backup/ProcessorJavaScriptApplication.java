package com.ktds.targetatom.test.backup;
//package com.ktds.targetatom;
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
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.context.annotation.PropertySource;
//
//import com.ktds.targetatom.bean.AuditHandler;
//import com.ktds.targetatom.common.DynamicAddRoute;
//import com.ktds.targetatom.common.RouteLoader;
//import com.ktds.targetatom.dao.ReferenceInfoDao;
//import com.ktds.targetatom.bean.ErrMappgBasBean;
//import com.ktds.targetatom.tracer.TraceFormatter;
//import com.ktds.targetatom.vo.CamelRouteFileInfo;
//import com.ktds.targetatom.vo.CdrReferenceInfo;
//import com.ktds.targetatom.bean.CdrValidationCheck;
//
//@Configuration
//@SpringBootApplication
//@ImportResource("classpath:spring/camelContextMain.xml")
////@ImportResource("classpath:spring/camelContext.xml")
////@ImportResource("classpath:spring/camelContext_async.xml")
////@ImportResource("file:/home/sangsoo_kim_rnb/processor/spring/camelContext.xml")
//@PropertySource(value = {"classpath:application.properties", "classpath:camel-properties.properties"})
//public class ProcessorJavaScriptApplication  {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	
//	private static final String PROCESS_INSTANCE = "PRC";
//	
//	@Autowired
//	private CamelContext camelContext;
//	
//	@Value("${processor.format}") 
//	private String formatName;
//	
//	@Value("${processor.instance.id}")
//	private String instanceId;
//	
//	@Value("${message.collect.queue.id}")
//	private String queueCollectId;
//	
////	@Value("${dynamic.route.enabled}")
////	private boolean routeEnabled;
//	
//	@Value("${dss.id}")
//	private String dssId;
//	
//	@Autowired
//	AuditHandler auditHandler;
//	
//	@Autowired
//	ReferenceInfoDao referenceInfoDao;
//	
//	@Autowired
//	ErrMappgBasBean errMappgBasBean;
//	
//	@Autowired
//	CdrValidationCheck cdrValChk;
//	
//	public static void main(String[] args) {
//		SpringApplication.run(ProcessorJavaScriptApplication.class, args);
//	}
//	
//	@Bean
//	CamelContextConfiguration contextConfiguration() {
//		return new CamelContextConfiguration() {
//			@Override
//			public void beforeApplicationStart(CamelContext camelContext) {
//				
//			}
//			@Override
//			public void afterApplicationStart(CamelContext camelContex) {
//				Tracer tracer = new Tracer();
//				TraceFormatter formatter = new TraceFormatter();
//				tracer.setFormatter(formatter);
//				tracer.setEnabled(true);
//				camelContext.addInterceptStrategy(tracer);
//				
//				try {
//					
//					List<CamelRouteFileInfo> resultSet = referenceInfoDao.getCamelRouteFileList(formatName, PROCESS_INSTANCE);
//					
//					if (resultSet==null) return;
//					
//					if (resultSet.get(0).getModuleType().toUpperCase().equals("FILE")) {
//					//if (routeEnabled == true) {
//						List<String> sourceDirectoryList = new ArrayList<String>();
//						//Map<String, String> targetDirectoryMap = new HashMap<String, String>();
//					
//						List<CdrReferenceInfo> referenceInfos = referenceInfoDao.getCdrReferenceInfoList(instanceId);
//						Map<String, String> mainTargetDirectoryMap  = new HashMap<String, String>();
//						Map<String, String> dropTargetDirectoryMap  = new HashMap<String, String>();
//						Map<String, String> errorTargetDirectoryMap = new HashMap<String, String>();
//						
//						for (int i = 0; i < referenceInfos.size(); i++) {
//							
//							CdrReferenceInfo referenceInfo = referenceInfos.get(i);
//						
//							if (referenceInfo.getDssId().equals(dssId)) {
//								if (!sourceDirectoryList.contains(referenceInfo.getCdrFileColecDirNm())) {
//									sourceDirectoryList.add(referenceInfo.getCdrFileColecDirNm());
//								}
//							
//								if (referenceInfo.getTrmDirNm().contains("DRP")) {
//									if (!dropTargetDirectoryMap.containsValue(referenceInfo.getNeId())) {
//										dropTargetDirectoryMap.put(referenceInfo.getNeId(), referenceInfo.getTrmDirNm());
//									}
//								} else if (referenceInfo.getTrmDirNm().contains("ERR")) {
//									if (!errorTargetDirectoryMap.containsValue(referenceInfo.getNeId())) {
//										errorTargetDirectoryMap.put(referenceInfo.getNeId(), referenceInfo.getTrmDirNm());
//									}
//								} else {
//									if (!mainTargetDirectoryMap.containsValue(referenceInfo.getNeId())) {
//										mainTargetDirectoryMap.put(referenceInfo.getNeId(), referenceInfo.getTrmDirNm());
//									}
//								}
//							}
//
//						}
//					
//						log.debug("mainTargetDirectoryMap  ::: {}", mainTargetDirectoryMap.toString());
//						log.debug("dropTargetDirectoryMap  ::: {}", dropTargetDirectoryMap.toString());
//						log.debug("errorTargetDirectoryMap ::: {}", errorTargetDirectoryMap.toString());
//				
//										
//						////////////////////////////// AddRoute /////////////////////////////////////
//						for (int i = 0; i < sourceDirectoryList.size(); i++) {
//							String sourceDirectory = "file:" + sourceDirectoryList.get(i) + "?noop=false&charset=iso-8859-1";
//							
//							//Queue 처리용
//							//camelContext.addRoutes(new InitialRouter(camelContext, sourceDirectory, "jms:queue:" + queueCollectId));
//							//File 처리용
//							camelContext.addRoutes(new DynamicAddRoute(camelContext, sourceDirectory, "direct:source"));
//						
//							//targetDirectory.put(referenceInfo.getCdrFileNmngRuleSbst().substring(0, 6), referenceInfo.getTrmDirNm());
//						
//							//camelContext.addRoutes(new InitialRouter(camelContext, sourceDirectory, "direct:source"));
//						}
//						auditHandler.setMainTargetDirectoryMap(mainTargetDirectoryMap);
//						auditHandler.setDropTargetDirectoryMap(dropTargetDirectoryMap);
//						auditHandler.setErrorTargetDirectoryMap(errorTargetDirectoryMap);
//					}
//					
////					auditHandler.setKieSession(resultSet.get(0).getKieSession());
//					
//					RouteLoader.getInstance().addRoute(camelContext, "spring/routes/volte/volteRouteProcessor.xml");
//					
//					// Error Mapping bean 
//					String sNeTypeId = "VOVLTE";
//					int iRowCnt = errMappgBasBean.getErrMappgList(sNeTypeId);
//					
//					errMappgBasBean.setComAuditErrorSearch(sNeTypeId, "");
//					
//					//cdrValChk.setValidation1();
//				} catch (Exception e) {
//					log.error("Exception occured during initialize application after camelContext stated : \n {}", e);
//				}	finally {
//					log.debug("<-- afterApplicationStart");
//				}
//			}
//		};
//	}
//}

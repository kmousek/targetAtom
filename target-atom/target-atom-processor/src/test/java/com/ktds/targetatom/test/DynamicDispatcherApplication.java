package com.ktds.targetatom.test;
//package com.ktds.targetatom.test;
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
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.ImportResource;
//
//import com.ktds.targetatom.bean.TerminateBean;
//import com.ktds.targetatom.common.RouteLoader;
//
//@SpringBootApplication
//@ImportResource("classpath:spring/camelContextMain.xml")
////@ImportResource("classpath:spring/camelContext.xml")
////@ImportResource("classpath:spring/camelContext_async.xml")
////@ImportResource("file:/home/sangsoo_kim_rnb/processor/spring/camelContext.xml")
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//public class DynamicDispatcherApplication  {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	private CamelContext camelContext;
//	
//	@Value("${processor.format}") 
//	private String formatName;
//	
//	public static void main(String[] args) {
//		SpringApplication.run(DynamicDispatcherApplication.class, args);
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
//			@Override
//			public void afterApplicationStart(CamelContext camelContex) {
//				log.debug("--> afterApplicationStart");
//				log.trace("   camelContext.name= {}", camelContext.getName());
//				MznDBProcessor mznDBProcessor = new MznDBProcessor();
//				try {
//        	Tracer tracer = new Tracer();
//        	tracer.setEnabled(true);
//        	camelContext.addInterceptStrategy(tracer);
//
//					mznDBProcessor.getReferenceInfo(camelContext);
//					String routePath = "spring/routes/DynamicDispatcher.xml";
//		
//					RouteLoader.getInstance().addRoute(camelContext, routePath);
//				} catch (Exception e) {
//					log.error("Exception occured during initialize application after camelContext stated : \n {}", e);
//				}	finally {
//					log.debug("<-- afterApplicationStart");
//				}
//			}
//		};
//	}
//}

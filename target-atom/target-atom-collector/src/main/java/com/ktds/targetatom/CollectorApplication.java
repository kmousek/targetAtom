package com.ktds.targetatom;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.processor.interceptor.Tracer;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import com.ktds.targetatom.common.DynamicAddRoute;
import com.ktds.targetatom.common.RouteLoader;
import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.tracer.TraceFormatter;
import com.ktds.targetatom.vo.CamelRouteFileInfo;
import com.ktds.targetatom.vo.CollectStrategyInfo;

@SpringBootApplication
@ImportResource("classpath:spring/camelContextMain.xml")
@Configuration
@ComponentScan("com.ktds.targetatom")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CollectorApplication {
	

	@Autowired
	private ReferenceInfoDao collRefDao;
	
	@Value("${localcollinfo.instanceName}")
	private String instanceId;
	
	@Value("${dataformat.id}")
	private String formatId;
	
//	@Value("${config.test.acet}")
//	private String acet;
	
	private final Logger log = LogManager.getLogger(this.getClass());

	public static void main(String[] args) throws Exception {

		SpringApplication.run(CollectorApplication.class, args);
	}
	
	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext camelContext) {
			
			}
			
			@Override
			public void afterApplicationStart(CamelContext camelContext) {
				
//				log.info("config.test.acet is {} ", acet);
				
				try {
					Tracer tracer = new Tracer();
					TraceFormatter formatter = new TraceFormatter();
					tracer.setFormatter(formatter);
					tracer.setEnabled(true);
					camelContext.addInterceptStrategy(tracer);

					List<CollectStrategyInfo> result = collRefDao.getCollectStrategyList(instanceId);
					
					for (int idx=0; idx < result.size(); idx++) {
						CollectStrategyInfo collDirVo = result.get(idx);
						
						if (collDirVo.getFnsFileCretYn().equals("Y")) {
							System.out.println("if (collDirVo.getFnsFileCretYn().equals(\"Y\")) {");
							if (collDirVo.getFnsFileDivCd().equals("1")) {
								System.out.println("if (collDirVo.getFnsFileDivCd().equals(\"1\")) {");
								camelContext.addRoutes(new DynamicAddRoute(camelContext, "file:"+collDirVo.getCdrFileColecDirNm()+"?noop=true&delete=false&idempotent=false&doneFileName=${file:name.noext}.FIN", "direct:start1"));
							}
							else {
								System.out.println("else");
								camelContext.addRoutes(new DynamicAddRoute(camelContext, "file:"+collDirVo.getCdrFileColecDirNm()+"?noop=true&delete=false&idempotent=false&doneFileName=${file:name.noext}.DAT.FIN", "direct:start1"));
							}
						}
						else {
							camelContext.addRoutes(new DynamicAddRoute(camelContext, "file:"+collDirVo.getCdrFileColecDirNm()+"?delete=true", "direct:start1"));
						}

					}
					
					List<CamelRouteFileInfo> resultSet = collRefDao.getCamelRouteFileList(formatId, "COL");
					
					for (CamelRouteFileInfo row : resultSet) {
						log.info("Route path & file is {} ", row.getRouteFile());
						RouteLoader.getInstance().addRoute(camelContext, row.getRouteFile());
					}
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
		
			}
		};
	}
}

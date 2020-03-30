package com.ktds.targetatom.rule.test;
//package com.ktds.targetatom.rule.test;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.text.SimpleDateFormat;
//
//import org.apache.camel.test.junit4.TestSupport;
//import org.apache.camel.test.spring.CamelSpringBootRunner;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.ktds.targetatom.targetatomProcessorApplication;
//import com.ktds.targetatom.ProcessorJavaScriptApplication;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
//@RunWith(CamelSpringBootRunner.class)
////@PropertySource(value = {"classpath:application.properties", "classpath:camel-properties.properties"})
//@SpringBootTest(classes = ProcessorJavaScriptApplication.class)
//public class ProcessorApplicationJavaScriptTest {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	AbstractApplicationContext applicationContext; 
//	String cdrFileName = "SMOKD1_FW4GCLTE_ID0608_T20190502235802.DAT";
//	String cdrFilePath = "/WRK/PRC/WLSS_NRAT/VOVLTE/MOKD1/";
//	String orgFilePath = "data/inbox/";
//	String destFilePath = "data/outbox/";
//	
//	@Before
//	public void setUp() throws Exception {
//		TestSupport.deleteDirectory(destFilePath);
//		
//		File destDirectory = new File(cdrFilePath);
//		if (!destDirectory.exists()) {
//			log.info("{} is not exist, create directory", destDirectory.toPath());
//			Files.createDirectories(destDirectory.toPath());
//		}
//		File org = new File(orgFilePath + cdrFileName);
//		File dest = new File(cdrFilePath + cdrFileName);
//		log.info("Copying test cdr from {} to {}", org.getPath(), dest.getPath());
//		Files.copy(org.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
//	}
//	
//	@Test
//	public void testComponent() throws Exception {
//		Thread.sleep(30000);
////		File target = new File(destFilePath + cdrFileName);
////		assertTrue("File not moved " + target.getAbsolutePath(), target.exists());
//	}
//}

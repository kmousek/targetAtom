package com.ktds.targetatom.test;
//package com.ktds.targetatom.test;
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
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@RunWith(CamelSpringBootRunner.class)
//@SpringBootTest(classes = DynamicDispatcherRefactoredApplication.class)
//public class DynamicDispatcherRefactoredApplicationTest {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	AbstractApplicationContext applicationContext; 
//	String cdrFileName = "SLPNPM_FGIDRO01_ID0001_T20190312000500.DAT";
//	String cdrFilePath = "/home/WRK/PRC/WLNE/IPTVKR/KRLPPM10/";
//	String orgFilePath = "data/inbox/";
//	String destFilePath = "data/outbox/";
//	
//	@Before
//	public void setUp() throws Exception {
//		TestSupport.deleteDirectory(destFilePath);
//		
//		File destDirectory = new File(cdrFilePath);
//		if (!destDirectory.exists()) {
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
//		Thread.sleep(2000);
//		File target = new File(destFilePath + cdrFileName);
//		assertTrue("File not moved " + target.getAbsolutePath(), target.exists());
//	}
//}

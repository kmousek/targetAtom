package com.ktds.targetatom.dao.test;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.json.JSONObject;

import com.ktds.targetatom.dao.AuditInfoDao;
import com.ktds.targetatom.vo.AuditInfo;
import com.ktds.targetatom.vo.BaseInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootJDBCTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@PropertySource(value = {"classpath:application.properties", "classpath:sql.properties"})
public class AuditInfoDaoTest {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private AuditInfoDao dao;
	
	private static AuditInfo vo; 
	private static String cdrFileName = "SLPNPM_FGIDRO01_ID0001_T20190312020500_0_001.DAT"; // SLPNPM_FGIDRO01_ID0001_T20190312000500.DAT
	private static String cdrFilePath = "/home/WRK/SFTP/NRAT/IPTVKR/KRLPPM10"; // /home/WRK/PRC/WLNE/IPTVKR/KRLPPM10

//	public static void main(String[] args) {
//		SpringApplication.run(AuditInfoDaoTest.class, args);
//	}

	@BeforeClass
	public static void setUp() {
//		String pModuleNm, long pUpAditSeq, String pFileNm, String pTrmDirNm, String pOrgnFileNm, String pSttus, String pFileType
		vo = new AuditInfo();
		vo.setFileNm(cdrFileName);
		vo.setTrmDirNm(cdrFilePath);
		vo.setOrgnFileNm(cdrFileName);
		vo.setModuleNm("TargetAtom");
		vo.setSttus("RD");
		vo.setFileType("PRC");
	}

	@Test 
	public void test1_insert() {
		Long result = dao.insert(vo);
		assertTrue(result > 0);
		vo.setAditCretSeq(result);
		log.info("QueryResult={}", result);
	}
	
	@Test
	public void test2_findByFileName() throws Exception {
//		Map<String, Object> auditInfoMap;
//		auditInfoMap = auditInfoDao.getAuditList(cdrFileName, cdrFilePath);
//		assertNotNull(auditInfoMap);
//		MapUtils.debugPrint(System.out, "QueryResult", auditInfoMap);
//		log.info("QueryResult={}", MapUtils.toProperties(auditInfoMap));
//		log.info("QueryResult={}", (new JSONObject(auditInfoMap)).toString(2));
		AuditInfo result = dao.findByFileName(cdrFileName,cdrFilePath);
		assertNotNull(result);
		assertThat(result.getFileNm(), is(cdrFileName));
//		log.info("QueryResult={}", result);
	}
	@Test
	public void test3_update() throws Exception {
		vo.setRcrdCnt(10L);
		vo.setErrCnt(10L);
		int result = dao.update(vo);
		assertTrue(result == 1);
//		log.info("QueryResult={}", result);
	}
	@Test
	public void test4_updateStatus() throws Exception {
		vo.setSttus("CO");
		int result = dao.updateStatus(vo);
		assertTrue(result == 1);
//		log.info("QueryResult={}", result);
	}
		
	@Test
	public void test5_finallValidation() throws Exception {
		AuditInfo result = dao.findById(vo.getAditCretSeq());
		assertNotNull(result);
		assertThat(result.getFileNm(), is(cdrFileName));
//		log.info("QueryResult={}", result);
	}
	
	// Distributer
	@Test
	public void test6_updateDistributerStatus() throws Exception {
		// Distributer Test용  임시코드
		vo.setSttus("RD");
		vo.setFileType("PRC_CMP");
		int result = dao.updateDistributerStatus(vo); //update

//		vo.setSttus("RD");
		vo.setChgSttus("IU");
//		vo.setFileNm("SLPNPM_FGIDRO01_ID0001_T20190312020500_0_001.DAT");
//		vo.setAditCretSeq(1000087190);
		result = dao.updateDistributerStatus(vo);
//		assertTrue(result == 1);
//		log.info("QueryResult={}", result);
	}
	
	// Distributer
		@Test
		public void test7_updateDistriLastStatus() throws Exception {
			// Distributer Test용  임시코드
			vo.setSttus("IU");
			int result = dao.updateDistributerStatus(vo); // update

//			vo.setSttus("IU");
			vo.setChgSttus("CO");
//			vo.setFileNm("SLPNPM_FGIDRO01_ID0001_T20190312020500_0_001.DAT");
//			vo.setAditCretSeq(1000087190);
			result = dao.updateDistributerStatus(vo);
//			assertTrue(result == 1);
//			log.info("QueryResult={}", result);
		}

	@Test
	public void test8_deleteTestData() throws Exception {
		int result = dao.delete(vo.getAditCretSeq());
		assertTrue(result == 1);
	}
}



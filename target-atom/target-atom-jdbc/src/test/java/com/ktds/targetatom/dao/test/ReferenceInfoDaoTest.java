package com.ktds.targetatom.dao.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.vo.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootJDBCTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@PropertySource(value = {"classpath:application.properties", "classpath:sql.properties"})
public class ReferenceInfoDaoTest {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ReferenceInfoDao dao;
	
	@BeforeClass
	public static void setUp() {
	}
/*
	@Test
	public void test1_getCamelRouteFileList() throws Exception {
		List<CamelRouteFileInfo> result = dao.getCamelRouteFileList("KR", "COL");
		assertNotNull(result);
		assertTrue(result.size() > 0);
//		log.info("QueryResult={}", result);
	}

	@Test
	public void test1_getCdrReferenceInfo() throws Exception {
		List<CdrReferenceInfo> result = dao.getCdrReferenceInfoList("C3_IPTVKR_D");
		assertNotNull(result);
		assertTrue(result.size() > 0);
//		log.info("QueryResult={}", result);
	}
	
	@Test
	public void test3_getCollectStrategyList() throws Exception {
		List<CollectStrategyInfo> result = dao.getCollectStrategyList("C2_IPTVKR_D");
		assertNotNull(result);
		assertTrue(result.size() > 0);
//		log.info("QueryResult={}", result);
	}
	
	@Test
	public void test4_getCdrBaseList() throws Exception {
		List<CdrBaseInfo> result = dao.getCdrBaseList("C2_IPTVKR_D");
		assertNotNull(result);
		assertTrue(result.size() > 0);
//		log.info("QueryResult={}", result);
	}

	// Distributer
	@Test
	public void test2_getDistributerCdrReferenceInfo() throws Exception {
		List<CdrReferenceInfo> result = dao.getDistributerCdrReferenceInfoList("F1_RWLNIP_D");
		assertNotNull(result);
		assertTrue(result.size() > 0);
//		log.info("QueryResult={}", result);
	}
	
	@Test
	public void test5_getTbPfixRgnBasInfo() throws Exception {
		//List<TbPfixRgnBasInfo> result = dao.getPfixRgnBasInfo("053","20190601");
		TbPfixRgnBasInfo result = dao.getPfixRgnBasInfo("053","20190601");
		assertNotNull(result);
		assertTrue(result != null);
	}
*/
	@Test
	public void test6_getTbErrMappgBasinfo() throws Exception {
		List<TbErrMappgBasInfo> result = dao.getTbErrMappgBasinfo("VOVLTE");
		assertNotNull(result);
		assertTrue(result != null);
	}
	
	@Test
	public void test7() throws Exception {
		//DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd24HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		//Date date = dateFormat.parse("20070915000000");
		Date date = dateFormat.parse("20190709122345");
		
	}

}

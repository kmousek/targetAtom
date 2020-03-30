package com.ktds.targetatom.samples.route;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ktds.targetatom.util.CommonUtils;

public class CommonUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		System.out.println(CommonUtils.findCondition("20120901000000", "99991231235959", "20190502235327"));
		//System.out.println(CommonUtil.findCondition("20120901000000", "99991231235959", "99991231235959"));
		assertTrue(CommonUtils.findCondition("20120901000000", "99991231235959", "20190502235327"));
	}

}

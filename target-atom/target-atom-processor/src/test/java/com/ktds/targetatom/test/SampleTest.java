package com.ktds.targetatom.test;
//package com.ktds.targetatom.test;
//
//import static org.junit.Assert.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class SampleTest {
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void test1() {
//		String temp = "C:/home/WRK/PRC/WLNE/IPTVKR/KRLPPM10";
//		int num = temp.lastIndexOf('/');
//		//System.out.println(temp.substring(temp.lastIndexOf("/"), 1));
//		System.out.println(num);
//		System.out.println(temp.substring(num+1, temp.length()));
//	}
//	
//	@Test
//	public void test2() {
//		String temp = "3  ";
//		System.out.println(String.format("%3s", temp.trim()).replace(' ','0'));
//	}
//	
//	@Test
//	public void test3() throws ParseException {
//		assertFalse(isThisDateValid("20191514110550", "yyyyMMddHHmmss"));
//	}
//	
//	public boolean isThisDateValid(String dateToValidate, String dateFormat) {
//		if (dateToValidate == null) {
//			return false;
//		}
//		
//		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
//		sdf.setLenient(false);
//		
//		try {
//			Date date = sdf.parse(dateToValidate);
//			System.out.println(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//		return false;
//		
//	}
//
//}

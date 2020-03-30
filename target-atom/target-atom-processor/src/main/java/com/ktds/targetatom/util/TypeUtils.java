package com.ktds.targetatom.util;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

public class TypeUtils {
	public static int TYPE_ID = 3;
	
	public static int toInt(Object objInt) {
		if(objInt == null) return -1;
		return NumberUtils.toInt(objInt.toString());
	}
	public static int toInt(String strInt) {
		if(strInt == null) return -1;
		return NumberUtils.toInt(strInt);
	}
	public static long toLong(Object objLong) {
		if(objLong == null) return -1;
		return NumberUtils.toLong(objLong.toString());
	}
	public static long toLong(String strLong) {
		if(strLong == null) return -1;
		return NumberUtils.toLong(strLong);
	}
	public static Date toDate(Object objDate) throws Exception {
		return DateUtils.parseDate(objDate.toString());
	}
	public static Date toDate(String strDate) throws Exception {
		return DateUtils.parseDate(strDate);
	}
}

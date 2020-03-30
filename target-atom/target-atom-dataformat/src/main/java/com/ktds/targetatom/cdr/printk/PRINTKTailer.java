package com.ktds.targetatom.cdr.printk;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=200, paddingChar=' ', crlf="WINDOWS")
public class PRINTKTailer implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, defaultValue="T")
	char sType;
	
	@DataField(pos=2, length=8, trim=true, align="R")
	long lTotCnt;
	
	@DataField(pos=10, length=13, trim=true, align="R")
	long lTotMnt;
	
	@DataField(pos=23, length=12, trim=true, defaultValue=" ")
	String sFiller;
	
	@DataField(pos=35, length=1, trim=true, defaultValue=" ")
	String sEOF;

	
	public PRINTKTailer() {
		super();
	}

	public PRINTKTailer(char sType, long lTotCnt, long lTotMnt, String sFiller, String sEOF) {
		super();
		this.sType = sType;
		this.lTotCnt = lTotCnt;
		this.lTotMnt = lTotMnt;
		this.sFiller = sFiller;
		this.sEOF = sEOF;
	}

	@Override
	public String toString() {
		return "PRINTKTailer [sType=" + sType + ", lTotCnt=" + lTotCnt + ", lTotMnt=" + lTotMnt
				+ ", sFiller=" + sFiller + ", sEOF=" + sEOF + "]";
	}
}

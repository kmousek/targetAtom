package com.ktds.targetatom.cdr.kr;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=44, paddingChar=' ', crlf="UNIX")
public class KRTailer implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, defaultValue="T")
	char sType;
	
	@DataField(pos=2, length=7, trim=true, align="R")
	long lTotCnt;
	
	@DataField(pos=9, length=14, trim=true, align="L", pattern="yyyy-mm-dd 24hh:mm:ss")
	String sFileCreatedDate;
	
	@DataField(pos=23, length=10, trim=true, align="R")
	long sFileSize;
	
	@DataField(pos=33, length=12, trim=true, defaultValue=" ")
	String sFiller;
	
	@DataField(pos=45, length=1, trim=true, defaultValue=" ")
	String sEOF;

	
	public KRTailer() {
		super();
	}

	public KRTailer(char sType, long lTotCnt, String sFileCreatedDate, long sFileSize, String sFiller, String sEOF) {
		super();
		this.sType = sType;
		this.lTotCnt = lTotCnt;
		this.sFileCreatedDate = sFileCreatedDate;
		this.sFileSize = sFileSize;
		this.sFiller = sFiller;
		this.sEOF = sEOF;
	}

	@Override
	public String toString() {
		return "KRTailer [sType=" + sType + ", lTotCnt=" + lTotCnt + ", sFileCreatedDate=" + sFileCreatedDate
				+ ", sFileSize=" + sFileSize + ", sFiller=" + sFiller + ", sEOF=" + sEOF + "]";
	}
}

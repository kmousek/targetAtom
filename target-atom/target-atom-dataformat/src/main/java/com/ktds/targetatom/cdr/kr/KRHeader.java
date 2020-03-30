package com.ktds.targetatom.cdr.kr;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=39, paddingChar=' ', crlf="UNIX")
public class KRHeader implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, defaultValue="H")
	String sType;
	
	@DataField(pos=2, length=14, trim=true, pattern="yyyy-mm-dd 24hh:mm:ss")
	String sFileCreationDate;
	
	@DataField(pos=16, length=7, trim=true, align="L")
	String sFileType;
	
	@DataField(pos=23, length=5, trim=true, align="L")
	String sSensorId;
	
	@DataField(pos=28, length=13, trim=true, defaultValue=" ")
	String sFiller;

	
	public KRHeader() {
		super();
	}

	public KRHeader(String sType, String sFileCreationDate, String sFileType, String sSensorId, String sFiller) {
		super();
		this.sType = sType;
		this.sFileCreationDate = sFileCreationDate;
		this.sFileType = sFileType;
		this.sSensorId = sSensorId;
		this.sFiller = sFiller;
	}

	@Override
	public String toString() {
		return "KRHeader [sType=" + sType + ", sFileCreationDate=" + sFileCreationDate + ", sFileType=" + sFileType
				+ ", sSensorId=" + sSensorId + ", sFiller=" + sFiller + "]";
	}
}
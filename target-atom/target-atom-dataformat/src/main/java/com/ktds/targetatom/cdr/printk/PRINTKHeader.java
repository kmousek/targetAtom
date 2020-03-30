package com.ktds.targetatom.cdr.printk;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=200, paddingChar=' ', crlf="WINDOWS")
public class PRINTKHeader implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, defaultValue="H")
	String sType;
	
	@DataField(pos=2, length=6, trim=true, pattern="yyyyMM")
	String sUsedMonth;
	
	@DataField(pos=8, length=5, trim=true, align="L")
	String sSwitchId;
	
	@DataField(pos=13, length=4, trim=true, align="L")
	String sFileId;
	
	@DataField(pos=17, length=184, trim=true, defaultValue=" ")
	String sFiller;

	
	public PRINTKHeader() {
		super();
	}

	public PRINTKHeader(String sType, String sUsedMonth, String sSwitchId, String sFileId, String sFiller) {
		super();
		this.sType = sType;
		this.sUsedMonth = sUsedMonth;
		this.sSwitchId = sSwitchId;
		this.sFileId = sFileId;
		this.sFiller = sFiller;
	}

	@Override
	public String toString() {
		return "PRINTKHeader [sType=" + sType + ", sUsedMonth=" + sUsedMonth + ", sSwitchId=" + sSwitchId
				+ ", sFileId=" + sFileId + ", sFiller=" + sFiller + "]";
	}
}
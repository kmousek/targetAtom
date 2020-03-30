package com.ktds.targetatom.cdr.intcc;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=120, paddingChar=' ')
public class INTCCHeader implements Serializable { 
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, align="L")
	String record_type;
	
	@DataField(pos=2, length=6, trim=true, align="L")
	String used_month;
	
	@DataField(pos=8, length=5, trim=true, align="L")
	String company_name;
	
	@DataField(pos=13, length=4, trim=true, align="L")
	String file_ID;
	
	@DataField(pos=17, length=104, trim=true, align="L")
	String filler104;

	public INTCCHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public INTCCHeader(String record_type, String used_month, String company_name, String file_ID, String filler104) {
		super();
		this.record_type = record_type;
		this.used_month = used_month;
		this.company_name = company_name;
		this.file_ID = file_ID;
		this.filler104 = filler104;
	}

	
	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public String getUsed_month() {
		return used_month;
	}

	public void setUsed_month(String used_month) {
		this.used_month = used_month;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getFile_ID() {
		return file_ID;
	}

	public void setFile_ID(String file_ID) {
		this.file_ID = file_ID;
	}

	public String getFiller104() {
		return filler104;
	}

	public void setFiller104(String filler104) {
		this.filler104 = filler104;
	}

	public Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "INTCCHeader [record_type=" + record_type + ", used_month=" + used_month + ", company_name="
				+ company_name + ", file_ID=" + file_ID + ", filler104=" + filler104  + "]";
	}


	
}

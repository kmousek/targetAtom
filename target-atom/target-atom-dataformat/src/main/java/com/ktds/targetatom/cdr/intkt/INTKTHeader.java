package com.ktds.targetatom.cdr.intkt;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=200, paddingChar=' ')
public class INTKTHeader implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true)
	String record_type;
	
	@DataField(pos=2, length=6, trim=true, paddingChar=' ')
	String used_month;
	
	@DataField(pos=8, length=5, trim=true, paddingChar=' ')
	String switch_id;
	
	@DataField(pos=13, length=4, trim=true)
	String file_ID;
	
	@DataField(pos=17, length=184, trim=true, paddingChar=' ')
	String filler;

	public INTKTHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public INTKTHeader(String record_type, String used_month, String company_name, String file_ID, String filler104) {
		super();
		this.record_type = record_type;
		this.used_month = used_month;
		this.switch_id = company_name;
		this.file_ID = file_ID;
		this.filler = filler104;
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

	public String getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}

	public String getFile_ID() {
		return file_ID;
	}

	public void setFile_ID(String file_ID) {
		this.file_ID = file_ID;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "INTKTHeader [log=" + log + ", record_type=" + record_type + ", used_month=" + used_month
				+ ", switch_id=" + switch_id + ", file_ID=" + file_ID + ", filler=" + filler + "]";
	}

	
}

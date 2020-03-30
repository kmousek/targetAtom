package com.ktds.targetatom.cdr.intcc;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=120, paddingChar=' ')
public class INTCCTailer implements Serializable { 
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, align="L")
	String record_type;
	
	@DataField(pos=2, length=8, trim=true, align="R", paddingChar='0')
	String total_record;
	
	@DataField(pos=10, length=10, trim=true, align="R", paddingChar='0')
	String total_amount;
	
	@DataField(pos=20, length=101, trim=true, align="L")
	String filler101;
	
	public INTCCTailer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public INTCCTailer(String record_type, String total_record, String total_amount, String filler101) {
		super();
		this.record_type = record_type;
		this.total_record = total_record;
		this.total_amount = total_amount;
		this.filler101 = filler101;
	}


	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public String getTotal_record() {
		return total_record;
	}

	public void setTotal_record(String total_record) {
		this.total_record = total_record;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getFiller101() {
		return filler101;
	}

	public void setFiller101(String filler101) {
		this.filler101 = filler101;
	}

	public Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "INTCCTailer [record_type=" + record_type + ", total_record=" + total_record
				+ ", total_amount=" + total_amount + ", filler101=" + filler101 + "]";
	}

	
}

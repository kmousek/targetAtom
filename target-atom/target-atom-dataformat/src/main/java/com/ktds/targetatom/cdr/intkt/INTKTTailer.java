package com.ktds.targetatom.cdr.intkt;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=200, paddingChar=' ')
public class INTKTTailer implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true)
	String record_type;
	
	@DataField(pos=2, length=8, trim=true, align="R", paddingChar='0')
	String total_record;
	
	@DataField(pos=10, length=13, trim=true, align="R", paddingChar='0')
	String total_amount;
	
	@DataField(pos=23, length=178, trim=true, paddingChar=' ')
	String filler;
	
	public INTKTTailer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public INTKTTailer(String record_type, String total_record, String total_amount, String filler101) {
		super();
		this.record_type = record_type;
		this.total_record = total_record;
		this.total_amount = total_amount;
		this.filler = filler101;
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
		return "INTKTTailer [record_type=" + record_type + ", total_record=" + total_record
				+ ", total_amount=" + total_amount + ", filler=" + filler + "]";
	}
	
}

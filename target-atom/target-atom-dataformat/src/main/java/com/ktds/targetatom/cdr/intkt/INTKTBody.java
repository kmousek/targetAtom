package com.ktds.targetatom.cdr.intkt;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=200, header=INTKTHeader.class, footer=INTKTTailer.class)
public class INTKTBody extends INTKTEnrichedCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1,trim=true, paddingChar=' ')
	String record_type;
	
	@DataField(pos=2, length=16,trim=true, paddingChar=' ')
	String calling_number;
	
	@DataField(pos=18, length=8,trim=true, paddingChar=' ')
	String call_start_date;
	
	@DataField(pos=26, length=6,trim=true, paddingChar=' ')
	String call_start_time;
	
	@DataField(pos=32, length=6,trim=true, align="R", paddingChar='0')
	String use_time;
	
	@DataField(pos=38, length=1,trim=true, paddingChar=' ')
	String orig_term_ind;
	
	@DataField(pos=39, length=1,trim=true, paddingChar=' ')
	String service_ind;
	
	@DataField(pos=40, length=5,trim=true, paddingChar=' ')
	String call_code;
	
	@DataField(pos=45, length=5,trim=true, paddingChar=' ')
	String dis_code;
	
	@DataField(pos=50, length=3,trim=true, paddingChar=' ')
	String nation_code;
	
	@DataField(pos=53, length=5,trim=true, paddingChar=' ')
	String intl_prefix;
	
	@DataField(pos=58, length=16,trim=true, paddingChar=' ')
	String called_number ;
	
	@DataField(pos=74, length=10,trim=true, align="R", paddingChar='0')
	String charge_amount;
	
	@DataField(pos=84, length=10,trim=true, align="R", paddingChar='0')
	String discount_amount;
	
	@DataField(pos=94, length=107,trim=true, paddingChar=' ')
	String filler;
	
	
	boolean sFlag;

	public INTKTBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public INTKTBody(String record_type, String calling_number,String call_start_date,	String call_start_time,	String use_time,	
			String orig_term_ind,	String service_ind,	String call_code, String dis_code,	String nation_code,	String intl_prefix,	
			String called_number ,	String charge_amount, String discount_amount, String filler) {
		super();
		this.record_type = record_type;
		this.calling_number =calling_number;
		this.call_start_date = call_start_date;
		this.call_start_time = call_start_time;
		this.use_time = use_time;
		this.orig_term_ind = orig_term_ind;
		this.service_ind = service_ind;
		this.call_code = call_code;
		this.dis_code = dis_code;
		this.nation_code = nation_code;
		this.intl_prefix = intl_prefix;
		this.called_number = called_number;
		this.charge_amount = charge_amount;
		this.discount_amount = discount_amount;
		this.filler = filler;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public String getCalling_number() {
		return calling_number;
	}

	public void setCalling_number(String calling_number) {
		this.calling_number = calling_number;
	}

	public String getCall_start_date() {
		return call_start_date;
	}

	public void setCall_start_date(String call_start_date) {
		this.call_start_date = call_start_date;
	}

	public String getCall_start_time() {
		return call_start_time;
	}

	public void setCall_start_time(String call_start_time) {
		this.call_start_time = call_start_time;
	}

	public String getUse_time() {
		return use_time;
	}

	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}

	public String getOrig_term_ind() {
		return orig_term_ind;
	}

	public void setOrig_term_ind(String orig_term_ind) {
		this.orig_term_ind = orig_term_ind;
	}

	public String getService_ind() {
		return service_ind;
	}

	public void setService_ind(String service_ind) {
		this.service_ind = service_ind;
	}

	public String getCall_code() {
		return call_code;
	}

	public void setCall_code(String call_code) {
		this.call_code = call_code;
	}

	public String getDis_code() {
		return dis_code;
	}

	public void setDis_code(String dis_code) {
		this.dis_code = dis_code;
	}

	public String getNation_code() {
		return nation_code;
	}

	public void setNation_code(String nation_code) {
		this.nation_code = nation_code;
	}

	public String getIntl_prefix() {
		return intl_prefix;
	}

	public void setIntl_prefix(String intl_prefix) {
		this.intl_prefix = intl_prefix;
	}

	public String getCalled_number() {
		return called_number;
	}

	public void setCalled_number(String called_number) {
		this.called_number = called_number;
	}

	public String getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(String charge_amount) {
		this.charge_amount = charge_amount;
	}

	public String getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public boolean issFlag() {
		return sFlag;
	}

	public void setsFlag(boolean sFlag) {
		this.sFlag = sFlag;
	}

	public Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "INTKTBody [log=" + log + ", record_type=" + record_type + ", calling_number=" + calling_number
				+ ", call_start_date=" + call_start_date + ", call_start_time=" + call_start_time + ", use_time="
				+ use_time + ", orig_term_ind=" + orig_term_ind + ", service_ind=" + service_ind + ", call_code="
				+ call_code + ", dis_code=" + dis_code + ", nation_code=" + nation_code + ", intl_prefix=" + intl_prefix
				+ ", called_number=" + called_number + ", charge_amount=" + charge_amount + ", discount_amount="
				+ discount_amount + ", filler=" + filler + ", sFlag=" + sFlag + "]";
	}

	
}

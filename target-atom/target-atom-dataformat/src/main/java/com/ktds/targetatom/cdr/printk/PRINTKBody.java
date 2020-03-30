package com.ktds.targetatom.cdr.printk;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.printk.PRINTKEnrichedCdr;

@FixedLengthRecord(length=200, header=PRINTKHeader.class, footer=PRINTKTailer.class, crlf="WINDOWS")
public class PRINTKBody  extends PRINTKEnrichedCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1,trim=true)
	String record_type;		// record_type  레코드 구분, "2"
	
	@DataField(pos=2, length=16, trim=true, align="L")
	String calling_number;		// calling_number  발신전화번호
	
	@DataField(pos=18, length=8, trim=true, pattern="yyyyMMdd")
	String call_start_date;		// call_start_date  통화개시일
	
	@DataField(pos=26, length=6, trim=true, pattern="HHmmss")
	String call_start_time;		// call_start_time  통화시작시간
	
	@DataField(pos=32, length=6, trim=true, pattern="HHmmss")
	String use_time;		// use_time  통화시간
	
	@DataField(pos=38, length=1, trim=true, align="L")
	String orig_term_ind;		// orig_term_ind  발착신 구분
	
	@DataField(pos=39, length=1, trim=true, align="L")
	String service_ind;		// service_ind  서비스 구분
	
	@DataField(pos=40, length=5, trim=true, align="L")
	String call_code;		// call_code  통화료코드
	
	@DataField(pos=45, length=5, trim=true, align="L")
	String dis_code;		// dis_code  할인코드
	
	@DataField(pos=50, length=3, trim=true, align="L")
	String nation_code;		// nation_code  국가코드 EDP_CD
	
	@DataField(pos=53, length=5, trim=true, align="L")
	String intl_prefix;		// intl_prefix  접속 prefix
	
	@DataField(pos=58, length=16, trim=true, align="L")
	String called_number;		// called_number  착신전화번호
	
	@DataField(pos=74, length=10, trim=true)
	int charge_amount;		// charge_amount  통화금액
	
	@DataField(pos=84, length=10, trim=true)
	int discount_amount;		// discount_amount  할인금액
	
	@DataField(pos=94, length=107, trim=true, align="L")
	String filler;		// filler
	
	boolean sFlag;

	public PRINTKBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PRINTKBody(String record_type, String calling_number, String call_start_date, String call_start_time, 
			String use_time, String orig_term_ind, String service_ind, String call_code, String dis_code, 
			String nation_code, String intl_prefix, String called_number, int charge_amount, int discount_amount, 
			String filler) {
		super();
		this.record_type = record_type;
		this.calling_number = calling_number;
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
	
	public boolean issFlag() {
		return sFlag;
	}

	public void setsFlag(boolean sFlag) {
		this.sFlag = sFlag;
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

	public long getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(int charge_amount) {
		this.charge_amount = charge_amount;
	}

	public int getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
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
		return "PRINTKBody [log=" + log + ", record_type=" + record_type + ", calling_number=" + calling_number 
				+ ", call_start_date=" + call_start_date + ", sCallStartTime=" + call_start_time + ", use_time=" 
				+ use_time + ", orig_term_ind=" + orig_term_ind + ", service_ind="+ service_ind + ", call_code=" 
				+ call_code + ", dis_code=" + dis_code + ", nation_code=" + nation_code + ", intl_prefix=" 
				+ intl_prefix + ", called_number=" + called_number + ", charge_amount=" + charge_amount 
				+ ", discount_amount=" + discount_amount + ", filler=" + filler + ", sFlag=" + sFlag + "]";
	}

}

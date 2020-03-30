package com.ktds.targetatom.cdr.intcc;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=120, header=INTCCHeader.class, footer=INTCCTailer.class)
public class INTCCBody extends INTCCEnrichedCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1,trim=true,  align="L", paddingChar=' ')
	String record_type;
	
	@DataField(pos=2, length=16,trim=true, align="L", paddingChar=' ')
	String calling_number;
	
	@DataField(pos=18, length=8,trim=true, align="L", paddingChar=' ')
	String call_start_date;
	
	@DataField(pos=26, length=6,trim=true, align="L", paddingChar=' ')
	String call_start_time;
	
	@DataField(pos=32, length=8,trim=true, align="L", paddingChar=' ')
	String call_term_date;
	
	@DataField(pos=40, length=6,trim=true, align="L", paddingChar=' ')
	String call_term_time;
	
	@DataField(pos=46, length=6,trim=true, align="R", paddingChar='0')
	String use_time;
	
	@DataField(pos=52, length=5,trim=true, align="R", paddingChar='0')
	String message_rate;
	
	@DataField(pos=57, length=5,trim=true, align="R", paddingChar='0')
	String charge_per_message;
	
	@DataField(pos=62, length=1,trim=true, align="L", paddingChar=' ')
	String gubun1;
	
	@DataField(pos=63, length=3,trim=true, align="L", paddingChar=' ')
	String nation_code;
	
	@DataField(pos=66, length=5,trim=true, align="L", paddingChar=' ')
	String intl_prefix ;
	
	@DataField(pos=71, length=16,trim=true, align="L", paddingChar=' ')
	String called_number;
	
	@DataField(pos=87, length=7,trim=true, align="R", paddingChar='0')
	String charge_amount;
	
	@DataField(pos=94, length=3,trim=true, align="L", paddingChar=' ')
	String call_type;
	
	@DataField(pos=97, length=8,trim=true, align="L", paddingChar=' ')
	String subscribed_date;
	
	@DataField(pos=105, length=8,trim=true, align="L", paddingChar=' ')
	String terminated_date;
	
	@DataField(pos=113, length=8,trim=true, align="L", paddingChar=' ')
	String filler;
	
	boolean sFlag;

	public INTCCBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public INTCCBody(String record_type, String calling_number,String call_start_date,	String call_start_time,	String call_term_date,	
			String call_term_time,	String use_time,	String message_rate,
			String charge_per_message,	String gubun1,	String nation_code,	String intl_prefix ,	String called_number,	
			String charge_amount,	String call_type,	String subscribed_date,
			String terminated_date,	String filler) {
		super();
		this.record_type = record_type;
		this.calling_number =calling_number;
		this.call_start_date = call_start_date;
		this.call_start_time = call_start_time;
		this.call_term_date = call_term_date;
		this.call_term_time = call_term_time;
		this.use_time = use_time;
		this.message_rate = message_rate;
		this.charge_per_message = charge_per_message;
		this.gubun1 = gubun1;
		this.nation_code = nation_code;
		this.intl_prefix = intl_prefix;
		this.called_number = called_number;
		this.terminated_date = terminated_date;
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

	public String getCall_term_date() {
		return call_term_date;
	}

	public void setCall_term_date(String call_term_date) {
		this.call_term_date = call_term_date;
	}

	public String getCall_term_time() {
		return call_term_time;
	}

	public void setCall_term_time(String call_term_time) {
		this.call_term_time = call_term_time;
	}

	public String getUse_time() {
		return use_time;
	}

	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}

	public String getMessage_rate() {
		return message_rate;
	}

	public void setMessage_rate(String message_rate) {
		this.message_rate = message_rate;
	}

	public String getCharge_per_message() {
		return charge_per_message;
	}

	public void setCharge_per_message(String charge_per_message) {
		this.charge_per_message = charge_per_message;
	}

	public String getGubun1() {
		return gubun1;
	}

	public void setGubun1(String gubun1) {
		this.gubun1 = gubun1;
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

	public String getCall_type() {
		return call_type;
	}

	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}

	public String getSubscribed_date() {
		return subscribed_date;
	}

	public void setSubscribed_date(String subscribed_date) {
		this.subscribed_date = subscribed_date;
	}

	public String getTerminated_date() {
		return terminated_date;
	}

	public void setTerminated_date(String terminated_date) {
		this.terminated_date = terminated_date;
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
		return "INTCCBody [log=" + log + ",  record_type = " + record_type + ",  calling_number = " + calling_number + ",  call_start_date = " + 
				call_start_date + ",  call_start_time = " + call_start_time + ",  call_term_date = " + call_term_date + ",  call_term_time = " +	
				call_term_time + ",  use_time = " +	use_time + ",  message_rate = " + message_rate + ",  charge_per_message = " +
				charge_per_message + ",  gubun1 = " +	gubun1 + ",  nation_code = " +	nation_code + ",  intl_prefix  = " +	
				intl_prefix + ",  called_number = " +	called_number + ",  charge_amount = " +	charge_amount + ",  call_type = " +
				call_type + ",  subscribed_date = " + subscribed_date + ",  terminated_date = " +	terminated_date + ",  filler = " +
				filler + ", sFlag=" + sFlag + super.toString() +"]";
	}



	
}

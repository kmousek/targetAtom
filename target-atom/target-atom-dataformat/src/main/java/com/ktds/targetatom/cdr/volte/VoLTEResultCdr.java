package com.ktds.targetatom.cdr.volte;


import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.BeanCopyUtils;
import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

@FixedLengthRecord(header=VoLTEHeader.class, footer=VoLTETailer.class)
public class VoLTEResultCdr extends ConmonCdr {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, align="L", defaultValue = "1")
	String sRecordType;
	
	@DataField(pos=2, length=2, trim=true, align="L", paddingChar=' ', defaultValue = "12")
	String sSourceFormat;
	
	@DataField(pos=4, length=16,trim=true, align="L", paddingChar=' ')
	String sCallingSvcNo;
	
	@DataField(pos=20, length=16,trim=true, align="L", paddingChar=' ')
	String sCalledNumber;
	
	@DataField(pos=36, length=16,trim=true, align="L", paddingChar=' ')
	String sChargingNumber;
	
	@DataField(pos=52, length=3,trim=true, align="L", paddingChar=' ')
	String wstype;
	
	@DataField(pos=55, length=8,trim=true, align="L", paddingChar=' ')
	String sCallStartDate;
	
	@DataField(pos=63, length=6,trim=true, align="L", paddingChar=' ')
	String sCallStartTime;
	
	@DataField(pos=69, length=8,trim=true, align="L", paddingChar=' ')
	String sCallEndDate;
	
	@DataField(pos=77, length=6,trim=true, align="L", paddingChar=' ')
	String sCallEndTime;
	
	@DataField(pos=83, length=10,trim=true, align="L", paddingChar=' ')
	String service_time_duration;
	
	@DataField(pos=93, length=12,trim=true, align="R", paddingChar=' ')
	String sChargeAmount;
	
	@DataField(pos=105, length=5,trim=true, align="L", paddingChar=' ')
	String sInternationalPrefix;
	
	@DataField(pos=110, length=8,trim=true, align="L", paddingChar=' ')
	String sNationCode;
	
	@DataField(pos=118, length=1, trim=true, align="L", paddingChar=' ')
	String sOriginOperator;
	
	@DataField(pos=119, length=1, trim=true, align="L", paddingChar=' ')
	String sCallKind;
	
	@DataField(pos=120, length=16, trim=true, align="L", paddingChar=' ')
	String imsi;
	
	@DataField(pos=136, length=3,trim=true, align="L", paddingChar=' ')
	String sService_Feature_Type;
	
	@DataField(pos=139, length=15,trim=true, align="L", paddingChar=' ')
	String subscriber_ip_address;
	
	@DataField(pos=154, length=2,trim=true, align="L", paddingChar=' ')
	String sService_ind;
	
	@DataField(pos=156, length=5,trim=true, align="L", paddingChar=' ')
	String sCallCode;

	@DataField(pos=161, length=5,trim=true, align="L", paddingChar=' ')
	String sDisocunt_code;
	//BigDecimal service_prepaid_airtime_charge;

	@DataField(pos=166, length=10,trim=true, align="L", paddingChar=' ')
	String sDiscount_amount;
	
	@DataField(pos=176, length=2,trim=true, align="L", paddingChar=' ')
	String sCallingsaup;

	@DataField(pos=178, length=2,trim=true, align="L", paddingChar=' ')
	String sCalledsaup;

	@DataField(pos=180, length=10, trim=true, align="R", paddingChar=' ')
	String sDirectConnectFee;

	@DataField(pos=190, length=3, trim=true, align="L", paddingChar=' ')
	String sService_type;

	@DataField(pos=193, length=8,trim=true, align="L", paddingChar=' ')
	String terminating_inter_operator_identifier;

	@DataField(pos=201, length=1,trim=true, align="L", paddingChar=' ')
	String hd_call;
	
	@DataField(pos=202, length=3,trim=true, align="L", paddingChar=' ')
	String supplementary_services_code_1;
	
	@DataField(pos=205, length=1,trim=true, align="L", paddingChar=' ')
	String supplementary_services_code_2;

	@DataField(pos=206, length=16, trim=true, align="L", paddingChar=' ')
	String service_start_3gpp_user_location_info;

	@DataField(pos=222, length=3, trim=true, align="L", paddingChar=' ')
	String service_category_id;

	@DataField(pos=225, length=4,trim=true, align="L", paddingChar=' ')
	String application_release_indicator;

	@DataField(pos=229, length=3,trim=true, align="L", paddingChar=' ')
	String service_charging_type;
	
	@DataField(pos=232, length=10,trim=true, align="R", paddingChar=' ')
	String service_prepaid_airtime_charge;

	@DataField(pos=242, length=10,trim=true, align="R", paddingChar=' ')
	String service_prepaid_count_charge;
	
	@DataField(pos=252, length=10,trim=true, align="R", paddingChar=' ')
	String service_prepaid_information_charge;
	
	@DataField(pos=262, length=3, trim=true, align="L", paddingChar=' ')
	String sCallType;

	@DataField(pos=265, length=10, trim=true, align="R", paddingChar=' ')
	String service_contents_count;

	@DataField(pos=275, length=11,trim=true, align="L", paddingChar=' ')
	String routing_number;

	@DataField(pos=286, length=30,trim=true, align="L", paddingChar=' ')
	String service_called_information;
	
	@DataField(pos=316, length=6,trim=true, align="L", paddingChar=' ')
	String sgsn_mcc_mnc;

	@DataField(pos=322, length=5,trim=true, align="L", paddingChar=' ')
	String sMessageRate;
	
	@DataField(pos=327, length=5,trim=true, align="L", paddingChar=' ')
	String sChargePerMessage;
	
	@DataField(pos=332, length=1, trim=true, align="L", paddingChar=' ')
	String sGubun1;

	@DataField(pos=333, length=5, trim=true, align="L", paddingChar=' ')
	String sTotalServiceRate;

	@DataField(pos=338, length=1,trim=true, align="L", paddingChar=' ')
	String sSvcProvider;

	@DataField(pos=339, length=15,trim=true, align="L", paddingChar=' ')
	String calling_asserted_identity;
	
	@DataField(pos=354, length=1,trim=true, align="L", paddingChar=' ')
	String sInNetCallYn;

	@DataField(pos=355, length=3, trim=true, align="L", paddingChar=' ')
	String sCarrierCode;

	@DataField(pos=358, length=1, trim=true, align="L", paddingChar=' ')
	String sNpIndicator;

	@DataField(pos=359, length=20,trim=true, align="L", paddingChar=' ')
	String sReserved;
	
	public VoLTEResultCdr() {
		
	}
	
	public VoLTEResultCdr(VoLTEBody org) {
		BeanCopyUtils.copyProperties(this, org);
		super.setCdrType(CdrType.OUTPUT);
	}

	public String getsRecordType() {
		return sRecordType;
	}

	public void setsRecordType(String sRecordType) {
		this.sRecordType = sRecordType;
	}

	public String getsSourceFormat() {
		return sSourceFormat;
	}

	public void setsSourceFormat(String sSourceFormat) {
		this.sSourceFormat = sSourceFormat;
	}

	public String getsCallingSvcNo() {
		return sCallingSvcNo;
	}

	public void setsCallingSvcNo(String sCallingSvcNo) {
		this.sCallingSvcNo = sCallingSvcNo;
	}

	public String getsCalledNumber() {
		return sCalledNumber;
	}

	public void setsCalledNumber(String sCalledNumber) {
		this.sCalledNumber = sCalledNumber;
	}

	public String getsChargingNumber() {
		return sChargingNumber;
	}

	public void setsChargingNumber(String sChargingNumber) {
		this.sChargingNumber = sChargingNumber;
	}

	public String getWstype() {
		return wstype;
	}

	public void setWstype(String wstype) {
		this.wstype = wstype;
	}

	public String getsCallStartDate() {
		return sCallStartDate;
	}

	public void setsCallStartDate(String sCallStartDate) {
		this.sCallStartDate = sCallStartDate;
	}

	public String getsCallStartTime() {
		return sCallStartTime;
	}

	public void setsCallStartTime(String sCallStartTime) {
		this.sCallStartTime = sCallStartTime;
	}

	public String getsCallEndDate() {
		return sCallEndDate;
	}

	public void setsCallEndDate(String sCallEndDate) {
		this.sCallEndDate = sCallEndDate;
	}

	public String getsCallEndTime() {
		return sCallEndTime;
	}

	public void setsCallEndTime(String sCallEndTime) {
		this.sCallEndTime = sCallEndTime;
	}

	public String getService_time_duration() {
		return service_time_duration;
	}

	public void setService_time_duration(String service_time_duration) {
		this.service_time_duration = service_time_duration;
	}

	public String getsChargeAmount() {
		return sChargeAmount;
	}

	public void setsChargeAmount(String sChargeAmount) {
		this.sChargeAmount = sChargeAmount;
	}

	public String getsInternationalPrefix() {
		return sInternationalPrefix;
	}

	public void setsInternationalPrefix(String sInternationalPrefix) {
		this.sInternationalPrefix = sInternationalPrefix;
	}

	public String getsNationCode() {
		return sNationCode;
	}

	public void setsNationCode(String sNationCode) {
		this.sNationCode = sNationCode;
	}

	public String getsOriginOperator() {
		return sOriginOperator;
	}

	public void setsOriginOperator(String sOriginOperator) {
		this.sOriginOperator = sOriginOperator;
	}

	public String getsCallKind() {
		return sCallKind;
	}

	public void setsCallKind(String sCallKind) {
		this.sCallKind = sCallKind;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getsService_Feature_Type() {
		return sService_Feature_Type;
	}

	public void setsService_Feature_Type(String sService_Feature_Type) {
		this.sService_Feature_Type = sService_Feature_Type;
	}

	public String getSubscriber_ip_address() {
		return subscriber_ip_address;
	}

	public void setSubscriber_ip_address(String subscriber_ip_address) {
		this.subscriber_ip_address = subscriber_ip_address;
	}

	public String getsService_ind() {
		return sService_ind;
	}

	public void setsService_ind(String sService_ind) {
		this.sService_ind = sService_ind;
	}

	public String getsCallCode() {
		return sCallCode;
	}

	public void setsCallCode(String sCallCode) {
		this.sCallCode = sCallCode;
	}

	public String getsDisocunt_code() {
		return sDisocunt_code;
	}

	public void setsDisocunt_code(String sDisocunt_code) {
		this.sDisocunt_code = sDisocunt_code;
	}

	public String getsDiscount_amount() {
		return sDiscount_amount;
	}

	public void setsDiscount_amount(String sDiscount_amount) {
		this.sDiscount_amount = sDiscount_amount;
	}

	public String getsCallingsaup() {
		return sCallingsaup;
	}

	public void setsCallingsaup(String sCallingsaup) {
		this.sCallingsaup = sCallingsaup;
	}

	public String getsCalledsaup() {
		return sCalledsaup;
	}

	public void setsCalledsaup(String sCalledsaup) {
		this.sCalledsaup = sCalledsaup;
	}

	public String getsDirectConnectFee() {
		return sDirectConnectFee;
	}

	public void setsDirectConnectFee(String sDirectConnectFee) {
		this.sDirectConnectFee = sDirectConnectFee;
	}

	public String getsService_type() {
		return sService_type;
	}

	public void setsService_type(String sService_type) {
		this.sService_type = sService_type;
	}

	public String getTerminating_inter_operator_identifier() {
		return terminating_inter_operator_identifier;
	}

	public void setTerminating_inter_operator_identifier(String terminating_inter_operator_identifier) {
		this.terminating_inter_operator_identifier = terminating_inter_operator_identifier;
	}

	public String getHd_call() {
		return hd_call;
	}

	public void setHd_call(String hd_call) {
		this.hd_call = hd_call;
	}

	public String getSupplementary_services_code_1() {
		return supplementary_services_code_1;
	}

	public void setSupplementary_services_code_1(String supplementary_services_code_1) {
		this.supplementary_services_code_1 = supplementary_services_code_1;
	}

	public String getSupplementary_services_code_2() {
		return supplementary_services_code_2;
	}

	public void setSupplementary_services_code_2(String supplementary_services_code_2) {
		this.supplementary_services_code_2 = supplementary_services_code_2;
	}

	public String getService_start_3gpp_user_location_info() {
		return service_start_3gpp_user_location_info;
	}

	public void setService_start_3gpp_user_location_info(String service_start_3gpp_user_location_info) {
		this.service_start_3gpp_user_location_info = service_start_3gpp_user_location_info;
	}

	public String getService_category_id() {
		return service_category_id;
	}

	public void setService_category_id(String service_category_id) {
		this.service_category_id = service_category_id;
	}

	public String getApplication_release_indicator() {
		return application_release_indicator;
	}

	public void setApplication_release_indicator(String application_release_indicator) {
		this.application_release_indicator = application_release_indicator;
	}

	public String getService_charging_type() {
		return service_charging_type;
	}

	public void setService_charging_type(String service_charging_type) {
		this.service_charging_type = service_charging_type;
	}

	public String getService_prepaid_airtime_charge() {
		return service_prepaid_airtime_charge;
	}

	public void setService_prepaid_airtime_charge(String service_prepaid_airtime_charge) {
		this.service_prepaid_airtime_charge = service_prepaid_airtime_charge;
	}

	public String getService_prepaid_count_charge() {
		return service_prepaid_count_charge;
	}

	public void setService_prepaid_count_charge(String service_prepaid_count_charge) {
		this.service_prepaid_count_charge = service_prepaid_count_charge;
	}

	public String getService_prepaid_information_charge() {
		return service_prepaid_information_charge;
	}

	public void setService_prepaid_information_charge(String service_prepaid_information_charge) {
		this.service_prepaid_information_charge = service_prepaid_information_charge;
	}

	public String getsCallType() {
		return sCallType;
	}

	public void setsCallType(String sCallType) {
		this.sCallType = sCallType;
	}

	public String getService_contents_count() {
		return service_contents_count;
	}

	public void setService_contents_count(String service_contents_count) {
		this.service_contents_count = service_contents_count;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public String getService_called_information() {
		return service_called_information;
	}

	public void setService_called_information(String service_called_information) {
		this.service_called_information = service_called_information;
	}

	public String getSgsn_mcc_mnc() {
		return sgsn_mcc_mnc;
	}

	public void setSgsn_mcc_mnc(String sgsn_mcc_mnc) {
		this.sgsn_mcc_mnc = sgsn_mcc_mnc;
	}

	public String getsMessageRate() {
		return sMessageRate;
	}

	public void setsMessageRate(String sMessageRate) {
		this.sMessageRate = sMessageRate;
	}

	public String getsChargePerMessage() {
		return sChargePerMessage;
	}

	public void setsChargePerMessage(String sChargePerMessage) {
		this.sChargePerMessage = sChargePerMessage;
	}

	public String getsGubun1() {
		return sGubun1;
	}

	public void setsGubun1(String sGubun1) {
		this.sGubun1 = sGubun1;
	}

	public String getsTotalServiceRate() {
		return sTotalServiceRate;
	}

	public void setsTotalServiceRate(String sTotalServiceRate) {
		this.sTotalServiceRate = sTotalServiceRate;
	}

	public String getsSvcProvider() {
		return sSvcProvider;
	}

	public void setsSvcProvider(String sSvcProvider) {
		this.sSvcProvider = sSvcProvider;
	}

	public String getCalling_asserted_identity() {
		return calling_asserted_identity;
	}

	public void setCalling_asserted_identity(String calling_asserted_identity) {
		this.calling_asserted_identity = calling_asserted_identity;
	}

	public String getsInNetCallYn() {
		return sInNetCallYn;
	}

	public void setsInNetCallYn(String sInNetCallYn) {
		this.sInNetCallYn = sInNetCallYn;
	}

	public String getsCarrierCode() {
		return sCarrierCode;
	}

	public void setsCarrierCode(String sCarrierCode) {
		this.sCarrierCode = sCarrierCode;
	}

	public String getsNpIndicator() {
		return sNpIndicator;
	}

	public void setsNpIndicator(String sNpIndicator) {
		this.sNpIndicator = sNpIndicator;
	}

	public String getsReserved() {
		return sReserved;
	}

	public void setsReserved(String sReserved) {
		this.sReserved = sReserved;
	}

	public Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "VoLTEResultCdr [log=" + log + ", sRecordType=" + sRecordType + ", sSourceFormat=" + sSourceFormat
				+ ", sCallingSvcNo=" + sCallingSvcNo + ", sCalledNumber=" + sCalledNumber + ", sChargingNumber="
				+ sChargingNumber + ", wstype=" + wstype + ", sCallStartDate=" + sCallStartDate + ", sCallStartTime="
				+ sCallStartTime + ", sCallEndDate=" + sCallEndDate + ", sCallEndTime=" + sCallEndTime
				+ ", service_time_duration=" + service_time_duration + ", sChargeAmount=" + sChargeAmount
				+ ", sInternationalPrefix=" + sInternationalPrefix + ", sNationCode=" + sNationCode
				+ ", sOriginOperator=" + sOriginOperator + ", sCallKind=" + sCallKind + ", imsi=" + imsi
				+ ", sService_Feature_Type=" + sService_Feature_Type + ", subscriber_ip_address="
				+ subscriber_ip_address + ", sService_ind=" + sService_ind + ", sCallCode=" + sCallCode
				+ ", sDisocunt_code=" + sDisocunt_code + ", sDiscount_amount=" + sDiscount_amount + ", sCallingsaup="
				+ sCallingsaup + ", sCalledsaup=" + sCalledsaup + ", sDirectConnectFee=" + sDirectConnectFee
				+ ", sService_type=" + sService_type + ", terminating_inter_operator_identifier="
				+ terminating_inter_operator_identifier + ", hd_call=" + hd_call + ", supplementary_services_code_1="
				+ supplementary_services_code_1 + ", supplementary_services_code_2=" + supplementary_services_code_2
				+ ", service_start_3gpp_user_location_info=" + service_start_3gpp_user_location_info
				+ ", service_category_id=" + service_category_id + ", application_release_indicator="
				+ application_release_indicator + ", service_charging_type=" + service_charging_type
				+ ", service_prepaid_airtime_charge=" + service_prepaid_airtime_charge
				+ ", service_prepaid_count_charge=" + service_prepaid_count_charge
				+ ", service_prepaid_information_charge=" + service_prepaid_information_charge + ", sCallType="
				+ sCallType + ", service_contents_count=" + service_contents_count + ", routing_number="
				+ routing_number + ", service_called_information=" + service_called_information + ", sgsn_mcc_mnc="
				+ sgsn_mcc_mnc + ", sMessageRate=" + sMessageRate + ", sChargePerMessage=" + sChargePerMessage
				+ ", sGubun1=" + sGubun1 + ", sTotalServiceRate=" + sTotalServiceRate + ", sSvcProvider=" + sSvcProvider
				+ ", calling_asserted_identity=" + calling_asserted_identity + ", sInNetCallYn=" + sInNetCallYn
				+ ", sCarrierCode=" + sCarrierCode + ", sNpIndicator=" + sNpIndicator + ", sReserved=" + sReserved
				+ "]";
	}
	
	
}

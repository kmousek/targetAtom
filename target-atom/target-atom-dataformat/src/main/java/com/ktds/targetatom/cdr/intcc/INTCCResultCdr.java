package com.ktds.targetatom.cdr.intcc;


import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.BeanCopyUtils;
import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

@FixedLengthRecord(header=INTCCHeader.class, footer=INTCCTailer.class) 
public class INTCCResultCdr extends ConmonCdr {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, align="L", defaultValue = "1")
	String sRecordType;
	
	@DataField(pos=2, length=2, trim=true, align="L", paddingChar=' ', defaultValue = "03")
	String source_format;
	
	@DataField(pos=4, length=16,trim=true, align="L", paddingChar=' ')
	String sCallingNumber;
	
	@DataField(pos=20, length=16,trim=true, align="L", paddingChar=' ')
	String called_number;
	
	@DataField(pos=36, length=16,trim=true, align="L", paddingChar=' ')
	String sChargingNumber;
	
	@DataField(pos=52, length=3,trim=true, align="L", paddingChar=' ')
	String swin_service_type;
	
	@DataField(pos=55, length=8,trim=true, align="L", paddingChar=' ')
	String call_start_date;
	
	@DataField(pos=63, length=6,trim=true, align="L", paddingChar=' ')
	String call_start_time;
	
	@DataField(pos=69, length=8,trim=true, align="L", paddingChar=' ')
	String call_term_date;
	
	@DataField(pos=77, length=6,trim=true, align="L", paddingChar=' ')
	String call_term_time;
	
	@DataField(pos=83, length=10,trim=true, align="L", paddingChar=' ')
	String use_time;
	
	@DataField(pos=93, length=12,trim=true, align="R", paddingChar=' ')
	String charge_amount;
	
	@DataField(pos=105, length=5,trim=true, align="L", paddingChar=' ')
	String intl_prefix;
	
	@DataField(pos=110, length=8,trim=true, align="L", paddingChar=' ')
	String nation_code;
	
	@DataField(pos=118, length=1, trim=true, align="L", paddingChar=' ')
	String sOriginOperator;
	
	@DataField(pos=119, length=1, trim=true, align="L", paddingChar=' ')
	String sCallKind;
	
	@DataField(pos=120, length=16, trim=true, align="L", paddingChar=' ')
	String sIMSI;
	
	@DataField(pos=136, length=3,trim=true, align="L", paddingChar=' ')
	String sService_Feature_Type;
	
	@DataField(pos=139, length=15,trim=true, align="L", paddingChar=' ')
	String sSGSNIPaddress;
	
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
	String sTerminating_InterOperaor_ID;

	@DataField(pos=201, length=1,trim=true, align="L", paddingChar=' ')
	String sHDcallindicator;
	
	@DataField(pos=202, length=4,trim=true, align="L", paddingChar=' ')
	String sSupplimentaryservicecode;

	@DataField(pos=206, length=16, trim=true, align="L", paddingChar=' ')
	String sServiceStart3GPPUserLocationInfo;

	@DataField(pos=222, length=3, trim=true, align="L", paddingChar=' ')
	String sServiceCategoryID_VoLTE;

	@DataField(pos=225, length=4,trim=true, align="L", paddingChar=' ')
	String sApplicationReleaseIndicator;

	@DataField(pos=229, length=3,trim=true, align="L", paddingChar=' ')
	String sServiceChargingType;
	
	@DataField(pos=232, length=10,trim=true, align="R", paddingChar=' ')
	String sServicePrepaidAirtimeCharge;

	@DataField(pos=242, length=10,trim=true, align="R", paddingChar=' ')
	String sServicePrepaidCountCharge;
	
	@DataField(pos=252, length=10,trim=true, align="R", paddingChar=' ')
	String sServicePrepaidInformationCharge;
	
	@DataField(pos=262, length=3, trim=true, align="L", paddingChar=' ')
	String sCallType;

	@DataField(pos=265, length=10, trim=true, align="R", paddingChar=' ')
	String sServiceContentsCount;

	@DataField(pos=275, length=11,trim=true, align="L", paddingChar=' ')
	String sRoutingNumber;

	@DataField(pos=286, length=30,trim=true, align="L", paddingChar=' ')
	String sServiceCalledInformation;
	
	@DataField(pos=316, length=6,trim=true, align="L", paddingChar=' ')
	String sSgsnMccMnc;

	@DataField(pos=322, length=5,trim=true, align="L", paddingChar=' ')
	String message_rate;
	
	@DataField(pos=327, length=5,trim=true, align="L", paddingChar=' ')
	String charge_per_message;
	
	@DataField(pos=332, length=1, trim=true, align="L", paddingChar=' ')
	String gubun1;

	@DataField(pos=333, length=5, trim=true, align="L", paddingChar=' ')
	String sTotalServiceRate;

	@DataField(pos=338, length=1,trim=true, align="L", paddingChar=' ')
	String sSvcProvider;

	@DataField(pos=339, length=15,trim=true, align="L", paddingChar=' ')
	String sCallingAssertedIdentity;
	
	@DataField(pos=354, length=1,trim=true, align="L", paddingChar=' ')
	String sInNetCallYn;

	@DataField(pos=355, length=3, trim=true, align="L", paddingChar=' ')
	String sCarrierCode;

	@DataField(pos=358, length=1, trim=true, align="L", paddingChar=' ')
	String sNpIndicator;

	@DataField(pos=359, length=20,trim=true, align="L", paddingChar=' ')
	String sReserved;

	public INTCCResultCdr() {
		
	}
	
	public INTCCResultCdr(INTCCBody org) {
		BeanCopyUtils.copyProperties(this, org);
		super.setCdrType(CdrType.OUTPUT);
	}

	public String getsRecordType() {
		return sRecordType;
	}

	public void setsRecordType(String sRecordType) {
		this.sRecordType = sRecordType;
	}

	public String getSource_format() {
		return source_format;
	}

	public void setSource_format(String source_format) {
		this.source_format = source_format;
	}

	public String getsCallingNumber() {
		return sCallingNumber;
	}

	public void setsCallingNumber(String sCallingNumber) {
		this.sCallingNumber = sCallingNumber;
	}

	public String getCalled_number() {
		return called_number;
	}

	public void setCalled_number(String called_number) {
		this.called_number = called_number;
	}

	public String getsChargingNumber() {
		return sChargingNumber;
	}

	public void setsChargingNumber(String sChargingNumber) {
		this.sChargingNumber = sChargingNumber;
	}

	public String getSwin_service_type() {
		return swin_service_type;
	}

	public void setSwin_service_type(String swin_service_type) {
		this.swin_service_type = swin_service_type;
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

	public String getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(String charge_amount) {
		this.charge_amount = charge_amount;
	}

	public String getIntl_prefix() {
		return intl_prefix;
	}

	public void setIntl_prefix(String intl_prefix) {
		this.intl_prefix = intl_prefix;
	}

	public String getNation_code() {
		return nation_code;
	}

	public void setNation_code(String nation_code) {
		this.nation_code = nation_code;
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

	public String getsIMSI() {
		return sIMSI;
	}

	public void setsIMSI(String sIMSI) {
		this.sIMSI = sIMSI;
	}

	public String getsService_Feature_Type() {
		return sService_Feature_Type;
	}

	public void setsService_Feature_Type(String sService_Feature_Type) {
		this.sService_Feature_Type = sService_Feature_Type;
	}

	public String getsSGSNIPaddress() {
		return sSGSNIPaddress;
	}

	public void setsSGSNIPaddress(String sSGSNIPaddress) {
		this.sSGSNIPaddress = sSGSNIPaddress;
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

	public String getsTerminating_InterOperaor_ID() {
		return sTerminating_InterOperaor_ID;
	}

	public void setsTerminating_InterOperaor_ID(String sTerminating_InterOperaor_ID) {
		this.sTerminating_InterOperaor_ID = sTerminating_InterOperaor_ID;
	}

	public String getsHDcallindicator() {
		return sHDcallindicator;
	}

	public void setsHDcallindicator(String sHDcallindicator) {
		this.sHDcallindicator = sHDcallindicator;
	}

	public String getsSupplimentaryservicecode() {
		return sSupplimentaryservicecode;
	}

	public void setsSupplimentaryservicecode(String sSupplimentaryservicecode) {
		this.sSupplimentaryservicecode = sSupplimentaryservicecode;
	}

	public String getsServiceStart3GPPUserLocationInfo() {
		return sServiceStart3GPPUserLocationInfo;
	}

	public void setsServiceStart3GPPUserLocationInfo(String sServiceStart3GPPUserLocationInfo) {
		this.sServiceStart3GPPUserLocationInfo = sServiceStart3GPPUserLocationInfo;
	}

	public String getsServiceCategoryID_VoLTE() {
		return sServiceCategoryID_VoLTE;
	}

	public void setsServiceCategoryID_VoLTE(String sServiceCategoryID_VoLTE) {
		this.sServiceCategoryID_VoLTE = sServiceCategoryID_VoLTE;
	}

	public String getsApplicationReleaseIndicator() {
		return sApplicationReleaseIndicator;
	}

	public void setsApplicationReleaseIndicator(String sApplicationReleaseIndicator) {
		this.sApplicationReleaseIndicator = sApplicationReleaseIndicator;
	}

	public String getsServiceChargingType() {
		return sServiceChargingType;
	}

	public void setsServiceChargingType(String sServiceChargingType) {
		this.sServiceChargingType = sServiceChargingType;
	}

	public String getsServicePrepaidAirtimeCharge() {
		return sServicePrepaidAirtimeCharge;
	}

	public void setsServicePrepaidAirtimeCharge(String sServicePrepaidAirtimeCharge) {
		this.sServicePrepaidAirtimeCharge = sServicePrepaidAirtimeCharge;
	}

	public String getsServicePrepaidCountCharge() {
		return sServicePrepaidCountCharge;
	}

	public void setsServicePrepaidCountCharge(String sServicePrepaidCountCharge) {
		this.sServicePrepaidCountCharge = sServicePrepaidCountCharge;
	}

	public String getsServicePrepaidInformationCharge() {
		return sServicePrepaidInformationCharge;
	}

	public void setsServicePrepaidInformationCharge(String sServicePrepaidInformationCharge) {
		this.sServicePrepaidInformationCharge = sServicePrepaidInformationCharge;
	}

	public String getsCallType() {
		return sCallType;
	}

	public void setsCallType(String sCallType) {
		this.sCallType = sCallType;
	}

	public String getsServiceContentsCount() {
		return sServiceContentsCount;
	}

	public void setsServiceContentsCount(String sServiceContentsCount) {
		this.sServiceContentsCount = sServiceContentsCount;
	}

	public String getsRoutingNumber() {
		return sRoutingNumber;
	}

	public void setsRoutingNumber(String sRoutingNumber) {
		this.sRoutingNumber = sRoutingNumber;
	}

	public String getsServiceCalledInformation() {
		return sServiceCalledInformation;
	}

	public void setsServiceCalledInformation(String sServiceCalledInformation) {
		this.sServiceCalledInformation = sServiceCalledInformation;
	}

	public String getsSgsnMccMnc() {
		return sSgsnMccMnc;
	}

	public void setsSgsnMccMnc(String sSgsnMccMnc) {
		this.sSgsnMccMnc = sSgsnMccMnc;
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

	public String getsCallingAssertedIdentity() {
		return sCallingAssertedIdentity;
	}

	public void setsCallingAssertedIdentity(String sCallingAssertedIdentity) {
		this.sCallingAssertedIdentity = sCallingAssertedIdentity;
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
		return "INTCCResultCdr [log=" + log + ", sRecordType=" + sRecordType + ", source_format=" + source_format
				+ ", sCallingNumber=" + sCallingNumber + ", called_number=" + called_number + ", sChargingNumber="
				+ sChargingNumber + ", swin_service_type=" + swin_service_type + ", call_start_date=" + call_start_date
				+ ", call_start_time=" + call_start_time + ", call_term_date=" + call_term_date + ", call_term_time="
				+ call_term_time + ", use_time=" + use_time + ", charge_amount=" + charge_amount + ", intl_prefix="
				+ intl_prefix + ", nation_code=" + nation_code + ", sOriginOperator=" + sOriginOperator + ", sCallKind="
				+ sCallKind + ", sIMSI=" + sIMSI + ", sService_Feature_Type=" + sService_Feature_Type
				+ ", sSGSNIPaddress=" + sSGSNIPaddress + ", sService_ind=" + sService_ind + ", sCallCode=" + sCallCode
				+ ", sDisocunt_code=" + sDisocunt_code + ", sDiscount_amount=" + sDiscount_amount + ", sCallingsaup="
				+ sCallingsaup + ", sCalledsaup=" + sCalledsaup + ", sDirectConnectFee=" + sDirectConnectFee
				+ ", sService_type=" + sService_type + ", sTerminating_InterOperaor_ID=" + sTerminating_InterOperaor_ID
				+ ", sHDcallindicator=" + sHDcallindicator + ", sSupplimentaryservicecode=" + sSupplimentaryservicecode
				+ ", sServiceStart3GPPUserLocationInfo=" + sServiceStart3GPPUserLocationInfo
				+ ", sServiceCategoryID_VoLTE=" + sServiceCategoryID_VoLTE + ", sApplicationReleaseIndicator="
				+ sApplicationReleaseIndicator + ", sServiceChargingType=" + sServiceChargingType
				+ ", sServicePrepaidAirtimeCharge=" + sServicePrepaidAirtimeCharge + ", sServicePrepaidCountCharge="
				+ sServicePrepaidCountCharge + ", sServicePrepaidInformationCharge=" + sServicePrepaidInformationCharge
				+ ", sCallType=" + sCallType + ", sServiceContentsCount=" + sServiceContentsCount + ", sRoutingNumber="
				+ sRoutingNumber + ", sServiceCalledInformation=" + sServiceCalledInformation + ", sSgsnMccMnc="
				+ sSgsnMccMnc + ", message_rate=" + message_rate + ", charge_per_message=" + charge_per_message
				+ ", gubun1=" + gubun1 + ", sTotalServiceRate=" + sTotalServiceRate + ", sSvcProvider=" + sSvcProvider
				+ ", sCallingAssertedIdentity=" + sCallingAssertedIdentity + ", sInNetCallYn=" + sInNetCallYn
				+ ", sCarrierCode=" + sCarrierCode + ", sNpIndicator=" + sNpIndicator + ", sReserved=" + sReserved
				+ "]";
	}

	
}

package com.ktds.targetatom.cdr.volte;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=800, header=VoLTEHeader.class, footer=VoLTETailer.class)
public class VoLTEBody extends VoLTEEnrichedCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=4,trim=true, align="L")
	String cdr_version;
	
	@DataField(pos=5, length=1,trim=true, align="L")
	String cdr_type;
	
	@DataField(pos=6, length=11,trim=true, align="L")
	String charging_number;
	
	@DataField(pos=17, length=15,trim=true, align="L")
	String imsi;
	
	@DataField(pos=32, length=15,trim=true, align="L")
	String subscriber_ip_address;
	
	@DataField(pos=47, length=3,trim=true, align="L")
	String wstype;
	
	@DataField(pos=50, length=4,trim=true, align="L")
	String win_service_name;
	
	@DataField(pos=54, length=100,trim=true, align="L")
	String apn;
	
	@DataField(pos=154, length=1,trim=true, align="L")
	String accounting_status_type;
	
	@DataField(pos=155, length=15,trim=true, align="L")
	String called_asserted_identity;
	
	@DataField(pos=170, length=15,trim=true, align="L")
	String calling_asserted_identity;
	
	@DataField(pos=185, length=1,trim=true, align="L")
	String gpp_pdp_type;
	
	@DataField(pos=186, length=4,trim=true, align="L")
	String gpp_nasapi;
	
	@DataField(pos=190, length=4,trim=true, align="L")
	String gpp_charging_characteristic;
	
	@DataField(pos=194, length=6,trim=true, align="L")
	String sgsn_mcc_mnc;
	
	@DataField(pos=200, length=31,trim=true, align="L")
	String gpp_gprs_negotiated_qos_profile;
	
	@DataField(pos=231, length=10,trim=true, align="L")
	String gpp_charging_id;
	
	@DataField(pos=241, length=80,trim=true, align="L")
	String ims_charging_identifier;
	
	@DataField(pos=321, length=8,trim=true, align="L")
	String originating_inter_operator_identifier;
	
	@DataField(pos=329, length=8,trim=true, align="L", paddingChar=' ')
	String terminating_inter_operator_identifier;
	
	@DataField(pos=337, length=16,trim=true, align="L")
	String service_start_3gpp_user_location_info;
	
	@DataField(pos=353, length=16,trim=true, align="L")
	String service_terminating_3gpp_user_location_info;
	
	@DataField(pos=369, length=2,trim=true, align="L")
	String nas_release_indicator;
	
	@DataField(pos=371, length=4, trim=true, align="L", paddingChar=' ', defaultValue="0")
	String application_release_indicator;
	
	@DataField(pos=375, length=28,trim=true, align="L")
	String number_portablility_routing_information;
	
	@DataField(pos=403, length=40,trim=true, align="L")
	String subscriber_ipv6_address;
	
	@DataField(pos=443, length=3,trim=true, align="L")
	String service_category_id;
	
	@DataField(pos=446, length=3,trim=true, align="L", paddingChar='0')
	String service_charging_type;
	
	@DataField(pos=449, length=3,trim=true, align="L")
	String service_charging_rate;
	
	@DataField(pos=452, length=1,trim=true, align="L")
	String service_call_type;
	
	@DataField(pos=453, length=30,trim=true, align="L")
	String service_calling_information;
	
	@DataField(pos=483, length=30, trim=true, align="L", paddingChar=' ')
	String service_called_information;
	
	@DataField(pos=513, length=8,trim=true, align="L")
	String service_start_date;
	
	@DataField(pos=521, length=6,trim=true, align="L")
	String service_start_time;
	
	@DataField(pos=527, length=8,trim=true, align="L")
	String service_end_date;
	
	@DataField(pos=535, length=6,trim=true, align="L")
	String service_end_time;
	
	@DataField(pos=541, length=16,trim=true, align="L")
	String service_upload_data_octects;
	
	@DataField(pos=557, length=16,trim=true, align="L")
	String service_download_data_octects;
	
	@DataField(pos=573, length=4,trim=true, align="R", paddingChar='0')
	String service_contents_count;
	
	@DataField(pos=577, length=2,trim=true, align="L")
	String service_contents_type;
	
	@DataField(pos=579, length=8,trim=true, align="L")
	String service_time_duration;
	
	@DataField(pos=587, length=10,trim=true, align="R", paddingChar='0')
	String service_prepaid_airtime_charge;
	//BigDecimal service_prepaid_airtime_charge;
	
	@DataField(pos=597, length=10,trim=true, align="R", paddingChar='0')
	String service_prepaid_packet_charge;
	
	@DataField(pos=607, length=10,trim=true, align="R", paddingChar='0')
	String service_prepaid_count_charge;
	
	@DataField(pos=617, length=10,trim=true, align="R", paddingChar='0')
	String service_prepaid_information_charge;
	
	@DataField(pos=627, length=31, trim=true, align="L")
	String service_3gpp_gprs_negotiated_qos_profile;
	
	@DataField(pos=658, length=3, trim=true, paddingChar=' ')
	String supplementary_services_code_1;
	
	@DataField(pos=661, length=1, trim=true, align="L")
	String supplementary_services_code_2;
	
	@DataField(pos=662, length=1, trim=true, align="L")
	String hd_call;
	
	@DataField(pos=663, length=11, trim=true, align="L")
	String routing_number;
	
	@DataField(pos=674, length=31, trim=true, align="L")
	String sdp_codec_information;
	
	@DataField(pos=705, length=14, trim=true, align="L")
	String request_timestamp;
	
	@DataField(pos=719, length=44, trim=true, align="L")
	String reason_header;
	
	@DataField(pos=763, length=32, trim=true, align="L")
	String origin_host;
	
	@DataField(pos=795, length=5, trim=true, align="L")
	String reserved;
	
	@DataField(pos=800, length=1, trim=true, align="L")
	String record_type;
	
	boolean sFlag;

	public VoLTEBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoLTEBody(String cdr_version, String cdr_type, String charging_number, String imsi,
			String subscriber_ip_address, String wstype, String win_service_name, String apn,
			String accounting_status_type, String called_asserted_identity, String calling_asserted_identity,
			String gpp_pdp_type, String gpp_nasapi, String gpp_charging_characteristic, String sgsn_mcc_mnc,
			String gpp_gprs_negotiated_qos_profile, String gpp_charging_id, String ims_charging_identifier,
			String originating_inter_operator_identifier, String terminating_inter_operator_identifier,
			String service_start_3gpp_user_location_info, String service_terminating_3gpp_user_location_info,
			String nas_release_indicator, String application_release_indicator,
			String number_portablility_routing_information, String subscriber_ipv6_address, String service_category_id,
			String service_charging_type, String service_charging_rate, String service_call_type,
			String service_calling_information, String service_called_information, String service_start_date,
			String service_start_time, String service_end_date, String service_end_time,
			String service_upload_data_octects, String service_download_data_octects, 
			String service_contents_count,
			String service_contents_type, String service_time_duration, 
			String service_prepaid_airtime_charge,
			String service_prepaid_packet_charge, 
			String service_prepaid_information_charge,
			String service_prepaid_count_charge,
			String service_3gpp_gprs_negotiated_qos_profile,
			String supplementary_services_code_1, String supplementary_services_code_2, String hd_call,
			String routing_number, String sdp_codec_information, String request_timestamp, String reason_header,
			String origin_host, String reserved, String record_type) {
		super();
		this.cdr_version = cdr_version;
		this.cdr_type = cdr_type;
		this.charging_number = charging_number;
		this.imsi = imsi;
		this.subscriber_ip_address = subscriber_ip_address;
		this.wstype = wstype;
		this.win_service_name = win_service_name;
		this.apn = apn;
		this.accounting_status_type = accounting_status_type;
		this.called_asserted_identity = called_asserted_identity;
		this.calling_asserted_identity = calling_asserted_identity;
		this.gpp_pdp_type = gpp_pdp_type;
		this.gpp_nasapi = gpp_nasapi;
		this.gpp_charging_characteristic = gpp_charging_characteristic;
		this.sgsn_mcc_mnc = sgsn_mcc_mnc;
		this.gpp_gprs_negotiated_qos_profile = gpp_gprs_negotiated_qos_profile;
		this.gpp_charging_id = gpp_charging_id;
		this.ims_charging_identifier = ims_charging_identifier;
		this.originating_inter_operator_identifier = originating_inter_operator_identifier;
		this.terminating_inter_operator_identifier = terminating_inter_operator_identifier;
		this.service_start_3gpp_user_location_info = service_start_3gpp_user_location_info;
		this.service_terminating_3gpp_user_location_info = service_terminating_3gpp_user_location_info;
		this.nas_release_indicator = nas_release_indicator;
		this.application_release_indicator = application_release_indicator;
		this.number_portablility_routing_information = number_portablility_routing_information;
		this.subscriber_ipv6_address = subscriber_ipv6_address;
		this.service_category_id = service_category_id;
		this.service_charging_type = service_charging_type;
		this.service_charging_rate = service_charging_rate;
		this.service_call_type = service_call_type;
		this.service_calling_information = service_calling_information;
		this.service_called_information = service_called_information;
		this.service_start_date = service_start_date;
		this.service_start_time = service_start_time;
		this.service_end_date = service_end_date;
		this.service_end_time = service_end_time;
		this.service_upload_data_octects = service_upload_data_octects;
		this.service_download_data_octects = service_download_data_octects;
		this.service_contents_count = service_contents_count;
		this.service_contents_type = service_contents_type;
		this.service_time_duration = service_time_duration;
		this.service_prepaid_airtime_charge = service_prepaid_airtime_charge;
		this.service_prepaid_packet_charge = service_prepaid_packet_charge;
		this.service_prepaid_count_charge = service_prepaid_count_charge;
		this.service_prepaid_information_charge = service_prepaid_information_charge;
		this.service_3gpp_gprs_negotiated_qos_profile = service_3gpp_gprs_negotiated_qos_profile;
		this.supplementary_services_code_1 = supplementary_services_code_1;
		this.supplementary_services_code_2 = supplementary_services_code_2;
		this.hd_call = hd_call;
		this.routing_number = routing_number;
		this.sdp_codec_information = sdp_codec_information;
		this.request_timestamp = request_timestamp;
		this.reason_header = reason_header;
		this.origin_host = origin_host;
		this.reserved = reserved;
		this.record_type = record_type;
	}

	public String getCdr_version() {
		return cdr_version;
	}

	public void setCdr_version(String cdr_version) {
		this.cdr_version = cdr_version;
	}

	public String getCdr_type() {
		return cdr_type;
	}

	public void setCdr_type(String cdr_type) {
		this.cdr_type = cdr_type;
	}

	public String getCharging_number() {
		return charging_number;
	}

	public void setCharging_number(String charging_number) {
		this.charging_number = charging_number;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSubscriber_ip_address() {
		return subscriber_ip_address;
	}

	public void setSubscriber_ip_address(String subscriber_ip_address) {
		this.subscriber_ip_address = subscriber_ip_address;
	}

	public String getWstype() {
		return wstype;
	}

	public void setWstype(String wstype) {
		this.wstype = wstype;
	}

	public String getWin_service_name() {
		return win_service_name;
	}

	public void setWin_service_name(String win_service_name) {
		this.win_service_name = win_service_name;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public String getAccounting_status_type() {
		return accounting_status_type;
	}

	public void setAccounting_status_type(String accounting_status_type) {
		this.accounting_status_type = accounting_status_type;
	}

	public String getCalled_asserted_identity() {
		return called_asserted_identity;
	}

	public void setCalled_asserted_identity(String called_asserted_identity) {
		this.called_asserted_identity = called_asserted_identity;
	}

	public String getCalling_asserted_identity() {
		return calling_asserted_identity;
	}

	public void setCalling_asserted_identity(String calling_asserted_identity) {
		this.calling_asserted_identity = calling_asserted_identity;
	}

	public String getGpp_pdp_type() {
		return gpp_pdp_type;
	}

	public void setGpp_pdp_type(String gpp_pdp_type) {
		this.gpp_pdp_type = gpp_pdp_type;
	}

	public String getGpp_nasapi() {
		return gpp_nasapi;
	}

	public void setGpp_nasapi(String gpp_nasapi) {
		this.gpp_nasapi = gpp_nasapi;
	}

	public String getGpp_charging_characteristic() {
		return gpp_charging_characteristic;
	}

	public void setGpp_charging_characteristic(String gpp_charging_characteristic) {
		this.gpp_charging_characteristic = gpp_charging_characteristic;
	}

	public String getSgsn_mcc_mnc() {
		return sgsn_mcc_mnc;
	}

	public void setSgsn_mcc_mnc(String sgsn_mcc_mnc) {
		this.sgsn_mcc_mnc = sgsn_mcc_mnc;
	}

	public String getGpp_gprs_negotiated_qos_profile() {
		return gpp_gprs_negotiated_qos_profile;
	}

	public void setGpp_gprs_negotiated_qos_profile(String gpp_gprs_negotiated_qos_profile) {
		this.gpp_gprs_negotiated_qos_profile = gpp_gprs_negotiated_qos_profile;
	}

	public String getGpp_charging_id() {
		return gpp_charging_id;
	}

	public void setGpp_charging_id(String gpp_charging_id) {
		this.gpp_charging_id = gpp_charging_id;
	}

	public String getIms_charging_identifier() {
		return ims_charging_identifier;
	}

	public void setIms_charging_identifier(String ims_charging_identifier) {
		this.ims_charging_identifier = ims_charging_identifier;
	}

	public String getOriginating_inter_operator_identifier() {
		return originating_inter_operator_identifier;
	}

	public void setOriginating_inter_operator_identifier(String originating_inter_operator_identifier) {
		this.originating_inter_operator_identifier = originating_inter_operator_identifier;
	}

	public String getTerminating_inter_operator_identifier() {
		return terminating_inter_operator_identifier;
	}

	public void setTerminating_inter_operator_identifier(String terminating_inter_operator_identifier) {
		this.terminating_inter_operator_identifier = terminating_inter_operator_identifier;
	}

	public String getService_start_3gpp_user_location_info() {
		return service_start_3gpp_user_location_info;
	}

	public void setService_start_3gpp_user_location_info(String service_start_3gpp_user_location_info) {
		this.service_start_3gpp_user_location_info = service_start_3gpp_user_location_info;
	}

	public String getService_terminating_3gpp_user_location_info() {
		return service_terminating_3gpp_user_location_info;
	}

	public void setService_terminating_3gpp_user_location_info(String service_terminating_3gpp_user_location_info) {
		this.service_terminating_3gpp_user_location_info = service_terminating_3gpp_user_location_info;
	}

	public String getNas_release_indicator() {
		return nas_release_indicator;
	}

	public void setNas_release_indicator(String nas_release_indicator) {
		this.nas_release_indicator = nas_release_indicator;
	}

	public String getApplication_release_indicator() {
		return application_release_indicator;
	}

	public void setApplication_release_indicator(String application_release_indicator) {
		this.application_release_indicator = application_release_indicator;
	}

	public String getNumber_portablility_routing_information() {
		return number_portablility_routing_information;
	}

	public void setNumber_portablility_routing_information(String number_portablility_routing_information) {
		this.number_portablility_routing_information = number_portablility_routing_information;
	}

	public String getSubscriber_ipv6_address() {
		return subscriber_ipv6_address;
	}

	public void setSubscriber_ipv6_address(String subscriber_ipv6_address) {
		this.subscriber_ipv6_address = subscriber_ipv6_address;
	}

	public String getService_category_id() {
		return service_category_id;
	}

	public void setService_category_id(String service_category_id) {
		this.service_category_id = service_category_id;
	}

	public String getService_charging_type() {
		return service_charging_type;
	}

	public void setService_charging_type(String service_charging_type) {
		this.service_charging_type = service_charging_type;
	}

	public String getService_charging_rate() {
		return service_charging_rate;
	}

	public void setService_charging_rate(String service_charging_rate) {
		this.service_charging_rate = service_charging_rate;
	}

	public String getService_call_type() {
		return service_call_type;
	}

	public void setService_call_type(String service_call_type) {
		this.service_call_type = service_call_type;
	}

	public String getService_calling_information() {
		return service_calling_information;
	}

	public void setService_calling_information(String service_calling_information) {
		this.service_calling_information = service_calling_information;
	}

	public String getService_called_information() {
		return service_called_information;
	}

	public void setService_called_information(String service_called_information) {
		this.service_called_information = service_called_information;
	}

	public String getService_start_date() {
		return service_start_date;
	}

	public void setService_start_date(String service_start_date) {
		this.service_start_date = service_start_date;
	}

	public String getService_start_time() {
		return service_start_time;
	}

	public void setService_start_time(String service_start_time) {
		this.service_start_time = service_start_time;
	}

	public String getService_end_date() {
		return service_end_date;
	}

	public void setService_end_date(String service_end_date) {
		this.service_end_date = service_end_date;
	}

	public String getService_end_time() {
		return service_end_time;
	}

	public void setService_end_time(String service_end_time) {
		this.service_end_time = service_end_time;
	}

	public String getService_upload_data_octects() {
		return service_upload_data_octects;
	}

	public void setService_upload_data_octects(String service_upload_data_octects) {
		this.service_upload_data_octects = service_upload_data_octects;
	}

	public String getService_download_data_octects() {
		return service_download_data_octects;
	}

	public void setService_download_data_octects(String service_download_data_octects) {
		this.service_download_data_octects = service_download_data_octects;
	}

	//public BigDecimal getService_contents_count() {
	public String getService_contents_count() {
		return service_contents_count;
	}

	//public void setService_contents_count(BigDecimal service_contents_count) {
	public void setService_contents_count(String service_contents_count) {
		this.service_contents_count = service_contents_count;
	}

	public String getService_contents_type() {
		return service_contents_type;
	}

	public void setService_contents_type(String service_contents_type) {
		this.service_contents_type = service_contents_type;
	}

	public String getService_time_duration() {
		return service_time_duration;
	}

	public void setService_time_duration(String service_time_duration) {
		this.service_time_duration = service_time_duration;
	}

	//public BigDecimal getService_prepaid_airtime_charge() {
	public String getService_prepaid_airtime_charge() {
		return service_prepaid_airtime_charge;
	}

	public void setService_prepaid_airtime_charge(String service_prepaid_airtime_charge) {
	//public void setService_prepaid_airtime_charge(BigDecimal service_prepaid_airtime_charge) {
		this.service_prepaid_airtime_charge = service_prepaid_airtime_charge;
	}
	
	public String getService_prepaid_packet_charge() {
	//public BigDecimal getService_prepaid_packet_charge() {
		return service_prepaid_packet_charge;
	}

	public void setService_prepaid_packet_charge(String service_prepaid_packet_charge) {
	//public void setService_prepaid_packet_charge(BigDecimal service_prepaid_packet_charge) {
		this.service_prepaid_packet_charge = service_prepaid_packet_charge;
	}
	
	public String getService_prepaid_count_charge() {
	//public BigDecimal getService_prepaid_count_charge() {
		return service_prepaid_count_charge;
	}

	public void setService_prepaid_count_charge(String service_prepaid_count_charge) {
	//public void setService_prepaid_count_charge(BigDecimal service_prepaid_count_charge) {
		this.service_prepaid_count_charge = service_prepaid_count_charge;
	}
	
	public String getService_prepaid_information_charge() {
	//public BigDecimal getService_prepaid_information_charge() {
		return service_prepaid_information_charge;
	}

	public void setService_prepaid_information_charge(String service_prepaid_information_charge) {
	//public void setService_prepaid_information_charge(BigDecimal service_prepaid_information_charge) {
		this.service_prepaid_information_charge = service_prepaid_information_charge;
	}

	public String getService_3gpp_gprs_negotiated_qos_profile() {
		return service_3gpp_gprs_negotiated_qos_profile;
	}

	public void setService_3gpp_gprs_negotiated_qos_profile(String service_3gpp_gprs_negotiated_qos_profile) {
		this.service_3gpp_gprs_negotiated_qos_profile = service_3gpp_gprs_negotiated_qos_profile;
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

	public String getHd_call() {
		return hd_call;
	}

	public void setHd_call(String hd_call) {
		this.hd_call = hd_call;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public String getSdp_codec_information() {
		return sdp_codec_information;
	}

	public void setSdp_codec_information(String sdp_codec_information) {
		this.sdp_codec_information = sdp_codec_information;
	}

	public String getRequest_timestamp() {
		return request_timestamp;
	}

	public void setRequest_timestamp(String request_timestamp) {
		this.request_timestamp = request_timestamp;
	}

	public String getReason_header() {
		return reason_header;
	}

	public void setReason_header(String reason_header) {
		this.reason_header = reason_header;
	}

	public String getOrigin_host() {
		return origin_host;
	}

	public void setOrigin_host(String origin_host) {
		this.origin_host = origin_host;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
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
		return "VoLTEBody [log=" + log + ", cdr_version=" + cdr_version + ", cdr_type=" + cdr_type
				+ ", charging_number=" + charging_number + ", imsi=" + imsi + ", subscriber_ip_address="
				+ subscriber_ip_address + ", wstype=" + wstype + ", win_service_name=" + win_service_name + ", apn="
				+ apn + ", accounting_status_type=" + accounting_status_type + ", called_asserted_identity="
				+ called_asserted_identity + ", calling_asserted_identity=" + calling_asserted_identity
				+ ", gpp_pdp_type=" + gpp_pdp_type + ", gpp_nasapi=" + gpp_nasapi + ", gpp_charging_characteristic="
				+ gpp_charging_characteristic + ", sgsn_mcc_mnc=" + sgsn_mcc_mnc + ", gpp_gprs_negotiated_qos_profile="
				+ gpp_gprs_negotiated_qos_profile + ", gpp_charging_id=" + gpp_charging_id
				+ ", ims_charging_identifier=" + ims_charging_identifier + ", originating_inter_operator_identifier="
				+ originating_inter_operator_identifier + ", terminating_inter_operator_identifier="
				+ terminating_inter_operator_identifier + ", service_start_3gpp_user_location_info="
				+ service_start_3gpp_user_location_info + ", service_terminating_3gpp_user_location_info="
				+ service_terminating_3gpp_user_location_info + ", nas_release_indicator=" + nas_release_indicator
				+ ", application_release_indicator=" + application_release_indicator
				+ ", number_portablility_routing_information=" + number_portablility_routing_information
				+ ", subscriber_ipv6_address=" + subscriber_ipv6_address + ", service_category_id="
				+ service_category_id + ", service_charging_type=" + service_charging_type + ", service_charging_rate="
				+ service_charging_rate + ", service_call_type=" + service_call_type + ", service_calling_information="
				+ service_calling_information + ", service_called_information=" + service_called_information
				+ ", service_start_date=" + service_start_date + ", service_start_time=" + service_start_time
				+ ", service_end_date=" + service_end_date + ", service_end_time=" + service_end_time
				+ ", service_upload_data_octects=" + service_upload_data_octects + ", service_download_data_octects="
				+ service_download_data_octects + ", service_contents_count=" + service_contents_count
				+ ", service_contents_type=" + service_contents_type + ", service_time_duration="
				+ service_time_duration + ", service_prepaid_airtime_charge=" + service_prepaid_airtime_charge
				+ ", service_prepaid_packet_charge=" + service_prepaid_packet_charge + ", service_prepaid_count_charge="
				+ service_prepaid_count_charge + ", service_prepaid_information_charge="
				+ service_prepaid_information_charge + ", service_3gpp_gprs_negotiated_qos_profile="
				+ service_3gpp_gprs_negotiated_qos_profile + ", supplementary_services_code_1="
				+ supplementary_services_code_1 + ", supplementary_services_code_2=" + supplementary_services_code_2
				+ ", hd_call=" + hd_call + ", routing_number=" + routing_number + ", sdp_codec_information="
				+ sdp_codec_information + ", request_timestamp=" + request_timestamp + ", reason_header="
				+ reason_header + ", origin_host=" + origin_host + ", reserved=" + reserved + ", record_type="
				+ record_type + ", sFlag=" + sFlag + super.toString() +"]";
	}

	
	
}

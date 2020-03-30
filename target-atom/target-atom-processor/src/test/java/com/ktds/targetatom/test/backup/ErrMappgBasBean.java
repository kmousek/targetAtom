package com.ktds.targetatom.test.backup;
/*package com.ktds.targetatom.bean;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.vo.TbErrMappgBasInfo;

@Repository
public class ErrMappgBasBean {

	private static List<TbErrMappgBasInfo> errMappgBasList = null;

	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ReferenceInfoDao dao;
	
	private static ErrorCD_Intl u_application_release_indicator_3;
	private static ErrorCD_Intl u_application_release_indicator_4;
	private static ErrorCD_Intl u_application_release_indicator_5;
	private static ErrorCD_Intl u_cdr_version_3;
	private static ErrorCD_Intl u_cdr_version_4;
	private static ErrorCD_Intl u_charging_number_3;
	private static ErrorCD_Intl u_charging_number_4;
	private static ErrorCD_Intl u_record_type_3;
	private static ErrorCD_Intl u_service_called_information_3;
	private static ErrorCD_Intl u_service_called_information_4;
	private static ErrorCD_Intl u_service_called_information_5;
	private static ErrorCD_Intl u_service_called_information_6;
	private static ErrorCD_Intl u_service_called_information_7;
	private static ErrorCD_Intl u_service_called_information_8;
	private static ErrorCD_Intl u_service_called_information_9;
	private static ErrorCD_Intl u_service_category_id_3;
	private static ErrorCD_Intl u_service_end_time_3;
	private static ErrorCD_Intl u_service_end_time_4;
	private static ErrorCD_Intl u_service_end_time_5;
	private static ErrorCD_Intl u_service_end_time_6;
	private static ErrorCD_Intl u_service_end_time_7;
	private static ErrorCD_Intl u_service_end_time_8;
	private static ErrorCD_Intl u_service_end_time_9;
	private static ErrorCD_Intl u_service_prepaid_airtime_charge_3;
	private static ErrorCD_Intl u_service_prepaid_airtime_charge_4;
	private static ErrorCD_Intl u_service_prepaid_airtime_charge_5;
	private static ErrorCD_Intl u_service_prepaid_airtime_charge_6;
	private static ErrorCD_Intl u_service_prepaid_count_charge_3;
	private static ErrorCD_Intl u_service_start_time_3;
	private static ErrorCD_Intl u_service_start_time_4;
	private static ErrorCD_Intl u_service_time_duration_3;
	private static ErrorCD_Intl u_service_time_duration_4;
	private static ErrorCD_Intl u_service_time_duration_5;
	private static ErrorCD_Intl u_service_time_duration_6;
	private static ErrorCD_Intl u_service_time_duration_7;
	private static ErrorCD_Intl u_service_time_duration_8;
	private static ErrorCD_Intl u_service_time_duration_9;
	private static ErrorCD_Intl u_supplementary_services_code_3;
	private static ErrorCD_Intl u_supplementary_services_code_4;
	private static ErrorCD_Intl u_supplementary_services_code_5;
	private static ErrorCD_Intl u_supplementary_services_code_6;
	private static ErrorCD_Intl u_wstype_3;
	private static ErrorCD_Intl u_wstype_4;
	private static ErrorCD_Intl u_wstype_5;
	private static ErrorCD_Intl u_wstype_6;
	private static ErrorCD_Intl u_wstype_7;
	private static ErrorCD_Intl u_wstype_8;
	private static ErrorCD_Intl u_service_called_information_2; // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 추가
	private static ErrorCD_Intl u_service_category_id_1; // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 추가
	private static ErrorCD_Intl u_supplementary_services_code_7; // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml - 추가
	private static ErrorCD_Intl u_service_time_duration_zero_1; // uBody.service_time_duration 0 이거나 0 보다 적을 경우 Error 처리 - 2016.12.23 Rater 요청건
	 
	private static ErrorCD_Intl u_supplementary_services_code_8; // 기업형 음성 총량제 drop 
	 
	private static ErrorCD_Intl u_service_called_information_1;
	 
	private static ErrorCD_Intl work_np_operator_3;
	private static ErrorCD_Intl work_service_prepaid_count_charge_3;
	private static ErrorCD_Intl work_sp_feature_code_3;
	private static ErrorCD_Intl work_sp_type_3;
	

	public int getErrMappgList(String neTypeId) {
		log.debug("neTypeId={}", neTypeId);
		if(errMappgBasList == null) {
			errMappgBasList = dao.getTbErrMappgBasinfo(neTypeId);
		}
		return errMappgBasList.size();
	}
	
	
	
	public ErrorCD_Intl getU_application_release_indicator_3() {
		return u_application_release_indicator_3;
	}



	public ErrorCD_Intl getU_application_release_indicator_4() {
		return u_application_release_indicator_4;
	}



	public ErrorCD_Intl getU_application_release_indicator_5() {
		return u_application_release_indicator_5;
	}



	public ErrorCD_Intl getU_cdr_version_3() {
		return u_cdr_version_3;
	}



	public ErrorCD_Intl getU_cdr_version_4() {
		return u_cdr_version_4;
	}



	public ErrorCD_Intl getU_charging_number_3() {
		return u_charging_number_3;
	}



	public ErrorCD_Intl getU_charging_number_4() {
		return u_charging_number_4;
	}



	public ErrorCD_Intl getU_record_type_3() {
		return u_record_type_3;
	}



	public ErrorCD_Intl getU_service_called_information_3() {
		return u_service_called_information_3;
	}



	public ErrorCD_Intl getU_service_called_information_4() {
		return u_service_called_information_4;
	}



	public ErrorCD_Intl getU_service_called_information_5() {
		return u_service_called_information_5;
	}



	public ErrorCD_Intl getU_service_called_information_6() {
		return u_service_called_information_6;
	}



	public ErrorCD_Intl getU_service_called_information_7() {
		return u_service_called_information_7;
	}



	public ErrorCD_Intl getU_service_called_information_8() {
		return u_service_called_information_8;
	}



	public ErrorCD_Intl getU_service_called_information_9() {
		return u_service_called_information_9;
	}



	public ErrorCD_Intl getU_service_category_id_3() {
		return u_service_category_id_3;
	}



	public ErrorCD_Intl getU_service_end_time_3() {
		return u_service_end_time_3;
	}



	public ErrorCD_Intl getU_service_end_time_4() {
		return u_service_end_time_4;
	}



	public ErrorCD_Intl getU_service_end_time_5() {
		return u_service_end_time_5;
	}



	public ErrorCD_Intl getU_service_end_time_6() {
		return u_service_end_time_6;
	}



	public ErrorCD_Intl getU_service_end_time_7() {
		return u_service_end_time_7;
	}



	public ErrorCD_Intl getU_service_end_time_8() {
		return u_service_end_time_8;
	}



	public ErrorCD_Intl getU_service_end_time_9() {
		return u_service_end_time_9;
	}



	public ErrorCD_Intl getU_service_prepaid_airtime_charge_3() {
		return u_service_prepaid_airtime_charge_3;
	}



	public ErrorCD_Intl getU_service_prepaid_airtime_charge_4() {
		return u_service_prepaid_airtime_charge_4;
	}



	public ErrorCD_Intl getU_service_prepaid_airtime_charge_5() {
		return u_service_prepaid_airtime_charge_5;
	}



	public ErrorCD_Intl getU_service_prepaid_airtime_charge_6() {
		return u_service_prepaid_airtime_charge_6;
	}



	public ErrorCD_Intl getU_service_prepaid_count_charge_3() {
		return u_service_prepaid_count_charge_3;
	}



	public ErrorCD_Intl getU_service_start_time_3() {
		return u_service_start_time_3;
	}



	public ErrorCD_Intl getU_service_start_time_4() {
		return u_service_start_time_4;
	}



	public ErrorCD_Intl getU_service_time_duration_3() {
		return u_service_time_duration_3;
	}



	public ErrorCD_Intl getU_service_time_duration_4() {
		return u_service_time_duration_4;
	}



	public ErrorCD_Intl getU_service_time_duration_5() {
		return u_service_time_duration_5;
	}



	public ErrorCD_Intl getU_service_time_duration_6() {
		return u_service_time_duration_6;
	}



	public ErrorCD_Intl getU_service_time_duration_7() {
		return u_service_time_duration_7;
	}



	public ErrorCD_Intl getU_service_time_duration_8() {
		return u_service_time_duration_8;
	}



	public ErrorCD_Intl getU_service_time_duration_9() {
		return u_service_time_duration_9;
	}



	public ErrorCD_Intl getU_supplementary_services_code_3() {
		return u_supplementary_services_code_3;
	}



	public ErrorCD_Intl getU_supplementary_services_code_4() {
		return u_supplementary_services_code_4;
	}



	public ErrorCD_Intl getU_supplementary_services_code_5() {
		return u_supplementary_services_code_5;
	}



	public ErrorCD_Intl getU_supplementary_services_code_6() {
		return u_supplementary_services_code_6;
	}



	public ErrorCD_Intl getU_wstype_3() {
		return u_wstype_3;
	}



	public ErrorCD_Intl getU_wstype_4() {
		return u_wstype_4;
	}



	public ErrorCD_Intl getU_wstype_5() {
		return u_wstype_5;
	}



	public ErrorCD_Intl getU_wstype_6() {
		return u_wstype_6;
	}



	public ErrorCD_Intl getU_wstype_7() {
		return u_wstype_7;
	}



	public ErrorCD_Intl getU_wstype_8() {
		return u_wstype_8;
	}



	public ErrorCD_Intl getU_service_called_information_2() {
		return u_service_called_information_2;
	}



	public ErrorCD_Intl getU_service_category_id_1() {
		return u_service_category_id_1;
	}



	public ErrorCD_Intl getU_supplementary_services_code_7() {
		return u_supplementary_services_code_7;
	}



	public ErrorCD_Intl getU_service_time_duration_zero_1() {
		return u_service_time_duration_zero_1;
	}



	public ErrorCD_Intl getU_supplementary_services_code_8() {
		return u_supplementary_services_code_8;
	}



	public ErrorCD_Intl getU_service_called_information_1() {
		return u_service_called_information_1;
	}



	public ErrorCD_Intl getWork_np_operator_3() {
		return work_np_operator_3;
	}



	public ErrorCD_Intl getWork_service_prepaid_count_charge_3() {
		return work_service_prepaid_count_charge_3;
	}



	public ErrorCD_Intl getWork_sp_feature_code_3() {
		return work_sp_feature_code_3;
	}



	public ErrorCD_Intl getWork_sp_type_3() {
		return work_sp_type_3;
	}



	public void setComAuditErrorSearch(String sNeTypeId, String sWorkTime) {
		u_application_release_indicator_3 = comnAuditErrorSearch(sNeTypeId, "application_release_indicator", 3, sWorkTime);
		u_application_release_indicator_4 = comnAuditErrorSearch(sNeTypeId, "application_release_indicator", 4, sWorkTime);
	    u_application_release_indicator_5 = comnAuditErrorSearch(sNeTypeId, "application_release_indicator", 5, sWorkTime);
	    u_cdr_version_3 = comnAuditErrorSearch(sNeTypeId, "cdr_version", 3, sWorkTime);
	    u_cdr_version_4 = comnAuditErrorSearch(sNeTypeId, "cdr_version", 4, sWorkTime);
	    u_charging_number_3 = comnAuditErrorSearch(sNeTypeId, "charging_number", 3, sWorkTime);
	    u_charging_number_4 = comnAuditErrorSearch(sNeTypeId, "charging_number", 4, sWorkTime);
	    u_record_type_3 = comnAuditErrorSearch(sNeTypeId, "record_type", 3, sWorkTime);
	    u_service_called_information_3 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 3, sWorkTime);
	    u_service_called_information_4 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 4, sWorkTime);
	    u_service_called_information_5 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 5, sWorkTime);
	    u_service_called_information_6 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 6, sWorkTime);
	    u_service_called_information_7 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 7, sWorkTime);
	    u_service_called_information_8 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 8, sWorkTime);
	    u_service_called_information_9 = comnAuditErrorSearch(sNeTypeId, "service_called_information", 9, sWorkTime);
	    u_service_category_id_3 = comnAuditErrorSearch(sNeTypeId, "service_category_id", 3, sWorkTime);
	    u_service_end_time_3 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 3, sWorkTime);
	    u_service_end_time_4 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 4, sWorkTime);
	    u_service_end_time_5 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 5, sWorkTime);
	    u_service_end_time_6 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 6, sWorkTime);
	    u_service_end_time_7 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 7, sWorkTime);
	    u_service_end_time_8 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 8, sWorkTime);
	    u_service_end_time_9 = comnAuditErrorSearch(sNeTypeId, "service_end_time", 9, sWorkTime);
	    u_service_prepaid_airtime_charge_3 = comnAuditErrorSearch(sNeTypeId, "service_prepaid_airtime_charge", 3, sWorkTime);
	    u_service_prepaid_airtime_charge_4 = comnAuditErrorSearch(sNeTypeId, "service_prepaid_airtime_charge", 4, sWorkTime);
	    u_service_prepaid_airtime_charge_5 = comnAuditErrorSearch(sNeTypeId, "service_prepaid_airtime_charge", 5, sWorkTime);
	    u_service_prepaid_airtime_charge_6 = comnAuditErrorSearch(sNeTypeId, "service_prepaid_airtime_charge", 6, sWorkTime);
	    u_service_prepaid_count_charge_3 = comnAuditErrorSearch(sNeTypeId, "service_prepaid_count_charge", 3, sWorkTime);
	    u_service_start_time_3 = comnAuditErrorSearch(sNeTypeId, "service_start_time", 3, sWorkTime);
	    u_service_start_time_4 = comnAuditErrorSearch(sNeTypeId, "service_start_time", 4, sWorkTime);
	    u_service_time_duration_3 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 3, sWorkTime);
	    u_service_time_duration_4 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 4, sWorkTime);
	    u_service_time_duration_5 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 5, sWorkTime);
	    u_service_time_duration_6 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 6, sWorkTime);
	    u_service_time_duration_7 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 7, sWorkTime);
	    u_service_time_duration_8 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 8, sWorkTime);
	    u_service_time_duration_9 = comnAuditErrorSearch(sNeTypeId, "service_time_duration", 9, sWorkTime);
	    u_supplementary_services_code_3 = comnAuditErrorSearch(sNeTypeId, "supplementary_services_code", 3, sWorkTime);
	    u_supplementary_services_code_4 = comnAuditErrorSearch(sNeTypeId, "supplementary_services_code", 4, sWorkTime);
	    u_supplementary_services_code_5 = comnAuditErrorSearch(sNeTypeId, "supplementary_services_code", 5, sWorkTime);
	    u_supplementary_services_code_6 = comnAuditErrorSearch(sNeTypeId, "supplementary_services_code", 6, sWorkTime);
	    u_wstype_3 = comnAuditErrorSearch(sNeTypeId, "wstype", 3, sWorkTime);
	    u_wstype_4 = comnAuditErrorSearch(sNeTypeId, "wstype", 4, sWorkTime);
	    u_wstype_5 = comnAuditErrorSearch(sNeTypeId, "wstype", 5, sWorkTime);
	    u_wstype_6 = comnAuditErrorSearch(sNeTypeId, "wstype", 6, sWorkTime);
	    u_wstype_7 = comnAuditErrorSearch(sNeTypeId, "wstype", 7, sWorkTime);
	    u_wstype_8 = comnAuditErrorSearch(sNeTypeId, "wstype", 8, sWorkTime);
	    work_np_operator_3 = comnAuditErrorSearch(sNeTypeId, "np_operator", 3, sWorkTime);
	    work_service_prepaid_count_charge_3 = comnAuditErrorSearch(sNeTypeId, "service_prepaid_count_charge", 3, sWorkTime);
	    work_sp_feature_code_3 = comnAuditErrorSearch(sNeTypeId, "sp_feature_code", 3, sWorkTime);
	    work_sp_type_3 = comnAuditErrorSearch(sNeTypeId, "sp_type", 3, sWorkTime);
	 
	    // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 추가
	    u_service_called_information_2 =  comnAuditErrorSearch(sNeTypeId, "service_called_information", 2, sWorkTime); 
	 
	    // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 추가
	    u_service_category_id_1 =  comnAuditErrorSearch(sNeTypeId, "service_category_id", 1, sWorkTime); 
	 
	    // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml - 추가
	    u_supplementary_services_code_7 = comnAuditErrorSearch(sNeTypeId, "supplementary_services_code", 7, sWorkTime);
	 
	    // uBody.service_time_duration 0 이거나 0 보다 적을 경우 Error 처리 - 2016.12.23 Rater 요청건
	    u_service_time_duration_zero_1 = comnAuditErrorSearch(sNeTypeId, "service_time_duration_zero", 1, sWorkTime);
	 
	    // 기업형 음성 총량제 droop 
	    u_supplementary_services_code_8 = comnAuditErrorSearch(sNeTypeId, "supplementary_services_code", 8, sWorkTime);
	}
	
	public ErrorCD_Intl comnAuditErrorSearch(String sNeTypeId, String sMappgFieldId, int iFieldOdrg, String sWorkDate) {
/*		ErrorCD_Intl uErrCdOut = new ErrorCD_Intl();
		boolean bMatched = false;

		for (TbErrMappgBasInfo obj : errMappgBasList) {
			if (obj.getNeTypeId().equals(sNeTypeId) && obj.getMappgFieldId().equals(sMappgFieldId) && obj.getFieldOdrg() == iFieldOdrg) {
				uErrCdOut.sErrCd = obj.getCdrErrId();
				uErrCdOut.sErrLevelDivCd = obj.getMzErrLevelDivCd();
				uErrCdOut.iErrPriority = obj.getMzErrPrirtNo();
				uErrCdOut.sOldErrCd = obj.getOldMzErrId();
				bMatched = true;
				
				break;
			}
		}
		
		if (!bMatched) {
			uErrCdOut.sErrCd          = "E999999"; // CDR_ERR_ID
	        uErrCdOut.sErrLevelDivCd  = "10";      // MZ_ERR_LEVEL_DIV_CD
	        uErrCdOut.iErrPriority    = 99999;     // MZ_ERR_PRIRT_NO
		}
		return null;
		//return uErrCdOut;
	}
}*/

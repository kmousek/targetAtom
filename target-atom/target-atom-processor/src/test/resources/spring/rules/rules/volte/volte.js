//load('volte_functions.js');

Matcher = java.util.regex.Matcher;
Pattern = java.util.regex.Pattern;
StringBuilder = java.lang.StringBuilder;
CollectionUtils = org.apache.commons.collections.CollectionUtils;
CommonUtil = com.ktds.targetatom.util.CommonUtil;

function comnPadChr(inputString, length, padding, type) {
	inputString = inputString.trim();
	if (inputString.length() >= length) {
		return inputString;
	}
 
	var sb = new StringBuilder();
	if (type.equals("L")) {
		while (sb.length() < length - inputString.length()) {
			sb.append(padding);
		}
		sb.append(inputString);
	} else {
		sb.append(inputString);
		while (sb.length() < length) {
			sb.append(padding);
		}
	}
	return sb.toString();
}

function comnStrTrim(inputString) {
 
	if (inputString == null || inputString.equals("")){
		return "";
	}
		
	return inputString.trim();
}


function strREIndexOf(inputString) {   
    var p = Pattern.compile("([^0-9])");
 var matcher = p.matcher(inputString);
  
 if (matcher.find()) {      
  return matcher.start();       
 } else {      
  return -1;
 }
}

function comnSubstring(sTgtString, iStart, iEnd) {
  
  if (sTgtString == null || sTgtString == "" || sTgtString.equals("") || iStart < 0 || iEnd < 0 || sTgtString.length() < iStart || iStart >= iEnd) {  
   return "";   
  } else if ( sTgtString.length() < iEnd ) {    
   iEnd = sTgtString.length();
  }
  
  return sTgtString.substring(iStart, iEnd);   
}

//function listSort(errLst, target, type) {
//	
//	if ( type.equals("descending") ) {
//		errLst.sort(function (priority0, priority1) {
//			return priority0.target > priority1.target ? -1 : priority0.target < priority1.target ? 1 : 0 ;
//		});
//	} else {
//		errLst.sort(function (priority0, priority1) {
//			return priority0.target < priority1.target ? -1 : priority0.target > priority1.target ? 1 : 0 ;
//		});
//	}
//	
//	var sErrLevelDivCd1 = errLst.get(0).sErrLevelDivCd
//    log.debug("Message listSortMMain sErrLevelDivCd: {}", sErrLevelDivCd1);
//	
//	return sErrLevelDivCd1;
//}

function recordTrim(ex, className) {
 var bodyList = ex.getIn().getBody(ArrayList.class);
 var resultList = new ArrayList();
 var body;
 
 if(!CollectionUtils.isEmpty(bodyList)){
  for (var i = 0; i < bodyList.size(); i++) {
   body = bodyList[i];
   if (body.getClass().getSimpleName().equals(className))
    resultList.add(body);
  }
 }
 ex.getIn().setBody(resultList);
}

function isValidDate(sDateTime, dateFormat) {
 
 if (dateFormat == "yyyyMMddHHmmss") {
  var datetime_pattern = /^(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$/;
  
  if (datetime_pattern.test(sDateTime)) return true;
  else return false;
 }
 else {
  return false;
 }
 
 return false;
}

LoggerFactory = org.slf4j.LoggerFactory;
List = java.util.List;
ArrayList = java.util.ArrayList;
SpringBeanUtils = com.ktds.targetatom.util.SpringBeanUtils;
Integer = java.lang.Integer;

CdrType = com.ktds.targetatom.cdr.common.CdrType;
VoLTEBody = com.targetatom.ktds.dao.volte.input.VoLTEBody;
EnrichedCdr = com.ktds.targetatom.cdr.volte.VoLTEEnrichedCdr;
ErrorCdr = com.ktds.targetatom.cdr.volte.VoLTEErrorCdr;
ResultCdr = com.ktds.targetatom.cdr.volte.VoLTEResultCdr;
ErrorCDIntl = com.ktds.targetatom.bean.ErrorCD_Intl;

cdrProfReferenceInfo = com.ktds.targetatom.vo.CdrProfReferenceInfo;
tbPfixRgnBasInfo = com.ktds.targetatom.vo.TbPfixRgnBasInfo;
tbPfixRgnBas = com.ktds.targetatom.vo.TbPfixRgnBasInfo;
tbIntlPfixBas = com.ktds.targetatom.vo.IntlPfixBasInfo;
tmpTbProfBas = com.ktds.targetatom.vo.TbProfBasInfo;
tbNoMovBizrBas = com.ktds.targetatom.vo.TbNoMovBizrBasInfo;
tbWlessSpclNoBas2 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbWlessSpclNoBas3 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbWlessSpclNoBas4 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbWlessSpclNoBas6 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbWlessSpclNoBas7 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbWlessSpclNoBas8 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbWlessSpclNoBas9 = com.ktds.targetatom.vo.WlessSpclNoBasInfo;
tbCalNoBas = com.ktds.targetatom.vo.TbCalNoBasInfo;
tbFeatrBas = com.ktds.targetatom.vo.TbFeatrBasInfo;
tbFeatrBas1 = com.ktds.targetatom.vo.TbFeatrBasInfo;
tbFeatrBas2 = com.ktds.targetatom.vo.TbFeatrBasInfo;
tbFeatrBas3 = com.ktds.targetatom.vo.TbFeatrBasInfo;
tbSvcPrvrBas = com.ktds.targetatom.vo.TbSvcPrvrBasInfo;
tbProfBas = com.ktds.targetatom.vo.TbProfBasInfo;

var log = LoggerFactory.getLogger("com.ktds.targetatom.VoLTE");
var bodyList = exchange.in.getBody(ArrayList.class);
var resultList = new ArrayList();
var errorList = new ArrayList();
var success_count = 0;
var total_count = 0;
var error_count = 0;
var enriched = new EnrichedCdr();
var errored = new ErrorCdr();
var auditInfoDao = SpringBeanUtils.getBean("auditInfoDao");
var audit = SpringBeanUtils.getBean("auditHandler");
var errMapBean = SpringBeanUtils.getBean("errMappgBasBean");

if(!CollectionUtils.isEmpty(bodyList)) {
 var uBody;
 var enriched;
 var result;
 
 log.debug("bodyList size is : {}", bodyList.size());
 for (total_count = 0; total_count < bodyList.size(); total_count++) {
  uBody = bodyList[total_count];
  log.debug("total_count is : {}", total_count);
  log.debug("Message : {}", uBody);
  enriched = enrich(uBody);
  log.debug("Normalized : {}", enriched);
  result = validate(enriched);
  log.debug("Result : {}", result);
  log.debug("Result Type : {}", result.cdrType);
  if(result.cdrType == CdrType.OUTPUT) {
   success_count++;
  } else {
   error_count++;
  }
  resultList.add(result);
 }
 
 log.debug("ResultList :::: {}", resultList.toString());  
 
 log.debug("file total_count ::: " + total_count);
 exchange.getIn().setHeader("file.total.count", total_count);
 
 log.debug("file success_count ::: " + success_count);
 exchange.getIn().setHeader("file.success.count", success_count);
 
 log.debug("file error_count ::: " + error_count);
 exchange.getIn().setHeader("file.error.count", error_count);
}

resultList;

function enrich(uBody) {
	
 uBody.service_charging_type = comnPadChr(uBody.service_charging_type, 3, "0", "L");
// uBody.application_release_indicator =
// comnStrTrim(uBody.application_release_indicator);
    if (uBody.application_release_indicator == "" || uBody.application_release_indicator.equals("")) {
    	uBody.application_release_indicator = 0;
    }

    // //////////////////////////////////////////////////////////////////////////
    // / String Value 초기화
    // //////////////////////////////////////////////////////////////////////////
    if (uBody.getService_prepaid_airtime_charge().trim().equals(""))
     uBody.setService_prepaid_airtime_charge("0");
    
    if (uBody.getService_prepaid_information_charge().trim().equals(""))
     uBody.setService_prepaid_information_charge("0");
    
    if (uBody.getService_prepaid_count_charge().trim().equals(""))
     uBody.setService_prepaid_count_charge("0");   
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
   
    
    // uBody.terminating_inter_operator_identifier =
	// comnStrTrim(uBody.terminating_inter_operator_identifier);
    uBody.terminating_inter_operator_identifier = comnStrTrim(uBody.terminating_inter_operator_identifier); 
    // uBody.service_called_information =
	// comnStrTrim(uBody.service_called_information);
    uBody.service_called_information = comnStrTrim(uBody.service_called_information);
    
    // if (comnStrTrim(uBody.service_category_id) == "") {
    // uBody.service_category_id = "000";
 // }
    if (comnStrTrim(uBody.service_category_id) == "" || comnStrTrim(uBody.service_category_id).equals("")) {
    	uBody.service_category_id = "000";
    }

    // listFindIndex 함수 소스코드가 필요함. 그리고 i는 뭥미?
    // if (listFindIndex(LST_BIGI_CDR, i, i == uBody.wstype) > -1) {
    // uBody.bIsBIGI_CDR = true;
    // }
	 if (uBody.getLST_BIGI_CDR().contains(uBody.wstype)) {
	  uBody.bIsBIGI_CDR = true;
	 }

 // if (listFindIndex(LST_CALL_FORWARDING, i, i ==
	// uBody.supplementary_services_code_1) > -1) {
    // uBody.bIsCALL_FORWARDING = true;
 // }
 if(uBody.LST_CALL_FORWARDING.contains(uBody.supplementary_services_code_1)) {
  uBody.bIsCALL_FORWARDING = true;
 }
    
 // if (iAppReleaseInd == -11) {
    // uBody.bIsCDF_ABNORMAL = true;
 // }
 if (uBody.application_release_indicator == -11) {
     uBody.bIsCDF_ABNORMAL = true;
 }
 
 var iAppReleaseInd = uBody.application_release_indicator;
    // if ((listFindIndex(LST_CDF_NORMAL, i, i == iAppReleaseInd) > -1) ||
    // (iAppReleaseInd <= -200 && iAppReleaseInd >= -299) || (iAppReleaseInd <=
	// -300 && iAppReleaseInd >= -399)) {
    // uBody.bIsCDF_NORMAL = true;
 // }
 log.debug("application_release_indicator={}", iAppReleaseInd);
 
 if ( (uBody.intContains(uBody.LST_CDF_NORMAL, iAppReleaseInd))  || 
		 (iAppReleaseInd <= -200 && iAppReleaseInd >= -299) || 
		 (iAppReleaseInd <= -300 && iAppReleaseInd >= -399)) {
  uBody.bIsCDF_NORMAL = true;
 }
 log.debug("11111");
 // if ((listFindIndex(LST_CDF_UNSUCCESS, i, i == iAppReleaseInd) > -1) ||
    // (iAppReleaseInd <= 499 && iAppReleaseInd >= 400) || (iAppReleaseInd <=
	// 599 && iAppReleaseInd >= 500) ||
    // (iAppReleaseInd <= 699 && iAppReleaseInd >= 600)) {
    // uBody.bIsCDF_UNSUCCESS = true;
 // } else {
    // uBody.bIsCDF_UNSUCCESS = false;
 // } 
 if ( (uBody.intContains(uBody.LST_CDF_UNSUCCESS, iAppReleaseInd)) || 
     (iAppReleaseInd <= 499 && iAppReleaseInd >= 400) || 
     (iAppReleaseInd <= 599 && iAppReleaseInd >= 500) || 
     (iAppReleaseInd <= 699 && iAppReleaseInd >= 600)) {
  uBody.bIsCDF_UNSUCCESS = true;
 } else {
  uBody.bIsCDF_UNSUCCESS = false;
 }
 log.debug("22222");
 
 if (uBody.wstype == uBody.getFpsCall()) {
     uBody.bIsFPS_CALL = true;
 }
 log.debug("33333");
    if (uBody.service_category_id == uBody.getIm3g()) {
     uBody.bIsIM_3G = true;
 }
    log.debug("44444");
  
 if (uBody.service_category_id == uBody.getIm4g()) {
     uBody.bIsIM_4G = true;
 }
 log.debug("55555");
 
 if (uBody.service_category_id == uBody.getPsvt3gCall()) {
     uBody.bIsPSVT_3G_CALL = true;
 }
 log.debug("66666");
 
 if (uBody.service_category_id == uBody.getPsvt4gCall()) {
     uBody.bIsPSVT_4G_CALL = true;
 }
 log.debug("77777");
 
 // if ((listFindIndex(LST_VOLTE_4G_CALL, i, i == uBody.service_category_id)
	// > -1)) {
 if (uBody.getLST_VOLTE_4G_CALL().contains(uBody.service_category_id)) {
     uBody.bIsVOLTE_4G_CALL = true;
 }
 log.debug("88888");
 
 if (uBody.service_category_id == uBody.getVs3gCall()) {
     uBody.bIsVS_3G_CALL = true;
 }
 log.debug("99999");
 
 if (uBody.service_category_id == uBody.getVs4gCall()) {
     uBody.bIsVS_4G_CALL = true;
 }
 log.debug("101010");
 
 if (uBody.service_charging_type.equals("004") || uBody.service_charging_type.equals("006")) {
     uBody.bIsTIME_CDR = true;
 }
 log.debug("11");
 
 if (uBody.service_charging_type == uBody.getOcrCdr()) {
     uBody.bIsOCR_CDR = true;
 }
 log.debug("12");
 
 if (!(uBody.wstype == uBody.getWinCdr())) {
     uBody.bIsWIN_CDR = true;
 }
 log.debug("13");
 
 if ((uBody.bIsWIN_CDR) && (uBody.service_prepaid_airtime_charge > 0 || 
     uBody.service_prepaid_information_charge > 0 || uBody.service_prepaid_count_charge > 0)) {
     uBody.bIsWIN_CHARGEABLE = true;
 }
 log.debug("14");
 
 if ((uBody.bIsVOLTE_4G_CALL) && (uBody.hd_call.equals("1")) && 
     (!(uBody.terminating_inter_operator_identifier == null || uBody.terminating_inter_operator_identifier.equals("") ) && 
     (uBody.terminating_inter_operator_identifier.startsWith("cs.kt.co") || uBody.terminating_inter_operator_identifier.startsWith("octave.c"))) ) {
     uBody.bIsHD_3G_VOICE = true;
 }
 log.debug("15");
 
 if ((uBody.bIsVOLTE_4G_CALL) && (uBody.hd_call.equals("1")) && 
     (uBody.terminating_inter_operator_identifier != null && 
     (uBody.terminating_inter_operator_identifier.startsWith("ims.kt.c") || 
      uBody.terminating_inter_operator_identifier.startsWith("sktims.n") ||
      uBody.terminating_inter_operator_identifier.startsWith("lte-lgup"))) ) {
     uBody.bIsHD_4G_VOICE = true;
 }
 log.debug("16");
 
 if (uBody.wstype == uBody.getBfreeCall() || uBody.wstype == uBody.getBrateCall() || uBody.wstype == uBody.getBrrcfCall() ||
     uBody.wstype == uBody.getSmsbgCall() || uBody.wstype == uBody.getBlimitCall() || uBody.wstype == uBody.getBsmocCall() || uBody.wstype == uBody.getBoverCall()) {
     uBody.bIsWIN_SRV_BG = true;
 }
 log.debug("17");
 
 if (uBody.service_charging_type.equals("001")){
     uBody.bIsNO_CHARGE_CDR = true;
 }
 log.debug("18");
 
 if (uBody.bIsNO_CHARGE_CDR && uBody.bIsWIN_CDR && 
     (uBody.bIsVOLTE_4G_CALL || uBody.bIsPSVT_3G_CALL || uBody.bIsPSVT_4G_CALL || uBody.bIsVS_3G_CALL || uBody.bIsVS_4G_CALL)) {
     uBody.bIsTIME_CDR_001 = true;
 }
 log.debug("19");
 
 if (uBody.bIsNO_CHARGE_CDR && uBody.bIsWIN_CDR && ( uBody.bIsIM_3G || uBody.bIsIM_4G )) {
     uBody.bIsOCR_CDR_001 = true;
 }
 log.debug("20");
 
 if (uBody.supplementary_services_code_1.equals("227")) {
     uBody.bIsEMS_CALLED = true;
 }
 log.debug("21");
 
 uBody.sCallingSvcNo = CommonUtil.comnSubstring(uBody.charging_number,0,11);
 log.debug("21-1");
 uBody.sCallingSvcNo = comnSubstring(uBody.charging_number,0,11);
 log.debug("21-2");
 uBody.sChrgSvcNo    = uBody.sCallingSvcNo;
 // uBody.setsCallingSvcNo(comnSubstring(volteBody.getCharging_number(), 0,
	// 11));
 // uBody.setsChrgSvcNo(enriched.getsCallingSvcNo());
         
         // boolean bIsValidEndDate = strToDate(dConnectingTimeDate,
			// uBody.service_end_date + uBody.service_end_time,
			// "yyyyMMddHHmmss");
      // boolean bIsValidStartDate = strToDate(dConnectingTimeDate,
		// uBody.service_start_date + uBody.service_start_time,
		// "yyyyMMddHHmmss");
 var bIsValidEndDate = isValidDate(uBody.getService_end_date() + uBody.getService_end_time(), "yyyyMMddHHmmss");
 var bIsValidStartDate = isValidDate(uBody.getService_start_date() + uBody.getService_start_time(), "yyyyMMddHHmmss");
 
 log.debug("22");
 if ((!(bIsValidEndDate)) || (!(bIsValidStartDate))) {
	 uBody.sCallEndDate  = uBody.getDummyDate();
     uBody.sCallEndTime  = uBody.getDummyTime1();
     uBody.sCallStartDate  = uBody.getDummyDate();
     uBody.sCallStartTime  = uBody.getDummyTime0();
 } else {
       // Rule : date_time_sstt - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml -
		// 추가
     uBody.sCallStartDate = uBody.service_start_date;
     uBody.sCallStartTime = uBody.service_start_time;
     uBody.sCallEndDate = uBody.service_end_date;
     uBody.sCallEndTime = uBody.service_end_time;
 }
 log.debug("23");
         // if ((!(bIsValidEndDate)) || (!(bIsValidStartDate))) {
         // uBody.setsCallEndDate(enriched.getDummyDate());
         // uBody.setsCallEndTime(enriched.getDummyTime1());
         // uBody.setsCallStartDate(enriched.getDummyDate());
         // uBody.setsCallStartTime(enriched.getDummyTime0());
         // } else {
         // uBody.setsCallEndDate(volteBody.getService_start_date());
         // uBody.setsCallEndTime(volteBody.getService_start_time());
         // uBody.setsCallStartDate(volteBody.getService_end_date());
         // uBody.setsCallStartTime(volteBody.getService_end_time());
         // }
          
         // Rule : vms_access
      // uBody.sVmsIdent = "VMSFW_VMS_ACCESS";
      // uBody.sVmsDivision = "VOLTE";
      // --> Common Structure Defined
      
      // Rule : vms_feature_change_dt_init
      // uBody.sIpasIdent = "VMS_FEATURE_OPEN";
      // uBody.sIpasDivision = "VOLTE";
   // --> Common Structure Defined
   
      // Rule : called_number_normal
      log.debug("service_called_information ::: {}", uBody.service_called_information);
      uBody.sCalledSvcNo = uBody.service_called_information;
      // uBody.setsCalledSvcNo(uBody.getService_called_information());
      uBody.sWorkCalledSvcNo = uBody.sCalledSvcNo;
      uBody.sDrewCalledSvcNo = uBody.sCalledSvcNo;
         // uBody.setsWorkCalledSvcNo(uBody.getsCalledSvcNo());
         // uBody.setsDrewCalledSvcNo(uBody.getsCalledSvcNo());

      // Rule : called_number_vms
      // if (uBody.bIsVOLTE_4G_CALL && (uBody.supplementary_services_code_1 ==
		// "241") &&
         // ((strstartsWith(uBody.service_called_information, "*88") ||
			// (strstartsWith(uBody.service_called_information, "*89"))))) {
         // uBody.sCalledSvcNo = uBody.sCallingSvcNo;
      log.debug("isbIsVOLTE_4G_CALL ::: {} ::: getSupplementary_services_code_1 ::: {} ::: getService_called_information ::: {}", uBody.isbIsVOLTE_4G_CALL(), uBody.getSupplementary_services_code_1(), uBody.getService_called_information());
      log.debug("getsCallingSvcNo ::: {}", uBody.getsCallingSvcNo());
      	 if (uBody.isbIsVOLTE_4G_CALL() && uBody.getSupplementary_services_code_1().equals("241") &&
            (uBody.getService_called_information().startsWith("*88") || uBody.getService_called_information().startsWith("*89")))
          uBody.setsCalledSvcNo(uBody.getsCallingSvcNo());
         // sCallStartDateTime = uBody.sCallStartDate + uBody.sCallStartTime;
         uBody.setsCallStartDateTime(uBody.getsCallStartDate() + uBody.getsCallStartTime());


     if (uBody.wstype == uBody.getPpsCall()) {
         uBody.bIsPPS_CALL = true;
     }
     // if (uBody.getWstype().equals(enriched.getPpsCall()))
     // uBody.setbIsBIGI_CDR(true);
    
  if (uBody.getLST_DADAN_CALL().contains(uBody.getWstype())) {
         uBody.bIsDADAN_CALL = true;
     }
     // if (uBody.getLST_DADAN_CALL().contains(uBody.getWstype()))
     // uBody.setbIsDADAN_CALL(true);

     // Rule : call_gubun_setting - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     if (uBody.sCalledSvcNo.startsWith("001") && !(uBody.bIsPPS_CALL || uBody.bIsDADAN_CALL)) {
         uBody.sCallGubun = "IC";
     } else if (uBody.bIsPSVT_3G_CALL || uBody.bIsPSVT_4G_CALL) {
         uBody.sCallGubun = "VS";
         uBody.bIsVISUAL_CALL = true;
     } else {
         uBody.sCallGubun = "VC";
         uBody.bIsVOICE_CALL = true;
     }
     // if (enriched.getsCalledSvcNo().startsWith("001") &&
		// !(enriched.isbIsPPS_CALL() || enriched.isbIsDADAN_CALL())) {
     // enriched.setsCallGubun("IC");
     // } else if (enriched.isbIsPSVT_3G_CALL() ||
		// enriched.isbIsPSVT_4G_CALL()) {
     // enriched.setsCallGubun("VS");
     // enriched.setbIsVISUAL_CALL(true);
     // } else {
     // enriched.setsCallGubun("VC");
     // enriched.setbIsVOICE_CALL(true);
     // }
        // Rule : fix_np_oper_fps
     // Rule : fix_np_oper_fps_otr
     if (uBody.bIsFPS_CALL) {
         // if (listFindIndex(LST_MOBILE_PREFIX, i, i ==
			// uBody.supplementary_services_code_1) > -1) {
      if (enriched.getLST_MOBILE_PREFIX().contains(uBody.getSupplementary_services_code_1())) {
             // Rule : fix_np_oper_fps 
             uBody.sNpInd = "Y";
             uBody.sNpOperator = "06";
         } else {
             // Rule : fix_np_oper_fps_otr 
             uBody.sNpInd = "Y";
             uBody.sNpOperator = "01";
         }
     }
     // if (enriched.isbIsFPS_CALL()) {
     // if
		// (enriched.getLST_MOBILE_PREFIX().contains(volteBody.getSupplementary_services_code_1()))
		// {
     // enriched.setsNpInd("Y");
     // enriched.setsNpOperator("06");
     // } else {
     // enriched.setsNpInd("Y");
     // enriched.setsNpOperator("01");
     // }
     // }
     
     // Rule : in_network_ind
     if (uBody.bIsHD_4G_VOICE) {
         uBody.sTermArea = "WL";

         if (uBody.terminating_inter_operator_identifier.equals("ims.kt.c")) {
             uBody.sInNetCallYn = "Y";
         } else {
             uBody.sInNetCallYn = "N";
         }
     }
     // if (enriched.isbIsHD_4G_VOICE()) {
     // enriched.setsTermArea("WL");
     // 
     // if
		// (volteBody.getTerminating_inter_operator_identifier().equals("ims.kt.c"))
     // enriched.setsInNetCallYn("Y");
     // else
     // enriched.setsInNetCallYn("N");
     // }
      // Rule : input_file_type_assignment - 불필요

     // Rule : np_information
     var sPreRoutingNumber = comnStrTrim(comnSubstring(uBody.routing_number, 0, 2));

     if (!(sPreRoutingNumber.equals("") || sPreRoutingNumber == null)) {
         uBody.sNpInd = "Y";
         uBody.sNpOperator = comnPadChr(sPreRoutingNumber, 2, "0", "L");
     }


     // Rule : np_information_exception - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
      
     if (sPreRoutingNumber.equals("") || sPreRoutingNumber == null) {
         if ((uBody.supplementary_services_code_1.equals("241")) && (uBody.service_category_id.equals("611") || uBody.service_category_id.equals("000"))) {
             uBody.terminating_inter_operator_identifier = "ims.kt.c";
         }

         if (uBody.terminating_inter_operator_identifier.equals("ims.kt.c")) {
             uBody.sNpOperator = "13";
             uBody.sNpInd = "Y";
         } else if (uBody.terminating_inter_operator_identifier.equals("octave.c")) {
             uBody.sNpOperator = "15";
             uBody.sNpInd = "Y";
         }
     }
     
     // if (sPreRoutingNumber.equals("") || sPreRoutingNumber == null) {
     // if (volteBody.getSupplementary_services_code_1().equals("241") &&
     // (volteBody.getService_category_id().equals("611") ||
		// volteBody.getService_category_id().equals("000")))
     // volteBody.setTerminating_inter_operator_identifier("ims.kt.c");
     // 
     // if
		// (volteBody.getTerminating_inter_operator_identifier().equals("ims.kt.c"))
		// {
     // enriched.setsNpOperator("13");
     // enriched.setsNpInd("Y");
     // } else if
		// (volteBody.getTerminating_inter_operator_identifier().equals("octave.c"))
		// {
     // enriched.setsNpOperator("15");
     // enriched.setsNpInd("Y");
     // }
     // }
 
     // Rule : prefix_area
     // IF (PSVT_4G_CALL OR PSVT_3G_CALL) THEN WORK.key_val="OTR" ELSE
		// WORK.key_val="ORG";
     // VOLTE.service_category_id = "612" || VOLTE.service_category_id =
		// "622"
     if (uBody.bIsPSVT_4G_CALL || uBody.bIsPSVT_3G_CALL) {
         uBody.sKeyVal = "OTR";
     } else {
         uBody.sKeyVal = "ORG";
     }
     // if (enriched.isbIsPSVT_4G_CALL() || enriched.isbIsPSVT_3G_CALL())
     // enriched.setsKeyVal("OTR");
     // else
     // enriched.setsKeyVal("ORG");
       
  // 특수문자 앞자리까지 사용하도록 수정.
     // string sTmpCalledSvcNo = uBody.sCalledSvcNo;
     
     var sTmpCalledSvcNo = null;
     log.debug("sCalledSvcNo ::: {}", uBody.sCalledSvcNo);
     sTmpCalledSvcNo = uBody.sCalledSvcNo.replace(/\*/gi, "");
     sTmpCalledSvcNo = sTmpCalledSvcNo.replace(/\#/gi, "");
     // sTmpCalledSvcNo = strREReplaceAll(uBody.sCalledSvcNo, "\\*", "");
     // sTmpCalledSvcNo = strREReplaceAll(sTmpCalledSvcNo, "#", "");
     // if (sTmpCalledSvcNo != null) {
     // sTmpCalledSvcNo = strREIndexOf(sTmpCalledSvcNo, "[^0-9]") < 0 ?
		// sTmpCalledSvcNo : comnSubstring (sTmpCalledSvcNo, 0,
		// strREIndexOf(sTmpCalledSvcNo, "[^0-9]"));
     // }
    
     // 숫자만 패턴 매치 필요
     if ( !(uBody.getsCalledSvcNo() == null || uBody.getsCalledSvcNo().equals("")) ) {
      sTmpCalledSvcNo = uBody.getsCalledSvcNo();
      sTmpCalledSvcNo = strREIndexOf(sTmpCalledSvcNo) < 0 ? sTmpCalledSvcNo : comnSubstring(sTmpCalledSvcNo, 0,strREIndexOf(sTmpCalledSvcNo));
     } 
     

  // 정산 Rule
     // TB_PFIX_RGN_BAS_Intl uTB_PFIX_RGN_BAS =
		// get_TB_PFIX_RGN_BAS(sTmpCalledSvcNo,
		// enriched.getsCallStartDateTime(), uBody.sKeyVal);
     // if (uTB_PFIX_RGN_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_PFIX_RGN_BAS.sSettlFileName; // ex) KTF
		// WORK.sett_fname
     // uBody.sSettlCarrier = uTB_PFIX_RGN_BAS.sSettlCarrier; // ex) KTF
		// WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_PFIX_RGN_BAS.sSettlFeatureCd; // ex)
		// KTF010 WORK.sett_feature
     // uBody.sCalledArea = uTB_PFIX_RGN_BAS.sNpaAreaNo; // ex) 010
		// WORK.called_area
     // uBody.sChrgOtherNo = uTB_PFIX_RGN_BAS.sChrgOtherNo; // ex) 1032164062
		// WORK.tn_called_number
     // uBody.sInNetCallYn = uTB_PFIX_RGN_BAS.sInNetCallYn; // ex) Y
		// WORK.in_network_ind
     // uBody.sTermArea = uTB_PFIX_RGN_BAS.sWiredLessDivideValue; // ex) WL
		// WORK.term_area
     // }
     log.debug("sTmpCalledSvcNo ::: {} ::: getsCallStartDateTime ::: {} ::: getsKeyVal ::: {}", sTmpCalledSvcNo, uBody.getsCallStartDateTime(), uBody.getsKeyVal());
     tbPfixRgnBasInfo = audit.get_TB_PFIX_RGN_BAS(sTmpCalledSvcNo, uBody.getsCallStartDateTime(), uBody.getsKeyVal());
     
      if (tbPfixRgnBasInfo != null ) {
       uBody.setsSettlFileName(tbPfixRgnBasInfo.getsSettlFileName());
       uBody.setsSettlCarrier(tbPfixRgnBasInfo.getsSettlCarrier());
       uBody.setsSettlFeatureCd(tbPfixRgnBasInfo.getsSettlFeatureCd());
       uBody.setsCalledArea(tbPfixRgnBasInfo.getsNpaAreaNo());
       uBody.setsInNetCallYn(tbPfixRgnBasInfo.getsInNetCallYn());
       uBody.setsChrgOtherNo(tbPfixRgnBasInfo.getsChrgOtherNo());
       uBody.setsTermArea(tbPfixRgnBasInfo.getsWiredLessDivideValue());
      }
      
     // Rule : record_date_time_sstt - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 삭제됨


     // Rule : service_feature_gubun - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     if (uBody.bIsTIME_CDR || uBody.bIsTIME_CDR_001) {
         uBody.sProdFromatId = "VOLTE";
         uBody.sProdCdType = uBody.sCallGubun;
         uBody.sInsProdType = "SCP";
     }
     // if (enriched.isbIsTIME_CDR() || enriched.isbIsTIME_CDR_001()) {
     // enriched.setsProdFormatId("VOLTE");
     // enriched.setsProdCdType(enriched.getsCallGubun());
     // enriched.setsInsProdType("SCP");
     // }
     
     // Rule : service_feature_number - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     if (uBody.bIsOCR_CDR || uBody.bIsOCR_CDR_001) {
         uBody.sProdFromatId = "VOLTE";
         uBody.sProdCdType = "";
         uBody.sProdNumber = uBody.wstype;
         uBody.sInsProdType = "WIN";
     }
  // if (enriched.isbIsOCR_CDR() || enriched.isbIsOCR_CDR_001()) {
  // enriched.setsProdFormatId("VOLTE");
     // enriched.setsProdCdType("");
     // enriched.setsProdNumber(volteBody.getWstype());
     // enriched.setsInsProdType("WIN");
     // }
  
     // Rule : set_cdf_abnorm
     if (uBody.bIsCDF_ABNORMAL) {
         uBody.sTermCode = uBody.CDF_ABNORM_V;    // "2"
         uBody.bIsABNORMAL_COMP = true;
     }
     // if (enriched.isbIsCDF_ABNORMAL()) {
     // enriched.setsTermCode(enriched.getCdfAbnormV());
     // enriched.setbIsABNORMAL_COMP(true);
     // }

     // Rule : set_cdf_norm
     if (uBody.bIsCDF_NORMAL) {
         uBody.sTermCode = uBody.getCdfNormV();    // "0"
         uBody.bIsNORMAL_COMP = true;
     }
     // if (enriched.isbIsCDF_NORMAL()) {
     // enriched.setsTermCode(enriched.getCdfNormV());
     // enriched.setbIsNORMAL_COMP(true);
     // }

     // Rule : set_cdf_unsuccess

     if (!(uBody.bIsCDF_ABNORMAL || uBody.bIsCDF_NORMAL)) {
         uBody.sTermCode = uBody.getCdfUnsuccessV();    // "1"
         uBody.bIsUNSUCCESS_COMP = true;
     }
     // if (!(enriched.isbIsCDF_ABNORMAL() || enriched.isbIsCDF_NORMAL())) {
     // enriched.setsTermCode(enriched.getCdfUnsuccessV());
     // enriched.setbIsUNSUCCESS_COMP(true);
     // }
     // Rule : set_win
     if (uBody.bIsWIN_CDR) {
         uBody.sWinSvcType = uBody.wstype;
     }
     // if (enriched.isbIsWIN_CDR())
     // enriched.setsWinSvcType(volteBody.getWstype());

     // Rule : sgsn_ip_address - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
		// - 삭제됨
     uBody.sCallingIp = uBody.sgsn_ip_address;
  // enriched.setsCallingIp(volteBody.getCalling_asserted_identity());
     // Rule : sscode
     uBody.sSscode = uBody.supplementary_services_code_1;
  // enriched.setsSscode(volteBody.getSupplementary_services_code_1());
     // Rule : special_number_type - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     
     // VOLTE.INTERNATIONAL
     //if (uBody.sCalledSvcNo.startsWith("00") && !(uBody.sSscode == SSCODE_FREE_ZONE)) {
      if (uBody.sCalledSvcNo.startsWith("00") && !(uBody.sSscode == uBody.SSCODE_FREE_ZONE)) {
         uBody.bIsINTERNATIONAL = true;
     }
     // if (enriched.getsCalledSvcNo().startsWith("00") &&
		// !enriched.getsSscode().equals(enriched.getSscodeFreeZone()))
     // enriched.setbIsINTERNATIONAL(true);
     
     if (uBody.bIsINTERNATIONAL) {
         uBody.sSpNumberType = uBody.getSpip();
     } else {
         uBody.sSpNumberType = uBody.getSpnm();
     }
     // if (enriched.isbIsINTERNATIONAL())
     // enriched.setsSpNumberType(enriched.getSpip());
     // else
     // enriched.setsSpNumberType(enriched.getSpnm());

     // Rule : special_number_yn

     uBody.sSpIndi = "N"; // WORK.special_num_ind default value "N" 으로 처리
							// 20130605jek(MAF 쪽 확인 함)
     // enriched.setsSpIndi("N");
//   
// //** 미구현
// //TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_1 =
// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo, "KTF",
// enriched.getsCallStartDateTime(), uBody.sSpNumberType);
// WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS_1 =
// audit.get_TB_WLESS_SPCL_NO_BAS(enriched.getsCalledArea(),
// enriched.getsChrgOtherNo(), "KTF", enriched.getsCallStartDateTime(),
// enriched.getsSpNumberType());
//
// //if (uTB_WLESS_SPCL_NO_BAS_1.lRowCnt > 0) {
// // uBody.sSpIndi = "Y";
// //}
// if (uTB_WLESS_SPCL_NO_BAS_1 != null ) {
// enriched.setsSpIndi("Y");
// }
     
     // Rule : usage_domain
     uBody.sChrgCallYn = "Y";
  // enriched.setsChrgCallYn("Y");
     // Rule : vms_access
     // if (listFindIndex(LST_SSCODE_VMS, i, i == uBody.sSscode) > -1) {
     // uBody.sVmsAccess = "00";
     // uBody.sVmsInd = "Y";
     // }
     if (uBody.getLST_SSCODE_VMS().contains(enriched.getsSscode())) {
      uBody.setsVmsAccess("00");
      uBody.setsVmsInd("Y");
     }  


// rule "Setting Volte Edit" salience 20
// //include attributes such as "salience" here...
// when
// volteBody : VoLTEBody ()
// eval(true)
// then
// // Rule : called_number_freezone - Rule 현행화(변경사항 적용) -
// RBMS_20150726201256.xml - 신규
//   
     
     if (uBody.service_called_information.startsWith("0995")) {
         uBody.bIsPREFIX_FREEZONE = true;
     }
     // if (volteBody.getService_called_information().startsWith("0995"))
     // enriched.setbIsPREFIX_FREEZONE(true);

     if ( uBody.sSscode == uBody.getSscodeFreeZone() && uBody.bIsPREFIX_FREEZONE) {
         uBody.sCalledSvcNo = comnSubstring(uBody.sCalledSvcNo,7,18);
         uBody.sWorkCalledSvcNo = uBody.sCalledSvcNo;
     }
     // if (enriched.getsSscode().equals(enriched.getSscodeFreeZone()) &&
		// enriched.isbIsPREFIX_FREEZONE()) {
     // enriched.setsCalledSvcNo(comnSubstring(enriched.getsCalledSvcNo(), 7,
		// 18));
     // enriched.setsWorkCalledSvcNo(enriched.getsCalledSvcNo());

     // }
       

     // Rule : prefix_area_freezone - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     
     if ( uBody.sSscode == uBody.getSscodeFreeZone() && uBody.bIsPREFIX_FREEZONE) {
         if (uBody.bIsVISUAL_CALL) {
             uBody.sKeyVal = "OTR";
         } else {
             uBody.sKeyVal = "ORG";
         }
     // if (enriched.getsSscode().equals(enriched.getSscodeFreeZone()) &&
		// enriched.isbIsPREFIX_FREEZONE()) {
     // if (enriched.isbIsVISUAL_CALL())
     // enriched.setsKeyVal("OTR");
     // else
     // enriched.setsKeyVal("ORG");

     // TB_PFIX_RGN_BAS_Intl uTB_PFIX_RGN_BAS =
		// get_TB_PFIX_RGN_BAS("01029445555", enriched.getsCallStartDateTime(),
		// uBody.sKeyVal);

     // if (uTB_PFIX_RGN_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_PFIX_RGN_BAS.sSettlFileName; // ex) KTF
		// WORK.sett_fname
     // uBody.sSettlCarrier = uTB_PFIX_RGN_BAS.sSettlCarrier; // ex) KTF
		// WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_PFIX_RGN_BAS.sSettlFeatureCd; // ex)
		// KTF010 WORK.sett_feature
     // uBody.sCalledArea = uTB_PFIX_RGN_BAS.sNpaAreaNo; // ex) 010
		// WORK.called_area
     // uBody.sChrgOtherNo = uTB_PFIX_RGN_BAS.sChrgOtherNo; // ex) 1032164062
		// WORK.tn_called_number
     // uBody.sInNetCallYn = uTB_PFIX_RGN_BAS.sInNetCallYn; // ex) Y
		// WORK.in_network_ind
     // uBody.sTermArea = uTB_PFIX_RGN_BAS.sWiredLessDivideValue; // ex) WL
		// WORK.term_area
     // }
     // }
     
       // TbPfixRgnBasInfo tbPfixRgnBas =
		// audit.get_TB_PFIX_RGN_BAS("01029445555",
		// uBody.getsCallStartDateTime(), uBody.getsKeyVal());
       tbPfixRgnBas = audit.get_TB_PFIX_RGN_BAS("01029445555", uBody.getsCallStartDateTime(), uBody.getsKeyVal());
       
       if (tbPfixRgnBas != null ) {
        uBody.setsSettlFileName(tbPfixRgnBas.getsSettlFileName());
        uBody.setsSettlCarrier(tbPfixRgnBas.getsSettlCarrier());
        uBody.setsSettlFeatureCd(tbPfixRgnBas.getsSettlFeatureCd());
        uBody.setsCalledArea(tbPfixRgnBas.getsNpaAreaNo());
        uBody.setsChrgOtherNo(tbPfixRgnBas.getsChrgOtherNo());
        uBody.setsInNetCallYn(tbPfixRgnBas.getsInNetCallYn());
        uBody.setsTermArea(tbPfixRgnBas.getsWiredLessDivideValue());
    }
   }

     // Rule : tb_special_numbers_dom - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     
     if (uBody.sSpIndi.equals("Y") && uBody.bIsINTERNATIONAL) {
         uBody.bIsINTL_SERVICE_PROVIDER = true;
     }
     // if (enriched.getsSpIndi().equals("Y") &&
		// enriched.isbIsINTERNATIONAL())
     // enriched.setbIsINTL_SERVICE_PROVIDER(true);

     // Rule : country_code_001_same - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     
     // if (strstartsWith(uBody.sCalledSvcNo,"001") && !(uBody.bIsPPS_CALL ||
		// uBody.bIsDADAN_CALL)) {
     // TB_INTL_PFIX_BAS_Intl uTB_INTL_PFIX_BAS =
		// get_TB_INTL_PFIX_BAS(uBody.sChrgOtherNo);
     // if ( uTB_INTL_PFIX_BAS.lRowCnt > 0 ) {
     // uBody.sNationCd = uTB_INTL_PFIX_BAS.sIntlPrefixCd; // country_code
     // uBody.sSecondNationCd = uTB_INTL_PFIX_BAS.sSubIntlPrefixCd; //
		// fic_country_code
     // }
     // }
     
     if (uBody.getsCalledSvcNo().startsWith("001") && !(uBody.isbIsPPS_CALL() || uBody.isbIsDADAN_CALL())) {
   tbIntlPfixBas = audit.get_TB_INTL_PFIX_BAS(uBody.getsChrgOtherNo());
   
   if (tbIntlPfixBas != null ) {
    uBody.setsNationCd(tbIntlPfixBas.getsIntlPrefixCd());
    uBody.setsSecondNationCd(tbIntlPfixBas.getsSubIntlPrefixCd());
   }
  }

     // Rule : duration - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // if (uBody.bIsTIME_CDR || uBody.bIsTIME_CDR_001) {
     // strToInt(uBody.iUseTime, comnDateDiff(uBody.sCallEndDate +
		// uBody.sCallEndTime, enriched.getsCallStartDateTime(),
		// "yyyymmddhhmmss", "S","f"));
     // uBody.iDeductUseTime = 0;
     // }
      if (enriched.isbIsTIME_CDR() || enriched.isbIsTIME_CDR_001()) {
     // 미구현
      // strToInt(uBody.iUseTime, comnDateDiff(uBody.sCallEndDate +
		// uBody.sCallEndTime, enriched.getsCallStartDateTime(),
		// "yyyymmddhhmmss", "S","f"));
     // uBody.iDeductUseTime = 0;
      }  
     
     // Rule : format_id
     // uBody.sFormatId = sFileType;

     uBody.setsFormatId(uBody.getsFileType()); // ==> sFileType?
     // Rule : get_fps_open_date
     // TB_PROF_BAS_Intl uTB_PROF_BAS_1 = get_TB_PROF_BAS("FPS_OPEN_DATE",
		// "VOLTE", enriched.getsCallStartDateTime());

     // if (!(uTB_PROF_BAS_1 == null)) {
     // uBody.sProfileResult = uTB_PROF_BAS_1.sReturnValue;
     // uBody.sFpsResult = uTB_PROF_BAS_1.sReturnValue;
     // }  
  tmpTbProfBas = audit.get_TB_PROF_BAS("FPS_OPEN_DATE", "VOLTE", uBody.getsCallStartDateTime());
  
  if (tmpTbProfBas != null ) {
   uBody.setsProfileResult(tmpTbProfBas.getsReturnvalue());
   uBody.setsFpsResult(tmpTbProfBas.getsReturnvalue());
  }

     // Rule : get_vms_access_date
     // TB_PROF_BAS_Intl uTB_PROF_BAS_2 = get_TB_PROF_BAS(uBody.sVmsIdent,
		// uBody.sVmsDivision, enriched.getsCallStartDateTime());
  //
     // if (!(uTB_PROF_BAS_2 == null)) {
     // uBody.sProfileResult = uTB_PROF_BAS_2.sReturnValue;
     // uBody.sVmsResult = uTB_PROF_BAS_2.sReturnValue;
     // }
     tmpTbProfBas = audit.get_TB_PROF_BAS(uBody.getSvmsident(), uBody.getSvmsdivision(), uBody.getsCallStartDateTime());
     
     if (tmpTbProfBas != null ) {
      uBody.setsProfileResult(tmpTbProfBas.getsReturnvalue());
      uBody.setsVmsResult(tmpTbProfBas.getsReturnvalue());
     }
      
     // Rule : get_vms_feature_change_date
     // TB_PROF_BAS_Intl uTB_PROF_BAS_3 = get_TB_PROF_BAS(uBody.sIpasIdent,
		// uBody.sIpasDivision, enriched.getsCallStartDateTime());

     // if (!(uTB_PROF_BAS_3 == null)) {
     // uBody.sProfileResult = uTB_PROF_BAS_3.sReturnValue;
     // uBody.sIpasResult = uTB_PROF_BAS_3.sReturnValue;
     // }
     tmpTbProfBas = audit.get_TB_PROF_BAS(uBody.getSipasident(), uBody.getSipasdivision(), uBody.getsCallStartDateTime());
     
     if (tmpTbProfBas != null ) {
      uBody.setsProfileResult(tmpTbProfBas.getsReturnvalue());
      uBody.setsVmsResult(tmpTbProfBas.getsReturnvalue());
  }
      
     // Rule : not_sp_np_vc - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // if ((!(uBody.sSpIndi == "Y") && (uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL) && (uBody.bIsVOICE_CALL)) {

     // uBody.sNpSettProvider = uBody.sSettlCarrier;
     // uBody.sNpSettFeature = uBody.sSettlFeatureCd;

     // TB_NO_MOV_BIZR_BAS_Intl uTB_NO_MOV_BIZR_BAS =
		// getTB_NO_MOV_BIZR_BAS(uBody.sNpOperator,
		// enriched.getsCallStartDateTime(), uBody.sSpIndi, "ORG",
		// uBody.sChrgOtherNo);

     // if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_NO_MOV_BIZR_BAS.sSettlFileName;
		// //WORK.sett_fname
     // uBody.sSettlCarrier = uTB_NO_MOV_BIZR_BAS.sNpSettlCarrier;
		// //WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
		// //WORK.sett_feature
     // uBody.sInNetCallYn = uTB_NO_MOV_BIZR_BAS.sInNetCallYn;
		// //WORK.in_network_ind
     // }

     // }
     
   if ((!(uBody.getsSpIndi().equals("Y")) && (uBody.getsNpInd().equals("Y")) && !uBody.isbIsINTERNATIONAL()) && (uBody.isbIsVOICE_CALL())) {
    uBody.setsNpSettProvider(uBody.getsSettlCarrier());
    uBody.setsNpSettFeature(uBody.getsSettlFeatureCd());
    
    tbNoMovBizrBas = audit.get_TB_NO_MOV_BIZR_BAS(uBody.getsNpOperator(), uBody.getsCallStartDateTime(), uBody.getsSpIndi(), "ORG", uBody.getsChrgOtherNo());
    
    if (tbNoMovBizrBas != null ) {
     uBody.setsSettlFileName(tbNoMovBizrBas.getsSettlFileName());
     uBody.setsSettlCarrier(tbNoMovBizrBas.getsNpSettlCarrier());
     uBody.setsSettlFeatureCd(tbNoMovBizrBas.getsSettlFeatureCd());
     uBody.setsInNetCallYn(tbNoMovBizrBas.getsInNetCallYn());
       }
      }
     // Rule : not_sp_np_vs - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml

     // if ((!(uBody.sSpIndi == "Y") && (uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL) && uBody.bIsVISUAL_CALL) {
     // uBody.sNpSettProvider = uBody.sSettlCarrier;
     // uBody.sNpSettFeature = uBody.sSettlFeatureCd;

     // TB_NO_MOV_BIZR_BAS_Intl uTB_NO_MOV_BIZR_BAS =
		// getTB_NO_MOV_BIZR_BAS(uBody.sNpOperator,
		// enriched.getsCallStartDateTime(), uBody.sSpIndi, "OTR",
		// uBody.sChrgOtherNo);

     // if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_NO_MOV_BIZR_BAS.sSettlFileName;
		// //WORK.sett_fname
     // uBody.sSettlCarrier = uTB_NO_MOV_BIZR_BAS.sNpSettlCarrier;
		// //WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
		// //WORK.sett_feature
     // uBody.sInNetCallYn = uTB_NO_MOV_BIZR_BAS.sInNetCallYn;
		// //WORK.in_network_ind
     // }
     // }
     
      if ((!(uBody.getsSpIndi().equals("Y")) && (uBody.getsNpInd().equals("Y") && !uBody.isbIsINTERNATIONAL()) && uBody.isbIsVISUAL_CALL())) {
       uBody.setsNpSettProvider(uBody.getsSettlCarrier());
       uBody.setsNpSettFeature(uBody.getsSettlFeatureCd());
       
       tbNoMovBizrBas = audit.get_TB_NO_MOV_BIZR_BAS(uBody.getsNpOperator(), uBody.getsCallStartDateTime(), uBody.getsSpIndi(), "OTR", uBody.getsChrgOtherNo());
       
       if (tbNoMovBizrBas != null ) {
        uBody.setsSettlFileName(tbNoMovBizrBas.getsSettlFileName());
        uBody.setsSettlCarrier(tbNoMovBizrBas.getsNpSettlCarrier());
        uBody.setsSettlFeatureCd(tbNoMovBizrBas.getsSettlFeatureCd());
        uBody.setsInNetCallYn(tbNoMovBizrBas.getsInNetCallYn());
       }
      }

     // Rule : sp_not_np_vc - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     
     // if (((uBody.sSpIndi == "Y") && (!(uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL) && (uBody.bIsVOICE_CALL))) {
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_2 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
     // if (uTB_WLESS_SPCL_NO_BAS_2.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_WLESS_SPCL_NO_BAS_2.sSettlFileName;
     // uBody.sSettlFeatureCd = uTB_WLESS_SPCL_NO_BAS_2.sSettlFeatureCd; //
		// ORG 인 경우
     // uBody.sSettlCarrier = uTB_WLESS_SPCL_NO_BAS_2.sSettlCarrier;
     // uBody.sIntlNxx = uTB_WLESS_SPCL_NO_BAS_2.sNxxNo;
     // uBody.sInNetCallYn = uTB_WLESS_SPCL_NO_BAS_2.sInNetCallYn;
     // }
     // if (uBody.sInNetCallYn == null || uBody.sInNetCallYn == "") {
     // uBody.sInNetCallYn = "N";
     // }
     // }
   if (((uBody.getsSpIndi().equals("Y")) && (!(uBody.getsNpInd().equals("Y") && !uBody.isbIsINTERNATIONAL()) && uBody.isbIsVOICE_CALL()))) {
    tbWlessSpclNoBas2 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
    
    if (tbWlessSpclNoBas2 != null ) {
     uBody.setsSettlFileName(tbWlessSpclNoBas2.getsSettlFileName());
     uBody.setsSettlFeatureCd(tbWlessSpclNoBas2.getsSettlFeatureCd());
     uBody.setsSettlCarrier(tbWlessSpclNoBas2.getsSettlCarrier());
     uBody.setsIntlNxx(tbWlessSpclNoBas2.getsNxxNo());
     uBody.setsInNetCallYn(tbWlessSpclNoBas2.getsInNetCallYn());
    }
    if (uBody.getsInNetCallYn() == null || uBody.getsInNetCallYn().equals("")) {
     uBody.setsInNetCallYn("N");
    }
   }
     // Rule : sp_not_np_vs - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     
     // if (((uBody.sSpIndi == "Y") && (!(uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL) && (uBody.bIsVISUAL_CALL))) {
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_3 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
         
     // if (uTB_WLESS_SPCL_NO_BAS_3.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_WLESS_SPCL_NO_BAS_3.sSettlFileName;
     // uBody.sSettlFeatureCd = uTB_WLESS_SPCL_NO_BAS_3.sSettlFeatureType; //
		// "OTR"
     // uBody.sSettlCarrier = uTB_WLESS_SPCL_NO_BAS_3.sSettlCarrier;
     // uBody.sIntlNxx = uTB_WLESS_SPCL_NO_BAS_3.sNxxNo;
     // uBody.sInNetCallYn = uTB_WLESS_SPCL_NO_BAS_3.sInNetCallYn;
     // }

     // if (uBody.sInNetCallYn == null || uBody.sInNetCallYn == "") {
     // uBody.sInNetCallYn = "N";
     // }
     // }
      if (((uBody.getsSpIndi().equals("Y")) && (!(uBody.getsNpInd().equals("Y") && !uBody.isbIsINTERNATIONAL()) && uBody.isbIsVISUAL_CALL()))) {
    tbWlessSpclNoBas3 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
    
    if (tbWlessSpclNoBas3 != null ) {
     uBody.setsSettlFileName(tbWlessSpclNoBas3.getsSettlFileName());
     uBody.setsSettlFeatureCd(tbWlessSpclNoBas3.getsSettlFeatureCd());
     uBody.setsSettlCarrier(tbWlessSpclNoBas3.getsSettlCarrier());
     uBody.setsIntlNxx(tbWlessSpclNoBas3.getsNxxNo());
     uBody.setsInNetCallYn(tbWlessSpclNoBas3.getsInNetCallYn());
    }
    if (uBody.getsInNetCallYn() == null || uBody.getsInNetCallYn().equals("")) {
     uBody.setsInNetCallYn("N");
    }
   }
     // Rule : sp_np_vc - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     
     // if ((uBody.sSpIndi == "Y") && (uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL && (uBody.bIsVOICE_CALL)) {

     // uBody.sNpSettProvider = uBody.sSettlCarrier;
     // uBody.sNpSettFeature = uBody.sSettlFeatureCd;
         
     // TB_NO_MOV_BIZR_BAS_Intl uTB_NO_MOV_BIZR_BAS =
		// getTB_NO_MOV_BIZR_BAS(uBody.sNpOperator,
		// enriched.getsCallStartDateTime(), uBody.sSpIndi, "ORG",
		// uBody.sChrgOtherNo);

     // if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_NO_MOV_BIZR_BAS.sSettlFileName;
		// //WORK.sett_fname
     // uBody.sSettlCarrier = uTB_NO_MOV_BIZR_BAS.sNpSettlCarrier;
		// //WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
		// //WORK.sett_feature
     // uBody.sInNetCallYn = uTB_NO_MOV_BIZR_BAS.sInNetCallYn;
		// //WORK.in_network_ind
     // }
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_4 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
         
     // if (uTB_WLESS_SPCL_NO_BAS_4.lRowCnt > 0) {
     // uBody.sSettlFeatureCd = uTB_WLESS_SPCL_NO_BAS_4.sSettlFeatureType;
     // }
     
      if ((!(uBody.getsSpIndi().equals("Y")) && (uBody.getsNpInd().equals("Y") && !uBody.isbIsINTERNATIONAL()) && uBody.isbIsVOICE_CALL())) {
       uBody.setsNpSettProvider(uBody.getsSettlCarrier());
       uBody.setsNpSettFeature(uBody.getsSettlFeatureCd());
       
       tbNoMovBizrBas = audit.get_TB_NO_MOV_BIZR_BAS(uBody.getsNpOperator(), uBody.getsCallStartDateTime(), uBody.getsSpIndi(), "ORG", uBody.getsChrgOtherNo());
       
       if (tbNoMovBizrBas != null ) {
        uBody.setsSettlFileName(tbNoMovBizrBas.getsSettlFileName());
        uBody.setsSettlCarrier(tbNoMovBizrBas.getsNpSettlCarrier());
        uBody.setsSettlFeatureCd(tbNoMovBizrBas.getsSettlFeatureCd());
        uBody.setsInNetCallYn(tbNoMovBizrBas.getsInNetCallYn());
       }
       
       tbWlessSpclNoBas4 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
    
    if (tbWlessSpclNoBas4 != null ) {
     uBody.setsSettlFeatureCd(tbWlessSpclNoBas4.getsSettlFeatureCd());
    }
      }

     
     // Rule : sp_np_vc_no_mobile - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     
     // if ((uBody.sSpIndi == "Y") && (uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL && (uBody.bIsVOICE_CALL)) {
     // if (!(uBody.sCalledSvcNo == null || uBody.sCalledSvcNo == "") &&
		// (!(listFindIndex(LST_MOBILE_PREFIX, i, i ==
		// comnSubstring(uBody.sCalledSvcNo, 0, 3)) > -1))) {
     // uBody.sNpSettProvider = uBody.sSettlCarrier;
     // uBody.sNpSettFeature = uBody.sSettlFeatureCd;

     // TB_NO_MOV_BIZR_BAS_Intl uTB_NO_MOV_BIZR_BAS =
		// getTB_NO_MOV_BIZR_BAS(uBody.sNpOperator,
		// enriched.getsCallStartDateTime(), uBody.sSpIndi, "ORG",
		// uBody.sChrgOtherNo);

     // if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_NO_MOV_BIZR_BAS.sSettlFileName;
		// //WORK.sett_fname
     // uBody.sSettlCarrier = uTB_NO_MOV_BIZR_BAS.sNpSettlCarrier;
		// //WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
		// //WORK.sett_feature
     // uBody.sInNetCallYn = uTB_NO_MOV_BIZR_BAS.sInNetCallYn;
		// //WORK.in_network_ind
     // }

     // }
     // }
     
      if ((!(uBody.getsSpIndi().equals("Y")) && (uBody.getsNpInd().equals("Y") && !uBody.isbIsINTERNATIONAL()) && uBody.isbIsVOICE_CALL())) {
       if (!(uBody.getsCalledSvcNo() == null || uBody.getsCalledSvcNo().equals("")) && 
          (!(uBody.getLST_MOBILE_PREFIX().contains(comnSubstring(uBody.getsCalledSvcNo(),0,3))))) {
        uBody.setsNpSettProvider(uBody.getsSettlCarrier());
        uBody.setsNpSettFeature(uBody.getsSettlFeatureCd());
       
        tbNoMovBizrBas = audit.get_TB_NO_MOV_BIZR_BAS(uBody.getsNpOperator(), uBody.getsCallStartDateTime(), uBody.getsSpIndi(), "ORG", uBody.getsChrgOtherNo());
       
        if (tbNoMovBizrBas != null ) {
         uBody.setsSettlFileName(tbNoMovBizrBas.getsSettlFileName());
         uBody.setsSettlCarrier(tbNoMovBizrBas.getsNpSettlCarrier());
         uBody.setsSettlFeatureCd(tbNoMovBizrBas.getsSettlFeatureCd());
         uBody.setsInNetCallYn(tbNoMovBizrBas.getsInNetCallYn());
        }
       }
      }

     // Rule : sp_np_vs - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     
     // if ((uBody.sSpIndi == "Y") && (uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL && (uBody.bIsVISUAL_CALL)) {
     // uBody.sNpSettProvider = uBody.sSettlCarrier;
     // uBody.sNpSettFeature = uBody.sSettlFeatureCd;

     // TB_NO_MOV_BIZR_BAS_Intl uTB_NO_MOV_BIZR_BAS =
		// getTB_NO_MOV_BIZR_BAS(uBody.sNpOperator,
		// enriched.getsCallStartDateTime(), uBody.sSpIndi, "OTR",
		// uBody.sChrgOtherNo);

     // if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_NO_MOV_BIZR_BAS.sSettlFileName;
		// //WORK.sett_fname
     // uBody.sSettlCarrier = uTB_NO_MOV_BIZR_BAS.sNpSettlCarrier;
		// //WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
		// //WORK.sett_feature
     // uBody.sInNetCallYn = uTB_NO_MOV_BIZR_BAS.sInNetCallYn;
		// //WORK.in_network_ind
     // }
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_5 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
         
     // if (uTB_WLESS_SPCL_NO_BAS_5.lRowCnt > 0) {
     // uBody.sSettlFeatureCd = uTB_WLESS_SPCL_NO_BAS_5.sSettlFeatureType;
     // }

     // }
      if ((uBody.getsSpIndi().equals("Y")) && (uBody.getsNpInd().equals("Y")) && !uBody.isbIsINTERNATIONAL() && uBody.isbIsVISUAL_CALL()) {
       uBody.setsNpSettProvider(uBody.getsSettlCarrier());
       uBody.setsNpSettFeature(uBody.getsSettlFeatureCd());
       
       tbNoMovBizrBas = audit.get_TB_NO_MOV_BIZR_BAS(uBody.getsNpOperator(), uBody.getsCallStartDateTime(), uBody.getsSpIndi(), "ORG", uBody.getsChrgOtherNo());
       
       if (tbNoMovBizrBas != null ) {
        uBody.setsSettlFileName(tbNoMovBizrBas.getsSettlFileName());
        uBody.setsSettlCarrier(tbNoMovBizrBas.getsNpSettlCarrier());
        uBody.setsSettlFeatureCd(tbNoMovBizrBas.getsSettlFeatureCd());
        uBody.setsInNetCallYn(tbNoMovBizrBas.getsInNetCallYn());
       }
      }

     // Rule : sp_np_vs_no_mobile - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     
     // if ((uBody.sSpIndi == "Y") && (uBody.sNpInd == "Y") &&
		// !uBody.bIsINTERNATIONAL && (uBody.bIsVISUAL_CALL)) {
     // if (!(uBody.sCalledSvcNo == null || uBody.sCalledSvcNo == "") &&
		// (!(listFindIndex(LST_MOBILE_PREFIX, i, i ==
		// comnSubstring(uBody.sCalledSvcNo, 0, 3)) > -1))) {
     // uBody.sNpSettProvider = uBody.sSettlCarrier;
     // uBody.sNpSettFeature = uBody.sSettlFeatureCd;

     // TB_NO_MOV_BIZR_BAS_Intl uTB_NO_MOV_BIZR_BAS =
		// getTB_NO_MOV_BIZR_BAS(uBody.sNpOperator,
		// enriched.getsCallStartDateTime(), uBody.sSpIndi, "OTR",
		// uBody.sChrgOtherNo);

     // if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_NO_MOV_BIZR_BAS.sSettlFileName;
		// //WORK.sett_fname
     // uBody.sSettlCarrier = uTB_NO_MOV_BIZR_BAS.sNpSettlCarrier;
		// //WORK.sett_provider
     // uBody.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
		// //WORK.sett_feature
     // uBody.sInNetCallYn = uTB_NO_MOV_BIZR_BAS.sInNetCallYn;
		// //WORK.in_network_ind
     // }
     // }
     // }
      if ((uBody.getsSpIndi().equals("Y")) && (uBody.getsNpInd().equals("Y")) && !uBody.isbIsINTERNATIONAL() && uBody.isbIsVISUAL_CALL()) {
       if (!(uBody.getsCalledSvcNo() == null || uBody.getsCalledSvcNo().equals("")) && (!(uBody.getLST_MOBILE_PREFIX().contains(comnSubstring(uBody.getsCalledSvcNo(),0,3))))) {
        uBody.setsNpSettProvider(uBody.getsSettlCarrier());
        uBody.setsNpSettFeature(uBody.getsSettlFeatureCd());
       
        tbNoMovBizrBas = audit.get_TB_NO_MOV_BIZR_BAS(uBody.getsNpOperator(), uBody.getsCallStartDateTime(), uBody.getsSpIndi(), "ORG", uBody.getsChrgOtherNo());
       
        if (tbNoMovBizrBas != null ) {
         uBody.setsSettlFileName(tbNoMovBizrBas.getsSettlFileName());
         uBody.setsSettlCarrier(tbNoMovBizrBas.getsNpSettlCarrier());
         uBody.setsSettlFeatureCd(tbNoMovBizrBas.getsSettlFeatureCd());
         uBody.setsInNetCallYn(tbNoMovBizrBas.getsInNetCallYn());
        }
       }
      }

     // Rule : special_number_kt114
     // if (uBody.sSpIndi == "Y") {
     
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_6 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
         
     // if (uTB_WLESS_SPCL_NO_BAS_6.lRowCnt > 0) {
     // uBody.sProdCd = uTB_WLESS_SPCL_NO_BAS_6.sFeatureCd;
     // }
     // }
     
     if (uBody.getsSpIndi().equals("Y")) {
      
      tbWlessSpclNoBas6 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
   
   if (tbWlessSpclNoBas6 != null )
    uBody.setsProdCd(tbWlessSpclNoBas6.getsFeatureCd());
  }
     // Rule : tb_calling_numbers_dom
     // if (uBody.sTermCode == CDF_NORM_V) {
     // TB_CAL_NO_BAS_Intl uTB_CAL_NO_BAS =
		// get_TB_CAL_NO_BAS(strSubstring(uBody.sChrgSvcNo, 0, 3),
		// strSubstring(uBody.sChrgSvcNo, 3, 7), strSubstring(uBody.sChrgSvcNo,
		// 7, 11), enriched.getsCallStartDateTime());
         
     // if (uTB_CAL_NO_BAS.lRowCnt > 0) {
     // uBody.sCallingDropIndi = "Y";
     // }
     // }
     
     if (uBody.getsTermCode().equals(uBody.getCdfNormV())) {
      tbCalNoBas = audit.get_TB_CAL_NO_BAS(uBody.getsChrgSvcNo().substring(0, 3), uBody.getsChrgSvcNo().substring(3, 7), uBody.getsChrgSvcNo().substring(7, 11), uBody.getsCallStartDateTime());
      
      if (tbCalNoBas != null )
       uBody.setsCallingDropIndi("Y");
  }
   
     // Rule : tb_maf_features_dom1
     // if (uBody.sTermCode == CDF_NORM_V) {

     // if (uBody.sProdFromatId != null && uBody.sProdCdType != null &&
		// uBody.sSscode != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sProdCdType,
		// uBody.sSscode, enriched.getsCallStartDateTime());
     // if (uTB_FEATR_BAS.lRowCnt > 0) {
     // uBody.sProdCdIndi = "Y";
     // uBody.sProdDropCall = "Y";
     // }
     // }
     // }
      if (uBody.getsTermCode().equals(uBody.getCdfNormV())) {
       if ( !( uBody.getsProdFormatId() == null || uBody.getsProdFormatId().equals("") ) && !( uBody.getsProdCdType() == null || uBody.getsProdCdType().equals("") ) && !( uBody.getsSscode() == null || uBody.getsSscode().equals("") )) {
 
        tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsProdCdType(), uBody.getsSscode(), uBody.getsCallStartDateTime());
        if (tbFeatrBas != null ) {
         uBody.setsProdCdIndi("Y");
         uBody.setsProdDropCall("Y");
        }
       }
      }
   
   
     // Rule : tb_maf_features_dom2 - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     
     // boolean bExistFeatures;
     // if (uBody.sProdFromatId != null && uBody.sProdCdType != null &&
		// uBody.sSscode != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sProdCdType,
		// uBody.sSscode,sCallStartDateTime);
     // if (uTB_FEATR_BAS.lRowCnt > 0) {
     // bExistFeatures = true;
     // }
     // }
      var bExistFeatures=false;
      if ( !( uBody.getsProdFormatId() == null || uBody.getsProdFormatId().equals("") ) && !( uBody.getsProdCdType() == null || uBody.getsProdCdType().equals("") ) && !( uBody.getsSscode() == null || uBody.getsSscode().equals("") )) {
     
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsProdCdType(), uBody.getsSscode(), uBody.getsCallStartDateTime());
       
       if (tbFeatrBas != null )
        bExistFeatures = true;
      }
      

     // if ((uBody.sTermCode == CDF_NORM_V) && !(uBody.sSscode == "" ||
		// uBody.sSscode == null) &&
     // !(listFindIndex(LST_SSCODE_VMS, i, i == uBody.sSscode) > -1) &&
     // !(uBody.sSscode == SSCODE_GC && uBody.bIsVOLTE_4G_CALL) &&
		// !(uBody.sSscode == SSCODE_TWONUMBER) &&
     // !(uBody.sSscode == SSCODE_FREE_ZONE && (uBody.bIsVOLTE_4G_CALL ||
		// uBody.bIsPSVT_3G_CALL || uBody.bIsPSVT_4G_CALL )) &&
		// !uBody.bIsEMS_CALLED && !bExistFeatures) {

     // uBody.sProdCdIndi = "E";
     // }
      if ((uBody.getsTermCode().equals(uBody.getCdfNormV())) && !(uBody.getsSscode().equals("") || uBody.getsSscode() == null) &&
       !(uBody.getLST_SSCODE_VMS().contains(uBody.getsSscode())) &&
       !(uBody.getsSscode().equals(uBody.getSscodeGc()) && uBody.isbIsVOLTE_4G_CALL()) && 
       !(uBody.getsSscode().equals(uBody.getSscodeTwonumber())) &&
       !(uBody.getsSscode().equals(uBody.getSscodeFreeZone())) && 
       (uBody.isbIsVOLTE_4G_CALL() || uBody.isbIsPSVT_3G_CALL() || uBody.isbIsPSVT_4G_CALL()) &&
       !uBody.isbIsEMS_CALLED() && !bExistFeatures)
       uBody.setsProdCdIndi("E");
       

     // Rule : tb_special_numbers_indicator
     // if (uBody.sSpIndi == "Y") {
      
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_7 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), uBody.sSpNumberType);;
     // if (uTB_WLESS_SPCL_NO_BAS_7.lRowCnt > 0) {
     // uBody.sSpnAcInd = uTB_WLESS_SPCL_NO_BAS_7.sAirtimeChrgYn; //
		// WORK.spn_ac_ind
     // uBody.sSpNoAtIndi = uTB_WLESS_SPCL_NO_BAS_7.sAirtimeIndi; //
		// WORK.spn_at_ind
     // uBody.sSpnSpInd = uTB_WLESS_SPCL_NO_BAS_7.sIspYn; // WORK.spn_sp_ind
     // uBody.sSpnDpClInd = uTB_WLESS_SPCL_NO_BAS_7.sFeatureDropIndi; //
		// WORK.spn_dp_cl_ind
     // uBody.sSpnPpsDpClInd = uTB_WLESS_SPCL_NO_BAS_7.sPpsDropIndi; //
		// WORK.spn_pps_dp_cl_ind
     // uBody.sSpnAtFtrInd = uTB_WLESS_SPCL_NO_BAS_7.sAirtimeFuncYn; //
		// WORK.spn_at_ftr_ind
     // uBody.sSpnSpNmTp = uTB_WLESS_SPCL_NO_BAS_7.sSpecialNoType; //
		// WORK.spn_sp_nm_tp
     // }
     // }
      if (uBody.getsSpIndi().equals("Y")) {
       tbWlessSpclNoBas7 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getsSpNumberType());
       
       if (tbWlessSpclNoBas7 != null ) {
        uBody.setsSpnAcInd(tbWlessSpclNoBas7.getsAirtimeChrgYn());
        uBody.setsSpNoAtIndi(tbWlessSpclNoBas7.getsAirtimeIndi());
        uBody.setsSpnSpInd(tbWlessSpclNoBas7.getsIspYn());
        uBody.setsSpnDpClInd(tbWlessSpclNoBas7.getsFeatureDropIndi());
        uBody.setsSpnPpsDpClInd(tbWlessSpclNoBas7.getsPpsDropIndi());
        uBody.setsSpnAtFtrInd(tbWlessSpclNoBas7.getsAirtimeFuncYn());
        uBody.setsSpnSpNmTp(tbWlessSpclNoBas7.getsSpecialNoType());
       }
      }
      

     // Rule : tb_special_numbers_settinfo_vc
     // if ((uBody.sSpIndi == "Y") && (uBody.bIsVOICE_CALL)) {
     
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_8 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
         
     // if (uTB_WLESS_SPCL_NO_BAS_8.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_WLESS_SPCL_NO_BAS_8.sSettlFileName;
     // uBody.sSettlFeatureCd = uTB_WLESS_SPCL_NO_BAS_8.sSettlFeatureCd; //
		// ORG 인 경우
     // uBody.sSettlCarrier = uTB_WLESS_SPCL_NO_BAS_8.sSettlCarrier;
     // uBody.sIntlNxx = uTB_WLESS_SPCL_NO_BAS_8.sNxxNo;
     // }
     // }
     
      if (uBody.getsSpIndi().equals("Y") && uBody.isbIsVOICE_CALL()) {
       tbWlessSpclNoBas8 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
       
       if (tbWlessSpclNoBas8 != null ) {
        uBody.setsSettlFileName(tbWlessSpclNoBas8.getsSettlFileName());
        uBody.setsSettlFeatureCd(tbWlessSpclNoBas8.getsSettlFeatureCd());
        uBody.setsSettlCarrier(tbWlessSpclNoBas8.getsSettlCarrier());
        uBody.setsIntlNxx(tbWlessSpclNoBas8.getsIntlNxx());
       }
      }     
        

     // Rule : tb_special_numbers_settinfo_vs
     // if ((uBody.sSpIndi == "Y") && (uBody.bIsVISUAL_CALL)) {
     
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_9 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", enriched.getsCallStartDateTime(), SPNM);
         
     // if (uTB_WLESS_SPCL_NO_BAS_9.lRowCnt > 0) {
     // uBody.sSettlFileName = uTB_WLESS_SPCL_NO_BAS_9.sSettlFileName;
     // uBody.sSettlFeatureCd = uTB_WLESS_SPCL_NO_BAS_9.sSettlFeatureType; //
		// OTR 인 경우
     // uBody.sSettlCarrier = uTB_WLESS_SPCL_NO_BAS_9.sSettlCarrier;
     // uBody.sIntlNxx = uTB_WLESS_SPCL_NO_BAS_9.sNxxNo;
     // }
     // }
      if (uBody.getsSpIndi().equals("Y") && uBody.isbIsVISUAL_CALL()) {
       tbWlessSpclNoBas9 = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
       
       if (tbWlessSpclNoBas9 != null ) {
        uBody.setsSettlFileName(tbWlessSpclNoBas9.getsSettlFileName());
        uBody.setsSettlFeatureCd(tbWlessSpclNoBas9.getsSettlFeatureCd());
        uBody.setsSettlCarrier(tbWlessSpclNoBas9.getsSettlCarrier());
        uBody.setsIntlNxx(tbWlessSpclNoBas9.getsIntlNxx());
       }
      }

     // Rule : tb_special_numbers_zzz
     // if (uBody.sSpIndi == "Y") {
     // if (uBody.sSpnSpNmTp == "S") {
     // uBody.sCalledPatryPayCall = "Y";
     // uBody.sChrgSvcNo = wlssEditTelNo(uBody.sCalledSvcNo, 11);
     // }
     // }
     if (uBody.getsSpIndi().equals("Y")) {
      if (uBody.getsSpnSpNmTp().equals("S")) {
       uBody.setsCalledPartyPayCall("Y");
       uBody.setsChrgSvcNo(uBody.wlssEditTelNo(uBody.getsCalledSvcNo(), 11));
      }
     }

   // Rule : add_ftr_wstype
     // if (uBody.sProdFromatId != null && uBody.sInsProdType != null &&
		// uBody.sWinSvcType != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sInsProdType,
		// uBody.sWinSvcType, sCallStartDateTime);
  //
     // if (uTB_FEATR_BAS.lRowCnt > 0) {
     // uBody.sFeatureDropIndi = uTB_FEATR_BAS.sFeatureDropIndi; //
		// chk_wstype Validation 에서 사용하기 위해 추가.
     // uBody.bIsWSTYPE_RANGE = true; // win_chk_service_not_found Validation
		// 에서 사용하기 위해 추가.
  //
     // if (!(uTB_FEATR_BAS.sFeatureDropIndi == "Y")) {
     // string sFeatureAdd = uTB_FEATR_BAS.sFeatureCd;
     // if (!(sFeatureAdd == "ZZZZZZ")) {
     // uBody.sMpsFeatureCd = comnPadChr(sFeatureAdd, 6, " ", "R");
     // }
     // }
     // }
     // }
      if ( !( uBody.getsProdFormatId() == null || uBody.getsProdFormatId().equals("") ) && !( uBody.getsInsProdType() == null || uBody.getsInsProdType().equals("") ) && !( uBody.getsWinSvcType() == null || uBody.getsWinSvcType().equals("") )) {
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsInsProdType(), uBody.getsWinSvcType(), uBody.getsCallStartDateTime());
       
       if (tbFeatrBas != null ) {
        uBody.setsFeatureDropIndi(tbFeatrBas.getsFeatureDropIndi());
        uBody.setbIsWSTYPE_RANGE(true);
        
        if (!tbFeatrBas.getsFeatureDropIndi().equals("Y")) {
         var sFeatureAdd = tbFeatrBas.getsFeatureCd();
         
         if (!(sFeatureAdd.equals("ZZZZZZ"))) {
          uBody.setsMpsFeatureCd(comnPadChr(sFeatureAdd, 6, " ", "R"));
         }
        }
       }
      }      
      
     // Rule : at_call_dur_sec - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // if (uBody.bIsTIME_CDR || uBody.bIsTIME_CDR_001) {
     // uBody.iDurSecPrepaid = uBody.service_prepaid_airtime_charge +
		// uBody.service_prepaid_information_charge;
     // } else if (uBody.bIsOCR_CDR || uBody.bIsOCR_CDR_001) {
     // uBody.iDurSecPrepaid = 1;
     // }
     if (uBody.isbIsTIME_CDR() || uBody.isbIsTIME_CDR_001()) {
    	 uBody.setiDurSecPrepaid(Integer.parseInt(uBody.getService_prepaid_airtime_charge().equals("") ? "0" : uBody.getService_prepaid_airtime_charge()) + Integer.parseInt(uBody.getService_prepaid_information_charge().equals("") ? "0" : uBody.getService_prepaid_information_charge()));
     } else if (uBody.isbIsOCR_CDR() || uBody.isbIsOCR_CDR_001()) {
      uBody.setiDurSecPrepaid(1);
     } 
      
     // if (uBody.sCalledPatryPayCall == "Y") {
     // uBody.bIsCALLED_PARTY_PAY_CALL = true;
     // }
     // else {
     // uBody.bIsCALLED_PARTY_PAY_CALL = false;
     // }
     if (uBody.getsCalledPartyPayCall().equals("Y")) {
      uBody.setbIsCALLED_PARTY_PAY_CALL(true);
     } else {
      uBody.setbIsCALLED_PARTY_PAY_CALL(false);
     }

     // Rule : call_to_tn_cal_prt_pay
     // if (uBody.bIsCALLED_PARTY_PAY_CALL) {
     // uBody.sWorkCalledSvcNo = uBody.sCallingSvcNo;
     // }
     if (uBody.isbIsCALLED_PARTY_PAY_CALL()) {
      uBody.setsWorkCalledSvcNo(uBody.getsCallingSvcNo());
     }

     // Rule : call_to_tn_int - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml -
		// 신규
     
     // if (uBody.bIsINTERNATIONAL && !uBody.bIsINTL_SERVICE_PROVIDER) {
     // if (uBody.sSettlFileName == "INN") {
     // uBody.sWorkCalledSvcNo = comnSubstring(uBody.sCalledSvcNo,5,17);
     // } else {
     // uBody.sWorkCalledSvcNo = comnSubstring(uBody.sCalledSvcNo,3,18);
     // }
     // }
     if (uBody.isbIsINTERNATIONAL() && !uBody.isbIsINTL_SERVICE_PROVIDER()) {
      if (uBody.getsSettlFileName().equals("INN")) {
       uBody.setsWorkCalledSvcNo(comnSubstring(uBody.getsCalledSvcNo(), 5, 17));
      } else {
       uBody.setsWorkCalledSvcNo(comnSubstring(uBody.getsCalledSvcNo(), 3, 18));
      }
     }
     
     
     // Rule : call_to_tn_normal - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     
     // if (!uBody.bIsINTERNATIONAL && !uBody.bIsCALLED_PARTY_PAY_CALL) {
     // uBody.sWorkCalledSvcNo = comnSubstring(uBody.sCalledSvcNo, 0, 14);
     // }
     if (!uBody.isbIsINTERNATIONAL() && !uBody.isbIsCALLED_PARTY_PAY_CALL()) {
      uBody.setsWorkCalledSvcNo(comnSubstring(uBody.getsCalledSvcNo(), 0, 14));
     }


     // Rule : country_code - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml -
		// 신규
     
     // if (uBody.bIsINTERNATIONAL && (uBody.sSpIndi == "N") && (
		// !(uBody.sCalledArea == null || uBody.sCalledArea.equals("") ) &&
		// uBody.sCalledArea != "")){
     // TB_INTL_PFIX_BAS_Intl uTB_INTL_PFIX_BAS =
		// get_TB_INTL_PFIX_BAS(uBody.sChrgOtherNo);
     // if ( uTB_INTL_PFIX_BAS.lRowCnt > 0 ) {
     // uBody.sNationCd = uTB_INTL_PFIX_BAS.sIntlPrefixCd; // country_code
     // uBody.sSecondNationCd = uTB_INTL_PFIX_BAS.sSubIntlPrefixCd; //
		// fic_country_code
     // }
     // }

     if (uBody.isbIsINTERNATIONAL() && uBody.getsSpIndi().equals("N") && uBody.getsCalledArea() != null && !uBody.getsCalledArea().equals("")) {
      
      tbIntlPfixBas = audit.get_TB_INTL_PFIX_BAS(uBody.getsChrgOtherNo());
      
      if (tbIntlPfixBas != null ) {
       uBody.setsNationCd(tbIntlPfixBas.getsIntlPrefixCd());
       uBody.setsSecondNationCd(tbIntlPfixBas.getsSubIntlPrefixCd());
      }
     }

     // Rule : customer_call_feature
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml
     // if ( (comnStrLength(uBody.sChrgOtherNo) == 3 && uBody.sChrgOtherNo ==
		// "100" ) || ((comnStrTrim(uBody.sChrgOtherNo) == "") &&
		// comnSubstring(uBody.sCalledArea,1,4) == "100")) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_1 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, "100", "", sCallStartDateTime);

     // if (uTB_FEATR_BAS_1.lRowCnt > 0) {
     // uBody.sMpsFeatureCd = comnPadChr(uTB_FEATR_BAS_1.sFeatureCd, 6, " ",
		// "R");
     // }
     // }
     if (uBody.getsChrgOtherNo().length() == 3 && uBody.getsChrgOtherNo().equals("100") || 
        (uBody.getsChrgOtherNo().trim().equals("") && comnSubstring(uBody.getsCalledArea(), 1,4).equals("100"))) {
        
         tbFeatrBas1 = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), "100", "", uBody.getsCallStartDateTime());
        
         if (tbFeatrBas1 != null ) {
          uBody.setsMpsFeatureCd(comnPadChr(tbFeatrBas1.getsFeatureCd(), 6, " ", "R"));
         }
     }
         

     // Rule : feature_add_5country - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     
     // if (uBody.sProdFromatId != null && uBody.sSecondNationCd != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_1 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, "100", uBody.sSecondNationCd,
		// sCallStartDateTime);

     // if (uTB_FEATR_BAS_1.lRowCnt > 0) {
     // uBody.bIsSAME_5COUNTRY = true;
     // }

     // if (uBody.bIsSAME_5COUNTRY && !(uBody.bIsPPS_CALL ||
		// uBody.bIsDADAN_CALL)) {
     // uBody.sMpsFeatureCd = comnPadChr(uTB_FEATR_BAS_1.sFeatureCd, 6, " ",
		// "R");
     // }
     // }
   if ( !(uBody.getsProdFormatId() == null || uBody.getsProdFormatId().equals("") ) && !( uBody.getsSecondNationCd() == null || uBody.getsSecondNationCd().equals("") )) {
    tbFeatrBas1 = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), "100", uBody.getsSecondNationCd(), uBody.getsCallStartDateTime());
    
    if (tbFeatrBas1 != null ) {
     uBody.setbIsSAME_5COUNTRY(true);
    }
    
    if (uBody.isbIsSAME_5COUNTRY() && !(uBody.isbIsPPS_CALL() || uBody.isbIsDADAN_CALL())) {
     uBody.setsMpsFeatureCd(comnPadChr(tbFeatrBas1.getsFeatureCd(), 6, " ", "R"));
    }
   }

     // Rule : feature_add_except_5country - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     
     // if ((uBody.sCalledSvcNo != null &&
		// strstartsWith(uBody.sCalledSvcNo,"001")) && !uBody.bIsSAME_5COUNTRY
		// && !(uBody.bIsPPS_CALL || uBody.bIsDADAN_CALL)) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_2 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, "001", "", sCallStartDateTime);
     // if (uTB_FEATR_BAS_2.lRowCnt > 0) {
     // uBody.sMpsFeatureCd = comnPadChr(uTB_FEATR_BAS_2.sFeatureCd, 6, " ",
		// "R");
     // }
     // }
      if ((!( uBody.getsCalledSvcNo() == null || uBody.getsCalledSvcNo().equals("") ) && uBody.getsCalledSvcNo().startsWith("001")) && !uBody.isbIsSAME_5COUNTRY() && !(uBody.isbIsPPS_CALL() || uBody.isbIsDADAN_CALL())) {
       tbFeatrBas2 = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), "100", "", uBody.getsCallStartDateTime());
       
       if (tbFeatrBas2 != null ) {
        uBody.setsMpsFeatureCd(comnPadChr(tbFeatrBas2.getsFeatureCd(), 6, " ", "R"));
       }
      }

     // Rule : feature_add_im - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml -
		// 신규
     
     // if (uBody.bIsIM_3G || uBody.bIsIM_4G) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_3 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, "IM",
		// uBody.service_category_id, sCallStartDateTime);

     // if (uTB_FEATR_BAS_3.lRowCnt > 0) {
     // uBody.sMpsFeatureCd = comnPadChr(uTB_FEATR_BAS_3.sFeatureCd, 6, " ",
		// "R");
     // }
     // }
     
      if (uBody.isbIsIM_3G() || uBody.isbIsIM_4G()) {
       tbFeatrBas3 = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), "IM", uBody.getService_category_id(), uBody.getsCallStartDateTime());

    if (tbFeatrBas3 != null ) {
     uBody.setsMpsFeatureCd(comnPadChr(tbFeatrBas3.getsFeatureCd(), 6, " ", "R"));
    }
   }
     // Rule : feature_add_mpbx (신규)- DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // if ((uBody.sTermCode == CDF_NORM_V) && (uBody.sSscode ==
		// SSCODE_MPBX_FREE) && uBody.bIsVOLTE_4G_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VCMPBX";
     // }
     
      if ((uBody.getsTermCode().equals(uBody.getCdfNormV())) && (uBody.getsSscode().equals(uBody.getSscodeMpbxFree()) && uBody.isbIsVOLTE_4G_CALL())) {
       uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VCMPBX");
      }

     // DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // Rule 현행화(변경사항 적용) : feature_add_psvt_3g, feature_add_psvt_4g,
		// feature_add_volte_3g_hd,
     // feature_add_volte_4g_hd, feature_add_volte_hd,
		// feature_add_volte_not_hd
     // if (listFindIndex(LST_SSCODE_MPBX, i, i == uBody.sSscode) > -1) {
     // uBody.bIsSSCODE_MPBX = true;
     // }
      if (uBody.getLST_SSCODE_MPBX().contains(uBody.getsSscode())) {
       uBody.setbIsSSCODE_MPBX(true);
      }
      

     // Rule : feature_add_psvt_3g
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // -- PSVT_3G_CALL : "622" / FPS_CALL : "004"
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // -- VOLTE.PSVT_3G_CALL AND (WORK.sscode is SPACE or WORK.sscode is
		// VOLTE.SSCODE_TWONUMBER OR VOLTE.EMS_CALLED OR VOLTE.SSCODE_MPBX) AND
		// NOT(VOLTE.FPS_CALL)
     // if (uBody.bIsPSVT_3G_CALL && (comnStrTrim(uBody.sSscode) == "" ||
		// uBody.sSscode == SSCODE_TWONUMBER || uBody.bIsEMS_CALLED ||
		// uBody.bIsSSCODE_MPBX) && !uBody.bIsFPS_CALL) {
     // if ((uBody.hd_call == "1") && (uBody.sInNetCallYn == "Y")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2LK";
     // }
     // else if ((uBody.hd_call == "1") && (uBody.sInNetCallYn == "N") &&
     // (comnSubstring(uBody.terminating_inter_operator_identifier,0,8) !=
		// "octave.c")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2LO";
     // }
     // else if ((uBody.hd_call == "1") && (uBody.sInNetCallYn == "N") &&
     // (comnSubstring(uBody.terminating_inter_operator_identifier,0,8) ==
		// "octave.c")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVW2OT";
     // }
     // else if ((uBody.hd_call == "0") && (uBody.sInNetCallYn == "Y")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVW2KT";
     // }
     // else if ((uBody.hd_call == "0") && (uBody.sInNetCallYn == "N")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVW2OT";
     // }
     // }
      if (uBody.isbIsPSVT_3G_CALL() && (uBody.getsSscode().trim().equals("") || uBody.getsSscode().equals(uBody.getSscodeTwonumber()) || uBody.isbIsEMS_CALLED() || uBody.isbIsSSCODE_MPBX()) && !uBody.isbIsFPS_CALL()) {
       if (uBody.getHd_call().equals("1") && uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVL2LK");
       } else if (uBody.getHd_call().equals("1") && uBody.getsInNetCallYn().equals("N") && 
           (!comnSubstring(uBody.getTerminating_inter_operator_identifier(),0,8).equals("octave.c"))) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVW2LO");
       } else if (uBody.getHd_call().equals("1") && uBody.getsInNetCallYn().equals("Y") && 
           (comnSubstring(uBody.getTerminating_inter_operator_identifier(),0,8).equals("octave.c"))) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVW2OT");
       } else if (uBody.getHd_call().equals("0") && uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVW2KT");
       } else if (uBody.getHd_call().equals("0") && uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVW2OT");
       }
      }

     // Rule : feature_add_psvt_4g
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // -- VOLTE.PSVT_4G_CALL AND (WORK.sscode is SPACE or WORK.sscode is
		// VOLTE.SSCODE_TWONUMBER OR VOLTE.EMS_CALLED OR VOLTE.SSCODE_MPBX) AND
		// NOT(VOLTE.FPS_CALL)
     // if (uBody.bIsPSVT_4G_CALL && (comnStrTrim(uBody.sSscode) == "" ||
		// uBody.sSscode == SSCODE_TWONUMBER || uBody.bIsEMS_CALLED ||
		// uBody.bIsSSCODE_MPBX ) && !uBody.bIsFPS_CALL) {
     // if ((uBody.hd_call == "1") && (uBody.sInNetCallYn == "Y")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2LK";
     // }
     // else if ((uBody.hd_call == "1") && (uBody.sInNetCallYn == "N") &&
     // (comnSubstring(uBody.terminating_inter_operator_identifier,0,8) !=
		// "octave.c")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2LO";
     // }
     // else if ((uBody.hd_call == "1") && (uBody.sInNetCallYn == "N") &&
     // (comnSubstring(uBody.terminating_inter_operator_identifier,0,8) ==
		// "octave.c")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2OT";
     // }
     // else if ((uBody.hd_call == "0") && (uBody.sInNetCallYn == "Y")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2KT";
     // }
     // else if ((uBody.hd_call == "0") && (uBody.sInNetCallYn == "N")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "PVL2OT";
     // }
     // }
      if (uBody.isbIsPSVT_4G_CALL() && (uBody.getsSscode().trim().equals("") || uBody.getsSscode().equals(uBody.getSscodeTwonumber()) || uBody.isbIsEMS_CALLED() || uBody.isbIsSSCODE_MPBX()) && !uBody.isbIsFPS_CALL()) {
       if (uBody.getHd_call().equals("1") && uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVL2LK");
       } else if (uBody.getHd_call().equals("1") && uBody.getsInNetCallYn().equals("N") && 
           (!comnSubstring(uBody.getTerminating_inter_operator_identifier(), 0,8).equals("octave.c"))) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVL2LO");
       } else if (uBody.getHd_call().equals("1") && uBody.getsInNetCallYn().equals("Y") && 
           (comnSubstring(uBody.getTerminating_inter_operator_identifier(), 0,8).equals("octave.c"))) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVL2OT");
       } else if (uBody.getHd_call().equals("0") && uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVL2KT");
       } else if (uBody.getHd_call().equals("0") && uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "PVL2OT");
       }
      }


   // feature_add_volte_3g_hd
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     
     // if ((uBody.bIsHD_3G_VOICE) && (comnStrTrim(uBody.sSscode) == "" ||
		// uBody.sSscode == SSCODE_TWONUMBER || uBody.bIsEMS_CALLED ||
		// uBody.bIsSSCODE_MPBX) && !(uBody.bIsFPS_CALL)) {
     // if (uBody.sInNetCallYn == "" || uBody.sInNetCallYn == null) {
     // uBody.sInNetCallYn = "N";
     // }
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2KT";
     // } else if (uBody.sInNetCallYn == "N") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2OT";
     // }
     // }
     
      if ((uBody.isbIsHD_3G_VOICE()) && (uBody.getsSscode().trim().equals("") || uBody.getsSscode().trim().equals(uBody.getSscodeTwonumber()) || uBody.isbIsEMS_CALLED() || uBody.isbIsSSCODE_MPBX()) && !(uBody.isbIsFPS_CALL())) {
       if (uBody.getsInNetCallYn().equals("") || uBody.getsInNetCallYn() == null) {
        uBody.setsInNetCallYn("N");
       }
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2OT");
       }
      }

     // feature_add_volte_4g_hd
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // if ((uBody.bIsHD_4G_VOICE) && (comnStrTrim(uBody.sSscode) == "" ||
		// uBody.sSscode == SSCODE_TWONUMBER || uBody.bIsEMS_CALLED ||
		// uBody.bIsSSCODE_MPBX) && !(uBody.bIsFPS_CALL)) {
     // if (uBody.sInNetCallYn == "" || uBody.sInNetCallYn == null) {
     // uBody.sInNetCallYn = "N";
     // }
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2LK";
     // } else if (uBody.sInNetCallYn == "N") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2LO";
     // }
     // }
   if ((uBody.isbIsHD_4G_VOICE()) && (uBody.getsSscode().equals("") || uBody.getsSscode().equals(uBody.getSscodeTwonumber()) || uBody.isbIsEMS_CALLED() || uBody.isbIsSSCODE_MPBX()) && !(uBody.isbIsFPS_CALL())) {
    if (uBody.getsInNetCallYn().equals("") || uBody.getsInNetCallYn() == null) {
        uBody.setsInNetCallYn("N");
       }
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2OT");
       }
   }
   
     // Rule : feature_add_volte_hd
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml -- 신규

     // string sTIOI =
		// comnSubstring(uBody.terminating_inter_operator_identifier,0,8);
     // if (sTIOI == "ims.kt.c" || sTIOI == "sktims.n" || sTIOI == "lte-lgup"
		// || sTIOI == "octave.c" || sTIOI == "cs.kt.co") {
     // uBody.bIsTIOI_LIST = true;
     // }
      var sTIOI = comnSubstring(uBody.getTerminating_inter_operator_identifier(), 0, 8);
      if (sTIOI.equals("ims.kt.c") || sTIOI.equals("sktims.n") || sTIOI.equals("lte-lgup") || sTIOI.equals("octave.c") || sTIOI.equals("cs.kt.co")) {
       uBody.setbIsTIOI_LIST(true);
      }

     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // if ((uBody.bIsVOLTE_4G_CALL) && (comnStrTrim(uBody.sSscode) == "" ||
		// uBody.sSscode == SSCODE_TWONUMBER || uBody.bIsEMS_CALLED ||
		// uBody.bIsSSCODE_MPBX) &&
     // !(uBody.bIsFPS_CALL) && (uBody.hd_call == "1") &&
		// !uBody.bIsTIOI_LIST) {
     // if (uBody.sInNetCallYn == null || uBody.sInNetCallYn == "") {
     // uBody.sInNetCallYn = "N";
     // }
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2KT";
     // } else if (uBody.sInNetCallYn == "N") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2OT";
     // }
     // }
      if ((uBody.isbIsVOLTE_4G_CALL()) && (uBody.getsSscode().equals("") || uBody.getsSscode().equals(uBody.getSscodeTwonumber()) || uBody.isbIsEMS_CALLED() || uBody.isbIsSSCODE_MPBX()) && 
       !(uBody.isbIsFPS_CALL()) && (uBody.getHd_call().equals("1")) && !(uBody.isbIsTIOI_LIST())) {
       if (uBody.getsInNetCallYn().equals("") || uBody.getsInNetCallYn() == null) {
        uBody.setsInNetCallYn("N");
       }
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2OT");
       }
      }

     // feature_add_volte_not_hd
     // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml -- condition
     // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
     // if ((uBody.bIsVOLTE_4G_CALL) && (comnStrTrim(uBody.sSscode) == "" ||
		// uBody.sSscode == SSCODE_TWONUMBER || uBody.bIsEMS_CALLED ||
		// uBody.bIsSSCODE_MPBX) &&
     // !(uBody.bIsFPS_CALL) && (uBody.hd_call == "0")) {
     // if (uBody.sInNetCallYn == "" || uBody.sInNetCallYn == null) {
     // uBody.sInNetCallYn = "N";
     // }

     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2KT";
     // } else if (uBody.sInNetCallYn == "N") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VLT2OT";
     // }
     // }
      if ((uBody.isbIsVOLTE_4G_CALL()) && (uBody.getsSscode().equals("") || uBody.getsSscode().equals(uBody.getSscodeTwonumber()) || uBody.isbIsEMS_CALLED() || uBody.isbIsSSCODE_MPBX()) &&
       !(uBody.isbIsFPS_CALL()) && (uBody.getHd_call().equals("0"))) {
       if (uBody.getsInNetCallYn().equals("") || uBody.getsInNetCallYn() == null) {
        uBody.setsInNetCallYn("N");
       }
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VLT2OT");
       }
      }

     // Rule : feature_add_vs_3g
     // if (uBody.bIsVS_3G_CALL) {
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "RVW2KT";
     // }
     // else if (uBody.sInNetCallYn == "N") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "RVW2OT";
     // }
     // }
   if (uBody.isbIsVS_3G_CALL()) {
    if (uBody.getsInNetCallYn().equals("") || uBody.getsInNetCallYn() == null) {
        uBody.setsInNetCallYn("N");
       }
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "RVW2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "RVW2OT");
       }
   }
     // Rule : feature_add_vs_4g
     // if (uBody.bIsVS_4G_CALL) {
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "RVL2KT";
     // }
     // else if (uBody.sInNetCallYn == "N") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "RVL2OT";
     // }
     // }
      if (uBody.isbIsVS_4G_CALL()) {
    if (uBody.getsInNetCallYn().equals("") || uBody.getsInNetCallYn() == null) {
        uBody.setsInNetCallYn("N");
       }
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "RVL2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "RVL2OT");
       }
   }

     // KTF_CSUB : WORK.sett_provider[1,4] is ("KTF ","HSP ")
     // sNpSettProvider
     // if ((uBody.sSettlCarrier == "KTF") || (uBody.sSettlCarrier == "HSP"))
		// {
     // uBody.bIsKTF_CSUB = true;
     // }
      if ((uBody.getsSettlCarrier().equals("KTF") || uBody.getsSettlCarrier().equals("HSP"))) {
       uBody.setbIsKTF_CSUB(true);
      }

     // Rule : feature_fps_0
     // if (uBody.bIsNORMAL_COMP) {
     // if ((uBody.sSettlFileName == "KTF") || (uBody.bIsKTF_CSUB)) {
     // if ((uBody.bIsFPS_CALL && uBody.sFpsResult == "Y") &&
     // (!(((listFindIndex(LST_SSCODE_VMS, i, i == uBody.sSscode) > -1) &&
		// uBody.sIpasResult == "Y") ||
     // (uBody.sVmsAccess == "00" && uBody.sVmsResult == "Y")))) {

     // uBody.sMutualConnType = "0";
     // if (uBody.bIsVISUAL_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPVSKF";
     // }
     // else {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPVCKF";
     // }
     // }
     // }
      if (uBody.isbIsNORMAL_COMP()) {
       if ((uBody.getsSettlFileName().equals("KTF") || uBody.isbIsKTF_CSUB())) {
        if ((uBody.isbIsFPS_CALL() && uBody.getsFpsResult().equals("Y") &&
         (!((uBody.getLST_SSCODE_VMS().contains(uBody.getsSscode())) && uBody.getsIpasResult().equals("Y")) ||
         (uBody.getsVmsAccess().equals("00") && uBody.getsVmsResult().equals("Y"))))) {
         uBody.setsMutualConnType("0");
         
         if (uBody.isbIsVISUAL_CALL()) {
          uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPVSKF");
         } else {
          uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPVCKF");
         }
        }
       }
      

     // Rule : feature_fps_3
     // if (!((uBody.sSettlFileName == "INT" || uBody.sSettlFileName ==
		// "INN") || uBody.sSettlFileName == "KTF" || uBody.bIsKTF_CSUB)) {
     // if ((uBody.bIsFPS_CALL) && (uBody.sFpsResult == "Y")) {
     // uBody.sMutualConnType = "3";
     // uBody.sInNetCallYn = "N";

     // if (uBody.bIsVISUAL_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPVSOT";
     // }
     // else {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPVCOT";
     // }
     // }
     // }
     // }
    if (!((uBody.getsSettlFileName().equals("INT") || uBody.getsSettlFileName().equals("INN")) || uBody.getsSettlFileName().equals("KTF") || uBody.isbIsKTF_CSUB())) {
     if ((uBody.isbIsFPS_CALL()) && (uBody.getsFpsResult().equals("Y"))) {
      uBody.setsMutualConnType("3");
      uBody.setsInNetCallYn("Y");
      
      if (uBody.isbIsVISUAL_CALL()) {
       uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPVSOT");
      } else {
       uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPVCOT");
      }
     }
    }
   }
     // Rule : feature_wire - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml -
		// 신규
     // if (uBody.bIsVOICE_CALL) {
     // if (uBody.sTermArea == "WR") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KT2WIR";
     // }
     // }
      if (uBody.isbIsVOICE_CALL()) {
       if (uBody.getsTermArea().equals("WR")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KT2WIR");
       }
      }

     // Rule : interconnection_type
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMutualConnType = "0";
     // }
     // else if (uBody.sInNetCallYn == "N") {
     // uBody.sMutualConnType = "3";
     // }
      if (uBody.getsInNetCallYn().equals("Y")) {
       uBody.setsMutualConnType("0");
      } else if (uBody.getsInNetCallYn().equals("N")) {
       uBody.setsMutualConnType("3");
      }

     // Rule : interconnection_type_0
     // if ((uBody.bIsNORMAL_COMP) && (listFindIndex(LST_SSCODE_CF, i, i ==
		// uBody.sSscode) > -1) &&
     // (!(uBody.sSettlFileName == "INT" || uBody.sSettlFileName == "INN"))
		// &&
     // (uBody.sSettlFileName == "KTF" || uBody.bIsKTF_CSUB) &&
     // (!(uBody.bIsFPS_CALL && uBody.sFpsResult == "Y")) &&
     // (!(((listFindIndex(LST_SSCODE_VMS, i, i == uBody.sSscode) > -1) &&
		// (uBody.sIpasResult == "Y")) ||
     // (uBody.sVmsAccess == "00" && uBody.sVmsResult == "Y")))) {

     // uBody.sMutualConnType = "0";

     // if (uBody.bIsVISUAL_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VS2KTF";
     // }
     // else {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KTF2KT";
     // }
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getLST_SSCODE_CF().contains(uBody.getsSscode())) &&
       (!(uBody.getsSettlFileName().equals("INT") || uBody.getsSettlFileName().equals("INN"))) &&
       (uBody.getsSettlFileName().equals("KTF") || uBody.isbIsKTF_CSUB()) &&
       (!(uBody.isbIsFPS_CALL() && uBody.getsFpsResult().equals("Y")) &&
       (!(uBody.getLST_SSCODE_VMS().contains(uBody.getsSscode())) && (uBody.getsIpasResult().equals("Y"))) ||
       (uBody.getsVmsAccess().equals("00") && uBody.getsVmsResult().equals("Y")))) {
       uBody.setsMutualConnType("0");
        
       if (uBody.isbIsVISUAL_CALL()) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VS2KTF");
       } else {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KTF2KT");
       }
      }

     // Rule : interconnection_type_3 - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     // if ((uBody.bIsNORMAL_COMP) &&
     // (!(uBody.sSettlFileName == "INT" || uBody.sSettlFileName == "INN") ||
		// uBody.sSettlFileName == "KTF" || uBody.bIsKTF_CSUB) &&
     // (!(uBody.bIsFPS_CALL && uBody.sFpsResult == "Y"))) {
         
     // uBody.sMutualConnType = "3";
     // uBody.sOtrFlag = "Y";

     // if (listFindIndex(LST_SSCODE_CF, i, i == uBody.sSscode) > -1) {
     // if (uBody.bIsVISUAL_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VS2OTR";
     // }
     // else {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KT2OTR";
     // }
     // }
     // }
      if ((uBody.isbIsNORMAL_COMP()) &&
       (!(uBody.getsSettlFileName().equals("INT") || uBody.getsSettlFileName().equals("INN")) || uBody.getsSettlFileName().equals("KTF") || uBody.isbIsKTF_CSUB()) &&
       (!(uBody.isbIsFPS_CALL() && uBody.getsFpsResult().equals("Y")))) {
       
       uBody.setsMutualConnType("3");
       uBody.setsOtrFlag("Y");
       
       if (uBody.getLST_SSCODE_CF().contains(uBody.getsSscode())) {
        if (uBody.isbIsVISUAL_CALL()) {
         uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VS2KTF");
        } else {
         uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KTF2KT");
        }
       }
      }

     // Rule : mps_feature_code_gcall - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     // if (uBody.bIsNORMAL_COMP && uBody.bIsVOLTE_4G_CALL) {
     // if (uBody.sInNetCallYn == "Y") {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KTF2KT";
     // } else {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KT2OTR";
     // }
     // }
      if (uBody.isbIsNORMAL_COMP() && uBody.isbIsVOLTE_4G_CALL()) {
       if (uBody.getsInNetCallYn().equals("Y")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KTF2KT");
       } else if (uBody.getsInNetCallYn().equals("N")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KT2OTR");
       }
   }
     // Rule : mps_feature_code_smsbg
     // if (uBody.sWinSvcType == SMSBG_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "SMSBG ";
     // }
      if (uBody.getsWinSvcType().equals(uBody.getSmsbgCall())) {
       uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "SMSBG");
      }

     // Rule : mps_feature_code_vms
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sSettlFileName == "KTF" ||
		// uBody.bIsKTF_CSUB) &&
     // (listFindIndex(LST_SSCODE_VMS, i, i == uBody.sSscode) > -1) &&
     // (uBody.sIpasResult == "Y") || ((uBody.sVmsAccess == "00") &&
		// (uBody.sVmsResult == "Y"))) {

     // uBody.sMutualConnType = "0";

     // if (uBody.bIsVISUAL_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VS2VMS";
     // }
     // else {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VC2VMS";
     // }
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsSettlFileName().equals("KTF") || uBody.isbIsKTF_CSUB()) &&
       (uBody.getLST_SSCODE_VMS().contains(uBody.getsSscode()) &&
       (uBody.getsIpasResult().equals("Y")) || ((uBody.getsVmsAccess().equals("00")) && (uBody.getsVmsResult().equals("Y"))))) {
       
       uBody.setsMutualConnType("0");
       
       if (uBody.isbIsVISUAL_CALL()) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VS2VMS");
       } else {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VC2VMS");
       }
      }

     // Rule : mps_file_number -- 사용안함.

     // Rule : np_count -- 사용되는 곳 없음
     // Work.recv_cnt

      // Rule : service_feature_code
     // if (uBody.sProdFromatId != null && uBody.sProdCdType != null &&
		// uBody.sProdNumber != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sProdCdType,
		// uBody.sProdNumber, sCallStartDateTime);
         
     // if (uTB_FEATR_BAS.lRowCnt > 0) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd +
		// comnPadChr(uTB_FEATR_BAS.sFeatureCd, 6, " ", "R");
     // }
     // }
      if(uBody.getsProdFormatId() != null && uBody.getsProdCdType() != null && uBody.getsProdNumber() != null) {
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsProdCdType(), uBody.getsProdNumber(), uBody.getsCallStartDateTime());
       
       if (tbFeatrBas != null) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + comnPadChr(tbFeatrBas.getsFeatureCd(), 6, " ", "R"));
       }
      }
      
     // Rule : sett_domain - Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
     // if ((uBody.bIsTIME_CDR || uBody.bIsTIME_CDR_001) &&
		// !(uBody.bIsVS_3G_CALL || uBody.bIsVS_4G_CALL) && !(uBody.sInNetCallYn
		// == "Y")) {
     // uBody.sSettCall = "Y";
     // }
      if ((uBody.isbIsTIME_CDR() || uBody.isbIsTIME_CDR_001()) && !(uBody.isbIsVS_3G_CALL() || uBody.isbIsVS_4G_CALL()) && !(uBody.getsInNetCallYn().equals("Y"))) {
       uBody.setsSettCall("Y");
      }
     // Rule : sett_no_gen_win
     // if (uBody.sProdFromatId != null && uBody.sInsProdType != null &&
		// uBody.sWinSvcType != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_2 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sInsProdType,
		// uBody.sWinSvcType, sCallStartDateTime);
     // if (uTB_FEATR_BAS_2.lRowCnt > 0) {
     // uBody.sProdDropCall = uTB_FEATR_BAS_2.sFeatureDropIndi;

     // if (uBody.sProdDropCall == "Y") {
     // uBody.sSettCall = "Y";
     // }
     // }
     // }
      if(uBody.getsProdFormatId() != null && uBody.getsInsProdType() != null && uBody.getsWinSvcType() != null) {
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsInsProdType(), uBody.getsWinSvcType(), uBody.getsCallStartDateTime()); 
       
       if (tbFeatrBas != null) {
        uBody.setsProdDropCall(tbFeatrBas.getsFeatureDropIndi());
        
        if (uBody.getsProdDropCall().equals("Y")) {
         uBody.setsSettCall("Y");
        }
       }
      }
  // Rule : sett_not_gen
     // if ((uBody.bIsWIN_CHARGEABLE) && !(uBody.bIsINTERNATIONAL) &&
		// (uBody.sSpnDpClInd == "Y")) {
     // uBody.sSettCall = "N";
     // }
      if ((uBody.isbIsWIN_CHARGEABLE()) && !(uBody.isbIsINTERNATIONAL()) && (uBody.getsSpnDpClInd().equals("Y"))) {
       uBody.setsSettCall("N");
      }
     // Rule : tb_cln_sett_drop_ind
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sCallingDropIndi == "Y")) {
  //
     // TB_CAL_NO_BAS_Intl uTB_CAL_NO_BAS =
		// get_TB_CAL_NO_BAS(strSubstring(uBody.sChrgSvcNo, 0, 3),
		// strSubstring(uBody.sChrgSvcNo, 3, 7), strSubstring(uBody.sChrgSvcNo,
		// 7, 11), sCallStartDateTime);
     //    
     // if (uTB_CAL_NO_BAS.lRowCnt > 0) {
     // if (uTB_CAL_NO_BAS.sSettlDropInd == "Y") {
     // uBody.sSettDropInd = "Y";
     // }
     // }
     // }
      
      if(uBody.isbIsNORMAL_COMP() && (uBody.getsCallingDropIndi().equals("Y"))) {
       if (uBody.getsChrgSvcNo() != null) {
        tbCalNoBas = audit.get_TB_CAL_NO_BAS(uBody.getsChrgSvcNo().substring(0, 3), uBody.getsChrgSvcNo().substring(3, 7), uBody.getsChrgSvcNo().substring(7,11), uBody.getsCallStartDateTime());
       
        if (tbCalNoBas != null) {
         if (tbCalNoBas.getsSettlDropInd().equals("Y")) {
          uBody.setsSettDropInd("Y");
         }
        }
       }
      }
      
     // Rule : tb_cln_sett_drop_ind
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sCallingDropIndi == "Y")) {
  //
     // TB_CAL_NO_BAS_Intl uTB_CAL_NO_BAS =
		// get_TB_CAL_NO_BAS(strSubstring(uBody.sChrgSvcNo, 0, 3),
		// strSubstring(uBody.sChrgSvcNo, 3, 7), strSubstring(uBody.sChrgSvcNo,
		// 7, 11), sCallStartDateTime);
     //    
     // if (uTB_CAL_NO_BAS.lRowCnt > 0) {
     // if (uTB_CAL_NO_BAS.sSettlDropInd == "Y") {
     // uBody.sSettDropInd = "Y";
     // }
     // }
     // }
      if(uBody.isbIsNORMAL_COMP() && (uBody.getsCallingDropIndi().equals("Y"))) {
       tbCalNoBas = audit.get_TB_CAL_NO_BAS(uBody.getsChrgSvcNo().substring(0, 3), uBody.getsChrgSvcNo().substring(3, 7), uBody.getsChrgSvcNo().substring(7,11), uBody.getsCallStartDateTime());
       
       if (tbCalNoBas != null) {
        if (tbCalNoBas.getsSettlDropInd().equals("Y")) {
         uBody.setsSettDropInd("Y");
        }
       }
      }      
     // Rule : tb_mff_service_feature
     // string sDropIndi;
     // if (uBody.sProdFromatId != null && uBody.sProdCdType != null &&
		// uBody.sSscode != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_3 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sProdCdType,
		// uBody.sSscode, sCallStartDateTime);
     // if (uTB_FEATR_BAS_3.lRowCnt > 0) {
     // sDropIndi = uTB_FEATR_BAS_3.sFeatureDropIndi;
     // }
     // }
      var sDropIndi = "";
      if (uBody.getsProdFormatId() != null && uBody.getsProdCdType() != null && uBody.getsSscode() != null) {
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsProdCdType(), uBody.getsSscode(), uBody.getsCallStartDateTime());
       if (tbFeatrBas != null) {
        sDropIndi = tbFeatrBas.getsFeatureDropIndi();
       }
      }
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sProdCdIndi == "Y") &&
		// (sDropIndi == "N")) {
     // uBody.sProdDropCall = "N";

     // if (!(uBody.sSscode == "066")) {
     // if (uBody.sProdFromatId != null && uBody.sProdCdType != null &&
		// uBody.sSscode != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_4 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sProdCdType,
		// uBody.sSscode, sCallStartDateTime);
     
     // if (uTB_FEATR_BAS_4.lRowCnt > 0) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd +
		// comnPadChr(uTB_FEATR_BAS_4.sFeatureCd, 6, " ", "R");
     // }
     // }
     // }
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsProdCdIndi().equals("Y")) && (sDropIndi.equals("N"))) {
       uBody.setsProdDropCall("N");
       
       if (!(uBody.getsSscode().equals("066"))) {
        if (uBody.getsProdFormatId() != null && uBody.getsProdCdType() != null && uBody.getsSscode() != null) {
         tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsProdCdType(), uBody.getsSscode(), uBody.getsCallStartDateTime());
       
         if (tbFeatrBas != null) {
          uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + comnPadChr(tbFeatrBas.getsFeatureCd(), 6, " ", "R"));
         }
        }
       }
      }
  // Rule : tb_mff_sett_drop_ind
     // string sFeatureSettDropIndi;
     // if (uBody.sProdFromatId != null && uBody.sProdCdType != null &&
		// uBody.sSscode != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_5 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sProdCdType,
		// uBody.sSscode, sCallStartDateTime);
     // if (uTB_FEATR_BAS_5.lRowCnt > 0) {
     // sFeatureSettDropIndi = uTB_FEATR_BAS_5.sSettlDropIndi;
     // }
     // }
     
      var sFeatureSettDropIndi = "";
      if (uBody.getsProdFormatId() != null && uBody.getsProdCdType() != null && uBody.getsSscode() != null) {
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsProdCdType(), uBody.getsSscode(), uBody.getsCallStartDateTime());
       
       if (tbFeatrBas != null) {
        sFeatureSettDropIndi = tbFeatrBas.getsSettlDropIndi();
       }
      }

     // if ((uBody.bIsNORMAL_COMP) && (uBody.sProdCdIndi == "Y") &&
		// (sFeatureSettDropIndi == "Y")) {
     // uBody.sSettDropInd = "Y";
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsProdCdIndi().equals("Y")) && (sFeatureSettDropIndi.equals("Y"))) {
       uBody.setsSettDropInd("Y");
      }

     // Rule : tb_spn_air_time_feature_code - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml
     // TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS_10 =
		// get_TB_WLESS_SPCL_NO_BAS(uBody.sCalledArea, uBody.sChrgOtherNo,
		// "KTF", sCallStartDateTime, SPNM);
  tbWlessSpclNoBas = audit.get_TB_WLESS_SPCL_NO_BAS(uBody.getsCalledArea(), uBody.getsChrgOtherNo(), "KTF", uBody.getsCallStartDateTime(), uBody.getSpnm());
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sSpIndi == "Y") &&
		// !(uBody.bIsINTERNATIONAL) && (uBody.sSpnAtFtrInd == "Y")) {
     
     // if (uTB_WLESS_SPCL_NO_BAS_10.lRowCnt > 0) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd +
		// comnPadChr(uTB_WLESS_SPCL_NO_BAS_10.sAirtimeFeatureCd, 6, " ", "R");
     // }
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsSpIndi().equals("Y")) && !(uBody.isbIsINTERNATIONAL()) && (uBody.getsSpnAtFtrInd().equals("Y"))) {
       if (tbWlessSpclNoBas != null) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + comnPadChr(tbWlessSpclNoBas.getsAirtimeFeatureCd(), 6, " ", "R"));
       }
      }

     // Rule : tb_spn_sp_feature_code
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sSpIndi == "Y") &&
		// (uBody.sSpnSpInd == "Y")) {
     // uBody.sSpProdCd = uTB_WLESS_SPCL_NO_BAS_10.sSpFeatureCode;
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsSpIndi().equals("Y")) && (uBody.getsSpnSpInd().equals("Y"))) {
       uBody.setsSpProdCd(tbWlessSpclNoBas.getsSpFeatureCode());
      }

     // Rule : tb_spn_sp_type
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sSpIndi == "Y") &&
		// (uBody.sSpnSpInd == "Y")) {
     
     // TB_SVC_PRVR_BAS_Intl uTB_SVC_PRVR_BAS =
		// get_TB_SVC_PRVR_BAS(uTB_WLESS_SPCL_NO_BAS_10.sIspId);
     // uBody.sSpType = uTB_SVC_PRVR_BAS.sSpFeatureCode;

     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsSpIndi().equals("Y")) && (uBody.getsSpnSpInd().equals("Y"))) {
       tbSvcPrvrBas = audit.get_TB_SVC_PRVR_BAS(tbWlessSpclNoBas.getsIspId());
       uBody.setsSpType(tbSvcPrvrBas.getsSpFeatureCode());
      }


     // Rule : tb_spn_special_number_id
     // if ((uBody.bIsNORMAL_COMP) && (uBody.sSpIndi == "Y")) {
     // uBody.iSpecialNoId = (int)uTB_WLESS_SPCL_NO_BAS_10.lSpecialNoId;
     // }
      if ((uBody.isbIsNORMAL_COMP()) && (uBody.getsSpIndi().equals("Y"))) {

       uBody.setiSpecialNoId(Integer.parseInt(tbWlessSpclNoBas.getlSpecialNoId().equals("") ? "0" : tbWlessSpclNoBas.getlSpecialNoId()));

      }
      

     // if (uBody.sProdFromatId != null && uBody.sInsProdType != null &&
		// uBody.sWinSvcType != null) {
     // TB_FEATR_BAS_Intl uTB_FEATR_BAS_6 =
		// get_TB_FEATR_BAS(uBody.sProdFromatId, uBody.sInsProdType,
		// uBody.sWinSvcType, sCallStartDateTime);
     
     // if (uTB_FEATR_BAS_6.lRowCnt > 0) {
     // if ((uTB_FEATR_BAS_6.sFeatureType == "C") && (uBody.bIsWIN_CDR)) {
     // uBody.bIsWIN_CHARGED = true;
     // }
     // }
     // }
      if (uBody.getsProdFormatId() != null && uBody.getsInsProdType() != null && uBody.getsWinSvcType() != null) {
       tbFeatrBas = audit.get_TB_FEATR_BAS(uBody.getsProdFormatId(), uBody.getsInsProdType(), uBody.getsWinSvcType(), uBody.getsCallStartDateTime());
       
       if (tbFeatrBas != null) {
        if((tbFeatrBas.getsFeatureType().equals("C")) && uBody.isbIsWIN_CDR()) {
         uBody.setbIsWIN_CHARGED(true);
        }
       }
      }   

     // int iDeductTime;
      var iDeductTime = 0;

     // Rule : visual_duration
     // PSVT(영상) 통화 품질 관련 차감시간(초) 현재 10초임
     // if ((uBody.bIsPSVT_3G_CALL || uBody.bIsPSVT_4G_CALL) &&
		// !(uBody.bIsWIN_CHARGED)) {
     // uBody.sDeduct = "Y";

     // TB_PROF_BAS_Intl uTB_PROF_BAS_4 = get_TB_PROF_BAS("PSVT_DEDUCT_TIME",
		// "VOLTE", sCallStartDateTime);

     // if (!(uTB_PROF_BAS_4 == null)) {
     // uBody.sFpsResult = uTB_PROF_BAS_4.sReturnValue;
     // }
     // strToInt(iDeductTime, uBody.sFpsResult);

     // uBody.iDeductUseTime = uBody.iDeductUseTime + iDeductTime;
     // uBody.iUseTime = uBody.iUseTime - uBody.iDeductUseTime;
     // }
      if ((uBody.isbIsPSVT_3G_CALL() || uBody.isbIsPSVT_4G_CALL()) && !(uBody.isbIsWIN_CHARGED())) {
       uBody.setsDeduct("Y");
       
       tbProfBas = audit.get_TB_PROF_BAS("PSVT_DEDUCT_TIME", "VOLTE", uBody.getsCallStartDateTime());
       
       if (tbProfBas != null) {
        uBody.setsFpsResult(tbProfBas.getsReturnvalue());
       }

       iDeductTime = Integer.parseInt(uBody.getsFpsResult().equals("") ? "0" : uBody.getsFpsResult());

       
       uBody.setiDeductUseTime(uBody.getiDeductUseTime() + iDeductTime);
       uBody.setiUseTime(uBody.getiUseTime() - uBody.getiDeductUseTime());
      }

     // Rule : volte_duration
     // if (uBody.bIsVOLTE_4G_CALL) {
     // uBody.sDeduct = "Y";

     // TB_PROF_BAS_Intl uTB_PROF_BAS_5 =
		// get_TB_PROF_BAS("VoLTE_DEDUCT_TIME", "VOLTE", sCallStartDateTime);

     // if (!(uTB_PROF_BAS_5 == null)) {
     // uBody.sFpsResult = uTB_PROF_BAS_5.sReturnValue;
     // }
     // strToInt(iDeductTime, uBody.sFpsResult);

     // uBody.iDeductUseTime = uBody.iDeductUseTime + iDeductTime;
     // uBody.iUseTime = uBody.iUseTime - uBody.iDeductUseTime;
     // }
     
      if (uBody.isbIsVOLTE_4G_CALL()) {
       uBody.setsDeduct("Y");
       
       tbProfBas = audit.get_TB_PROF_BAS("VoLTE_DEDUCT_TIME", "VOLTE", uBody.getsCallStartDateTime());
       
       if (tbProfBas != null) {
        uBody.setsFpsResult(tbProfBas.getsReturnvalue());
       }
       iDeductTime = Integer.parseInt(uBody.getsFpsResult().equals("") ? "0" : uBody.getsFpsResult());
       
       uBody.setiDeductUseTime(uBody.getiDeductUseTime() + iDeductTime);
       uBody.setiUseTime(uBody.getiUseTime() - uBody.getiDeductUseTime());
      }
       

     // Rule : vs_vmsfw_duration
     // Rule : vs_vmsfw_duration_2
     // if ((uBody.bIsNORMAL_COMP) && ((uBody.sSettlFileName == "KTF") ||
		// (uBody.bIsKTF_CSUB)) && (uBody.bIsVISUAL_CALL) &&
     // (((uBody.sSscode == SSCODE_VMSFW) && (uBody.sIpasResult == "Y")) ||
     // ((uBody.sVmsAccess == "00") && (uBody.sVmsResult == "Y")))) {

     // if (!(uBody.bIsWIN_CHARGED)) { // Rule : vs_vmsfw_duration
     // uBody.sDeduct = "Y";
     // uBody.sVmsDivision = "00";

     // TB_PROF_BAS_Intl uTB_PROF_BAS_6 = get_TB_PROF_BAS("VMSFW_INIT_TIME",
		// "VOLTE", sCallStartDateTime);

     // if (!(uTB_PROF_BAS_6 == null)) {
     // uBody.sFpsResult = uTB_PROF_BAS_6.sReturnValue;
     // }
     // strToInt(iDeductTime, uBody.sFpsResult);

     // uBody.iDeductUseTime = uBody.iDeductUseTime + iDeductTime;
     // uBody.iUseTime = uBody.iUseTime - uBody.iDeductUseTime;

     // uBody.sMpsFeatureCd = strREReplaceAll(uBody.sMpsFeatureCd, "VMSFW ",
		// "VMSFW ");
     // }
     // else { // Rule : vs_vmsfw_duration_2 -- (VOLTE.WIN_CHARGED)
     // uBody.sMpsFeatureCd = strREReplaceAll(uBody.sMpsFeatureCd, "VMSFW ",
		// "VMSFW ");
     // }
     // }
     
      if ((uBody.isbIsNORMAL_COMP()) && ((uBody.getsSettlFileName().equals("KTF")) || (uBody.isbIsKTF_CSUB()) && (uBody.isbIsVISUAL_CALL())) && ((uBody.getsVmsAccess().equals("00")) 
       && (uBody.getsVmsResult().equals("Y")))) {
       if (!(uBody.isbIsWIN_CHARGED())) {
        uBody.setsDeduct("Y");
        // enriched.setsVmsDivision("00");
        
        tbProfBas = audit.get_TB_PROF_BAS("VMSFW_INIT_TIME", "VOLTE", uBody.getsCallStartDateTime());
        
        if (tbProfBas != null) {
         uBody.setsFpsResult(tbProfBas.getsReturnvalue());
        }
        iDeductTime = Integer.parseInt(uBody.getsFpsResult().equals("") ? "0" : uBody.getsFpsResult());
        
        uBody.setiDeductUseTime(uBody.getiDeductUseTime() + iDeductTime);
        uBody.setiUseTime(uBody.getiUseTime() - uBody.getiDeductUseTime());
        
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd().replace("VMSFW ", "VMSFW "));
       } else {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd().replace("VMSFW ", "VMSFW "));
       }
      }
       

     // Rule : vs_vmsfw_duration_otr
     // if (((uBody.bIsNORMAL_COMP) && !((uBody.sSettlFileName == "KTF") ||
		// (uBody.bIsKTF_CSUB)) && (uBody.sVmsAccess == "01" || uBody.sVmsAccess
		// == "02")) && (uBody.bIsVISUAL_CALL) && (uBody.sVmsResult == "Y")) {
     // if (!(uBody.bIsWIN_CHARGED)) {
     // uBody.sDeduct = "Y";
     // uBody.sVmsDivision = uBody.sVmsAccess;

     // TB_PROF_BAS_Intl uTB_PROF_BAS_7 = get_TB_PROF_BAS("VMSFW_INIT_TIME",
		// "VOLTE", sCallStartDateTime);

     // if (!(uTB_PROF_BAS_7 == null)) {
     // uBody.sFpsResult = uTB_PROF_BAS_7.sReturnValue;
     // }
     // strToInt(iDeductTime, uBody.sFpsResult);

     // uBody.iDeductUseTime = uBody.iDeductUseTime + iDeductTime;
     // uBody.iUseTime = uBody.iUseTime - uBody.iDeductUseTime;

     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VMSFW ";
     // }
     // else { // (VOLTE.WIN_CHARGED)
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VMSFW ";
     // }

     // }
     
      if (((uBody.isbIsNORMAL_COMP()) && !((uBody.getsSettlFileName().equals("KTF")) || (uBody.isbIsKTF_CSUB())) && (uBody.getsVmsAccess().equals("01") || uBody.getsVmsAccess().equals("02"))) && (uBody.isbIsVISUAL_CALL()) 
      && (uBody.getsVmsResult().equals("Y"))) {
       if (!(uBody.isbIsWIN_CHARGED())) {
        uBody.setsDeduct("Y");
        // enriched.setsVmsDivision(enriched.getsVmsAccess());
        
        tbProfBas = audit.get_TB_PROF_BAS("VMSFW_INIT_TIME", "VOLTE", uBody.getsCallStartDateTime());
        
        if (tbProfBas != null) {
         uBody.setsFpsResult(tbProfBas.getsReturnvalue());
        }
        iDeductTime = Integer.parseInt(uBody.getsFpsResult().equals("") ? "0" : uBody.getsFpsResult());
       
        uBody.setiDeductUseTime(uBody.getiDeductUseTime() + iDeductTime);
        uBody.setiUseTime(uBody.getiUseTime() - uBody.getiDeductUseTime());
        
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VMSFW ");
       } else {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VMSFW ");
       }
      }
        

     // Rule : zone_feature_add_vc - Rule 현행화(변경사항 적용) -
		// RBMS_20150726201256.xml - 신규
     // if (uBody.bIsNORMAL_COMP && (uBody.sSscode == SSCODE_FREE_ZONE)) {
     // uBody.sSettCall = "N";
     // uBody.sMutualConnType = "0";

     // if (uBody.bIsPSVT_3G_CALL || uBody.bIsPSVT_4G_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VS2FZ ";
     // } else if (uBody.bIsVOLTE_4G_CALL) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VC2FZ ";
     // }
     // }
     
      if (uBody.isbIsNORMAL_COMP() && (uBody.getsSscode().equals(uBody.getSscodeFreeZone()))) {
       uBody.setsSettCall("N");
       uBody.setsMutualConnType("0");
       
       if (uBody.isbIsPSVT_3G_CALL() || uBody.isbIsPSVT_4G_CALL()) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VS2FZ ");
       } else if (uBody.isbIsVOLTE_4G_CALL()) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VC2FZ ");
       }
      }


     // VOLTE.TWONUMBER_MO -- NOT(VOLTE.calling_asserted_identity is SPACE)
     // if (!(uBody.calling_asserted_identity == null ||
		// strTrim(uBody.calling_asserted_identity) == "")) {
     // uBody.bIsTWONUMBER_MO = true;
     // }
      if (!(uBody.getCalling_asserted_identity() != null || uBody.getCalling_asserted_identity().trim().equals(""))) {
       uBody.setbIsTWONUMBER_MO(true);
      }

     // list<string> ltFeatureCode = listCreate(string);
     // int iFeatureCnt = comnStrLength(uBody.sMpsFeatureCd);
     // int i = 0;
     // while (i < iFeatureCnt) {
     // listAdd(ltFeatureCode,comnSubstring(uBody.sMpsFeatureCd, i, i+6));
     // i = i+6;
     // }
      // ArrayList<String> ltFeatureCode = new ArrayList<String>();
      var ltFeatureCode = new ArrayList();
      
      for (var i=0; i<uBody.getsMpsFeatureCd().length(); i=i+6) {
       ltFeatureCode.add(comnSubstring(uBody.getsMpsFeatureCd(), i, i+6));
      } 

     // if ((uBody.sSscode == SSCODE_TWONUMBER) && uBody.bIsTWONUMBER_MO) {

     // if (isFeatureExists(ltFeatureCode,"KTF2KT") ||
		// isFeatureExists(ltFeatureCode,"VLT2LK") ||
		// isFeatureExists(ltFeatureCode,"VLT2KT")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KTFKTT";
     // }
     // if (isFeatureExists(ltFeatureCode,"KT2OTR") ||
		// isFeatureExists(ltFeatureCode,"VLT2LO") ||
		// isFeatureExists(ltFeatureCode,"VLT2OT")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "KTOTRT";
     // }
     // if (isFeatureExists(ltFeatureCode,"VS2KTF") ||
		// isFeatureExists(ltFeatureCode,"PVL2LK") ||
		// isFeatureExists(ltFeatureCode,"PVL2KT") ||
		// isFeatureExists(ltFeatureCode,"PVW2KT")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VSKTFT";
     // }
     // if (isFeatureExists(ltFeatureCode,"VS2OTR") ||
		// isFeatureExists(ltFeatureCode,"PVL2LO") ||
		// isFeatureExists(ltFeatureCode,"PVL2OT") ||
		// isFeatureExists(ltFeatureCode,"PVW2OT")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VSOTRT";
     // }
     // if (isFeatureExists(ltFeatureCode,"FPVCKF")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPCKFT";
     // }
     // if (isFeatureExists(ltFeatureCode,"FPVCOT")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPCOTT";
     // }
     // if (isFeatureExists(ltFeatureCode,"FPVSKF")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPSKFT";
     // }
     // if (isFeatureExists(ltFeatureCode,"FPVSOT")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "FPSOTT";
     // }
     // if (isFeatureExists(ltFeatureCode,"VC2KTF")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VCKTFT";
     // }
     // if (isFeatureExists(ltFeatureCode,"VC2OTR")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VCOTRT";
     // }
     // if (isFeatureExists(ltFeatureCode,"VC2VMS")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VCVMST";
     // }
     // if (isFeatureExists(ltFeatureCode,"VS2VMS")) {
     // uBody.sMpsFeatureCd = uBody.sMpsFeatureCd + "VSVMST";
     // }
     // }
      if ((uBody.getsSscode().equals(uBody.getSscodeTwonumber()) && uBody.isbIsTWONUMBER_MO())) {
       if (ltFeatureCode.contains("KTF2KT") || ltFeatureCode.contains("VLT2LK") || ltFeatureCode.contains("VLT2KT")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KTFKTT");
       }
       if (ltFeatureCode.contains("KT2OTR") || ltFeatureCode.contains("VLT2LO") || ltFeatureCode.contains("VLT2OT")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "KTOTRT");
       }
       if (ltFeatureCode.contains("VS2KTF") || ltFeatureCode.contains("PVL2LK") || ltFeatureCode.contains("PVL2KT") || ltFeatureCode.contains("PVW2KT")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VSKTFT");
       }
       if (ltFeatureCode.contains("VS2OTR") || ltFeatureCode.contains("PVL2LO") || ltFeatureCode.contains("PVL2OT") || ltFeatureCode.contains("PVW2OT")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VSOTRT");
       }
       if (ltFeatureCode.contains("FPVCKF")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPCKFT");
       }
       if (ltFeatureCode.contains("FPVCOT")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPCOTT");
       }
       if (ltFeatureCode.contains("FPVSKF")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPSKFT");
       }
       if (ltFeatureCode.contains("FPVSOT")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "FPSOTT");
       }
       if (ltFeatureCode.contains("VC2KTF")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VCKTFT");
       }
       if (ltFeatureCode.contains("VC2OTR")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VCOTRT");
       }
       if (ltFeatureCode.contains("VC2VMS")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VCVMST");
       }
       if (ltFeatureCode.contains("VS2VMS")) {
        uBody.setsMpsFeatureCd(uBody.getsMpsFeatureCd() + "VSVMST");
       }
   }      
   // uBody.bIsChargeFeaturesAir = isFeatureCheck(uBody.sMpsFeatureCd, "AIR");
     // uBody.bIsChargeFeaturesOther = isFeatureCheck(uBody.sMpsFeatureCd,
		// "AC");
      uBody.setbIsChargeFeaturesAir(uBody.getsMpsFeatureCd().contains("AIR"));
      uBody.setbIsChargeFeaturesOther(uBody.getsMpsFeatureCd().contains("AC"));
      
 return uBody;
}

function validate(input) {
	var result;
	var ltErrorCd = new ArrayList();
	
	 cdrProfReferenceInfo = audit.get_TB_PROF_BAS(uBody.Imsi);
	 //cdrProfReferenceInfo = audit.get_TB_PROF_BAS("45008995");
		
	 if ( cdrProfReferenceInfo != null ) {

		 var uErrCdOut = new ErrorCDIntl();
		
		 // AS-IS에서는 Routing split 대상이나, PoC에서는  단순 drop 구현(임의 코드 부여)
		 uErrCdOut.sErrCd          = "0001"; // CDR_ERR_ID
	     uErrCdOut.sErrLevelDivCd  = "10";      // MZ_ERR_LEVEL_DIV_CD
	     uErrCdOut.iErrPriority    = "1";     // MZ_ERR_PRIRT_NO
	     uErrCdOut.sOldErrCd	   = "IMSI";
		 
//		 uBody.sErrCd          = "0001"; // CDR_ERR_ID
//		 uBody.sErrLevelDivCd  = "10";      // MZ_ERR_LEVEL_DIV_CD
//		 uBody.iErrPriority    = "1000";     // MZ_ERR_PRIRT_NO
//		 uBody.sOldErrCd	   = "IMSI";
	     
	     ltErrorCd.add(uErrCdOut);
		 
	 }
	
	
	if (uBody.iUseTime > 0) {

		if (uBody.iUseTime <= 3)  uBody.bIsDURATION_3SEC_CHECK = true;
		if (uBody.iUseTime <= 5)  uBody.bIsDURATION_5SEC_CHECK = true;
		if (uBody.iUseTime <= 10) uBody.bIsDURATION_10SEC_CHECK = true;

	}

	
	// Rule : called_area_not_exist -----> ERROR : ERROR_CODE = 6064
	// 착신번호 오류 : 예) 미등록된 착신번호 prefix
	//if (uBody.sChrgCallYn == "Y" && ((uBody.sCalledArea == "" || uBody.sCalledArea == null) || uBody.sSettlFeatureCd == "SP")) {
	log.debug("sChrgCallYn ::: {} ::: sCalledArea ::: {} ::: sSettlFeatureCd ::: {}", uBody.sChrgCallYn, uBody.sCalledArea, uBody.sSettlFeatureCd);
	
	if (uBody.sChrgCallYn.equals("Y") && ((uBody.sCalledArea.equals("") || uBody.sCalledArea == null) || uBody.sSettlFeatureCd.equals("SP"))) {

		// Rater Error 적용
		// listAdd (ltErrorCd, u_service_called_information_4);
		ltErrorCd.add(errMapBean.u_service_called_information_4);

	 }

	// Rule : called_number_length_less_than_9 -----> DROP : ERROR_CODE = 6065 -
	// Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
	// 착신번호 오류 : VOLTE.service_called_information가 9자리 미만인 경우.
	// CDF_NORM_V constant 실제값 확인
	if ((uBody.sTermCode.equals("CDF_NORM_V")) && (!uBody.bIsINTERNATIONAL) 
	 && (uBody.sCalledSvcNo.length() < 9) && (uBody.sSpIndi.equals("N")) 
	 && !(uBody.sSscode.equals("SSCODE_FREE_ZONE") && uBody.bIsPREFIX_FREEZONE)) {
	
		// listAdd (ltErrorCd, u_service_called_information_5);
		ltErrorCd.add(errMapBean.u_service_called_information_5);
	}
	

	// Rule : called_number_starts_with_0 -----> ERROR : ERROR_CODE = 6066
	// <ERR> Num start with ZERO

	if (!uBody.bIsINTERNATIONAL && (uBody.sChrgOtherNo != null && uBody.sChrgOtherNo.startsWith("0")) &&
		(uBody.sCalledArea != null && !uBody.sCalledArea.equals("")) && (uBody.sSpIndi.equals("N"))) {

		// Rater Error 적용
		
	    // listAdd (ltErrorCd, u_service_called_information_6);
		ltErrorCd.add(errMapBean.u_service_called_information_6);

	}


	// Rule : called_number_starts_with_1 -----> ERROR : ERROR_CODE = 6067
	// <ERR> Num start with 1


	if (!uBody.bIsINTERNATIONAL && (uBody.sChrgOtherNo != null && uBody.sChrgOtherNo.startsWith("1")) &&
		(uBody.sCalledArea != null && !uBody.sCalledArea.equals("")) && !(uBody.sCalledSvcNo.startsWith("011")) &&
		(uBody.sSpIndi.equals("N"))) {

		// Rater Error 적용
		// listAdd (ltErrorCd, u_service_called_information_7);
		ltErrorCd.add(errMapBean.u_service_called_information_7);
		
	}


	// Rule : calling_drop_cdr -----> DROP : ERROR_CODE = 6068 */
	// 발신번호 비과금 : 예) VOLTE.charging_number = 9128019268~인(교환기 Fix폰) 경우.
	tbCalNoBas = audit.get_TB_CAL_NO_BAS (comnSubstring(uBody.sChrgSvcNo, 0, 3), comnSubstring(uBody.sChrgSvcNo, 3, 7), comnSubstring(uBody.sChrgSvcNo, 7, 11), uBody.sCallStartDateTime);

	var sCallingDropIndi;

	if (tbCalNoBas != null) {

		sCallingDropIndi = tbCalNoBas.sCallingDropIndi;

	}

	if ((uBody.sCallingDropIndi.equals("Y")) && (sCallingDropIndi.equals("Y"))) {

		// Rater Error 적용

		// listAdd (ltErrorCd, u_charging_number_3);
		ltErrorCd.add(errMapBean.u_charging_number_3);

	}
	

	// Rule : category_id -----> DROP : ERROR_CODE = 6078
	// 정의되지 않은 Service Category Id : <DRP> Undefined Category Id
	// NOT(VOLTE_4G_CALL OR PSVT_4G_CALL OR PSVT_3G_CALL OR VS_4G_CALL OR
	// VS_3G_CALL OR IM_4G OR IM_3G)

	if (!(uBody.bIsVOLTE_4G_CALL || uBody.bIsPSVT_4G_CALL || uBody.bIsPSVT_3G_CALL ||
		uBody.bIsVS_4G_CALL || uBody.bIsVS_3G_CALL || uBody.bIsIM_4G || uBody.bIsIM_3G)) {
			// Rater Error 적용
		// listAdd (ltErrorCd, u_service_category_id_3);
		ltErrorCd.add(errMapBean.u_service_category_id_3);

	}
	

	// Rule : cause_for_termination_unsuccess -----> DROP : ERROR_CODE = 6021
	// 불완료 호 : <DRP> Unsuccessfual Record */
	// uRule.CDF_UNSUCCESS_V == "1"
	
	if (uBody.sTermCode.equals("CDF_UNSUCCESS_V")) {

		// Rater Error 적용
		// listAdd (ltErrorCd, u_application_release_indicator_4);
		ltErrorCd.add(errMapBean.u_application_release_indicator_4);

	}


	// Rule : duration_com_kor_3 ----> DROP : ERROR_CODE = 6073
	// Duration 비과금 : <DRP> KOR duration <= 3 sec

	/***************************************************************************
	 * * 2019.06.21 ** * DR-2019-12587 MZN drop rule 추가 요청 ** * 3초 이하 이벤트 Drop 1 ** *
	 * (NOT(VOLTE.INTERNATIONAL)) AND (VOLTE.DURATION_3SEC_CHECK) AND
	 * NOT(VOLTE.WIN_CHARGED) AND NOT(VOLTE.KT114) AND (VOLTE.TIME_CDR_ALL) **
	 **************************************************************************/

	if (!uBody.bIsINTERNATIONAL && uBody.bIsDURATION_3SEC_CHECK && 
		(!(uBody.bIsWIN_CHARGED)) && (!(uBody.sProdCd.equals("KT114"))) && (uBody.bIsTIME_CDR || uBody.bIsTIME_CDR_001) ) {

		// rater 와 협의로 mzn 에서 validation 적용안함.
		// listAdd (ltErrorCd, u_service_end_time_5);
		ltErrorCd.add(errMapBean.u_service_end_time_5);

	}
	

	// Rule : sscode_242_drop ----> DROP : ERROR_CODE = 6111
	// <DRP>음성사서함전환 삭제 ss_code = 242

	if ((uBody.bIsVOLTE_4G_CALL) && (uBody.sSscode.equals("242"))) {

		// Rater Error 적용
		// listAdd (ltErrorCd, u_supplementary_services_code_6);
		ltErrorCd.add(errMapBean.u_supplementary_services_code_6);
		
	}
	
	//DR-2016-08380 [무기]mPBX 개발 - 2016.10.10
	//if (comnSubstring(uBody.service_called_information,0,7) == "1593409") {
	if (comnSubstring(uBody.service_called_information,0,7).equals("1593409")) {

	     //listAdd (ltErrorCd, u_service_called_information_8);
	     ltErrorCd.add(errMapBean.u_service_called_information_8);
	}

	// 기업형 음성 총량제 drop  -- E022951
	//if(uBody.sSscode == "231"){
	if(uBody.sSscode.equals("231")){
		//listAdd (ltErrorCd, u_supplementary_services_code_8);
		ltErrorCd.add(errMapBean.u_supplementary_services_code_8);

	}

	// uBody.service_time_duration 0  이거나 0 보다 적을 경우 Error 처리 - 2016.12.23 Rater 요청건
	// 원본 CDR 값 : service_time_duration: 00000000

	//int iServiceTimeDuration;
	//strToInt(iServiceTimeDuration, uBody.service_time_duration);
	var iServiceTimeDuration = parseInt(uBody.service_time_duration);
	
	log.debug("Message parseInt service_time_duration: {}", iServiceTimeDuration);
	log.debug("Goood~~~~~~~~~~~");
	if (iServiceTimeDuration <= 0) {

		//listAdd (ltErrorCd, u_service_time_duration_zero_1);
		ltErrorCd.add(errMapBean.u_service_time_duration_zero_1);
	}

	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++ 테스트 세팅
	
//	var uErrCdOut = new ErrorCDIntl();
//	
//
//    uErrCdOut.sErrCd          = "0001"; // CDR_ERR_ID
//    uErrCdOut.sErrLevelDivCd  = "10";      // MZ_ERR_LEVEL_DIV_CD
//    uErrCdOut.iErrPriority    = "100";     // MZ_ERR_PRIRT_NO
//    uErrCdOut.sOldErrCd	   = "IMSI1";
//	 
//    ltErrorCd.add(uErrCdOut);
//
//    uErrCdOut.sErrCd          = "0002"; // CDR_ERR_ID
//    uErrCdOut.sErrLevelDivCd  = "20";      // MZ_ERR_LEVEL_DIV_CD
//    uErrCdOut.iErrPriority    = "1";     // MZ_ERR_PRIRT_NO
//    uErrCdOut.sOldErrCd	   = "IMSI2";
//
//    ltErrorCd.add(uErrCdOut);
//
//    uErrCdOut.sErrCd          = "0003"; // CDR_ERR_ID
//    uErrCdOut.sErrLevelDivCd  = "30";      // MZ_ERR_LEVEL_DIV_CD
//    uErrCdOut.iErrPriority    = "300";     // MZ_ERR_PRIRT_NO
//    uErrCdOut.sOldErrCd	   = "IMSI3";
//
//    ltErrorCd.add(uErrCdOut);


//	log.debug("Message Error ltErrorCd1: {}", ltErrorCd.get(0));
//	log.debug("Message Error ltErrorCd2: {}", ltErrorCd.get(1));
//	log.debug("Message Error ltErrorCd3: {}", ltErrorCd.get(2));
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++
	   //string sErrLevelDivCd;
	   var sErrLevelDivCd;
	        //if (listSize(ltErrorCd) > 0) {
	   if ( ltErrorCd.size() != 0 ) {

	            //listSort(ltErrorCd, iErrPriority, ascending);   // Sort the error priority(에러 우선순위)
//함수동작안함	        	sErrLevelDivCd = listSort(ltErrorCd, "iErrPriority", "ascending");
	            ltErrorCd.sort(function (priority0, priority1) {
	            	return priority0.iErrPriority < priority1.iErrPriority ? -1 : priority0.iErrPriority > priority1.iErrPriority ? 1 : 0 ;
	            });    
	            //ErrorCD_Intl uMainErrCd = udrCreate(ErrorCD_Intl);
	            //uMainErrCd    = listGet(ltErrorCd, 0);
	            //sErrLevelDivCd = uMainErrCd.sErrLevelDivCd;
	            sErrLevelDivCd = ltErrorCd.get(0).sErrLevelDivCd
	        	
	            log.debug("Message sErrLevelDivCd: {}", sErrLevelDivCd);
	        }

	        //--------------------------------------------------------------

	        // drop 여부 판단 체크를 위해 Rule 위치 조정
	        // Rule : invalid_drop_record
	        //(VOLTE.WIN_CHARGEABLE) AND (WORK.calling_drop_ind = "N")

	        //if ((uBody.bIsWIN_CHARGEABLE) && (uBody.sCallingDropIndi == "N")) {
            //  if (sErrLevelDivCd == "20") {
	        if ((uBody.bIsWIN_CHARGEABLE) && (uBody.sCallingDropIndi.equals("N"))) {
	            if (sErrLevelDivCd.equals("20")) {
	                uBody.sSettDropInd = "Y";
	                uBody.sSettDropTeen = "Y";
	            }
	        }
	        //--------------------------------------------------------------


	        // Error, Drop, Pre-Drop 오류처리, Warn 는 Rater 로 전송

	               //if (sErrLevelDivCd != null && (sErrLevelDivCd == "10" || sErrLevelDivCd == "20" || sErrLevelDivCd == "30")) {
	               if (sErrLevelDivCd != null && (sErrLevelDivCd.equals("10") || sErrLevelDivCd.equals("20") || sErrLevelDivCd.equals("30"))) {

	         
	            	   uBody.sCdrSeq        =  3;   // CDR일련번호  , 파일 건수 변경 필요
	            	   uBody.sMzFileId        =  "SMOKD1_FW4GVLTE_ID0099_T20190707051759.DAT";   // 파일 아이디, 하드코딩 변경 필요
	            	   uBody.sCallgNo       =  uBody.sCallingSvcNo ;   // CALLING번호
	            	   uBody.sCalldNo       =  uBody.sCalledSvcNo  ;   // CALLED 번호
	                   //strToDate(uErrCdr.dClstrDate, uBody.sCallStartDate, "yyyyMMdd") ;   // 시화일자
	                   //uBody.sClstrTod      =  uBody.sCallStartTime ;   // 시화시각
	                   //strToDate(uErrCdr.dCstpDate, uBody.sCallEndDate, "yyyyMMdd")  ;   // 종화일자
	                   //uBody.sCstpTod       =  uBody.sCallEndTime  ;   // 종화시각
	                   uBody.sMzErrorLevelCd  = sErrLevelDivCd ;   // 오류수준 구분코드

	                   var ltErrCd = new Array();
	                   log.debug("Message ltErrorCdLength: {}", ltErrorCd.size() );
	                   
	                   for (var i=0; i<5; i++){
	                	   
	                	   if ( i < ltErrorCd.size() ) {
	                		   ltErrCd[i] = ltErrorCd.get(i).sErrCd;
	                	   } else {
	                		   ltErrCd[i] = ""
	                	   }	                		                	   
	                   }
	                   
	                   uBody.sMzErrorCd01 = ltErrCd[0];
	                   uBody.sMzErrorCd02 = ltErrCd[1];
	                   uBody.sMzErrorCd03 = ltErrCd[2];
	                   uBody.sMzErrorCd04 = ltErrCd[3];
	                   uBody.sMzErrorCd05 = ltErrCd[4];
	                   
	                   log.debug("Message sMzErrorCd01: {}", ltErrCd[0]);
	                   log.debug("Message sMzErrorCd02: {}", ltErrCd[1]);
	                   log.debug("Message sMzErrorCd03: {}", ltErrCd[2]);
	                   log.debug("Message sMzErrorCd04: {}", ltErrCd[3]);
	                   log.debug("Message sMzErrorCd05: {}", ltErrCd[4]);
	                   
//	                   int iErrCnt = listSize(ltErrorCd) ;
//
//	                   ErrorCD_Intl uErrCd = udrCreate(ErrorCD_Intl);
//	                   list<string> ltErrCd = listCreate(string);
//	                   int i = 0;
//
//	                   while(i < 5) {
//
//	                       if (i < iErrCnt) {
//	                           uErrCd = listGet(ltErrorCd, i);	                         
//	                           listAdd(ltErrCd,uErrCd.sErrCd);
//
//	                       } else {
//	                           listAdd(ltErrCd,"");
//	                       }
//	                       i = i + 1;
//	                   }
//   
//	                   uBody.sErr1Cd = listGet(ltErrCd, 0) ;   // 오류 1 코드
//	                   uBody.sErr2Cd = listGet(ltErrCd, 1) ;   // 오류 2 코드
//	                   uBody.sErr3Cd = listGet(ltErrCd, 2) ;   // 오류 3 코드
//	                   uBody.sErr4Cd = listGet(ltErrCd, 3) ;   // 오류 4 코드
//	                   uBody.sErr5Cd = listGet(ltErrCd, 4) ;   // 오류 5 코드
	                   //uErrCdr.sCdrTxn = baToStr(input.OriginalData); // CDR 내역
	                   //uBody.baCdrSbst = input.OriginalData  ;   // 원시 CDR 내역

	                   //udrRoute(uErrCdr,"to_Drew");

	               }


	if ( ltErrorCd.size() != 0 ) {
		
		result = new ErrorCdr(uBody);
		
	} else {
		
		result = new ResultCdr(input);
	}

	return result;
}
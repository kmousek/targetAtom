package com.ktds.targetatom.cdr.volte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

public class VoLTEEnrichedCdr extends ConmonCdr {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	//list<String> LST_BIGI_CDR         = listCreate( String, "044", "045", "046", "047", "048", "049", "056", "057", "058",  "069" );
	public List<String> LST_BIGI_CDR;
	public List<String> LST_CALL_FORWARDING;
	//listCreate( int, 0, -1, -2, -3, -12, -13);
	public int[] LST_CDF_NORMAL			= {0, -1, -2, -3, -12, -13};
	//listCreate( int, 1, 2, 3);
	public int[] LST_CDF_UNSUCCESS         = {1, 2, 3};
	public List<String> LST_VOLTE_4G_CALL;
	public List<String> LST_MOBILE_PREFIX;
	public List<String> LST_SSCODE_CF;
	public List<String> LST_SSCODE_VMS;
	public List<String> LST_DADAN_CALL;
	public List<String> LST_DFREE_CALL;
	public List<String> LST_SSCODE_MPBX;
	
	public final String FPS_CALL 	     = "004";
	public final String PPS_CALL   	     = "006";
	public final String RFREE_CALL		 = "039";
	public final String RRATE_CALL 		 = "040";

	public final String OCR_CDR    		 = "002";
	public final String WIN_CDR    		 = "250";

	public final String IM_3G          		 = "623";
	public final String IM_4G          = "613";
	public final String PSVT_3G_CALL   = "622";
	public final String PSVT_4G_CALL   = "612";
	public final String VS_3G_CALL     = "624";
	public final String VS_4G_CALL     = "614";

	public final String BFREE_CALL     = "044";
	public final String BFREVS_CALL    = "037";
	public final String BLIMIT_CALL    = "036";
	public final String BMATE_CALL     = "049";
	public final String BRATE_CALL     = "045";
	public final String BRATE1_CALL    = "056";

	public final String BRRCF_CALL     = "046";
	public final String BSMOC_CALL     = "058";
	public final String BOVER_CALL     = "069"; // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml
	public final String FNS_CALL       = "010";
	public final String IP_CALL        = "001";
	public final String SMSBG_CALL     = "035";
	public final String TELCOIN_CALL   = "043";

	//YOYO_CALL : YFREE_CALL OR YRATE_CALL OR YFCARD_CALL
	public final String YFCARD_CALL    = "018";
	public final String YFREE_CALL     = "012";
	public final String YRATE_CALL     = "014";

	public final String SPNM = "1";
	public final String SPIP = "2";

	public final String DUMMY_DATE  = "19700101";
	public final String DUMMY_TIME0 = "000000";
	public final String DUMMY_TIME1 = "000011";

	public final String CDF_NORM_V        = "0";
	public final String CDF_UNSUCCESS_V   = "1";
	public final String CDF_ABNORM_V      = "2";

	public final String KT_1577 = "1577";
	public final String KT114   = "S41";

	public final String SSCODE_FREE_ZONE    = "225";
	public final String SSCODE_TWONUMBER    = "226";
	public final String SSCODE_GC           = "352";
	public final String SSCODE_VMSFW        = "242";
	public final String SSCODE_MPBX_FREE    = "228";
	
	/* VOLTE  Declare MACRO_Use boolean variable */
    boolean bIsCALL_FORWARDING;
    boolean bIsCDF_ABNORMAL;
    boolean bIsCDF_NORMAL;
    boolean bIsCDF_UNSUCCESS;
    boolean bIsFPS_CALL;
    boolean bIsIM_3G;
    boolean bIsIM_4G;
    boolean bIsPSVT_3G_CALL;
    boolean bIsPSVT_4G_CALL;
    boolean bIsVOLTE_4G_CALL;
    boolean bIsVS_3G_CALL;
    boolean bIsVS_4G_CALL;
    boolean bIsABNORMAL_COMP;
    boolean bIsNORMAL_COMP;
    boolean bIsUNSUCCESS_COMP;
    boolean bIsKTF_CSUB;
    boolean bIsCALLED_PARTY_PAY_CALL;
    boolean bIsTIME_CDR;
    boolean bIsOCR_CDR;
    boolean bIsWIN_CDR;
    boolean bIsWIN_CHARGEABLE;
    boolean bIsWIN_CHARGED;
    boolean bIsVISUAL_CALL;
    boolean bIsVOICE_CALL;
    boolean bIsSPECIAL_NUMBER;
    boolean bIsChargeFeaturesAir;
    boolean bIsChargeFeaturesOther;
    boolean bIsHD_3G_VOICE;
    boolean bIsHD_4G_VOICE;
    boolean bIsDROPPED_RECORD;
    boolean bIsERROR_FILE;
    boolean bIsDURATION_3SEC_CHECK;
    boolean bIsDURATION_5SEC_CHECK;
    boolean bIsDURATION_10SEC_CHECK;
    boolean bIsBIGI_CDR;             // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsWIN_SRV_BG;
    boolean bIsPPS_CALL;
    boolean bIsDADAN_CALL;
    boolean bIsNO_CHARGE_CDR;         // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsTIME_CDR_001;          // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsOCR_CDR_001;           // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsINTERNATIONAL;
    boolean bIsPREFIX_FREEZONE;       // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsINTL_SERVICE_PROVIDER; // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsSAME_5COUNTRY;         // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsTIOI_LIST;             // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsTWONUMBER_MO;          // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsKT1577;
    boolean bIsExistCarriers;         // Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    boolean bIsRENTAL_CALL;
    boolean bIsWSTYPE_RANGE;
    boolean bIsEMS_CALLED;            // Rule 현행화(변경사항 적용) - RBMS_20160503111148.xml - 신규
    boolean bIsSSCODE_MPBX;           // Rule 현행화(변경사항 적용) - DR-2016-08380 [무기]mPBX 개발 - 2016.10.05
	
    String sCallStartDateTime       = "";
	String sCallGubun           	= "";//call_gubun  
    String sCallStartDate      		= "";//call_start_date     시화일자
    String sCallStartTime       	= "";//call_start_time     시화시간
    String sCallEndDate         	= "";//call_term_date      종화일자
    String sCallEndTime         	= "";//call_term_time      종화시간
    String sWorkCalledSvcNo     	= "";//call_to_tn          작업용수신서비스번호
    String sCalledSvcNo         	= "";//called_number       착신번호
    String sCalledPartyPayCall  	= "";//called_party_pay_call   
    String sCallingDropIndi     	= "";//calling_drop_ind    발신호Drop식별자
    String sCallingSvcNo        	= "";//calling_number      발신번호
    String sChrgSvcNo           	= "";//charging_number     과금번호
    String sDeduct              	= "";//deduct
    int    iDeductUseTime;				 //deduct_duration     공제시간
    String sDrewCalledSvcNo     	= "";//drew_called_number  
    int    iUseTime;					 //duration            사용시간
    String sProdCd              	= "";//feature_code        상품코드유형
    String sProdDropCall        	= "";//feature_drop_call 
    String sProdFormatId        	= "";//feature_format      상품FormatID
    String sProdCdType          	= "";//feature_kind        상품코드유형
    String sProdNumber          	= "";//feature_number        
    String sInsProdType         	= "";//feature_kind_win    지능망 상품 유형
    String sFormatId            	= "";//format_id           Format ID
    String sFpsResult           	= "";//fps_result  
    String sInNetCallYn         	= "";//in_network_ind      망내통화여부
    String sFileType            	= "";//input_file_type     파일유형
    String sMutualConnType      	= "";//interconnection_type상호 접속 유형
    String sIntlNxx             	= "";//intl_nxx  
    String sCallingIp           	= "";//ip_address          발신IP
    String sIpasResult         		= "";//ipas_result 
    String sKeyVal              	= "";//key_val 
    String sProdCdIndi          	= "";//maf_feature_ind     상품코드구분자
    String sMpsFeatureCd        	= "";//maf_feature_code
    int    iOriginFileNo;				 //mps_file_number     원시파일번호
    String sNpInd               	= "";//np_ind  
    String sNpOperator          	= "";//np_operator 
    String sNpSettFeature       	= "";//np_sett_feature 
    String sNpSettProvider      	= "";//np_sett_provider
    String sOriginFileDatetime  	= "";//orig_file_date      원시파일생성일시
    int    iOrigFileSeqNo;				 //orig_file_id        원시파일순번
    String sOtrFlag             	= "";//otr_flag  
    String sProfileResult       	= "";//profile_result      Prof결과값
    int    iRecvCnt;					 //recv_cnt  
    String sSensorId            	= "";//sensor_id           NE장비ID
    String sSpProdCd            	= "";//sp_feature_code     특수번호 상품코드
    String sSpType              	= "";//sp_type             특수번호 유형
    String sSpIndi              	= "";//special_num_ind     특수번호 구분자
    int    iSpecialNoId;				 //special_number_id   
    String sSpNumberType        	= "";//special_number_type 특수번호 유형
    String sSpnSpNmTp           	= "";//spn_sp_nm_tp  
    String sSpNoAtIndi          	= "";//spn_at_ind;
    String sSscode              	= "";//sscode  
    String sSwitchId            	= "";//switch_id           교환기ID
    String sTernInterOpId       	= "";//terminating_inter_operator_identifier 
    String sChrgCallYn          	= "";//usage_call          과금통화여부
    String sVmsAccess           	= "";//vms_access  
    String sVmsInd              	= "";//vms_ind 
    String sVmsResult           	= "";//vms_result
    String sTermCode            	= "";//term_code
    String sSpnSpInd            	= "";//spn_sp_ind
    String sWinSvcType          	= "";//win_service_type
    int    iDurSecPrepaid;				 //dur_sec_prepaid
    String sSettCall            	= "";//sett_call
    String sSettDropInd         	= "";//sett_drop_ind
    String sSettDropTeen        	= "";//sett_drop_teen
    String sSpnDpClInd          	= "";//spn_dp_cl_ind
    String sCfCallInd           	= "";//cf_call_ind
    String sCallingImsi         	= "";//calling_imsi
    String sSpnAcInd            	= "";//spn_ac_ind
    String sTermArea            	= "";//term_area
    String sCallToStateCode     	= "";//call_to_state_code
    String sSettlFileName       	= "";//sett_fname    정산 파일 명
    String sSettlCarrier        	= "";//sett_provider 정산 제공자 코드
    String sSpNoIndi				= "";
    String sSpnPpsDpClInd       	= "";//spn_pps_dp_cl_ind
    String sSpnAtFtrInd         	= "";//spn_at_ftr_ind
    //PopulateFields Validation 두곳 이상에서 사용됨 jektest
    String sCalledArea          	= "";//called_area
    String sSettlFeatureCd      	= "";//sett_feature  정산기타피쳐코드
    String sChrgOtherNo         	= "";//tn_called_number
    String sNationCd            	= "";//country_code Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    String sSecondNationCd      	= "";//fic_country_code Rule 현행화(변경사항 적용) - RBMS_20150726201256.xml - 신규
    String sFeatureDropIndi     	= "";//maf feature drop_yn
	
	// Rule : vms_access
	//	uBody.sVmsIdent = "VMSFW_VMS_ACCESS";
	//	uBody.sVmsDivision = "VOLTE";
	
	// Rule : vms_feature_change_dt_init
	//	uBody.sIpasIdent = "VMS_FEATURE_OPEN";
	//	uBody.sIpasDivision = "VOLTE";
	static String sVmsIdent		= "VMSFW_VMS_ACCESS";
	static String sVmsDivision	= "VOLTE";
	static String sIpasIdent		= "VMS_FEATURE_OPEN";
	static String sIpasDivision	= "VOLTE";
	
//	String sErrCd;
//	String sErrLevelDivCd;
//	int    iErrPriority;
//	String sOldErrCd;
	
	String sMzFileId;
	int sCdrSeq;
	String sCallingNo;
	String sCalledNo;
//	String sCallStartDate;  // 위에 기 선언 변수
//	String sCallStartTime;	// 위에 기 선언 변수
//	String sCallEndDate;	// 위에 기 선언 변수
//	String sCallEndTime;	// 위에 기 선언 변수
	String sMzErrorLevelCd;
	String sMzErrorCd01;
	String sMzErrorCd02;
	String sMzErrorCd03;
	String sMzErrorCd04;
	String sMzErrorCd05;

	
	
	public VoLTEEnrichedCdr () {
		log.debug("creating VoLTECommon");
		super.setCdrType(CdrType.ENRICHED);
		//"044", "045", "046", "047", "048", "049", "056", "057", "058",  "069"
		this.LST_BIGI_CDR = new ArrayList<String>();
		this.LST_BIGI_CDR.add("044");
		this.LST_BIGI_CDR.add("045");
		this.LST_BIGI_CDR.add("046");
		this.LST_BIGI_CDR.add("047");
		this.LST_BIGI_CDR.add("048");
		this.LST_BIGI_CDR.add("049");
		this.LST_BIGI_CDR.add("056");
		this.LST_BIGI_CDR.add("057");
		this.LST_BIGI_CDR.add("058");
		this.LST_BIGI_CDR.add("069");
		
		//list<String> LST_CALL_FORWARDING  = listCreate( String, "033", "041", "042", "043");
		this.LST_CALL_FORWARDING = new ArrayList<String>();
		this.LST_CALL_FORWARDING.add("033");
		this.LST_CALL_FORWARDING.add("041");
		this.LST_CALL_FORWARDING.add("042");
		this.LST_CALL_FORWARDING.add("043");
		
		//listCreate( String, "611", "000");
		this.LST_VOLTE_4G_CALL = new ArrayList<String>();
		this.LST_VOLTE_4G_CALL.add("611");
		this.LST_VOLTE_4G_CALL.add("000");
		
		//list<String> LST_MOBILE_PREFIX    = listCreate(String,"010","011","012","016","017","018","019");
		this.LST_MOBILE_PREFIX = new ArrayList<String>();
		this.LST_MOBILE_PREFIX.add("010");
		this.LST_MOBILE_PREFIX.add("011");
		this.LST_MOBILE_PREFIX.add("012");
		this.LST_MOBILE_PREFIX.add("016");
		this.LST_MOBILE_PREFIX.add("017");
		this.LST_MOBILE_PREFIX.add("018");
		this.LST_MOBILE_PREFIX.add("019");
		
		//list<String> LST_SSCODE_CF        = listCreate( String, "033", "036", "041", "042", "043");
		this.LST_SSCODE_CF = new ArrayList<String>();
		this.LST_SSCODE_CF.add("033");
		this.LST_SSCODE_CF.add("036");
		this.LST_SSCODE_CF.add("041");
		this.LST_SSCODE_CF.add("042");
		this.LST_SSCODE_CF.add("043");
		
		//list<String> LST_SSCODE_VMS       = listCreate( String, "241", "242");
		this.LST_SSCODE_VMS = new ArrayList<String>();
		this.LST_SSCODE_VMS.add("241");
		this.LST_SSCODE_VMS.add("242");
		
		//list<String> LST_DADAN_CALL           = listCreate(String,"016","017","022", "086", "091","092","093" ); 
		this.LST_DADAN_CALL = new ArrayList<String>();
		this.LST_DADAN_CALL.add("016");
		this.LST_DADAN_CALL.add("017");
		this.LST_DADAN_CALL.add("022");
		this.LST_DADAN_CALL.add("086");
		this.LST_DADAN_CALL.add("091");
		this.LST_DADAN_CALL.add("092");
		this.LST_DADAN_CALL.add("093");
		
		//list<String> LST_DFREE_CALL       = listCreate(String, "016","098");
		this.LST_DFREE_CALL = new ArrayList<String>();
		this.LST_DFREE_CALL.add("016");
		this.LST_DFREE_CALL.add("098");
		
		//list<string> LST_SSCODE_MPBX    = listCreate(string,"228","229");
		this.LST_SSCODE_MPBX = new ArrayList<String>();
		this.LST_SSCODE_MPBX.add("228");
		this.LST_SSCODE_MPBX.add("229");
		

	}
	
	public boolean intContains(int[] intList, int value) {
//		log.debug("value={}", value);
		for (int i=0; i < intList.length; i++) {
			//if (intList[i] == Integer.parseInt(value.trim())) {
			if (intList[i] == value) {
				return true;
			}
		}
		
		return false;
	}
	
	public int[] getLST_CDF_NORMAL() {
		return LST_CDF_NORMAL;
	}



	public void setLST_CDF_NORMAL(int[] lST_CDF_NORMAL) {
		LST_CDF_NORMAL = lST_CDF_NORMAL;
	}



	public int[] getLST_CDF_UNSUCCESS() {
		return LST_CDF_UNSUCCESS;
	}



	public void setLST_CDF_UNSUCCESS(int[] lST_CDF_UNSUCCESS) {
		LST_CDF_UNSUCCESS = lST_CDF_UNSUCCESS;
	}



	public List<String> getLST_VOLTE_4G_CALL() {
		return LST_VOLTE_4G_CALL;
	}



	public void setLST_VOLTE_4G_CALL(List<String> lST_VOLTE_4G_CALL) {
		LST_VOLTE_4G_CALL = lST_VOLTE_4G_CALL;
	}



	public List<String> getLST_MOBILE_PREFIX() {
		return LST_MOBILE_PREFIX;
	}



	public void setLST_MOBILE_PREFIX(List<String> lST_MOBILE_PREFIX) {
		LST_MOBILE_PREFIX = lST_MOBILE_PREFIX;
	}



	public List<String> getLST_SSCODE_CF() {
		return LST_SSCODE_CF;
	}



	public void setLST_SSCODE_CF(List<String> lST_SSCODE_CF) {
		LST_SSCODE_CF = lST_SSCODE_CF;
	}



	public List<String> getLST_SSCODE_VMS() {
		return LST_SSCODE_VMS;
	}



	public void setLST_SSCODE_VMS(List<String> lST_SSCODE_VMS) {
		LST_SSCODE_VMS = lST_SSCODE_VMS;
	}



	public List<String> getLST_DADAN_CALL() {
		return LST_DADAN_CALL;
	}



	public void setLST_DADAN_CALL(List<String> lST_DADAN_CALL) {
		LST_DADAN_CALL = lST_DADAN_CALL;
	}



	public List<String> getLST_DFREE_CALL() {
		return LST_DFREE_CALL;
	}



	public void setLST_DFREE_CALL(List<String> lST_DFREE_CALL) {
		LST_DFREE_CALL = lST_DFREE_CALL;
	}



	public List<String> getLST_BIGI_CDR() {
		return LST_BIGI_CDR;
	}

	public void setLST_BIGI_CDR(List<String> lST_BIGI_CDR) {
		LST_BIGI_CDR = lST_BIGI_CDR;
	}

	public List<String> getLST_CALL_FORWARDING() {
		return LST_CALL_FORWARDING;
	}

	public void setLST_CALL_FORWARDING(List<String> lST_CALL_FORWARDING) {
		LST_CALL_FORWARDING = lST_CALL_FORWARDING;
	}

	public List<String> getLST_SSCODE_MPBX() {
		return LST_SSCODE_MPBX;
	}

	public void setLST_SSCODE_MPBX(List<String> lST_SSCODE_MPBX) {
		LST_SSCODE_MPBX = lST_SSCODE_MPBX;
	}

	public boolean isbIsBIGI_CDR() {
		return bIsBIGI_CDR;
	}
	public void setbIsBIGI_CDR(boolean bIsBIGI_CDR) {
		this.bIsBIGI_CDR = bIsBIGI_CDR;
	}
	
	public boolean isbIsPPS_CALL() {
		return bIsPPS_CALL;
	}

	public void setbIsPPS_CALL(boolean bIsPPS_CALL) {
		this.bIsPPS_CALL = bIsPPS_CALL;
	}

	public boolean isbIsCDF_NORMAL() {
		return bIsCDF_NORMAL;
	}
	public void setbIsCDF_NORMAL(boolean bIsCDF_NORMAL) {
		this.bIsCDF_NORMAL = bIsCDF_NORMAL;
	}
	public boolean isbIsCDF_ABNORMAL() {
		return bIsCDF_ABNORMAL;
	}
	public void setbIsCDF_ABNORMAL(boolean bIsCDF_ABNORMAL) {
		this.bIsCDF_ABNORMAL = bIsCDF_ABNORMAL;
	}
	public boolean isbIsCDF_UNSUCCESS() {
		return bIsCDF_UNSUCCESS;
	}
	public void setbIsCDF_UNSUCCESS(boolean bIsCDF_UNSUCCESS) {
		this.bIsCDF_UNSUCCESS = bIsCDF_UNSUCCESS;
	}
	public boolean isbIsFPS_CALL() {
		return bIsFPS_CALL;
	}
	public void setbIsFPS_CALL(boolean bIsFPS_CALL) {
		this.bIsFPS_CALL = bIsFPS_CALL;
	}
	public boolean isbIsIM_3G() {
		return bIsIM_3G;
	}
	public void setbIsIM_3G(boolean bIsIM_3G) {
		this.bIsIM_3G = bIsIM_3G;
	}
	public boolean isbIsIM_4G() {
		return bIsIM_4G;
	}
	public void setbIsIM_4G(boolean bIsIM_4G) {
		this.bIsIM_4G = bIsIM_4G;
	}
	public boolean isbIsPSVT_3G_CALL() {
		return bIsPSVT_3G_CALL;
	}
	public void setbIsPSVT_3G_CALL(boolean bIsPSVT_3G_CALL) {
		this.bIsPSVT_3G_CALL = bIsPSVT_3G_CALL;
	}
	public boolean isbIsPSVT_4G_CALL() {
		return bIsPSVT_4G_CALL;
	}
	public void setbIsPSVT_4G_CALL(boolean bIsPSVT_4G_CALL) {
		this.bIsPSVT_4G_CALL = bIsPSVT_4G_CALL;
	}
	public boolean isbIsVOLTE_4G_CALL() {
		return bIsVOLTE_4G_CALL;
	}
	public void setbIsVOLTE_4G_CALL(boolean bIsVOLTE_4G_CALL) {
		this.bIsVOLTE_4G_CALL = bIsVOLTE_4G_CALL;
	}
	public boolean isbIsVS_3G_CALL() {
		return bIsVS_3G_CALL;
	}
	public void setbIsVS_3G_CALL(boolean bIsVS_3G_CALL) {
		this.bIsVS_3G_CALL = bIsVS_3G_CALL;
	}
	public boolean isbIsVS_4G_CALL() {
		return bIsVS_4G_CALL;
	}
	public void setbIsVS_4G_CALL(boolean bIsVS_4G_CALL) {
		this.bIsVS_4G_CALL = bIsVS_4G_CALL;
	}
	public boolean isbIsTIME_CDR() {
		return bIsTIME_CDR;
	}
	public void setbIsTIME_CDR(boolean bIsTIME_CDR) {
		this.bIsTIME_CDR = bIsTIME_CDR;
	}
	public boolean isbIsOCR_CDR() {
		return bIsOCR_CDR;
	}
	public void setbIsOCR_CDR(boolean bIsOCR_CDR) {
		this.bIsOCR_CDR = bIsOCR_CDR;
	}
	public boolean isbIsWIN_CDR() {
		return bIsWIN_CDR;
	}
	public void setbIsWIN_CDR(boolean bIsWIN_CDR) {
		this.bIsWIN_CDR = bIsWIN_CDR;
	}
	public boolean isbIsWIN_CHARGEABLE() {
		return bIsWIN_CHARGEABLE;
	}
	public void setbIsWIN_CHARGEABLE(boolean bIsWIN_CHARGEABLE) {
		this.bIsWIN_CHARGEABLE = bIsWIN_CHARGEABLE;
	}
	public boolean isbIsHD_3G_VOICE() {
		return bIsHD_3G_VOICE;
	}
	public void setbIsHD_3G_VOICE(boolean bIsHD_3G_VOICE) {
		this.bIsHD_3G_VOICE = bIsHD_3G_VOICE;
	}
	public boolean isbIsHD_4G_VOICE() {
		return bIsHD_4G_VOICE;
	}
	public void setbIsHD_4G_VOICE(boolean bIsHD_4G_VOICE) {
		this.bIsHD_4G_VOICE = bIsHD_4G_VOICE;
	}
	public boolean isbIsWIN_SRV_BG() {
		return bIsWIN_SRV_BG;
	}
	public void setbIsWIN_SRV_BG(boolean bIsWIN_SRV_BG) {
		this.bIsWIN_SRV_BG = bIsWIN_SRV_BG;
	}
	public boolean isbIsNO_CHARGE_CDR() {
		return bIsNO_CHARGE_CDR;
	}
	public void setbIsNO_CHARGE_CDR(boolean bIsNO_CHARGE_CDR) {
		this.bIsNO_CHARGE_CDR = bIsNO_CHARGE_CDR;
	}
	public boolean isbIsTIME_CDR_001() {
		return bIsTIME_CDR_001;
	}
	public void setbIsTIME_CDR_001(boolean bIsTIME_CDR_001) {
		this.bIsTIME_CDR_001 = bIsTIME_CDR_001;
	}
	public boolean isbIsOCR_CDR_001() {
		return bIsOCR_CDR_001;
	}
	public void setbIsOCR_CDR_001(boolean bIsOCR_CDR_001) {
		this.bIsOCR_CDR_001 = bIsOCR_CDR_001;
	}
	public boolean isbIsEMS_CALLED() {
		return bIsEMS_CALLED;
	}
	public void setbIsEMS_CALLED(boolean bIsEMS_CALLED) {
		this.bIsEMS_CALLED = bIsEMS_CALLED;
	}
	public boolean isbIsVISUAL_CALL() {
		return bIsVISUAL_CALL;
	}
	public boolean isbIsVOICE_CALL() {
		return bIsVOICE_CALL;
	}
	public void setbIsVISUAL_CALL(boolean bIsVISUAL_CALL) {
		this.bIsVISUAL_CALL = bIsVISUAL_CALL;
	}
	public void setbIsVOICE_CALL(boolean bIsVOICE_CALL) {
		this.bIsVOICE_CALL = bIsVOICE_CALL;
	}
	public boolean isbIsABNORMAL_COMP() {
		return bIsABNORMAL_COMP;
	}
	public void setbIsABNORMAL_COMP(boolean bIsABNORMAL_COMP) {
		this.bIsABNORMAL_COMP = bIsABNORMAL_COMP;
	}
	public boolean isbIsNORMAL_COMP() {
		return bIsNORMAL_COMP;
	}
	public void setbIsNORMAL_COMP(boolean bIsNORMAL_COMP) {
		this.bIsNORMAL_COMP = bIsNORMAL_COMP;
	}
	public boolean isbIsUNSUCCESS_COMP() {
		return bIsUNSUCCESS_COMP;
	}
	public void setbIsUNSUCCESS_COMP(boolean bIsUNSUCCESS_COMP) {
		this.bIsUNSUCCESS_COMP = bIsUNSUCCESS_COMP;
	}
	
	public boolean isbIsCALL_FORWARDING() {
		return bIsCALL_FORWARDING;
	}

	public boolean isbIsKTF_CSUB() {
		return bIsKTF_CSUB;
	}

	public boolean isbIsCALLED_PARTY_PAY_CALL() {
		return bIsCALLED_PARTY_PAY_CALL;
	}

	public boolean isbIsWIN_CHARGED() {
		return bIsWIN_CHARGED;
	}

	public boolean isbIsSPECIAL_NUMBER() {
		return bIsSPECIAL_NUMBER;
	}

	public boolean isbIsChargeFeaturesAir() {
		return bIsChargeFeaturesAir;
	}

	public boolean isbIsChargeFeaturesOther() {
		return bIsChargeFeaturesOther;
	}

	public boolean isbIsDROPPED_RECORD() {
		return bIsDROPPED_RECORD;
	}

	public boolean isbIsERROR_FILE() {
		return bIsERROR_FILE;
	}

	public boolean isbIsDURATION_3SEC_CHECK() {
		return bIsDURATION_3SEC_CHECK;
	}

	public boolean isbIsDURATION_5SEC_CHECK() {
		return bIsDURATION_5SEC_CHECK;
	}

	public boolean isbIsDURATION_10SEC_CHECK() {
		return bIsDURATION_10SEC_CHECK;
	}

	public boolean isbIsDADAN_CALL() {
		return bIsDADAN_CALL;
	}

	public boolean isbIsINTERNATIONAL() {
		return bIsINTERNATIONAL;
	}

	public boolean isbIsPREFIX_FREEZONE() {
		return bIsPREFIX_FREEZONE;
	}

	public boolean isbIsINTL_SERVICE_PROVIDER() {
		return bIsINTL_SERVICE_PROVIDER;
	}

	public boolean isbIsSAME_5COUNTRY() {
		return bIsSAME_5COUNTRY;
	}

	public boolean isbIsTIOI_LIST() {
		return bIsTIOI_LIST;
	}

	public boolean isbIsTWONUMBER_MO() {
		return bIsTWONUMBER_MO;
	}

	public boolean isbIsKT1577() {
		return bIsKT1577;
	}

	public boolean isbIsExistCarriers() {
		return bIsExistCarriers;
	}

	public boolean isbIsRENTAL_CALL() {
		return bIsRENTAL_CALL;
	}

	public boolean isbIsWSTYPE_RANGE() {
		return bIsWSTYPE_RANGE;
	}

	public boolean isbIsSSCODE_MPBX() {
		return bIsSSCODE_MPBX;
	}

	public void setbIsCALL_FORWARDING(boolean bIsCALL_FORWARDING) {
		this.bIsCALL_FORWARDING = bIsCALL_FORWARDING;
	}

	public void setbIsKTF_CSUB(boolean bIsKTF_CSUB) {
		this.bIsKTF_CSUB = bIsKTF_CSUB;
	}

	public void setbIsCALLED_PARTY_PAY_CALL(boolean bIsCALLED_PARTY_PAY_CALL) {
		this.bIsCALLED_PARTY_PAY_CALL = bIsCALLED_PARTY_PAY_CALL;
	}

	public void setbIsWIN_CHARGED(boolean bIsWIN_CHARGED) {
		this.bIsWIN_CHARGED = bIsWIN_CHARGED;
	}

	public void setbIsSPECIAL_NUMBER(boolean bIsSPECIAL_NUMBER) {
		this.bIsSPECIAL_NUMBER = bIsSPECIAL_NUMBER;
	}

	public void setbIsChargeFeaturesAir(boolean bIsChargeFeaturesAir) {
		this.bIsChargeFeaturesAir = bIsChargeFeaturesAir;
	}

	public void setbIsChargeFeaturesOther(boolean bIsChargeFeaturesOther) {
		this.bIsChargeFeaturesOther = bIsChargeFeaturesOther;
	}

	public void setbIsDROPPED_RECORD(boolean bIsDROPPED_RECORD) {
		this.bIsDROPPED_RECORD = bIsDROPPED_RECORD;
	}

	public void setbIsERROR_FILE(boolean bIsERROR_FILE) {
		this.bIsERROR_FILE = bIsERROR_FILE;
	}

	public void setbIsDURATION_3SEC_CHECK(boolean bIsDURATION_3SEC_CHECK) {
		this.bIsDURATION_3SEC_CHECK = bIsDURATION_3SEC_CHECK;
	}

	public void setbIsDURATION_5SEC_CHECK(boolean bIsDURATION_5SEC_CHECK) {
		this.bIsDURATION_5SEC_CHECK = bIsDURATION_5SEC_CHECK;
	}

	public void setbIsDURATION_10SEC_CHECK(boolean bIsDURATION_10SEC_CHECK) {
		this.bIsDURATION_10SEC_CHECK = bIsDURATION_10SEC_CHECK;
	}

	public void setbIsDADAN_CALL(boolean bIsDADAN_CALL) {
		this.bIsDADAN_CALL = bIsDADAN_CALL;
	}

	public void setbIsINTERNATIONAL(boolean bIsINTERNATIONAL) {
		this.bIsINTERNATIONAL = bIsINTERNATIONAL;
	}

	public void setbIsPREFIX_FREEZONE(boolean bIsPREFIX_FREEZONE) {
		this.bIsPREFIX_FREEZONE = bIsPREFIX_FREEZONE;
	}

	public void setbIsINTL_SERVICE_PROVIDER(boolean bIsINTL_SERVICE_PROVIDER) {
		this.bIsINTL_SERVICE_PROVIDER = bIsINTL_SERVICE_PROVIDER;
	}

	public void setbIsSAME_5COUNTRY(boolean bIsSAME_5COUNTRY) {
		this.bIsSAME_5COUNTRY = bIsSAME_5COUNTRY;
	}

	public void setbIsTIOI_LIST(boolean bIsTIOI_LIST) {
		this.bIsTIOI_LIST = bIsTIOI_LIST;
	}

	public void setbIsTWONUMBER_MO(boolean bIsTWONUMBER_MO) {
		this.bIsTWONUMBER_MO = bIsTWONUMBER_MO;
	}

	public void setbIsKT1577(boolean bIsKT1577) {
		this.bIsKT1577 = bIsKT1577;
	}

	public void setbIsExistCarriers(boolean bIsExistCarriers) {
		this.bIsExistCarriers = bIsExistCarriers;
	}

	public void setbIsRENTAL_CALL(boolean bIsRENTAL_CALL) {
		this.bIsRENTAL_CALL = bIsRENTAL_CALL;
	}

	public void setbIsWSTYPE_RANGE(boolean bIsWSTYPE_RANGE) {
		this.bIsWSTYPE_RANGE = bIsWSTYPE_RANGE;
	}

	public void setbIsSSCODE_MPBX(boolean bIsSSCODE_MPBX) {
		this.bIsSSCODE_MPBX = bIsSSCODE_MPBX;
	}

	/////////////////////////////////////////////////////
	///        Getter
	/////////////////////////////////////////////////////
	public String getFpsCall() {
		return FPS_CALL;
	}

	public String getPpsCall() {
		return PPS_CALL;
	}

	public String getRfreeCall() {
		return RFREE_CALL;
	}

	public String getRrateCall() {
		return RRATE_CALL;
	}

	public String getOcrCdr() {
		return OCR_CDR;
	}

	public String getWinCdr() {
		return WIN_CDR;
	}

	public String getIm3g() {
		return IM_3G;
	}

	public String getIm4g() {
		return IM_4G;
	}

	public String getPsvt3gCall() {
		return PSVT_3G_CALL;
	}

	public String getPsvt4gCall() {
		return PSVT_4G_CALL;
	}

	public String getVs3gCall() {
		return VS_3G_CALL;
	}

	public String getVs4gCall() {
		return VS_4G_CALL;
	}

	public String getBfreeCall() {
		return BFREE_CALL;
	}

	public String getBfrevsCall() {
		return BFREVS_CALL;
	}

	public String getBlimitCall() {
		return BLIMIT_CALL;
	}

	public String getBmateCall() {
		return BMATE_CALL;
	}

	public String getBrateCall() {
		return BRATE_CALL;
	}

	public String getBrate1Call() {
		return BRATE1_CALL;
	}

	public String getBrrcfCall() {
		return BRRCF_CALL;
	}

	public String getBsmocCall() {
		return BSMOC_CALL;
	}

	public String getBoverCall() {
		return BOVER_CALL;
	}

	public String getFnsCall() {
		return FNS_CALL;
	}

	public String getIpCall() {
		return IP_CALL;
	}

	public String getSmsbgCall() {
		return SMSBG_CALL;
	}

	public String getTelcoinCall() {
		return TELCOIN_CALL;
	}

	public String getYfcardCall() {
		return YFCARD_CALL;
	}

	public String getYfreeCall() {
		return YFREE_CALL;
	}

	public String getYrateCall() {
		return YRATE_CALL;
	}

	public String getSpnm() {
		return SPNM;
	}

	public String getSpip() {
		return SPIP;
	}

	public String getDummyDate() {
		return DUMMY_DATE;
	}

	public String getDummyTime0() {
		return DUMMY_TIME0;
	}

	public String getDummyTime1() {
		return DUMMY_TIME1;
	}

	public String getCdfNormV() {
		return CDF_NORM_V;
	}

	public String getCdfUnsuccessV() {
		return CDF_UNSUCCESS_V;
	}

	public String getCdfAbnormV() {
		return CDF_ABNORM_V;
	}

	public String getKt1577() {
		return KT_1577;
	}

	public String getKt114() {
		return KT114;
	}

	public String getSscodeFreeZone() {
		return SSCODE_FREE_ZONE;
	}

	public String getSscodeTwonumber() {
		return SSCODE_TWONUMBER;
	}

	public String getSscodeGc() {
		return SSCODE_GC;
	}

	public String getSscodeVmsfw() {
		return SSCODE_VMSFW;
	}
	
	public String getSscodeMpbxFree() {
		return SSCODE_MPBX_FREE;
	}

	public String getsCallingSvcNo() {
		return sCallingSvcNo;
	}

	public String getsChrgSvcNo() {
		return sChrgSvcNo;
	}

	public void setsCallingSvcNo(String sCallingSvcNo) {
		this.sCallingSvcNo = sCallingSvcNo;
	}

	public void setsChrgSvcNo(String sChrgSvcNo) {
		this.sChrgSvcNo = sChrgSvcNo;
	}

	public String getsCalledSvcNo() {
		return sCalledSvcNo;
	}

	public String getsWorkCalledSvcNo() {
		return sWorkCalledSvcNo;
	}

	public String getsDrewCalledSvcNo() {
		return sDrewCalledSvcNo;
	}

	public String getsCallEndDate() {
		return sCallEndDate;
	}

	public String getsCallEndTime() {
		return sCallEndTime;
	}

	public String getsCallStartDate() {
		return sCallStartDate;
	}

	public String getsCallStartTime() {
		return sCallStartTime;
	}

	public String getSvmsident() {
		return sVmsIdent;
	}

	public String getSvmsdivision() {
		return sVmsDivision;
	}

	public String getSipasident() {
		return sIpasIdent;
	}

	public String getSipasdivision() {
		return sIpasDivision;
	}

	public void setsCalledSvcNo(String sCalledSvcNo) {
		this.sCalledSvcNo = sCalledSvcNo;
	}

	public void setsWorkCalledSvcNo(String sWorkCalledSvcNo) {
		this.sWorkCalledSvcNo = sWorkCalledSvcNo;
	}

	public void setsDrewCalledSvcNo(String sDrewCalledSvcNo) {
		this.sDrewCalledSvcNo = sDrewCalledSvcNo;
	}

	public void setsCallEndDate(String sCallEndDate) {
		this.sCallEndDate = sCallEndDate;
	}

	public void setsCallEndTime(String sCallEndTime) {
		this.sCallEndTime = sCallEndTime;
	}

	public void setsCallStartDate(String sCallStartDate) {
		this.sCallStartDate = sCallStartDate;
	}

	public void setsCallStartTime(String sCallStartTime) {
		this.sCallStartTime = sCallStartTime;
	}

	
	public String getsCallStartDateTime() {
		return sCallStartDateTime;
	}

	public void setsCallStartDateTime(String sCallStartDateTime) {
		this.sCallStartDateTime = sCallStartDateTime;
	}

	public String getsCallGubun() {
		return sCallGubun;
	}

	public void setsCallGubun(String sCallGubun) {
		this.sCallGubun = sCallGubun;
	}

	public String getsNpInd() {
		return sNpInd;
	}

	public String getsNpOperator() {
		return sNpOperator;
	}

	public void setsNpInd(String sNpInd) {
		this.sNpInd = sNpInd;
	}

	public void setsNpOperator(String sNpOperator) {
		this.sNpOperator = sNpOperator;
	}

	public String getsTermArea() {
		return sTermArea;
	}

	public void setsTermArea(String sTermArea) {
		this.sTermArea = sTermArea;
	}

	public String getsInNetCallYn() {
		return sInNetCallYn;
	}

	public void setsInNetCallYn(String sInNetCallYn) {
		this.sInNetCallYn = sInNetCallYn;
	}

	public String getsKeyVal() {
		return sKeyVal;
	}

	public void setsKeyVal(String sKeyVal) {
		this.sKeyVal = sKeyVal;
	}

	public String getsProdFormatId() {
		return sProdFormatId;
	}

	public String getsProdCdType() {
		return sProdCdType;
	}

	public String getsInsProdType() {
		return sInsProdType;
	}

	public void setsProdFormatId(String sProdFormatId) {
		this.sProdFormatId = sProdFormatId;
	}

	public void setsProdCdType(String sProdCdType) {
		this.sProdCdType = sProdCdType;
	}

	public void setsInsProdType(String sInsProdType) {
		this.sInsProdType = sInsProdType;
	}

	public String getsProdNumber() {
		return sProdNumber;
	}

	public void setsProdNumber(String sProdNumber) {
		this.sProdNumber = sProdNumber;
	}

	public String getsWinSvcType() {
		return sWinSvcType;
	}

	public void setsWinSvcType(String sWinSvcType) {
		this.sWinSvcType = sWinSvcType;
	}

	public String getsCalledPartyPayCall() {
		return sCalledPartyPayCall;
	}

	public String getsCallingDropIndi() {
		return sCallingDropIndi;
	}

	public String getsDeduct() {
		return sDeduct;
	}

	public int getiDeductUseTime() {
		return iDeductUseTime;
	}

	public int getiUseTime() {
		return iUseTime;
	}

	public String getsProdCd() {
		return sProdCd;
	}

	public String getsProdDropCall() {
		return sProdDropCall;
	}

	public String getsFormatId() {
		return sFormatId;
	}

	public String getsFpsResult() {
		return sFpsResult;
	}

	public String getsFileType() {
		return sFileType;
	}

	public String getsMutualConnType() {
		return sMutualConnType;
	}

	public String getsIntlNxx() {
		return sIntlNxx;
	}

	public String getsCallingIp() {
		return sCallingIp;
	}

	public String getsIpasResult() {
		return sIpasResult;
	}

	public String getsProdCdIndi() {
		return sProdCdIndi;
	}

	public String getsMpsFeatureCd() {
		return sMpsFeatureCd;
	}

	public int getiOriginFileNo() {
		return iOriginFileNo;
	}

	public String getsNpSettFeature() {
		return sNpSettFeature;
	}

	public String getsNpSettProvider() {
		return sNpSettProvider;
	}

	public String getsOriginFileDatetime() {
		return sOriginFileDatetime;
	}

	public int getiOrigFileSeqNo() {
		return iOrigFileSeqNo;
	}

	public String getsOtrFlag() {
		return sOtrFlag;
	}

	public String getsProfileResult() {
		return sProfileResult;
	}

	public int getiRecvCnt() {
		return iRecvCnt;
	}

	public String getsSensorId() {
		return sSensorId;
	}

	public String getsSpProdCd() {
		return sSpProdCd;
	}

	public String getsSpType() {
		return sSpType;
	}

	public String getsSpIndi() {
		return sSpIndi;
	}

	public int getiSpecialNoId() {
		return iSpecialNoId;
	}

	public String getsSpNumberType() {
		return sSpNumberType;
	}

	public String getsSpnSpNmTp() {
		return sSpnSpNmTp;
	}

	public String getsSpNoAtIndi() {
		return sSpNoAtIndi;
	}

	public String getsSscode() {
		return sSscode;
	}

	public String getsSwitchId() {
		return sSwitchId;
	}

	public String getsTernInterOpId() {
		return sTernInterOpId;
	}

	public String getsChrgCallYn() {
		return sChrgCallYn;
	}

	public String getsVmsAccess() {
		return sVmsAccess;
	}

	public String getsVmsInd() {
		return sVmsInd;
	}

	public String getsVmsResult() {
		return sVmsResult;
	}

	public String getsTermCode() {
		return sTermCode;
	}

	public String getsSpnSpInd() {
		return sSpnSpInd;
	}

	public int getiDurSecPrepaid() {
		return iDurSecPrepaid;
	}

	public String getsSettCall() {
		return sSettCall;
	}

	public String getsSettDropInd() {
		return sSettDropInd;
	}

	public String getsSettDropTeen() {
		return sSettDropTeen;
	}

	public String getsSpnDpClInd() {
		return sSpnDpClInd;
	}

	public String getsCfCallInd() {
		return sCfCallInd;
	}

	public String getsCallingImsi() {
		return sCallingImsi;
	}

	public String getsSpnAcInd() {
		return sSpnAcInd;
	}

	public String getsCallToStateCode() {
		return sCallToStateCode;
	}

	public String getsSettlFileName() {
		return sSettlFileName;
	}

	public String getsSettlCarrier() {
		return sSettlCarrier;
	}

	public String getsSpNoIndi() {
		return sSpNoIndi;
	}

	public String getsSpnPpsDpClInd() {
		return sSpnPpsDpClInd;
	}

	public String getsSpnAtFtrInd() {
		return sSpnAtFtrInd;
	}

	public String getsCalledArea() {
		return sCalledArea;
	}

	public String getsSettlFeatureCd() {
		return sSettlFeatureCd;
	}

	public String getsChrgOtherNo() {
		return sChrgOtherNo;
	}

	public String getsNationCd() {
		return sNationCd;
	}

	public String getsSecondNationCd() {
		return sSecondNationCd;
	}

	public String getsFeatureDropIndi() {
		return sFeatureDropIndi;
	}

	public void setsCalledPartyPayCall(String sCalledPartyPayCall) {
		this.sCalledPartyPayCall = sCalledPartyPayCall;
	}

	public void setsCallingDropIndi(String sCallingDropIndi) {
		this.sCallingDropIndi = sCallingDropIndi;
	}

	public void setsDeduct(String sDeduct) {
		this.sDeduct = sDeduct;
	}

	public void setiDeductUseTime(int iDeductUseTime) {
		this.iDeductUseTime = iDeductUseTime;
	}

	public void setiUseTime(int iUseTime) {
		this.iUseTime = iUseTime;
	}

	public void setsProdCd(String sProdCd) {
		this.sProdCd = sProdCd;
	}

	public void setsProdDropCall(String sProdDropCall) {
		this.sProdDropCall = sProdDropCall;
	}

	public void setsFormatId(String sFormatId) {
		this.sFormatId = sFormatId;
	}

	public void setsFpsResult(String sFpsResult) {
		this.sFpsResult = sFpsResult;
	}

	public void setsFileType(String sFileType) {
		this.sFileType = sFileType;
	}

	public void setsMutualConnType(String sMutualConnType) {
		this.sMutualConnType = sMutualConnType;
	}

	public void setsIntlNxx(String sIntlNxx) {
		this.sIntlNxx = sIntlNxx;
	}

	public void setsCallingIp(String sCallingIp) {
		this.sCallingIp = sCallingIp;
	}

	public void setsIpasResult(String sIpasResult) {
		this.sIpasResult = sIpasResult;
	}

	public void setsProdCdIndi(String sProdCdIndi) {
		this.sProdCdIndi = sProdCdIndi;
	}

	public void setsMpsFeatureCd(String sMpsFeatureCd) {
		this.sMpsFeatureCd = sMpsFeatureCd;
	}

	public void setiOriginFileNo(int iOriginFileNo) {
		this.iOriginFileNo = iOriginFileNo;
	}

	public void setsNpSettFeature(String sNpSettFeature) {
		this.sNpSettFeature = sNpSettFeature;
	}

	public void setsNpSettProvider(String sNpSettProvider) {
		this.sNpSettProvider = sNpSettProvider;
	}

	public void setsOriginFileDatetime(String sOriginFileDatetime) {
		this.sOriginFileDatetime = sOriginFileDatetime;
	}

	public void setiOrigFileSeqNo(int iOrigFileSeqNo) {
		this.iOrigFileSeqNo = iOrigFileSeqNo;
	}

	public void setsOtrFlag(String sOtrFlag) {
		this.sOtrFlag = sOtrFlag;
	}

	public void setsProfileResult(String sProfileResult) {
		this.sProfileResult = sProfileResult;
	}

	public void setiRecvCnt(int iRecvCnt) {
		this.iRecvCnt = iRecvCnt;
	}

	public void setsSensorId(String sSensorId) {
		this.sSensorId = sSensorId;
	}

	public void setsSpProdCd(String sSpProdCd) {
		this.sSpProdCd = sSpProdCd;
	}

	public void setsSpType(String sSpType) {
		this.sSpType = sSpType;
	}

	public void setsSpIndi(String sSpIndi) {
		this.sSpIndi = sSpIndi;
	}

	public void setiSpecialNoId(int iSpecialNoId) {
		this.iSpecialNoId = iSpecialNoId;
	}

	public void setsSpNumberType(String sSpNumberType) {
		this.sSpNumberType = sSpNumberType;
	}

	public void setsSpnSpNmTp(String sSpnSpNmTp) {
		this.sSpnSpNmTp = sSpnSpNmTp;
	}

	public void setsSpNoAtIndi(String sSpNoAtIndi) {
		this.sSpNoAtIndi = sSpNoAtIndi;
	}

	public void setsSscode(String sSscode) {
		this.sSscode = sSscode;
	}

	public void setsSwitchId(String sSwitchId) {
		this.sSwitchId = sSwitchId;
	}

	public void setsTernInterOpId(String sTernInterOpId) {
		this.sTernInterOpId = sTernInterOpId;
	}

	public void setsChrgCallYn(String sChrgCallYn) {
		this.sChrgCallYn = sChrgCallYn;
	}

	public void setsVmsAccess(String sVmsAccess) {
		this.sVmsAccess = sVmsAccess;
	}

	public void setsVmsInd(String sVmsInd) {
		this.sVmsInd = sVmsInd;
	}

	public void setsVmsResult(String sVmsResult) {
		this.sVmsResult = sVmsResult;
	}

	public void setsTermCode(String sTermCode) {
		this.sTermCode = sTermCode;
	}

	public void setsSpnSpInd(String sSpnSpInd) {
		this.sSpnSpInd = sSpnSpInd;
	}

	public void setiDurSecPrepaid(int iDurSecPrepaid) {
		this.iDurSecPrepaid = iDurSecPrepaid;
	}

	public void setsSettCall(String sSettCall) {
		this.sSettCall = sSettCall;
	}

	public void setsSettDropInd(String sSettDropInd) {
		this.sSettDropInd = sSettDropInd;
	}

	public void setsSettDropTeen(String sSettDropTeen) {
		this.sSettDropTeen = sSettDropTeen;
	}

	public void setsSpnDpClInd(String sSpnDpClInd) {
		this.sSpnDpClInd = sSpnDpClInd;
	}

	public void setsCfCallInd(String sCfCallInd) {
		this.sCfCallInd = sCfCallInd;
	}

	public void setsCallingImsi(String sCallingImsi) {
		this.sCallingImsi = sCallingImsi;
	}

	public void setsSpnAcInd(String sSpnAcInd) {
		this.sSpnAcInd = sSpnAcInd;
	}

	public void setsCallToStateCode(String sCallToStateCode) {
		this.sCallToStateCode = sCallToStateCode;
	}

	public void setsSettlFileName(String sSettlFileName) {
		this.sSettlFileName = sSettlFileName;
	}

	public void setsSettlCarrier(String sSettlCarrier) {
		this.sSettlCarrier = sSettlCarrier;
	}

	public void setsSpNoIndi(String sSpNoIndi) {
		this.sSpNoIndi = sSpNoIndi;
	}

	public void setsSpnPpsDpClInd(String sSpnPpsDpClInd) {
		this.sSpnPpsDpClInd = sSpnPpsDpClInd;
	}

	public void setsSpnAtFtrInd(String sSpnAtFtrInd) {
		this.sSpnAtFtrInd = sSpnAtFtrInd;
	}

	public void setsCalledArea(String sCalledArea) {
		this.sCalledArea = sCalledArea;
	}

	public void setsSettlFeatureCd(String sSettlFeatureCd) {
		this.sSettlFeatureCd = sSettlFeatureCd;
	}

	public void setsChrgOtherNo(String sChrgOtherNo) {
		this.sChrgOtherNo = sChrgOtherNo;
	}

	public void setsNationCd(String sNationCd) {
		this.sNationCd = sNationCd;
	}

	public void setsSecondNationCd(String sSecondNationCd) {
		this.sSecondNationCd = sSecondNationCd;
	}

	public void setsFeatureDropIndi(String sFeatureDropIndi) {
		this.sFeatureDropIndi = sFeatureDropIndi;
	}
	
	

//	public String getsErrCd() {
//		return sErrCd;
//	}
//
//	public void setsErrCd(String sErrCd) {
//		this.sErrCd = sErrCd;
//	}
//
//	public String getsErrLevelDivCd() {
//		return sErrLevelDivCd;
//	}
//
//	public void setsErrLevelDivCd(String sErrLevelDivCd) {
//		this.sErrLevelDivCd = sErrLevelDivCd;
//	}
//
//	public int getiErrPriority() {
//		return iErrPriority;
//	}
//
//	public void setiErrPriority(int iErrPriority) {
//		this.iErrPriority = iErrPriority;
//	}
//
//	public String getsOldErrCd() {
//		return sOldErrCd;
//	}
//
//	public void setsOldErrCd(String sOldErrCd) {
//		this.sOldErrCd = sOldErrCd;
//	}
	
	

	public String getsMzFileId() {
		return sMzFileId;
	}

	public void setsMzFileId(String sMzFileId) {
		this.sMzFileId = sMzFileId;
	}

	public int getsCdrSeq() {
		return sCdrSeq;
	}

	public void setsCdrSeq(int sCdrSeq) {
		this.sCdrSeq = sCdrSeq;
	}

	public String getsCallingNo() {
		return sCallingNo;
	}

	public void setsCallingNo(String sCallingNo) {
		this.sCallingNo = sCallingNo;
	}

	public String getsCalledNo() {
		return sCalledNo;
	}

	public void setsCalledNo(String sCalledNo) {
		this.sCalledNo = sCalledNo;
	}

	public String getsMzErrorLevelCd() {
		return sMzErrorLevelCd;
	}

	public void setsMzErrorLevelCd(String sMzErrorLevelCd) {
		this.sMzErrorLevelCd = sMzErrorLevelCd;
	}

	public String getsMzErrorCd01() {
		return sMzErrorCd01;
	}

	public void setsMzErrorCd01(String sMzErrorCd01) {
		this.sMzErrorCd01 = sMzErrorCd01;
	}

	public String getsMzErrorCd02() {
		return sMzErrorCd02;
	}

	public void setsMzErrorCd02(String sMzErrorCd02) {
		this.sMzErrorCd02 = sMzErrorCd02;
	}

	public String getsMzErrorCd03() {
		return sMzErrorCd03;
	}

	public void setsMzErrorCd03(String sMzErrorCd03) {
		this.sMzErrorCd03 = sMzErrorCd03;
	}

	public String getsMzErrorCd04() {
		return sMzErrorCd04;
	}

	public void setsMzErrorCd04(String sMzErrorCd04) {
		this.sMzErrorCd04 = sMzErrorCd04;
	}

	public String getsMzErrorCd05() {
		return sMzErrorCd05;
	}

	public void setsMzErrorCd05(String sMzErrorCd05) {
		this.sMzErrorCd05 = sMzErrorCd05;
	}

	@Override
	public String toString() {
		return "VoLTEEnrichedCdr [LST_BIGI_CDR=" + LST_BIGI_CDR + ", LST_CALL_FORWARDING=" + LST_CALL_FORWARDING
				+ ", LST_CDF_NORMAL=" + Arrays.toString(LST_CDF_NORMAL) + ", LST_CDF_UNSUCCESS="
				+ Arrays.toString(LST_CDF_UNSUCCESS) + ", LST_VOLTE_4G_CALL=" + LST_VOLTE_4G_CALL
				+ ", LST_MOBILE_PREFIX=" + LST_MOBILE_PREFIX + ", LST_SSCODE_CF=" + LST_SSCODE_CF + ", LST_SSCODE_VMS="
				+ LST_SSCODE_VMS + ", LST_DADAN_CALL=" + LST_DADAN_CALL + ", LST_DFREE_CALL=" + LST_DFREE_CALL
				+ ", LST_SSCODE_MPBX=" + LST_SSCODE_MPBX + ", FPS_CALL=" + FPS_CALL + ", PPS_CALL=" + PPS_CALL
				+ ", RFREE_CALL=" + RFREE_CALL + ", RRATE_CALL=" + RRATE_CALL + ", OCR_CDR=" + OCR_CDR + ", WIN_CDR="
				+ WIN_CDR + ", IM_3G=" + IM_3G + ", IM_4G=" + IM_4G + ", PSVT_3G_CALL=" + PSVT_3G_CALL
				+ ", PSVT_4G_CALL=" + PSVT_4G_CALL + ", VS_3G_CALL=" + VS_3G_CALL + ", VS_4G_CALL=" + VS_4G_CALL
				+ ", BFREE_CALL=" + BFREE_CALL + ", BFREVS_CALL=" + BFREVS_CALL + ", BLIMIT_CALL=" + BLIMIT_CALL
				+ ", BMATE_CALL=" + BMATE_CALL + ", BRATE_CALL=" + BRATE_CALL + ", BRATE1_CALL=" + BRATE1_CALL
				+ ", BRRCF_CALL=" + BRRCF_CALL + ", BSMOC_CALL=" + BSMOC_CALL + ", BOVER_CALL=" + BOVER_CALL
				+ ", FNS_CALL=" + FNS_CALL + ", IP_CALL=" + IP_CALL + ", SMSBG_CALL=" + SMSBG_CALL + ", TELCOIN_CALL="
				+ TELCOIN_CALL + ", YFCARD_CALL=" + YFCARD_CALL + ", YFREE_CALL=" + YFREE_CALL + ", YRATE_CALL="
				+ YRATE_CALL + ", SPNM=" + SPNM + ", SPIP=" + SPIP + ", DUMMY_DATE=" + DUMMY_DATE + ", DUMMY_TIME0="
				+ DUMMY_TIME0 + ", DUMMY_TIME1=" + DUMMY_TIME1 + ", CDF_NORM_V=" + CDF_NORM_V + ", CDF_UNSUCCESS_V="
				+ CDF_UNSUCCESS_V + ", CDF_ABNORM_V=" + CDF_ABNORM_V + ", KT_1577=" + KT_1577 + ", KT114=" + KT114
				+ ", SSCODE_FREE_ZONE=" + SSCODE_FREE_ZONE + ", SSCODE_TWONUMBER=" + SSCODE_TWONUMBER + ", SSCODE_GC="
				+ SSCODE_GC + ", SSCODE_VMSFW=" + SSCODE_VMSFW + ", SSCODE_MPBX_FREE=" + SSCODE_MPBX_FREE
				+ ", bIsCALL_FORWARDING=" + bIsCALL_FORWARDING + ", bIsCDF_ABNORMAL=" + bIsCDF_ABNORMAL
				+ ", bIsCDF_NORMAL=" + bIsCDF_NORMAL + ", bIsCDF_UNSUCCESS=" + bIsCDF_UNSUCCESS + ", bIsFPS_CALL="
				+ bIsFPS_CALL + ", bIsIM_3G=" + bIsIM_3G + ", bIsIM_4G=" + bIsIM_4G + ", bIsPSVT_3G_CALL="
				+ bIsPSVT_3G_CALL + ", bIsPSVT_4G_CALL=" + bIsPSVT_4G_CALL + ", bIsVOLTE_4G_CALL=" + bIsVOLTE_4G_CALL
				+ ", bIsVS_3G_CALL=" + bIsVS_3G_CALL + ", bIsVS_4G_CALL=" + bIsVS_4G_CALL + ", bIsABNORMAL_COMP="
				+ bIsABNORMAL_COMP + ", bIsNORMAL_COMP=" + bIsNORMAL_COMP + ", bIsUNSUCCESS_COMP=" + bIsUNSUCCESS_COMP
				+ ", bIsKTF_CSUB=" + bIsKTF_CSUB + ", bIsCALLED_PARTY_PAY_CALL=" + bIsCALLED_PARTY_PAY_CALL
				+ ", bIsTIME_CDR=" + bIsTIME_CDR + ", bIsOCR_CDR=" + bIsOCR_CDR + ", bIsWIN_CDR=" + bIsWIN_CDR
				+ ", bIsWIN_CHARGEABLE=" + bIsWIN_CHARGEABLE + ", bIsWIN_CHARGED=" + bIsWIN_CHARGED
				+ ", bIsVISUAL_CALL=" + bIsVISUAL_CALL + ", bIsVOICE_CALL=" + bIsVOICE_CALL + ", bIsSPECIAL_NUMBER="
				+ bIsSPECIAL_NUMBER + ", bIsChargeFeaturesAir=" + bIsChargeFeaturesAir + ", bIsChargeFeaturesOther="
				+ bIsChargeFeaturesOther + ", bIsHD_3G_VOICE=" + bIsHD_3G_VOICE + ", bIsHD_4G_VOICE=" + bIsHD_4G_VOICE
				+ ", bIsDROPPED_RECORD=" + bIsDROPPED_RECORD + ", bIsERROR_FILE=" + bIsERROR_FILE
				+ ", bIsDURATION_3SEC_CHECK=" + bIsDURATION_3SEC_CHECK + ", bIsDURATION_5SEC_CHECK="
				+ bIsDURATION_5SEC_CHECK + ", bIsDURATION_10SEC_CHECK=" + bIsDURATION_10SEC_CHECK + ", bIsBIGI_CDR="
				+ bIsBIGI_CDR + ", bIsWIN_SRV_BG=" + bIsWIN_SRV_BG + ", bIsPPS_CALL=" + bIsPPS_CALL + ", bIsDADAN_CALL="
				+ bIsDADAN_CALL + ", bIsNO_CHARGE_CDR=" + bIsNO_CHARGE_CDR + ", bIsTIME_CDR_001=" + bIsTIME_CDR_001
				+ ", bIsOCR_CDR_001=" + bIsOCR_CDR_001 + ", bIsINTERNATIONAL=" + bIsINTERNATIONAL
				+ ", bIsPREFIX_FREEZONE=" + bIsPREFIX_FREEZONE + ", bIsINTL_SERVICE_PROVIDER="
				+ bIsINTL_SERVICE_PROVIDER + ", bIsSAME_5COUNTRY=" + bIsSAME_5COUNTRY + ", bIsTIOI_LIST=" + bIsTIOI_LIST
				+ ", bIsTWONUMBER_MO=" + bIsTWONUMBER_MO + ", bIsKT1577=" + bIsKT1577 + ", bIsExistCarriers="
				+ bIsExistCarriers + ", bIsRENTAL_CALL=" + bIsRENTAL_CALL + ", bIsWSTYPE_RANGE=" + bIsWSTYPE_RANGE
				+ ", bIsEMS_CALLED=" + bIsEMS_CALLED + ", bIsSSCODE_MPBX=" + bIsSSCODE_MPBX + ", sCallStartDateTime="
				+ sCallStartDateTime + ", sCallGubun=" + sCallGubun + ", sCallStartDate=" + sCallStartDate
				+ ", sCallStartTime=" + sCallStartTime + ", sCallEndDate=" + sCallEndDate + ", sCallEndTime="
				+ sCallEndTime + ", sWorkCalledSvcNo=" + sWorkCalledSvcNo + ", sCalledSvcNo=" + sCalledSvcNo
				+ ", sCalledPartyPayCall=" + sCalledPartyPayCall + ", sCallingDropIndi=" + sCallingDropIndi
				+ ", sCallingSvcNo=" + sCallingSvcNo + ", sChrgSvcNo=" + sChrgSvcNo + ", sDeduct=" + sDeduct
				+ ", iDeductUseTime=" + iDeductUseTime + ", sDrewCalledSvcNo=" + sDrewCalledSvcNo + ", iUseTime="
				+ iUseTime + ", sProdCd=" + sProdCd + ", sProdDropCall=" + sProdDropCall + ", sProdFormatId="
				+ sProdFormatId + ", sProdCdType=" + sProdCdType + ", sProdNumber=" + sProdNumber + ", sInsProdType="
				+ sInsProdType + ", sFormatId=" + sFormatId + ", sFpsResult=" + sFpsResult + ", sInNetCallYn="
				+ sInNetCallYn + ", sFileType=" + sFileType + ", sMutualConnType=" + sMutualConnType + ", sIntlNxx="
				+ sIntlNxx + ", sCallingIp=" + sCallingIp + ", sIpasResult=" + sIpasResult + ", sKeyVal=" + sKeyVal
				+ ", sProdCdIndi=" + sProdCdIndi + ", sMpsFeatureCd=" + sMpsFeatureCd + ", iOriginFileNo="
				+ iOriginFileNo + ", sNpInd=" + sNpInd + ", sNpOperator=" + sNpOperator + ", sNpSettFeature="
				+ sNpSettFeature + ", sNpSettProvider=" + sNpSettProvider + ", sOriginFileDatetime="
				+ sOriginFileDatetime + ", iOrigFileSeqNo=" + iOrigFileSeqNo + ", sOtrFlag=" + sOtrFlag
				+ ", sProfileResult=" + sProfileResult + ", iRecvCnt=" + iRecvCnt + ", sSensorId=" + sSensorId
				+ ", sSpProdCd=" + sSpProdCd + ", sSpType=" + sSpType + ", sSpIndi=" + sSpIndi + ", iSpecialNoId="
				+ iSpecialNoId + ", sSpNumberType=" + sSpNumberType + ", sSpnSpNmTp=" + sSpnSpNmTp + ", sSpNoAtIndi="
				+ sSpNoAtIndi + ", sSscode=" + sSscode + ", sSwitchId=" + sSwitchId + ", sTernInterOpId="
				+ sTernInterOpId + ", sChrgCallYn=" + sChrgCallYn + ", sVmsAccess=" + sVmsAccess + ", sVmsInd="
				+ sVmsInd + ", sVmsResult=" + sVmsResult + ", sTermCode=" + sTermCode + ", sSpnSpInd=" + sSpnSpInd
				+ ", sWinSvcType=" + sWinSvcType + ", iDurSecPrepaid=" + iDurSecPrepaid + ", sSettCall=" + sSettCall
				+ ", sSettDropInd=" + sSettDropInd + ", sSettDropTeen=" + sSettDropTeen + ", sSpnDpClInd=" + sSpnDpClInd
				+ ", sCfCallInd=" + sCfCallInd + ", sCallingImsi=" + sCallingImsi + ", sSpnAcInd=" + sSpnAcInd
				+ ", sTermArea=" + sTermArea + ", sCallToStateCode=" + sCallToStateCode + ", sSettlFileName="
				+ sSettlFileName + ", sSettlCarrier=" + sSettlCarrier + ", sSpNoIndi=" + sSpNoIndi + ", sSpnPpsDpClInd="
				+ sSpnPpsDpClInd + ", sSpnAtFtrInd=" + sSpnAtFtrInd + ", sCalledArea=" + sCalledArea
				+ ", sSettlFeatureCd=" + sSettlFeatureCd + ", sChrgOtherNo=" + sChrgOtherNo + ", sNationCd=" + sNationCd
				+ ", sSecondNationCd=" + sSecondNationCd + ", sFeatureDropIndi=" + sFeatureDropIndi + ", sMzFileId="
				+ sMzFileId + ", sCdrSeq=" + sCdrSeq + ", sCallingNo=" + sCallingNo + ", sCalledNo=" + sCalledNo
				+ ", sMzErrorLevelCd=" + sMzErrorLevelCd + ", sMzErrorCd01=" + sMzErrorCd01 + ", sMzErrorCd02="
				+ sMzErrorCd02 + ", sMzErrorCd03=" + sMzErrorCd03 + ", sMzErrorCd04=" + sMzErrorCd04 + ", sMzErrorCd05="
				+ sMzErrorCd05 + "]";
	}	
}	

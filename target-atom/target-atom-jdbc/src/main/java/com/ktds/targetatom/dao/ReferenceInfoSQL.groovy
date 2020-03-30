
package com.ktds.targetatom.dao

public class ReferenceInfoSQL {
	public static final String getCamelRouteFileList = """
		SELECT 
			format
			, route_file_path || route_file_name route_file
            , module_name
            , module_type
		FROM FORMAT_ROUTE_MAP_INFO
		WHERE 1=1
            AND module_name = :moduleName
			AND format = :format
            AND use_yn = 'Y'
		LIMIT 1
		;
	""";
	
	public static final String getCdrReferenceInfo = """
		SELECT DISTINCT
			A.wflow_inst_id
			, B.ne_id
			, B.ne_type_id
			, B.cdr_file_colec_dir_nm
			, B.fns_file_cret_yn
			, B.fns_file_div_cd
			, B.origin_fmt_id
			, B.trm_dir_nm
			, B.dss_id
			, C.cdr_file_fmt_type_cd
			, Coalesce(C.file_nm_len, '0') file_nm_len
			, C.cdr_file_nmng_rule_sbst
		FROM TB_WFLOW_INFO A
			, TB_CDRSEND_BASE_INFO B
			, TB_FILE_FMT_INFO C
		WHERE 1=1
			AND A.wflow_inst_id = :wflowInstId
			AND now() BETWEEN A.efct_st_dt AND A.exp_dt
			AND now() BETWEEN B.efct_st_dt AND B.exp_dt
			AND A.wflow_inst_id = B.wflow_inst_Id
			AND B.origin_fmt_id = C.cdr_file_fmt_id
		;
	""";
	
/*
	// Distributer before
	public static final String getDistributerCdrReferenceInfo = """
		SELECT DISTINCT
			A.wflow_inst_id
			, B.ne_id
			, B.ne_type_id
			, B.cdr_file_colec_dir_nm
			, Coalesce(B.FNS_FILE_CRET_YN, 'N') FNS_FILE_CRET_YN
			, Coalesce(B.FNS_FILE_DIV_CD, '0') FNS_FILE_DIV_CD
			, B.origin_fmt_id
			, B.trm_dir_nm
			, B.dss_id
			, C.cdr_file_fmt_type_cd
			, Coalesce(C.file_nm_len, '0') file_nm_len
			, C.cdr_file_nmng_rule_sbst
		FROM TB_WFLOW_INFO A
			, TB_CDRSEND_BASE_INFO B
			, TB_FILE_FMT_INFO C
		WHERE 1=1
			AND A.wflow_inst_id = :wflowInstId
			AND now() BETWEEN A.efct_st_dt AND A.exp_dt
			AND now() BETWEEN B.efct_st_dt AND B.exp_dt
			AND A.wflow_inst_id = B.wflow_inst_Id
			AND B.origin_fmt_id = C.cdr_file_fmt_id
			AND B.DSS_ID = 'NRAT'
			AND A.WFLOW_INST_ID LIKE 'F%'
			;
		""";
*/
		
		// Distributer after
		public static final String getDistributerCdrReferenceInfo = """
		SELECT DISTINCT
			A.wflow_inst_id
			, B.ne_id
			, B.ne_type_id
			, B.cdr_file_colec_dir_nm
			, Coalesce(B.FNS_FILE_CRET_YN, 'N') FNS_FILE_CRET_YN
			, Coalesce(B.FNS_FILE_DIV_CD, '0') FNS_FILE_DIV_CD
			, B.origin_fmt_id
			, B.trm_dir_nm
			, B.dss_id
			, C.cdr_file_fmt_type_cd
			, Coalesce(C.file_nm_len, '0') file_nm_len
			, C.cdr_file_nmng_rule_sbst
		FROM TB_WFLOW_INFO A
			, TB_CDRSEND_BASE_INFO B
			, TB_FILE_FMT_INFO C
		WHERE 1=1
			AND A.wflow_inst_id = :wflowInstId
			AND now() BETWEEN A.efct_st_dt AND A.exp_dt
			AND now() BETWEEN B.efct_st_dt AND B.exp_dt
			AND A.wflow_inst_id = B.wflow_inst_Id
			AND B.origin_fmt_id = C.cdr_file_fmt_id
			;
		""";

	public static final String getCollectStrategyList = """
		SELECT 
			  A.wflow_inst_id
			, B.cdr_file_colec_dir_nm
			, Coalesce(B.FNS_FILE_CRET_YN, 'N') AS fns_file_cret_yn
			, Coalesce(B.FNS_FILE_DIV_CD, '0')  AS fns_file_div_cd
		FROM TB_WFLOW_INFO A
			, TB_CDRCOLL_BASE_INFO B
		WHERE 1=1
			AND A.wflow_inst_id = :wflowInstId
			AND A.wflow_inst_id = B.wflow_inst_Id
			AND B.EXP_DT >= NOW()
		;
	""";
	
	public static final String getCdrBaseList = """
		SELECT 
			  A.wflow_inst_id
			, B.cdr_file_colec_dir_nm
			, coalesce(B.fns_file_cret_yn, 'N')  AS fns_file_cret_yn
			, coalesce(B.fns_file_div_cd, '0')   AS fns_file_div_cd
			, B.origin_fmt_id
			, D.cdr_file_nmng_rule_sbst
			, C.trm_dir_nm
			, C.dss_id
			, B.file_cret_cycl_time
			, B.cdrcoll_vrf_yn
			, B.cdrcoll_vrf_id
			, D.cdr_file_fmt_type_cd
            , coalesce(D.file_nm_len, 0) AS file_nm_len
            , B.dupl_file_colec_yn
            , C.cdr_file_type_cd
			, C.trm_que_nm
		FROM  TB_WFLOW_INFO A
			, TB_CDRCOLL_BASE_INFO B
            , TB_CDRSEND_BASE_INFO C
			, TB_FILE_FMT_INFO D
		WHERE 1=1
			AND A.wflow_inst_id = :wflowInstId
			AND B.wflow_inst_id = A.wflow_inst_id
		    AND B.EXP_DT >= NOW()
		    AND C.wflow_inst_id = B.wflow_inst_id
		    AND C.cdr_file_colec_dir_nm = B.cdr_file_colec_dir_nm
		    AND C.origin_fmt_id = B.origin_fmt_id
		    AND C.exp_dt > now()
		    AND C.efct_st_dt <= now()
		    AND D.cdr_file_fmt_id = B.origin_fmt_id
		    AND B.FNS_FILE_CRET_YN = 'Y'
		;
	""";
	
	// VoLTE SPLIT
	public static final String getCdrProfReferenceInfo = """
		SELECT PROF_ITEM_ID
			, PROF_ITEM_DIV_ID
			, EFCT_ST_DT
			, EXP_DT
			, PROF_ITEM_VAL
		FROM TB_PROF_BAS
		WHERE PROF_ITEM_ID IN ('JASPER_CDR_KEEP_V','BSSIOT_CDR_KEEP','GTOUCH_CDR_KEEP')
		;
	""";
	
	// TB_INTL_PFIX_CHANGE_BAS
	public static final String getTbIntlPfixChangeBasInfo = """
		SELECT CARR_ID as sCarrierCode
			, EDP_ID as sEdpId
			, EFCT_ST_DT as sCallStartDate
			, EFCT_ST_DT as sCallStartTime
		FROM TB_INTL_PFIX_CHANGE_BAS
		WHERE CARR_ID = '001'
		AND	EDP_ID = '313'
		AND EFCT_ST_DT = '1996-01-01 00:00:00'
		;
	""";
	
//  TB_PFIX_RGN_BAS
	public static final String getTbPfixRgnBasInfo = """
		SELECT  PFIX_IDFY_NO as sPfixIdfyNo
			, SETL_FILE_NM as sSettlFileName
			, SETL_PRVR_DIV_CD as sSettlCarrier
			, SETL_FEATR_ID as sSettlFeatureCd
			, IDFY_TEL_NO as sNpaAreaNo
			, INNET_YN as sInNetCallYn
			, SETL_ETC_FEATR_ID as sSettlEtcFeatureId
			, WRLIN_WLESS_DIV_VAL as sWiredLessDivideValue
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, NP_ID as sNpId
		FROM TB_PFIX_RGN_BAS
		WHERE PFIX_IDFY_NO = :sPrefix
			AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
			AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
		LIMIT 1
		;
	""";
	
	public static final String getLoadTbPfixRgnBasInfo = """
		SELECT PFIX_IDFY_NO as sPfixIdfyNo
            , SETL_FILE_NM as sSettlFileName
			, SETL_PRVR_DIV_CD as sSettlCarrier
			, SETL_FEATR_ID as sSettlFeatureCd
			, IDFY_TEL_NO as sNpaAreaNo
			, INNET_YN as sInNetCallYn
			, SETL_ETC_FEATR_ID as sSettlEtcFeatureId
			, WRLIN_WLESS_DIV_VAL as sWiredLessDivideValue
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, NP_ID as sNpId
		FROM TB_PFIX_RGN_BAS
		WHERE EXP_DT >= (current_date - (3*30))
		;
	""";
	
	//  TB_WLESS_SPCL_NO_BAS
	public static final String getWlessSpclNoBasInfo = """
		SELECT
              IDFY_TEL_NO as sIdfyTelNo
            , PFX_TEL_NO as sPfxTelNo
            , HHO_ST_NO as sHhoStNo
            , WLESS_MKT_DIV_CD as sWlessMktDivCd
			, AIRTM_YN as sAirtimeIndi     
			, AIRTM_CHAGE_YN as sAirtimeChrgYn
			, AIRTM_CHAGE_AMT as dAcAmt
			, DROP_YN as sFeatureDropIndi
			, SPECL_NO_TYPE_ID as sSpecialNoType
			, SPCL_NO_SBST as sSpecialNoDesc
			, FEATR_ID as sFeatureCd
			, SPCL_NO_ID as lSpecialNoId
			, SVC_PRVR_YN as sIspYn
			, SVC_PRVR_ID as sIspId
			, SVC_PRVR_FEATR_ID as sSpFeatureCode
			, SETL_FILE_NM as sSettlFileName
			, SETL_PRVR_DIV_CD as sSettlCarrier
			, SETL_FEATR_ID as sSettlFeatureCd
			, AIRTM_FEATR_YN as sAirtimeFuncYn
			, AIRTM_FEATR_ID as sAirtimeFeatureCd
			, SETL_ETC_FEATR_ID as sSettlFeatureType
			, PPS_DROP_YN as sPpsDropIndi
			, INNET_YN as sInNetCallYn
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, NP_ID as sNpId 
			FROM 
			(
				SELECT
					RTRIM(IDFY_TEL_NO) AS IDFY_TEL_NO,
					COALESCE(RTRIM(PFX_TEL_NO),'*') AS PFX_TEL_NO,
					COALESCE(RTRIM(HHO_ST_NO),'*') AS HHO_ST_NO,
					RTRIM(WLESS_MKT_DIV_CD) AS WLESS_MKT_DIV_CD,
                	AIRTM_YN,
                	AIRTM_CHAGE_YN,
                	AIRTM_CHAGE_AMT,
                	DROP_YN,
                	SPECL_NO_TYPE_ID,
               		SPCL_NO_SBST,
                	FEATR_ID,
                	SPCL_NO_ID,
                	SVC_PRVR_YN,
                	SVC_PRVR_ID,
                	SVC_PRVR_FEATR_ID,
                	SETL_FILE_NM,
                	SETL_PRVR_DIV_CD,
                	SETL_FEATR_ID,
                	AIRTM_FEATR_YN,
                	AIRTM_FEATR_ID,
                	SETL_ETC_FEATR_ID,
                	PPS_DROP_YN,
                	INNET_YN,
                	EFCT_ST_DT,
                	EXP_DT,
                	NP_ID	
				FROM TB_WLESS_SPCL_NO_BAS
				WHERE 
					WLESS_MKT_DIV_CD != 'KWL'
					AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')	
					AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')				
			) AS A 
			WHERE 
				A.IDFY_TEL_NO = :sNpaNo 
				AND A.PFX_TEL_NO = :sNxxNo
				AND A.HHO_ST_NO = :sLineNo
				AND A.WLESS_MKT_DIV_CD = :sMarketCd
			LIMIT 1
			;
	""";
	
	//  TB_WLESS_SPCL_NO_BAS All record loading
	public static final String getLoadWlessSpclNoBas = """
		SELECT
			AIRTM_YN as sAirtimeIndi     
			, AIRTM_CHAGE_YN as sAirtimeChrgYn
			, AIRTM_CHAGE_AMT as dAcAmt
			, DROP_YN as sFeatureDropIndi
			, SPECL_NO_TYPE_ID as sSpecialNoType
			, SPCL_NO_SBST as sSpecialNoDesc
			, FEATR_ID as sFeatureCd
			, SPCL_NO_ID as lSpecialNoId
			, SVC_PRVR_YN as sIspYn
			, SVC_PRVR_ID as sIspId
			, SVC_PRVR_FEATR_ID as sSpFeatureCode
			, SETL_FILE_NM as sSettlFileName
			, SETL_PRVR_DIV_CD as sSettlCarrier
			, SETL_FEATR_ID as sSettlFeatureCd
			, AIRTM_FEATR_YN as sAirtimeFuncYn
			, AIRTM_FEATR_ID as sAirtimeFeatureCd
			, SETL_ETC_FEATR_ID as sSettlFeatureType
			, PPS_DROP_YN as sPpsDropIndi
			, INNET_YN as sInNetCallYn
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, NP_ID as sNpId 
			FROM 
			(
				SELECT
					RTRIM(IDFY_TEL_NO) AS IDFY_TEL_NO,
					COALESCE(RTRIM(PFX_TEL_NO),'*') AS PFX_TEL_NO,
					COALESCE(RTRIM(HHO_ST_NO),'*') AS HHO_ST_NO,
					RTRIM(WLESS_MKT_DIV_CD) AS WLESS_MKT_DIV_CD,
                	AIRTM_YN,
                	AIRTM_CHAGE_YN,
                	AIRTM_CHAGE_AMT,
                	DROP_YN,
                	SPECL_NO_TYPE_ID,
               		SPCL_NO_SBST,
                	FEATR_ID,
                	SPCL_NO_ID,
                	SVC_PRVR_YN,
                	SVC_PRVR_ID,
                	SVC_PRVR_FEATR_ID,
                	SETL_FILE_NM,
                	SETL_PRVR_DIV_CD,
                	SETL_FEATR_ID,
                	AIRTM_FEATR_YN,
                	AIRTM_FEATR_ID,
                	SETL_ETC_FEATR_ID,
                	PPS_DROP_YN,
                	INNET_YN,
                	EFCT_ST_DT,
                	EXP_DT,
                	NP_ID	
				FROM TB_WLESS_SPCL_NO_BAS
				where WLESS_MKT_DIV_CD != 'KWL'
				AND EXP_DT >= (current_date - (3*30))) AS A;
	""";
	
	//  TB_INTL_PFIX_BAS
	public static final String getIntlPrfixBasInfo = """
		SELECT INTL_PFIX_ID as sIntlPrefixCd
			, ZONE_ID as sCommZoneCd
			, AUX_INTL_PFIX_ID as sSubIntlPrefixCd
		FROM TB_INTL_PFIX_BAS
		WHERE INTL_PFIX_ID = :sIntlPrefixCd
			AND ZONE_ID = :sCommZoneCd
		LIMIT 1
		;	
	""";
	
	//  TB_INTL_PFIX_CHANGE_BAS
	public static final String getIntlPrfixChangeBasInfo = """
		SELECT CARR_ID as sCarrierCd
			, EDP_ID as sEdpCd
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDD') as sEffectStartDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, INTL_PFIX_ID as sIntlPrefixCd
		FROM TB_INTL_PFIX_CHANGE_BAS
		WHERE CARR_ID = :sCarrierCd
			AND EDP_ID = :sEdpCd
			AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
			AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
		LIMIT 1
		;	
	""";
	
	//  TB_PROF_BAS
	public static final String getTbProfBasInfo = """
		SELECT PROF_ITEM_ID as sItemId
			, PROF_ITEM_DIV_ID as sItemIndi
			, PROF_ITEM_VAL as sReturnvalue
			, PROF_ITEM_SBST as sProfItemSbst
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
		FROM TB_PROF_BAS
		WHERE PROF_ITEM_ID = :sItemId
			AND PROF_ITEM_DIV_ID = :sItemIndi
			AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
			AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
		LIMIT 1
		;	
	""";
	
	//  TB_PROF_BAS
	public static final String getLoadTbProfBasInfo = """
		SELECT PROF_ITEM_ID as sItemId
			, PROF_ITEM_DIV_ID as sItemIndi
			, PROF_ITEM_VAL as sReturnvalue
			, PROF_ITEM_SBST as sProfItemSbst
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
		FROM TB_PROF_BAS
		WHERE EXP_DT >= (current_date - (3*30))
		;	
	""";
	
	//  TB_CAL_NO_BAS
	public static final String getTbCalNoBasInfo = """
		SELECT PFIX_IDFY_NO as sPrefix
			, PFX_TEL_NO as sNxxNo
			, INDIV_TEL_NO as sLineNo
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, CALLG_PFIX_SBST as sCallgPfixSbst
			, DROP_YN as sCallingDropIndi
			, SETL_DROP_YN as sSettlDropInd
		FROM TB_CAL_NO_BAS
		WHERE PFIX_IDFY_NO = :sPrefix
			AND PFX_TEL_NO = :sNxxNo
			AND INDIV_TEL_NO = :sLineNo
			AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
			AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
		LIMIT 1
		;	
	""";
	
	public static final String getLoadTbCalNoBasInfo = """
		SELECT PFIX_IDFY_NO as sPrefix
			, PFX_TEL_NO as sNxxNo
			, INDIV_TEL_NO as sLineNo
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, CALLG_PFIX_SBST as sCallgPfixSbst
			, DROP_YN as sCallingDropIndi
			, SETL_DROP_YN as sSettlDropInd
		FROM TB_CAL_NO_BAS



	""";
	
	//  TB_FEATR_BAS
	public static final String getTbFeatrBasInfo = """
		SELECT FEATR_FMT_ID as sFeatureFormatId
			, FEATR_FMT_TYPE_ID as sFormatType
			, FEATR_NO as sFeatureNo
			, FEATR_TYPE_CD as sFeatureType
			, FEATR_ID as sFeatureCd
			, DROP_YN as sFeatureDropIndi
			, FEATR_SBST as sFeatureDescSbst
			, ARVR_RAT_YN as sCalledChrgYn
			, SETL_DROP_YN as sSettlDropIndi
			, AUX_FEATR_ID as sSubFeatureCd
			, CUST_HNDSET_INDC_SBST as sRcvTerminalShowDesc
			, INDC_YN as sRcvTerminalShowYn
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
		FROM TB_FEATR_BAS
		WHERE FEATR_FMT_ID = :sFeatureFormatId
			AND FEATR_FMT_TYPE_ID = :sFormatType
			AND FEATR_NO = :sFeatureNo
			AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
			AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
		LIMIT 1
		;	
	""";
	
	public static final String getLoadTbFeatrBasInfo = """
		SELECT FEATR_FMT_ID as sFeatureFormatId
			, FEATR_FMT_TYPE_ID as sFormatType
			, FEATR_NO as sFeatureNo
			, FEATR_TYPE_CD as sFeatureType
			, FEATR_ID as sFeatureCd
			, DROP_YN as sFeatureDropIndi
			, FEATR_SBST as sFeatureDescSbst
			, ARVR_RAT_YN as sCalledChrgYn
			, SETL_DROP_YN as sSettlDropIndi
			, AUX_FEATR_ID as sSubFeatureCd
			, CUST_HNDSET_INDC_SBST as sRcvTerminalShowDesc
			, INDC_YN as sRcvTerminalShowYn
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
		FROM TB_FEATR_BAS
		;	
	""";
	
	
	//  TB_SVC_PRVR_BAS
	public static final String getTbSvcPrvrBasInfo = """
		SELECT SVC_PRVR_ID as sIspId
			, SVC_PRVR_NM as sIspName
			, SVC_PRVR_TYPE_CD as sSpFeatureCode
		FROM TB_SVC_PRVR_BAS
		WHERE SVC_PRVR_ID = :sLspId
		LIMIT 1
		;	
	""";
	
	//  TB_NO_MOV_BIZR_BAS
	public static final String getTbNoMovBizrBasinfo = """
		SELECT NP_ID as sNpCd
			, SETL_FILE_NM as sSettlFileName
			, SETL_PRVR_DIV_CD as sNpSettlCarrier
			, SETL_FEATR_ID as sSettlFeatureCd
			, INNET_YN as sInNetCallYn
			, AUX_SETL_FEATR_ID as sSubSettlFeatureId
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
		FROM TB_NO_MOV_BIZR_BAS
		WHERE NP_ID = :sNpCd
			AND EFCT_ST_DT <= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
			AND EXP_DT >= TO_TIMESTAMP(:sCallStartDatetime,'YYYYMMDDHH24MISS')
		LIMIT 1
		;	
	""";
	
	public static final String getLoadTbNoMovBizrBasinfo = """
		SELECT NP_ID as sNpCd
			, SETL_FILE_NM as sSettlFileName
			, SETL_PRVR_DIV_CD as sNpSettlCarrier
			, SETL_FEATR_ID as sSettlFeatureCd
			, INNET_YN as sInNetCallYn
			, AUX_SETL_FEATR_ID as sSubSettlFeatureId
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
		FROM TB_NO_MOV_BIZR_BAS
		;	
	""";
	
	//  TB_ERR_MAPPG_BAS
	public static final String getTbErrMappgBasinfo = """
		SELECT NE_TYPE_ID
			, MAPPG_FIELD_ID
			, FIELD_ODRG
			, EXP_DT
			, EFCT_ST_DT
			, CDR_ERR_ID
			, MZ_ERR_LEVEL_DIV_CD
			, MZ_ERR_PRIRT_NO
            , OLD_MZ_ERR_ID
		FROM TB_ERR_MAPPG_BAS
		WHERE NE_TYPE_ID = :neTypeId
		;	
	""";
	
	//  Load TB_ERR_MAPPG_BAS
	public static final String getLoadTbErrMappgBasinfo = """
		SELECT 'u_' || MAPPG_FIELD_ID || '_' || FIELD_ODRG as sErrorDescription
			, TO_CHAR(EFCT_ST_DT,'YYYYMMDDHH24MISS') as sEffectDate
			, TO_CHAR(EXP_DT,'YYYYMMDDHH24MISS') as sExpireDate
			, CDR_ERR_ID as sCdrErrId 
			, MZ_ERR_LEVEL_DIV_CD as sMzErrLevelDivCd
			, MZ_ERR_PRIRT_NO as iMzErrPrirtNo
            , OLD_MZ_ERR_ID as sOldMzErrId
		FROM TB_ERR_MAPPG_BAS
		WHERE NE_TYPE_ID = 'VOVLTE'
		;	
	""";
	
}

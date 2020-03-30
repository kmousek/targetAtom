SELECT ADIT_CRET_SEQ, UP_ADIT_SEQ, FILE_NM, TRM_DIR_NM, ORGN_FILE_NM, STTUS, MODULE_NM, FILE_TYPE 
FROM TB_ADIT_HST 
WHERE 1=1 
	AND   FILE_NM = 'SLPNPM_FGIDRO01_ID0001_T20190312000500.DAT' 
--	AND   TRM_DIR_NM = ? 
	AND   FILE_TYPE = 'PRC' 
	AND   STTUS = 'RD' 
LIMIT 1;


		SELECT DISTINCT
			A.wflow_inst_id
			, A.efct_st_dt
			, B.ne_id
			, B.ne_type_id
			, B.cdr_file_colec_dir_nm
			, B.origin_fmt_id
			, B.trm_dir_nm
			, C.cdr_file_nmng_rule_sbst
			, C.file_nm_len
			, B.fns_file_cret_yn
			, B.fns_file_div_cd
		FROM TB_WFLOW_INFO A
			, TB_CDRSEND_BASE_INFO B
			, TB_FILE_FMT_INFO C
		WHERE 1=1
--			AND A.wflow_inst_id = :wflowInstId
--			AND ne_id = 'KRLPPC90'
			AND ne_type_id = 'IPTVKR'
--			AND A.wflow_inst_id like '%_IPTVKR_D'
			AND now() BETWEEN A.efct_st_dt AND A.exp_dt
			AND now() BETWEEN B.efct_st_dt AND B.exp_dt
			AND A.wflow_inst_id = B.wflow_inst_Id
			AND B.origin_fmt_id = C.cdr_file_fmt_id
			ORDER BY A.wflow_inst_id
			
		SELECT route_file_path || route_file_name route_file
		FROM FORMAT_ROUTE_MAP_INFO
		WHERE 1=1
			AND format = 'KR'
		LIMIT 1
		;
			
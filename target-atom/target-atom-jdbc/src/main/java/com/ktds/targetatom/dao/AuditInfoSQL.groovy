
package com.ktds.targetatom.dao

public class AuditInfoSQL {
	public static final String updateStatus = """
		UPDATE TB_ADIT_HST SET    
			sttus = :sttus
			,last_chg_dt = now()
			,last_chg_trtr_id = :lastChgTrtrId
			,last_chg_pgm_id = :lastChgPgmId
	 WHERE adit_cret_seq = :aditCretSeq;
	""";
	
	public static final String updateDistributerStatus = """
		UPDATE TB_ADIT_HST SET    
			sttus = :chgSttus
			,last_chg_dt = now()
			,last_chg_trtr_id = :lastChgTrtrId
			,last_chg_pgm_id = :lastChgPgmId
	 	WHERE adit_cret_seq = :aditCretSeq
		AND   file_nm = :fileNm
	    AND   file_type = 'PRC_CMP'
	    AND   sttus = :sttus;
	""";
	
	/*
	public static final String update = """
		UPDATE TB_ADIT_HST SET    
			sttus = :sttus
			,last_chg_dt = now()
			,last_chg_trtr_id = :lastChgTrtrId
			,last_chg_pgm_id = :lastChgPgmId
			,file_type = :fileType
	 	WHERE adit_cret_seq = :aditCretSeq
		;
	"""; 
	*/
	public static final String update = """
		UPDATE TB_ADIT_HST SET    
			sttus = :sttus
			,last_chg_dt = now()
			,last_chg_trtr_id = :lastChgTrtrId
			,last_chg_pgm_id = :lastChgPgmId
			,rcrd_cnt = :rcrdCnt
			,err_cnt = :errCnt
			,prc_cnt = :prcCnt
	 	WHERE adit_cret_seq = :aditCretSeq
		;
	""";

	public static final String findById = """
		SELECT 
			adit_cret_seq 
			, first_Cret_Dt
			, first_Cret_Trtr_Id
			, last_Chg_Dt
			, last_Chg_Trtr_Id
			, first_Cret_Pgm_Id
			, last_Chg_Pgm_Id
			, up_Adit_Seq
			, file_Nm
			, trm_Dir_Nm
			, orgn_File_Nm
			, sttus
			, module_Nm
			, COALESCE(rcrd_Cnt,0) rcrd_Cnt
			, COALESCE(err_Cnt,0) err_Cnt
			, COALESCE(prc_Cnt,0) prc_Cnt
			, file_Type
    FROM TB_ADIT_HST
    WHERE 1=1
	    AND adit_cret_seq = :aditCretSeq
		;
	""";
	
	public static final String findByFileName = """
		SELECT 
			adit_cret_seq 
			, first_Cret_Dt
			, first_Cret_Trtr_Id
			, last_Chg_Dt
			, last_Chg_Trtr_Id
			, first_Cret_Pgm_Id
			, last_Chg_Pgm_Id
			, up_Adit_Seq
			, file_Nm
			, trm_Dir_Nm
			, orgn_File_Nm
			, sttus
			, module_Nm
			, COALESCE(rcrd_Cnt,0) rcrd_Cnt
			, COALESCE(err_Cnt,0) err_Cnt
			, COALESCE(prc_Cnt,0) prc_Cnt
			, file_Type
    FROM TB_ADIT_HST
    WHERE 1=1
	    AND   file_Nm = :fileName
	    AND   trm_Dir_Nm = :trmDirNm
	    AND   file_Type = 'PRC'
	    AND   STTUS = 'RD'
    LIMIT 1;
	""";
	
	public static final String distributerFindByFileName = """
		SELECT 
			adit_cret_seq 
			, first_Cret_Dt
			, first_Cret_Trtr_Id
			, last_Chg_Dt
			, last_Chg_Trtr_Id
			, first_Cret_Pgm_Id
			, last_Chg_Pgm_Id
			, up_Adit_Seq
			, file_Nm
			, trm_Dir_Nm
			, orgn_File_Nm
			, sttus
			, module_Nm
			, COALESCE(rcrd_Cnt,0) rcrd_Cnt
			, COALESCE(err_Cnt,0) err_Cnt
			, COALESCE(prc_Cnt,0) prc_Cnt
			, file_Type
    FROM TB_ADIT_HST
    WHERE 1=1
	    AND   file_Nm = :fileName
	    AND   file_Type = 'PRC_CMP'
	    AND   STTUS = 'RD'
    LIMIT 1;
	""";
	
	public static final String deleteById = """
		DELETE FROM TB_ADIT_HST 
	 	WHERE adit_cret_seq = :aditCretSeq
		;
	""";
	
	public static final String insertDownStreamAuditInfo = """
		INSERT INTO TB_ADIT_HST
		(
			FIRST_CRET_DT, 
			FIRST_CRET_TRTR_ID, 
			LAST_CHG_DT, 
			LAST_CHG_TRTR_ID, 
			FIRST_CRET_PGM_ID, 
			LAST_CHG_PGM_ID, 
			UP_ADIT_SEQ, 
			FILE_NM, 
			TRM_DIR_NM,
			ORGN_FILE_NM, 
			STTUS, 
			MODULE_NM,
            RCRD_CNT,
            ERR_CNT,
            PRC_CNT,
			FILE_TYPE
		)
        SELECT 
            now()
          , :firstCretTrtrId
          , now()
          , :lastChgTrtrId
          , :firstCretPgmId
          , :lastChgPgmId
          , ADIT_CRET_SEQ
          , :fileNm
          , :trmDirNm
          , :orgnFileNm
          , :sttus
          , :moduleNm
          , RCRD_CNT
          , ERR_CNT
          , PRC_CNT
          , :fileType
         FROM TB_ADIT_HST
		 WHERE adit_cret_seq = :aditCretSeq
	""";
	
	public static final String getAditDupFileCnt = """
		SELECT COUNT(0) AS ADIT_FILE_CNT
	    FROM TB_ADIT_HST
	    WHERE 1=1
		    AND   orgn_file_nm = :orgnFileNm
		    AND   module_nm = :moduleNm
	        AND   up_adit_seq = 0
	""";

	
	
	/*knj test용 */ 
	public static final String deleteAll = """
		DELETE FROM TB_ADIT_HST 
		;
	""";
	
	
	
}

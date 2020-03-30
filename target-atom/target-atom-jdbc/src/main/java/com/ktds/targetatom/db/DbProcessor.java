package com.ktds.targetatom.db;

import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.api.management.ManagedAttribute;
import org.apache.camel.api.management.ManagedResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ktds.targetatom.db.audit.AuditProcessor;
import com.ktds.targetatom.db.reference.ReferenceProcessor;

@ManagedResource
public class DbProcessor {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static DbProcessor instance = null;
	private static String query;
	private static String selectAudit_query;
	private static String updataAudit_query;
	private static String updataDistributerSatausAudit_query;
	private static String updataSatausAudit_query;
	private static String insertAudit_query;
	private static String fetchRoute_query;
	
	private static Map<String, String> targetDirectory = null;
	
	private static AuditProcessor auditProcessor;
	private static ReferenceProcessor referenceProcessor;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static DbProcessor getInstance(CamelContext camel) throws Exception {
		if (instance == null) {
			instance = camel.getInjector().newInstance(DbProcessor.class);
			query = camel.resolvePropertyPlaceholders("{{sql.referenceInfo}}");
			selectAudit_query = camel.resolvePropertyPlaceholders("{{sql.selectAudit}}");
			updataAudit_query = camel.resolvePropertyPlaceholders("{{sql.updateAudit}}");
			updataSatausAudit_query = camel.resolvePropertyPlaceholders("{{sql.updateStatusAudit}}");
			updataDistributerSatausAudit_query = camel.resolvePropertyPlaceholders("{{sql.updateDistributerStatusAudit}}");
			insertAudit_query = camel.resolvePropertyPlaceholders("{{sql.insertAudit}}");
			fetchRoute_query = camel.resolvePropertyPlaceholders("{{sql.fetchRoute}}");
			
			auditProcessor = new AuditProcessor();
			referenceProcessor = new ReferenceProcessor();
		}
		
		return instance;
	}
	
	@ManagedAttribute(description = "get to filePath")
	public void setToFilePath(Map<String, String> targetDirectory) {
		DbProcessor.targetDirectory = targetDirectory;
	}
	
	@ManagedAttribute(description = "get from filePath")
	public Map<String, String> getToFilePath() {
		return targetDirectory;
	}
	
	public Map<String, Object> getRouteFile(String format) {
		return referenceProcessor.getRouteFile(jdbcTemplate, format);
	}
	
	public List<Map<String, Object>> getPathList(String instance_id) {
		return referenceProcessor.getPathList(jdbcTemplate, instance_id);
	}
	
	public Map<String, Object> getAuditList(String file_nm, String file_path) {
		return auditProcessor.getAuditList(jdbcTemplate, file_nm, file_path);
	}
	
	public void updateStatusAudit(String status, String last_chg_trtr_id, String last_chg_pgm_id, long identifier) throws Exception {
		auditProcessor.updateStatusAudit(jdbcTemplate, status, last_chg_trtr_id, last_chg_pgm_id, identifier);
	}
	
	public void updateDistributerStatusAudit(String sttus, String last_chg_trtr_id, String last_chg_pgm_id, long identifier, String fileNm, String fileType) throws Exception {
		auditProcessor.updateDistributerStatusAudit(jdbcTemplate, sttus, last_chg_trtr_id, last_chg_pgm_id, identifier, fileNm, fileType);
	}
	
	public void updateAudit(String status, 
            String last_chg_trtr_id, String last_chg_pgm_id, long rcrd_cnt, long err_cnt, long prc_cnt, long identifier) {
		auditProcessor.updateAudit(jdbcTemplate, status, last_chg_trtr_id, last_chg_pgm_id, rcrd_cnt, err_cnt, prc_cnt, identifier);
	}
	
	public void insertAudit(String first_cret_pgm_id, long up_adit_seq, String file_nm, String trtm_dir_nm, String orgn_file_nm,
            String sttus, String module_nm, long rcrd_cnt, long err_cnt, long prc_cnt, String file_type) {
		auditProcessor.insertAudit(jdbcTemplate, first_cret_pgm_id, up_adit_seq, file_nm, trtm_dir_nm, orgn_file_nm, sttus, module_nm, rcrd_cnt, err_cnt, prc_cnt, file_type);
	}
	
	public static String getQuery() {
		return query;
	}

	public static void setQuery(String query) {
		DbProcessor.query = query;
	}

	public static String getSelectAudit_query() {
		return selectAudit_query;
	}

	public static void setSelectAudit_query(String selectAudit_query) {
		DbProcessor.selectAudit_query = selectAudit_query;
	}

	public static String getUpdataAudit_query() {
		return updataAudit_query;
	}

	public static void setUpdataAudit_query(String updataAudit_query) {
		DbProcessor.updataAudit_query = updataAudit_query;
	}

	public static String getUpdataSatausAudit_query() {
		return updataSatausAudit_query;
	}

	public static void setUpdataSatausAudit_query(String updataSatausAudit_query) {
		DbProcessor.updataSatausAudit_query = updataSatausAudit_query;
	}

	public static String getUpdataDistributerSatausAudit_query() {
		return updataDistributerSatausAudit_query;
	}

	public static void setUpdataDistributerSatausAudit_query(String updataDistributerSatausAudit_query) {
		DbProcessor.updataDistributerSatausAudit_query = updataDistributerSatausAudit_query;
	}

	public static String getInsertAudit_query() {
		return insertAudit_query;
	}

	public static void setInsertAudit_query(String insertAudit_query) {
		DbProcessor.insertAudit_query = insertAudit_query;
	}

	public static Map<String, String> getTargetDirectory() {
		return targetDirectory;
	}

	public static void setTargetDirectory(Map<String, String> targetDirectory) {
		DbProcessor.targetDirectory = targetDirectory;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static String getFetchRoute_query() {
		return fetchRoute_query;
	}

	public static void setFetchRoute_query(String fetchRoute_query) {
		DbProcessor.fetchRoute_query = fetchRoute_query;
	}
	
}

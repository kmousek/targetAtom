package com.ktds.targetatom.db.audit;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.apache.camel.api.management.ManagedResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.targetatom.db.DbProcessor;

@ManagedResource
public class AuditProcessor {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	public Map<String, Object> getAuditList(JdbcTemplate jdbcTemplate, String file_nm, String file_path) {
		try {
			return jdbcTemplate.queryForMap(DbProcessor.getSelectAudit_query(), file_nm, file_path.replace('\\', '/'));
		} catch (EmptyResultDataAccessException e) {
			log.error("File Name :: {} is not in audit table", file_nm);
			//e.printStackTrace();
			return null;
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void updateStatusAudit(JdbcTemplate jdbcTemplate, String status, 
            String last_chg_trtr_id, String last_chg_pgm_id, long identifier) throws Exception {
		
		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, status);
				ps.setString(2, last_chg_trtr_id);
				ps.setString(3, last_chg_pgm_id);
				ps.setLong(4, identifier);
			}
		};
		
		jdbcTemplate.update(DbProcessor.getUpdataSatausAudit_query(), inputParameter);
	}
	
	// Distributer
	@Transactional(rollbackFor=Exception.class)
	public void updateDistributerStatusAudit(JdbcTemplate jdbcTemplate, String status, 
            String last_chg_trtr_id, String last_chg_pgm_id, long identifier, String fileNm, String fileType) throws Exception {
		
		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, status);
				ps.setString(2, last_chg_trtr_id);
				ps.setString(3, last_chg_pgm_id);
				ps.setLong(4, identifier);
				ps.setString(5, fileNm);
				ps.setString(6, fileType);
			}
		};
		
		jdbcTemplate.update(DbProcessor.getUpdataDistributerSatausAudit_query(), inputParameter);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void updateAudit(JdbcTemplate jdbcTemplate, String status, 
            String last_chg_trtr_id, String last_chg_pgm_id, long rcrd_cnt, long err_cnt, long prc_cnt, long identifier) {
		
		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, status);
				ps.setString(2, last_chg_trtr_id);
				ps.setString(3, last_chg_pgm_id);
				ps.setLong(4, rcrd_cnt);
				ps.setLong(5, err_cnt);
				ps.setLong(6, prc_cnt);
				ps.setLong(7, identifier);
			}
		};
		
		jdbcTemplate.update(DbProcessor.getUpdataAudit_query(), inputParameter);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void insertAudit(JdbcTemplate jdbcTemplate, String first_cret_pgm_id, long up_adit_seq, String file_nm, String trtm_dir_nm, String orgn_file_nm,
			                String sttus, String module_nm, long rcrd_cnt, long err_cnt, long prc_cnt, String file_type) {
		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, first_cret_pgm_id);
				ps.setLong(2, up_adit_seq);
				ps.setString(3, file_nm);
				ps.setString(4, trtm_dir_nm);
				ps.setString(5, orgn_file_nm);
				ps.setString(6, sttus);
				ps.setString(7, module_nm);
				ps.setLong(8, rcrd_cnt);
				ps.setLong(9, err_cnt);
				ps.setLong(10, prc_cnt);
				ps.setString(11, file_type);
			}
		};
		
		jdbcTemplate.update(DbProcessor.getInsertAudit_query(), inputParameter);
	}
}

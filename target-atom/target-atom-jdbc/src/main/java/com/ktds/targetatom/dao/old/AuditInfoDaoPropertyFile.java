package com.ktds.targetatom.dao.old;
//package com.ktds.targetatom.dao;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.camel.api.management.ManagedResource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ktds.targetatom.vo.AuditInfo;
//import com.ktds.targetatom.vo.BaseInfo;
//
//@Component
//public class AuditInfoDaoPropertyFile {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//	private RowMapper<AuditInfo> auditInfoMapper = BeanPropertyRowMapper.newInstance(AuditInfo.class);
//	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Value("${sql.audit.findByFileName}") 
//	private String findByFileNameSQL;
//
//	@Value("${sql.insertAudit}") 
//	private String insertAudit;
//
//	
//	@Value("${sql.updateStatusAudit}") 
//	private String updateStatusAudit;
//
//	@Value("${sql.updateAudit}") 
//	private String updateAudit;
//	
////	@Transactional(rollbackFor=Exception.class)
////	public int insert(String first_cret_pgm_id, long up_adit_seq, String file_nm, String trtm_dir_nm, String orgn_file_nm,
////			                String sttus, String module_nm, long rcrd_cnt, long err_cnt, long prc_cnt, String file_type) {
////		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
////			public void setValues(PreparedStatement ps) throws SQLException {
////				ps.setString(1, first_cret_pgm_id);
////				ps.setLong(2, up_adit_seq);
////				ps.setString(3, file_nm);
////				ps.setString(4, trtm_dir_nm);
////				ps.setString(5, orgn_file_nm);
////				ps.setString(6, sttus);
////				ps.setString(7, module_nm);
////				ps.setLong(8, rcrd_cnt);
////				ps.setLong(9, err_cnt);
////				ps.setLong(10, prc_cnt);
////				ps.setString(11, file_type);
////			}
////		};
////		
////		return jdbcTemplate.update(insertAudit, inputParameter);
////	}
////
//	@Transactional(rollbackFor=Exception.class)
//	public int insert(long upAditSeq, String fileNm, String trmDirNm, String orgnFileNm,
//			                String sttus, String moduleNm, long rcrdCnt, long errCnt, long prcCnt, String fileType) {
//
//		AuditInfo auditInfo = new AuditInfo();
//		auditInfo.setUpAditSeq(upAditSeq);
//		auditInfo.setFileNm(fileNm);
//		auditInfo.setTrmDirNm(trmDirNm);
//		auditInfo.setOrgnFileNm(orgnFileNm);
//		auditInfo.setSttus(sttus);
//		auditInfo.setModuleNm(moduleNm);
//		auditInfo.setRcrdCnt(rcrdCnt);
//		auditInfo.setErrCnt(errCnt);
//		auditInfo.setPrcCnt(prcCnt);
//		auditInfo.setFileType(fileType);
//		return insert(auditInfo);
//	}
//
//	@Transactional(rollbackFor=Exception.class)
//	public int insert(AuditInfo auditInfo) {
//		
//		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setLong(2, auditInfo.getUpAditSeq());
//				ps.setString(3, auditInfo.getFileNm());
//				ps.setString(4, auditInfo.getTrmDirNm());
//				ps.setString(5, auditInfo.getOrgnFileNm());
//				ps.setString(6, auditInfo.getSttus());
//				ps.setString(7, auditInfo.getModuleNm());
//				ps.setLong(8, auditInfo.getRcrdCnt());
//				ps.setLong(9, auditInfo.getErrCnt());
//				ps.setLong(10, auditInfo.getPrcCnt());
//				ps.setString(11, auditInfo.getFileType());
//			}
//		};
//		
//		return jdbcTemplate.update(insertAudit, inputParameter);
//	}
//
//
//	@Transactional(rollbackFor=Exception.class)
//	public void update(String status, 
//            String last_chg_trtr_id, String last_chg_pgm_id, long rcrd_cnt, long err_cnt, long prc_cnt, long identifier) {
//		
//		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, status);
//				ps.setString(2, last_chg_trtr_id);
//				ps.setString(3, last_chg_pgm_id);
//				ps.setLong(4, rcrd_cnt);
//				ps.setLong(5, err_cnt);
//				ps.setLong(6, prc_cnt);
//				ps.setLong(7, identifier);
//			}
//		};
//		
//		jdbcTemplate.update(updateAudit, inputParameter);
//	}
//	
//	@Transactional(rollbackFor=Exception.class)
//	public void updateStatus(String status, 
//            String last_chg_trtr_id, String last_chg_pgm_id, long identifier) throws Exception {
//		
//		PreparedStatementSetter inputParameter = new PreparedStatementSetter() {
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, status);
//				ps.setString(2, last_chg_trtr_id);
//				ps.setString(3, last_chg_pgm_id);
//				ps.setLong(4, identifier);
//			}
//		};
//		
//		jdbcTemplate.update(updateStatusAudit, inputParameter);
//	}
//
////	@Transactional(readOnly=true)
////	public Map<String, Object> getAuditList(String fileName, String filePath) {
////		try {
////			return jdbcTemplate.queryForMap(selectAudit, fileName, filePath.replace('\\', '/'));
////		} catch (EmptyResultDataAccessException e) {
////			log.error("File Name :: {} is not in audit table", fileName);
////			return null;
////		}
////	}
//	@SuppressWarnings("finally")
//	@Transactional(readOnly=true)
//	public BaseInfo findByFileName(String fileName, String filePath) {
//		log.debug("--> findByFileName");
//		log.trace("   fileName={}", fileName);
//		log.trace("   filePath={}", filePath);
//		BaseInfo auditInfo = null;
//		
//		try {
//			List<AuditInfo> auditList = jdbcTemplate.query(findByFileNameSQL,  
//					new Object[] {fileName, filePath.replace('\\', '/')}, 
//					auditInfoMapper);
//			if(auditList != null && auditList.size() > 0) {
//				auditInfo = auditList.get(0);
//			}
//		} catch (DataAccessException e) {
//			log.error("Exception occurred during getAuditList :", e);
//		} finally {
//			log.debug("<-- findByFileName");
//			return auditInfo;
//		}
//	}
//}
//
//	

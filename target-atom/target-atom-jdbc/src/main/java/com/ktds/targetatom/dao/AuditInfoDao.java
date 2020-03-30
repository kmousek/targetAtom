package com.ktds.targetatom.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.targetatom.vo.*;

@Repository
public class AuditInfoDao {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private NamedParameterJdbcOperations db;
	private SimpleJdbcInsertOperations auditInsertion;
	private RowMapper<AuditInfo> auditInfoMapper = BeanPropertyRowMapper.newInstance(AuditInfo.class);
	
	@Autowired
	public AuditInfoDao(DataSource dataSource) {
		this.db = new NamedParameterJdbcTemplate(dataSource);
		this.auditInsertion = new SimpleJdbcInsert(dataSource)
			.withTableName("TB_ADIT_HST")
			.usingGeneratedKeyColumns("adit_cret_seq");
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Long insert(long upAditSeq, String fileNm, String trmDirNm, String orgnFileNm,
			                String sttus, String moduleNm, long rcrdCnt, long errCnt, long prcCnt, String fileType) {

		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setUpAditSeq(upAditSeq);
		auditInfo.setFileNm(fileNm);
		auditInfo.setTrmDirNm(trmDirNm);
		auditInfo.setOrgnFileNm(orgnFileNm);
		auditInfo.setSttus(sttus);
		auditInfo.setModuleNm(moduleNm);
		auditInfo.setRcrdCnt(rcrdCnt);
		auditInfo.setErrCnt(errCnt);
		auditInfo.setPrcCnt(prcCnt);
		auditInfo.setFileType(fileType);
		return insert(auditInfo);
	}

	@Transactional(rollbackFor=Exception.class)
	public Long insert(AuditInfo auditInfo) {
		Long result = 0L;
//		log.debug("--> insert");
		log.trace("   auditInfo={}", auditInfo);
		try {
			SqlParameterSource params = new BeanPropertySqlParameterSource(auditInfo);
			return (result = auditInsertion.executeAndReturnKey(params).longValue());
		} finally {
//			log.debug("<-- insert");
//			log.trace("   result={}", result);
		}
	}


	@Transactional(rollbackFor=Exception.class)
	public int update(long aditCretSeq, String sttus, 
            String lastChgTrtrId, String lastChgPgmId, long rcrdCnt, long errCnt, long prcCnt, String fileType) {
		
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setAditCretSeq(aditCretSeq);
		auditInfo.setSttus(sttus);
		auditInfo.setLastChgTrtrId(lastChgTrtrId);
		auditInfo.setLastChgPgmId(lastChgPgmId);
		auditInfo.setRcrdCnt(rcrdCnt);
		auditInfo.setErrCnt(errCnt);
		auditInfo.setPrcCnt(prcCnt);
		auditInfo.setFileType(fileType);
		return update(auditInfo);
	}

	@Transactional(rollbackFor=Exception.class)
	public int update(AuditInfo auditInfo) {
		int result = 0;
//		log.debug("--> update");
//		log.trace("   auditInfo={}", auditInfo);
		SqlParameterSource params = new BeanPropertySqlParameterSource(auditInfo);
		try {
			result = db.update(AuditInfoSQL.update, params);
			return result;
		} finally {
//			log.debug("<-- update");
//			log.trace("   result={}", result);
		}
	}

	@Transactional(rollbackFor=Exception.class)
	public int updateStatus(long aditCretSeq, String sttus, 
            String lastChgTrtrId, String lastChgPgmId) throws Exception {
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setAditCretSeq(aditCretSeq);
		auditInfo.setSttus(sttus);
		auditInfo.setLastChgTrtrId(lastChgTrtrId);
		auditInfo.setLastChgPgmId(lastChgPgmId);
		return updateStatus(auditInfo);			
	};

	@Transactional(rollbackFor=Exception.class)
	public int updateStatus(AuditInfo auditInfo) {
		int result = 0;
//		log.debug("--> updateStatus");
//		log.trace("   auditInfo={}", auditInfo);
		SqlParameterSource params = new BeanPropertySqlParameterSource(auditInfo);
		try {
			result = db.update(AuditInfoSQL.updateStatus, params);
			return result;
		} finally {
//			log.debug("<-- updateStatus");
//			log.trace("   result={}", result);
		}
	}
	
//	@Transactional(rollbackFor=Exception.class)
//	public int updateDistributerStatus(long aditCretSeq, String sttus, 
//            String lastChgTrtrId, String lastChgPgmId, String fileNm, String fileType) throws Exception {
//		AuditInfo auditInfo = new AuditInfo();
//		auditInfo.setAditCretSeq(aditCretSeq);
//		auditInfo.setSttus(sttus);
//		auditInfo.setLastChgTrtrId(lastChgTrtrId);
//		auditInfo.setLastChgPgmId(lastChgPgmId);
//		auditInfo.setFileNm(fileNm);
//		auditInfo.setFileType(fileType);
//		return updateDistributerStatus(auditInfo);			
//	};

	@Transactional(rollbackFor=Exception.class)
	public int updateDistributerStatus(AuditInfo auditInfo) {
		int result = 0;
//		log.debug("--> updateDistributeStatus");
//		log.trace("   auditInfo={}", auditInfo);
		SqlParameterSource params = new BeanPropertySqlParameterSource(auditInfo);
		try {
			result = db.update(AuditInfoSQL.updateDistributerStatus, params);
			return result;
		} finally {
//			log.debug("<-- updateDistributeStatus");
//			log.trace("   result={}", result);
		}
	}

	@Transactional(readOnly=true)
	public AuditInfo findById(Long aditCretSeq) {
//		log.debug("--> findById");
//		log.trace("   aditCretSeq={}", aditCretSeq);
		AuditInfo auditInfo = null;
		try {
			Map<String, Long> params = Collections.singletonMap("aditCretSeq", aditCretSeq);
			List<AuditInfo> auditList = db.query(AuditInfoSQL.findById, params, auditInfoMapper);
			if(auditList != null && auditList.size() > 0) {
				auditInfo = auditList.get(0);
			}
			return auditInfo;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- findById");
//			log.trace("   result={}", auditInfo);
		}
	}
	
	@Transactional(readOnly=true)
	public AuditInfo findByFileName(String fileName, String filePath) {
//		log.debug("--> findByFileName");
//		log.trace("   fileName={}", fileName);
//		log.trace("   filePath={}", filePath);
		AuditInfo auditInfo = null;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("fileName", fileName);
			params.put("trmDirNm", filePath);
//			log.trace("   auditInfoMapper={}", ToStringBuilder.reflectionToString(auditInfoMapper, ToStringStyle.MULTI_LINE_STYLE));
			List<AuditInfo> auditList = db.query(AuditInfoSQL.findByFileName, params, auditInfoMapper);
			if(auditList != null && auditList.size() > 0) {
				auditInfo = auditList.get(0);
			}
			return auditInfo;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- findByFileName");
//			log.trace("   result={}", auditInfo);
		}
	}
	
	@Transactional(readOnly=true)
	public AuditInfo distributerFindByFileName(String fileName, String fileType, long aditCretSeq) {
//		log.debug("--> findByFileName");
//		log.trace("   fileName={}", fileName);
//		log.trace("   filePath={}", filePath);
		AuditInfo auditInfo = null;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("fileName", fileName);
			params.put("fileType", fileType);
			params.put("aditCretSeq", aditCretSeq);
//			log.trace("   auditInfoMapper={}", ToStringBuilder.reflectionToString(auditInfoMapper, ToStringStyle.MULTI_LINE_STYLE));
			List<AuditInfo> auditList = db.query(AuditInfoSQL.distributerFindByFileName, params, auditInfoMapper);
			if(auditList != null && auditList.size() > 0) {
				auditInfo = auditList.get(0);
			}
			return auditInfo;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- findByFileName");
//			log.trace("   result={}", auditInfo);
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int delete(Long aditCretSeq) {
		int result = 0;
//		log.debug("--> delete");
//		log.trace("   aditCretSeq={}", aditCretSeq);
		Map<String, Long> params = Collections.singletonMap("aditCretSeq", aditCretSeq);
		try {
			result = db.update(AuditInfoSQL.deleteById, params);
			return result;
		} finally {
//			log.debug("<-- delete");
//			log.trace("   result={}", result);
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertDownStreamAuditInfo(AuditInfo auditInfo) {
		int result = 0;
//		log.debug("--> updateStatus");
//		log.trace("   auditInfo={}", auditInfo);
		SqlParameterSource params = new BeanPropertySqlParameterSource(auditInfo);
		try {
			result = db.update(AuditInfoSQL.insertDownStreamAuditInfo, params);
			return result;
		} finally {
//			log.debug("<-- updateStatus");
//			log.trace("   result={}", result);
		}
	}
	
	@Transactional(readOnly=true)
	public int getAditDupFileCnt(AuditInfo inAudit) {
//		log.debug("--> findById");
//		log.trace("   aditCretSeq={}", aditCretSeq);
//		AuditInfo auditInfo = null;
		try {
			SqlParameterSource params = new BeanPropertySqlParameterSource(inAudit);
			int result =db.queryForObject(AuditInfoSQL.getAditDupFileCnt, params, Integer.class);
//			if(auditList != null && auditList.size() > 0) {
//				auditInfo = auditList.get(0);
//			}
			return result;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return -1;
		} finally {
//			log.debug("<-- findById");
//			log.trace("   result={}", auditInfo);
		}
	}
	
	
	/* knj test 용 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteAll() {
		int result = 0;
		Map<String, Long> params = null;
		try {
			result = db.update(AuditInfoSQL.deleteAll, params);
			return result;
		} finally {
			
		}
	}
	
}

	

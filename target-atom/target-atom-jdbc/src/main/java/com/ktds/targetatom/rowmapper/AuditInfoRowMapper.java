package com.ktds.targetatom.rowmapper;
//package com.ktds.targetatom.rowmapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jdbc.core.RowMapper;
//
//import com.ktds.targetatom.vo.AuditInfo;
//
//public class AuditInfoRowMapper implements RowMapper<AuditInfo> {
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//	
//	@Override
//	public AuditInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//		AuditInfo audit = new AuditInfo();
//		audit.setAditCretSeq(rs.getLong("audit_cret_seq"));
////		audit.setFirstCretDt(LocalDateTime.parse(rs.getString("first_cret_dt"), dateFormatter));
//		audit.setFirstCretTrtrId(rs.getString("first_cret_trtr_id"));
////		audit.setLastChgDt(LocalDateTime.parse(rs.getString("first_cret_dt"), dateFormatter));
//		audit.setLastChgTrtrId(rs.getString("last_chg_trtr_id"));
//		audit.setFirstCretPgmId(rs.getString("first_cret_pgm_id"));
//		audit.setLastChgPgmId(rs.getString("last_chg_pgm_id"));
//		audit.setUpAditSeq(rs.getLong("up_adit_seq"));
//		audit.setFileNm(rs.getString("file_nm"));
//		audit.setTrmDirNm(rs.getString("trm_dir_nm"));
//		audit.setOrgnFileNm(rs.getString("orgn_file_nm"));
//		audit.setSttus(rs.getString("sttus"));
//		audit.setModuleNm(rs.getString("module_nm"));
//		audit.setRcrdCnt(rs.getLong("rcrd_cnt"));
//		audit.setErrCnt(rs.getLong("err_cnt"));
//		audit.setPrcCnt(rs.getLong("prc_cnt"));
//		audit.setFileType(rs.getString("file_type"));
//
//		return audit;
//	}
//}

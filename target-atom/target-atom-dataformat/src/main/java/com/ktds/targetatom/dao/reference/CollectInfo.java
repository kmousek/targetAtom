package com.ktds.targetatom.dao.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectInfo {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private String sWflow_inst_id;
	private String sEfct_st_dt;
	private String sNe_id;
	private String sNe_type_id;
	private String sCdr_file_colec_dir_nm;
	private String sOrigin_fmt_id;
	private String sTrm_dir_nm;
	private String sCdr_file_nmng_rule_sbst;
	private String sFile_nm_len;
	private String sFns_file_cret_yn;
	private String sFns_file_div_cd;
	
	public String getsWflow_inst_id() {
		return sWflow_inst_id;
	}
	
	public void setsWflow_inst_id(String sWflow_inst_id) {
		this.sWflow_inst_id = sWflow_inst_id;
	}
	
	public String getsEfct_st_dt() {
		return sEfct_st_dt;
	}
	
	public void setsEfct_st_dt(String sEfct_st_dt) {
		this.sEfct_st_dt = sEfct_st_dt;
	}
	
	public String getsNe_id() {
		return sNe_id;
	}
	
	public void setsNe_id(String sNe_id) {
		this.sNe_id = sNe_id;
	}
	
	public String getsNe_type_id() {
		return sNe_type_id;
	}
	
	public void setsNe_type_id(String sNe_type_id) {
		this.sNe_type_id = sNe_type_id;
	}
	
	public String getsCdr_file_colec_dir_nm() {
		return sCdr_file_colec_dir_nm;
	}
	
	public void setsCdr_file_colec_dir_nm(String sCdr_file_colec_dir_nm) {
		this.sCdr_file_colec_dir_nm = sCdr_file_colec_dir_nm;
	}
	
	public String getsOrigin_fmt_id() {
		return sOrigin_fmt_id;
	}
	
	public void setsOrigin_fmt_id(String sOrigin_fmt_id) {
		this.sOrigin_fmt_id = sOrigin_fmt_id;
	}
	
	public String getsTrm_dir_nm() {
		return sTrm_dir_nm;
	}
	
	public void setsTrm_dir_nm(String sTrm_dir_nm) {
		this.sTrm_dir_nm = sTrm_dir_nm;
	}
	
	public String getsCdr_file_nmng_rule_sbst() {
		return sCdr_file_nmng_rule_sbst;
	}
	
	public void setsCdr_file_nmng_rule_sbst(String sCdr_file_nmng_rule_sbst) {
		this.sCdr_file_nmng_rule_sbst = sCdr_file_nmng_rule_sbst;
	}
	
	public String getsFile_nm_len() {
		return sFile_nm_len;
	}
	
	public void setsFile_nm_len(String sFile_nm_len) {
		this.sFile_nm_len = sFile_nm_len;
	}
	
	public String getsFns_file_cret_yn() {
		return sFns_file_cret_yn;
	}
	
	public void setsFns_file_cret_yn(String sFns_file_cret_yn) {
		this.sFns_file_cret_yn = sFns_file_cret_yn;
	}
	
	public String getsFns_file_div_cd() {
		return sFns_file_div_cd;
	}
	
	public void setsFns_file_div_cd(String sFns_file_div_cd) {
		this.sFns_file_div_cd = sFns_file_div_cd;
	}
	
	@Override
	public String toString() {
		return "ReferenceInfo [sWflow_inst_id=" + sWflow_inst_id + ", sEfct_st_dt=" + sEfct_st_dt + ", sNe_id=" + sNe_id
				+ ", sNe_type_id=" + sNe_type_id + ", sCdr_file_colec_dir_nm=" + sCdr_file_colec_dir_nm
				+ ", sOrigin_fmt_id=" + sOrigin_fmt_id + ", sTrm_dir_nm=" + sTrm_dir_nm + ", sCdr_file_nmng_rule_sbst="
				+ sCdr_file_nmng_rule_sbst + ", sFile_nm_len=" + sFile_nm_len + ", sFns_file_cret_yn="
				+ sFns_file_cret_yn + ", sFns_file_div_cd=" + sFns_file_div_cd + "]";
	}
}

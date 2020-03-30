package com.ktds.targetatom.dao.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditInfo {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private long sAdit_cret_seq = 0;
	private String sFile_nm;
	private long sUp_adit_seq = 0;
	private String sTrm_dir_nm;
	private String sOrgn_file_nm;
	private String sSttus;	
	private String sModule_nm;
	private String sFile_type;
	
	public String getsFile_nm() {
		return sFile_nm;
	}
	
	public void setsFile_nm(String sFile_nm) {
		this.sFile_nm = sFile_nm;
	}
	
	public long getsAdit_cret_seq() {
		return sAdit_cret_seq;
	}
	
	public void setsAdit_cret_seq(long sAdit_cret_seq) {
		this.sAdit_cret_seq = sAdit_cret_seq;
	}
	
	public long getsUp_adit_seq() {
		return sUp_adit_seq;
	}
	
	public void setsUp_adit_seq(long sUp_adit_seq) {
		this.sUp_adit_seq = sUp_adit_seq;
	}
	
	public String getsTrm_dir_nm() {
		return sTrm_dir_nm;
	}
	
	public void setsTrm_dir_nm(String sTrm_dir_nm) {
		this.sTrm_dir_nm = sTrm_dir_nm;
	}
	
	public String getsOrgn_file_nm() {
		return sOrgn_file_nm;
	}
	
	public void setsOrgn_file_nm(String sOrgn_file_nm) {
		this.sOrgn_file_nm = sOrgn_file_nm;
	}
	
	public String getsSttus() {
		return sSttus;
	}
	
	public void setsSttus(String sSttus) {
		this.sSttus = sSttus;
	}
	
	public String getsModule_nm() {
		return sModule_nm;
	}
	
	public void setsModule_nm(String sModule_nm) {
		this.sModule_nm = sModule_nm;
	}
	
	public String getsFile_type() {
		return sFile_type;
	}
	
	public void setsFile_type(String sFile_type) {
		this.sFile_type = sFile_type;
	}
	
	@Override
	public String toString() {
		return "SelectAuditInfo [sAdit_cret_seq=" + sAdit_cret_seq + ", sFile_nm=" + sFile_nm + ", sUp_adit_seq="
				+ sUp_adit_seq + ", sTrm_dir_nm=" + sTrm_dir_nm + ", sOrgn_file_nm=" + sOrgn_file_nm + ", sSttus="
				+ sSttus + ", sModule_nm=" + sModule_nm + ", sFile_type=" + sFile_type + "]";
	}
}

package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CdrBaseInfo extends BaseInfo {

	private String wflowInstId;
	private String cdrFileColecDirNm;
	private String fnsFileCretYn;
	private String fnsFileDivCd;
	private String originFmtId;
	private String cdrFileNmngRuleSbst;
	private String trmDirNm;
	private String dssId;
	private int    fileCretCyclTime;
	private String cdrcollVrfYn;
	private String cdrcollVrfId;
	private String cdrFileFmtTypeCd;
	private int    fileNmLen;
	private String duplFileColecYn;
	private String cdrFileTypeCd;
	private String trmQueNm;
	
	public String getWflowInstId() {
		return wflowInstId;
	}
	public void setWflowInstId(String wflowInstId) {
		this.wflowInstId = wflowInstId;
	}
	public String getCdrFileColecDirNm() {
		return cdrFileColecDirNm;
	}
	public void setCdrFileColecDirNm(String cdrFileColecDirNm) {
		this.cdrFileColecDirNm = cdrFileColecDirNm;
	}
	public String getFnsFileCretYn() {
		return fnsFileCretYn;
	}
	public void setFnsFileCretYn(String fnsFileCretYn) {
		this.fnsFileCretYn = fnsFileCretYn;
	}
	public String getFnsFileDivCd() {
		return fnsFileDivCd;
	}
	public void setFnsFileDivCd(String fnsFileDivCd) {
		this.fnsFileDivCd = fnsFileDivCd;
	}
	public String getOriginFmtId() {
		return originFmtId;
	}
	public void setOriginFmtId(String originFmtId) {
		this.originFmtId = originFmtId;
	}
	public String getCdrFileNmngRuleSbst() {
		return cdrFileNmngRuleSbst;
	}
	public void setCdrFileNmngRuleSbst(String cdrFileNmngRuleSbst) {
		this.cdrFileNmngRuleSbst = cdrFileNmngRuleSbst;
	}
	public String getTrmDirNm() {
		return trmDirNm;
	}
	public void setTrmDirNm(String trmDirNm) {
		this.trmDirNm = trmDirNm;
	}
	public String getDssId() {
		return dssId;
	}
	public void setDssId(String dssId) {
		this.dssId = dssId;
	}
	public int getFileCretCyclTime() {
		return fileCretCyclTime;
	}
	public void setFileCretCyclTime(int fileCretCyclTime) {
		this.fileCretCyclTime = fileCretCyclTime;
	}
	public String getCdrcollVrfYn() {
		return cdrcollVrfYn;
	}
	public void setCdrcollVrfYn(String cdrcollVrfYn) {
		this.cdrcollVrfYn = cdrcollVrfYn;
	}
	public String getCdrcollVrfId() {
		return cdrcollVrfId;
	}
	public void setCdrcollVrfId(String cdrcollVrfId) {
		this.cdrcollVrfId = cdrcollVrfId;
	}
	public String getCdrFileFmtTypeCd() {
		return cdrFileFmtTypeCd;
	}
	public void setCdrFileFmtTypeCd(String cdrFileFmtTypeCd) {
		this.cdrFileFmtTypeCd = cdrFileFmtTypeCd;
	}
	public int getFileNmLen() {
		return fileNmLen;
	}
	public void setFileNmLen(int fileNmLen) {
		this.fileNmLen = fileNmLen;
	}
	public String getDuplFileColecYn() {
		return duplFileColecYn;
	}
	public void setDuplFileColecYn(String duplFileColecYn) {
		this.duplFileColecYn = duplFileColecYn;
	}
	public String getCdrFileTypeCd() {
		return cdrFileTypeCd;
	}
	public void setCdrFileTypeCd(String cdrFileTypeCd) {
		this.cdrFileTypeCd = cdrFileTypeCd;
	}
	
	public String getTrmQueNm() {
		return trmQueNm;
	}
	public void setTrmQueNm(String trmQueNm) {
		this.trmQueNm = trmQueNm;
	}
	
	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
}

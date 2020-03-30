package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CdrReferenceInfo extends BaseInfo {
	// TB_WFLOW_INFO
	private String wflowInstId;       // workflow id
	// TB_CDRSEND_BASE_INFO
	private String neId;
	private String neTypeId;
	private String cdrFileColecDirNm; // CDR file 디렉토리명
	private String fnsFileCretYn;     // FIN 파일 여부
	private String fnsFileDivCd;      // FIN 파일 종류 (파일명.FIN or 파일명.확장자.FIN)
	private String originFmtId;
	private String trmDirNm;
	private String dssId;
	// TB_FILE_FMT_INFO
	private String cdrFileTypeCd;      // CDR파일유형코드
	private String cdrFileFmtTypeCd;
	private int    fileNmLen;
	private String cdrFileNmngRuleSbst;

	/**
	 * @return the wflowInstId
	 */
	public String getWflowInstId() {
		return wflowInstId;
	}

	/**
	 * @param wflowInstId the wflowInstId to set
	 */
	public void setWflowInstId(String wflowInstId) {
		this.wflowInstId = wflowInstId;
	}

	/**
	 * @return the neId
	 */
	public String getNeId() {
		return neId;
	}

	/**
	 * @param neId the neId to set
	 */
	public void setNeId(String neId) {
		this.neId = neId;
	}

	/**
	 * @return the neTypeId
	 */
	public String getNeTypeId() {
		return neTypeId;
	}

	/**
	 * @param neTypeId the neTypeId to set
	 */
	public void setNeTypeId(String neTypeId) {
		this.neTypeId = neTypeId;
	}

	/**
	 * @return the cdrFileColecDirNm
	 */
	public String getCdrFileColecDirNm() {
		return cdrFileColecDirNm;
	}

	/**
	 * @param cdrFileColecDirNm the cdrFileColecDirNm to set
	 */
	public void setCdrFileColecDirNm(String cdrFileColecDirNm) {
		this.cdrFileColecDirNm = cdrFileColecDirNm;
	}

	/**
	 * @return the fnsFileCretYn
	 */
	public String getFnsFileCretYn() {
		return fnsFileCretYn;
	}

	/**
	 * @param fnsFileCretYn the fnsFileCretYn to set
	 */
	public void setFnsFileCretYn(String fnsFileCretYn) {
		this.fnsFileCretYn = fnsFileCretYn;
	}

	/**
	 * @return the fnsFileDivCd
	 */
	public String getFnsFileDivCd() {
		return fnsFileDivCd;
	}

	/**
	 * @param fnsFileDivCd the fnsFileDivCd to set
	 */
	public void setFnsFileDivCd(String fnsFileDivCd) {
		this.fnsFileDivCd = fnsFileDivCd;
	}

	/**
	 * @return the originFmtId
	 */
	public String getOriginFmtId() {
		return originFmtId;
	}

	/**
	 * @param originFmtId the originFmtId to set
	 */
	public void setOriginFmtId(String originFmtId) {
		this.originFmtId = originFmtId;
	}

	/**
	 * @return the trmDirNm
	 */
	public String getTrmDirNm() {
		return trmDirNm;
	}

	/**
	 * @param trmDirNm the trmDirNm to set
	 */
	public void setTrmDirNm(String trmDirNm) {
		this.trmDirNm = trmDirNm;
	}

	/**
	 * @return the dssId
	 */
	public String getDssId() {
		return dssId;
	}

	/**
	 * @param dssId the dssId to set
	 */
	public void setDssId(String dssId) {
		this.dssId = dssId;
	}

	/**
	 * @return the cdrFileTypeCd
	 */
	public String getCdrFileTypeCd() {
		return cdrFileTypeCd;
	}

	/**
	 * @param cdrFileTypeCd the cdrFileTypeCd to set
	 */
	public void setCdrFileTypeCd(String cdrFileTypeCd) {
		this.cdrFileTypeCd = cdrFileTypeCd;
	}

	/**
	 * @return the cdrFileFmtTypeCd
	 */
	public String getCdrFileFmtTypeCd() {
		return cdrFileFmtTypeCd;
	}

	/**
	 * @param cdrFileFmtTypeCd the cdrFileFmtTypeCd to set
	 */
	public void setCdrFileFmtTypeCd(String cdrFileFmtTypeCd) {
		this.cdrFileFmtTypeCd = cdrFileFmtTypeCd;
	}

	/**
	 * @return the fileNmLen
	 */
	public int getFileNmLen() {
		return fileNmLen;
	}

	/**
	 * @param fileNmLen the fileNmLen to set
	 */
	public void setFileNmLen(int fileNmLen) {
		this.fileNmLen = fileNmLen;
	}

	/**
	 * @return the cdrFileNmngRuleSbst
	 */
	public String getCdrFileNmngRuleSbst() {
		return cdrFileNmngRuleSbst;
	}

	/**
	 * @param cdrFileNmngRuleSbst the cdrFileNmngRuleSbst to set
	 */
	public void setCdrFileNmngRuleSbst(String cdrFileNmngRuleSbst) {
		this.cdrFileNmngRuleSbst = cdrFileNmngRuleSbst;
	}

	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
}

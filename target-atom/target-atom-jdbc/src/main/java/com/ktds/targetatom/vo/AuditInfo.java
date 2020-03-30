package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditInfo extends BaseInfo {
	private long aditCretSeq = 0L;
	private long upAditSeq = 0L;
	private String fileNm;
	private String trmDirNm;
	private String orgnFileNm;
	private String sttus;
	private String chgSttus;
	private String moduleNm;
	private long rcrdCnt = 0L;
	private long errCnt = 0L;
	private long prcCnt = 0L;
	private String fileType;

	public long getAditCretSeq() {
		return aditCretSeq;
	}
	public void setAditCretSeq(long aditCretSeq) {
		this.aditCretSeq = aditCretSeq;
	}

	public long getUpAditSeq() {
		return upAditSeq;
	}
	public void setUpAditSeq(long upAditSeq) {
		this.upAditSeq = upAditSeq;
	}
	
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	
	public String getTrmDirNm() {
		return trmDirNm;
	}
	public void setTrmDirNm(String trmDirNm) {
		this.trmDirNm = trmDirNm;
	}
	
	public String getOrgnFileNm() {
		return orgnFileNm;
	}
	public void setOrgnFileNm(String orgnFileNm) {
		this.orgnFileNm = orgnFileNm;
	}
	
	public String getSttus() {
		return sttus;
	}
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
	
	public String getChgSttus() {
		return chgSttus;
	}
	public void setChgSttus(String chgSttus) {
		this.chgSttus = chgSttus;
	}
	public String getModuleNm() {
		return moduleNm;
	}
	public void setModuleNm(String moduleNm) {
		this.moduleNm = moduleNm;
	}
	
	public long getRcrdCnt() {
		return rcrdCnt;
	}
	public void setRcrdCnt(long rcrdCnt) {
		this.rcrdCnt = rcrdCnt;
	}
	public long getErrCnt() {
		return errCnt;
	}
	public void setErrCnt(long errCnt) {
		this.errCnt = errCnt;
	}
	public long getPrcCnt() {
		return prcCnt;
	}
	public void setPrcCnt(long prcCnt) {
		this.prcCnt = prcCnt;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
}

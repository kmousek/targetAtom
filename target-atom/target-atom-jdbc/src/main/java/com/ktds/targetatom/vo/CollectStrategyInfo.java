package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CollectStrategyInfo extends BaseInfo {
	
	private String wflowInstId;       // workflow id
	private String cdrFileColecDirNm;
	private String fnsFileCretYn;
	private String fnsFileDivCd;
	
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

	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}

}

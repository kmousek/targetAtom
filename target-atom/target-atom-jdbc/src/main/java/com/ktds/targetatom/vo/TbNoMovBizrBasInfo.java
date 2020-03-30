package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbNoMovBizrBasInfo {
	
	// TB_NO_MOV_BIZR_BAS
	private String sNpCd;
	private String sSettlFileName;
	private String sNpSettlCarrier;
	private String sSettlFeatureCd;
	private String sInNetCallYn;
	private String sSubSettlFeatureId;
	private String sExpireDate;
	private String sEffectDate;

	
	public String getsNpCd() {
		return sNpCd;
	}



	public void setsNpCd(String sNpCd) {
		this.sNpCd = sNpCd;
	}



	public String getsSettlFileName() {
		return sSettlFileName;
	}



	public void setsSettlFileName(String sSettlFileName) {
		this.sSettlFileName = sSettlFileName;
	}



	public String getsNpSettlCarrier() {
		return sNpSettlCarrier;
	}



	public void setsNpSettlCarrier(String sNpSettlCarrier) {
		this.sNpSettlCarrier = sNpSettlCarrier;
	}



	public String getsSettlFeatureCd() {
		return sSettlFeatureCd;
	}



	public void setsSettlFeatureCd(String sSettlFeatureCd) {
		this.sSettlFeatureCd = sSettlFeatureCd;
	}



	public String getsInNetCallYn() {
		return sInNetCallYn;
	}



	public void setsInNetCallYn(String sInNetCallYn) {
		this.sInNetCallYn = sInNetCallYn;
	}



	public String getsSubSettlFeatureId() {
		return sSubSettlFeatureId;
	}



	public void setsSubSettlFeatureId(String sSubSettlFeatureId) {
		this.sSubSettlFeatureId = sSubSettlFeatureId;
	}



	public String getsExpireDate() {
		return sExpireDate;
	}



	public void setsExpireDate(String sExpireDate) {
		this.sExpireDate = sExpireDate;
	}



	public String getsEffectDate() {
		return sEffectDate;
	}



	public void setsEffectDate(String sEffectDate) {
		this.sEffectDate = sEffectDate;
	}




	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


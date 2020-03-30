package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbFeatrBasInfo {
	
	// TB_FEATR_BAS
	
	private String sFeatureFormatId;
	private String sFormatType;
	private String sFeatureNo;
	private String sFeatureType;
	private String sFeatureCd;
	private String sFeatureDropIndi;
	private String sFeatureDescSbst;
	private String sCalledChrgYn;
	private String sSettlDropIndi;
	private String sSubFeatureCd;
	private String sRcvTerminalShowDesc;
	private String sRcvTerminalShowYn;
	private String sExpireDate;
	private String sEffectDate;


	public String getsFeatureFormatId() {
		return sFeatureFormatId;
	}



	public void setsFeatureFormatId(String sFeatureFormatId) {
		this.sFeatureFormatId = sFeatureFormatId;
	}



	public String getsFormatType() {
		return sFormatType;
	}



	public void setsFormatType(String sFormatType) {
		this.sFormatType = sFormatType;
	}



	public String getsFeatureNo() {
		return sFeatureNo;
	}



	public void setsFeatureNo(String sFeatureNo) {
		this.sFeatureNo = sFeatureNo;
	}



	public String getsFeatureType() {
		return sFeatureType;
	}



	public void setsFeatureType(String sFeatureType) {
		this.sFeatureType = sFeatureType;
	}



	public String getsFeatureCd() {
		return sFeatureCd;
	}



	public void setsFeatureCd(String sFeatureCd) {
		this.sFeatureCd = sFeatureCd;
	}



	public String getsFeatureDropIndi() {
		return sFeatureDropIndi;
	}



	public void setsFeatureDropIndi(String sFeatureDropIndi) {
		this.sFeatureDropIndi = sFeatureDropIndi;
	}



	public String getsFeatureDescSbst() {
		return sFeatureDescSbst;
	}



	public void setsFeatureDescSbst(String sFeatureDescSbst) {
		this.sFeatureDescSbst = sFeatureDescSbst;
	}



	public String getsCalledChrgYn() {
		return sCalledChrgYn;
	}



	public void setsCalledChrgYn(String sCalledChrgYn) {
		this.sCalledChrgYn = sCalledChrgYn;
	}



	public String getsSettlDropIndi() {
		return sSettlDropIndi;
	}



	public void setsSettlDropIndi(String sSettlDropIndi) {
		this.sSettlDropIndi = sSettlDropIndi;
	}



	public String getsSubFeatureCd() {
		return sSubFeatureCd;
	}



	public void setsSubFeatureCd(String sSubFeatureCd) {
		this.sSubFeatureCd = sSubFeatureCd;
	}



	public String getsRcvTerminalShowDesc() {
		return sRcvTerminalShowDesc;
	}



	public void setsRcvTerminalShowDesc(String sRcvTerminalShowDesc) {
		this.sRcvTerminalShowDesc = sRcvTerminalShowDesc;
	}



	public String getsRcvTerminalShowYn() {
		return sRcvTerminalShowYn;
	}



	public void setsRcvTerminalShowYn(String sRcvTerminalShowYn) {
		this.sRcvTerminalShowYn = sRcvTerminalShowYn;
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


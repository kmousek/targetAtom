package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbCalNoBasInfo {
	
	// TB_CAL_NO_BAS

	private String sPrefix ;
	private String sNxxNo;
	private String sLineNo;
	private String sExpireDate;
	private String sEffectDate;
	private String sCallgPfixSbst;
	private String sCallingDropIndi;
	private String sSettlDropInd;


	
	public String getsPrefix() {
		return sPrefix;
	}



	public void setsPrefix(String sPrefix) {
		this.sPrefix = sPrefix;
	}



	public String getsNxxNo() {
		return sNxxNo;
	}



	public void setsNxxNo(String sNxxNo) {
		this.sNxxNo = sNxxNo;
	}



	public String getsLineNo() {
		return sLineNo;
	}



	public void setsLineNo(String sLineNo) {
		this.sLineNo = sLineNo;
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



	public String getsCallgPfixSbst() {
		return sCallgPfixSbst;
	}



	public void setsCallgPfixSbst(String sCallgPfixSbst) {
		this.sCallgPfixSbst = sCallgPfixSbst;
	}



	public String getsCallingDropIndi() {
		return sCallingDropIndi;
	}



	public void setsCallingDropIndi(String sCallingDropIndi) {
		this.sCallingDropIndi = sCallingDropIndi;
	}



	public String getsSettlDropInd() {
		return sSettlDropInd;
	}



	public void setsSettlDropInd(String sSettlDropInd) {
		this.sSettlDropInd = sSettlDropInd;
	}



	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


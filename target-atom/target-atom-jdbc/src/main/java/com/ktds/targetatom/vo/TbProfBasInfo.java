package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbProfBasInfo {
	
	// TB_PROF_BAS
	
	private String sItemId;
	private String sItemIndi;
	private String sReturnvalue;
	private String sProfItemSbst;
	private String sEffectDate;
	private String sExpireDate;

	public String getsItemId() {
		return sItemId;
	}




	public void setsItemId(String sItemId) {
		this.sItemId = sItemId;
	}




	public String getsItemIndi() {
		return sItemIndi;
	}




	public void setsItemIndi(String sItemIndi) {
		this.sItemIndi = sItemIndi;
	}




	public String getsReturnvalue() {
		return sReturnvalue;
	}




	public void setsReturnvalue(String sReturnvalue) {
		this.sReturnvalue = sReturnvalue;
	}




	public String getsProfItemSbst() {
		return sProfItemSbst;
	}




	public void setsProfItemSbst(String sProfItemSbst) {
		this.sProfItemSbst = sProfItemSbst;
	}




	public String getsEffectDate() {
		return sEffectDate;
	}




	public void setsEffectDate(String sEffectDate) {
		this.sEffectDate = sEffectDate;
	}




	public String getsExpireDate() {
		return sExpireDate;
	}




	public void setsExpireDate(String sExpireDate) {
		this.sExpireDate = sExpireDate;
	}





	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


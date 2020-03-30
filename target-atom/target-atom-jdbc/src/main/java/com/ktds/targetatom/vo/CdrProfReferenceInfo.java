package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CdrProfReferenceInfo {
	// TB_PROF_BAS
	
	private String profItemId;
	private String profItemDivId;
	private String expDt; 
	private String efctStDt;    
	private String profItemVal;


	
	
	public String getProfItemId() {
		return profItemId;
	}




	public void setProfItemId(String profItemId) {
		this.profItemId = profItemId;
	}




	public String getProfItemDivId() {
		return profItemDivId;
	}




	public void setProfItemDivId(String profItemDivId) {
		this.profItemDivId = profItemDivId;
	}




	public String getExpDt() {
		return expDt;
	}




	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}




	public String getEfctStDt() {
		return efctStDt;
	}




	public void setEfctStDt(String efctStDt) {
		this.efctStDt = efctStDt;
	}




	public String getProfItemVal() {
		return profItemVal;
	}




	public void setProfItemVal(String profItemVal) {
		this.profItemVal = profItemVal;
	}




	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
}

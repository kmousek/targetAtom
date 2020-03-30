package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbIntlPfixChangeBasInfo {
	
	// TB_INTL_PFIX_CHANGE_BAS
	
	private String sCarrierCode;
//	private String sNationCode ;
	private String sCallStartDate;
	private String sCallStartTime;
	private String sEdpId;

	public String getsCarrierCode() {
		return sCarrierCode;
	}

	public void setsCarrierCode(String sCarrierCode) {
		this.sCarrierCode = sCarrierCode;
	}

//	public String getsNationCode() {
//		return sNationCode;
//	}
//
//	public void setsNationCode(String sNationCode) {
//		this.sNationCode = sNationCode;
//	}

	public String getsCallStartDate() {
		return sCallStartDate;
	}

	public void setsCallStartDate(String sCallStartDate) {
		this.sCallStartDate = sCallStartDate;
	}

	public String getsCallStartTime() {
		return sCallStartTime;
	}

	public void setsCallStartTime(String sCallStartTime) {
		this.sCallStartTime = sCallStartTime;
	}

	public String getsEdpId() {
		return sEdpId;
	}

	public void setsEdpId(String sEdpId) {
		this.sEdpId = sEdpId;
	}
	
	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


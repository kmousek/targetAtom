package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbErrMappgBasInfo extends BaseInfo {
	private String sErrorDescription;
	private String sExpireDate;
	private String sEffectDate;
	private String sCdrErrId;
	private String sMzErrLevelDivCd;
	private int    iMzErrPrirtNo;
	private String sOldMzErrId;

	public String getsErrorDescription() {
		return sErrorDescription;
	}

	public String getsExpireDate() {
		return sExpireDate;
	}

	public String getsEffectDate() {
		return sEffectDate;
	}

	public String getsCdrErrId() {
		return sCdrErrId;
	}

	public String getsMzErrLevelDivCd() {
		return sMzErrLevelDivCd;
	}

	public int getiMzErrPrirtNo() {
		return iMzErrPrirtNo;
	}

	public String getsOldMzErrId() {
		return sOldMzErrId;
	}

	public void setsErrorDescription(String sErrorDescription) {
		this.sErrorDescription = sErrorDescription;
	}

	public void setsExpireDate(String sExpireDate) {
		this.sExpireDate = sExpireDate;
	}

	public void setsEffectDate(String sEffectDate) {
		this.sEffectDate = sEffectDate;
	}

	public void setsCdrErrId(String sCdrErrId) {
		this.sCdrErrId = sCdrErrId;
	}

	public void setsMzErrLevelDivCd(String sMzErrLevelDivCd) {
		this.sMzErrLevelDivCd = sMzErrLevelDivCd;
	}

	public void setiMzErrPrirtNo(int iMzErrPrirtNo) {
		this.iMzErrPrirtNo = iMzErrPrirtNo;
	}

	public void setsOldMzErrId(String sOldMzErrId) {
		this.sOldMzErrId = sOldMzErrId;
	}

	@Override
	public String toString() {
		String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		return thisObject;
	}

}

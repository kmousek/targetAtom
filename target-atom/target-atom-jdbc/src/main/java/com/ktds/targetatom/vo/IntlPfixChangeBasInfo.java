package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IntlPfixChangeBasInfo {
	
	// TB_INTL_PFIX_CHANGE_BAS
	
	private String sIntlPrefixCd;
	private String sEffectDate;
	private String sExpireDate;
	private String sCarrierCd;
	private String sEdpCd ;
	private String sEffectStartDate;

	
	public String getsIntlPrefixCd() {
		return sIntlPrefixCd;
	}




	public void setsIntlPrefixCd(String sIntlPrefixCd) {
		this.sIntlPrefixCd = sIntlPrefixCd;
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




	public String getsCarrierCd() {
		return sCarrierCd;
	}




	public void setsCarrierCd(String sCarrierCd) {
		this.sCarrierCd = sCarrierCd;
	}




	public String getsEdpCd() {
		return sEdpCd;
	}




	public void setsEdpCd(String sEdpCd) {
		this.sEdpCd = sEdpCd;
	}




	public String getsEffectStartDate() {
		return sEffectStartDate;
	}




	public void setsEffectStartDate(String sEffectStartDate) {
		this.sEffectStartDate = sEffectStartDate;
	}




	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


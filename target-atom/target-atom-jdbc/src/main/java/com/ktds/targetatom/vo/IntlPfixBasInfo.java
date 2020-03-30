package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IntlPfixBasInfo {
	
	// TB_INTL_PFIX_BAS
	
	private String sIntlPrefixCd;
	private String sCommZoneCd ;
	private String sSubIntlPrefixCd;

	
	public String getsIntlPrefixCd() {
		return sIntlPrefixCd;
	}



	public void setsIntlPrefixCd(String sIntlPrefixCd) {
		this.sIntlPrefixCd = sIntlPrefixCd;
	}



	public String getsCommZoneCd() {
		return sCommZoneCd;
	}



	public void setsCommZoneCd(String sCommZoneCd) {
		this.sCommZoneCd = sCommZoneCd;
	}



	public String getsSubIntlPrefixCd() {
		return sSubIntlPrefixCd;
	}



	public void setsSubIntlPrefixCd(String sSubIntlPrefixCd) {
		this.sSubIntlPrefixCd = sSubIntlPrefixCd;
	}





	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbSvcPrvrBasInfo {
	
	// TB_SVC_PRVR_BAS

	private String sIspId;
	private String sIspName;
	private String sSpFeatureCode;
	
	

	public String getsIspId() {
		return sIspId;
	}



	public void setsIspId(String sIspId) {
		this.sIspId = sIspId;
	}



	public String getsIspName() {
		return sIspName;
	}



	public void setsIspName(String sIspName) {
		this.sIspName = sIspName;
	}



	public String getsSpFeatureCode() {
		return sSpFeatureCode;
	}



	public void setsSpFeatureCode(String sSpFeatureCode) {
		this.sSpFeatureCode = sSpFeatureCode;
	}


	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
	
}


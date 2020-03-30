package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TbPfixRgnBasInfo {
	
	// TB_PFIX_RGN_BAS
		private String sPfixIdfyNo;
		private String sSettlFileName;
		private String sSettlCarrier;
		private String sSettlFeatureCd; 
		private String sNpaAreaNo;
		private String sInNetCallYn;
		private String sSettlEtcFeatureId;
		private String sWiredLessDivideValue;
		private String sEffectDate;
		private String sExpireDate;
		private String sNpId;
		private String sChrgOtherNo; // Common Variable, not DB column


	public String getsSettlFileName() {
			return sSettlFileName;
		}




		public void setsSettlFileName(String sSettlFileName) {
			this.sSettlFileName = sSettlFileName;
		}




		public String getsSettlCarrier() {
			return sSettlCarrier;
		}




		public void setsSettlCarrier(String sSettlCarrier) {
			this.sSettlCarrier = sSettlCarrier;
		}




		public String getsSettlFeatureCd() {
			return sSettlFeatureCd;
		}




		public void setsSettlFeatureCd(String sSettlFeatureCd) {
			this.sSettlFeatureCd = sSettlFeatureCd;
		}




		public String getsNpaAreaNo() {
			return sNpaAreaNo;
		}




		public void setsNpaAreaNo(String sNpaAreaNo) {
			this.sNpaAreaNo = sNpaAreaNo;
		}




		public String getsInNetCallYn() {
			return sInNetCallYn;
		}




		public void setsInNetCallYn(String sInNetCallYn) {
			this.sInNetCallYn = sInNetCallYn;
		}




		public String getsSettlEtcFeatureId() {
			return sSettlEtcFeatureId;
		}




		public void setsSettlEtcFeatureId(String sSettlEtcFeatureId) {
			this.sSettlEtcFeatureId = sSettlEtcFeatureId;
		}




		public String getsWiredLessDivideValue() {
			return sWiredLessDivideValue;
		}




		public void setsWiredLessDivideValue(String sWiredLessDivideValue) {
			this.sWiredLessDivideValue = sWiredLessDivideValue;
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




		public String getsNpId() {
			return sNpId;
		}




		public void setsNpId(String sNpId) {
			this.sNpId = sNpId;
		}




		public String getsChrgOtherNo() {
			return sChrgOtherNo;
		}




		public void setsChrgOtherNo(String sChrgOtherNo) {
			this.sChrgOtherNo = sChrgOtherNo;
		}

		

	public String getsPfixIdfyNo() {
			return sPfixIdfyNo;
		}




		public void setsPfixIdfyNo(String sPfixIdfyNo) {
			this.sPfixIdfyNo = sPfixIdfyNo;
		}




		@Override
		public String toString() {
			return "TbPfixRgnBasInfo [sPfixIdfyNo=" + sPfixIdfyNo + ", sSettlFileName=" + sSettlFileName
					+ ", sSettlCarrier=" + sSettlCarrier + ", sSettlFeatureCd=" + sSettlFeatureCd + ", sNpaAreaNo="
					+ sNpaAreaNo + ", sInNetCallYn=" + sInNetCallYn + ", sSettlEtcFeatureId=" + sSettlEtcFeatureId
					+ ", sWiredLessDivideValue=" + sWiredLessDivideValue + ", sEffectDate=" + sEffectDate
					+ ", sExpireDate=" + sExpireDate + ", sNpId=" + sNpId + ", sChrgOtherNo=" + sChrgOtherNo + "]";
		}

	


	/*@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}*/
	
}


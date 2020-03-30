package com.ktds.targetatom.cdr.kr;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.ConmonCdr;

@FixedLengthRecord(length=3005, header=KRHeader.class, footer=KRTailer.class, crlf="UNIX")
public class KRBody  extends ConmonCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1,trim=true)
	String sType;
	
	@DataField(pos=2, length=7, trim=true, align="L")
	String sRecordSeq;
	
	@DataField(pos=9, length=1, trim=true, defaultValue="2")
	String sServiceType;
	
	@DataField(pos=10, length=20, trim=true, align="L")
	String sChrgId;
	
	@DataField(pos=30, length=20, trim=true, align="L")
	String sChrgNo;
	
	@DataField(pos=50, length=40, trim=true, align="L")
	String sPayNo;
	
	@DataField(pos=90, length=8, trim=true)
	long lChrgAmt;
	
	@DataField(pos=98, length=8, trim=true)
	long lChrgAmt01;
	
	@DataField(pos=106, length=8, trim=true)
	long lChrgAmt02;
	
	@DataField(pos=114, length=14, trim=true, pattern="yyyyMMddHHmmss")
	Date sChrgDateTime;
	
	@DataField(pos=128, length=14, trim=true, pattern="yyyyMMddHHmmss")
	Date sValidDateTime;
	
	@DataField(pos=142, length=10, trim=true, align="L")
	String sBillingCd;
	
	@DataField(pos=152, length=128, trim=true, align="L")
	String sOrderNo;
	
	@DataField(pos=280, length=1, trim=true, align="L")
	String sChrgingType;
	
	@DataField(pos=281, length=128, trim=true, align="L")
	String sPmntNm;
	
	@DataField(pos=409, length=5, trim=true, align="L")
	String sPattern;
	
	@DataField(pos=414, length=20, trim=true, align="L")
	String sPayStorId;
	
	@DataField(pos=434, length=64, trim=true, align="L")
	String sSellerName;
	
	@DataField(pos=498, length=1024, trim=true, align="L")
	String sSellerContactInfo;
	
	@DataField(pos=1522, length=1024, trim=true, align="L")
	String sMrktContactInfo;
	
	@DataField(pos=2546, length=10, trim=true, align="L")
	String sErrorCd;
	
	@DataField(pos=2556, length=461, trim=true, align="L")
	String sFiller1;
	
	boolean sFlag = true;
	
	public boolean issFlag() {
		return sFlag;
	}

	public void setsFlag(boolean sFlag) {
		this.sFlag = sFlag;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getsRecordSeq() {
		return sRecordSeq;
	}

	public void setsRecordSeq(String sRecordSeq) {
		this.sRecordSeq = sRecordSeq;
	}

	public String getsServiceType() {
		return sServiceType;
	}

	public void setsServiceType(String sServiceType) {
		this.sServiceType = sServiceType;
	}

	public String getsChrgId() {
		return sChrgId;
	}

	public void setsChrgId(String sChrgId) {
		this.sChrgId = sChrgId;
	}

	public String getsChrgNo() {
		return sChrgNo;
	}

	public void setsChrgNo(String sChrgNo) {
		this.sChrgNo = sChrgNo;
	}

	public String getsPayNo() {
		return sPayNo;
	}

	public void setsPayNo(String sPayNo) {
		this.sPayNo = sPayNo;
	}

	public long getlChrgAmt() {
		return lChrgAmt;
	}

	public void setlChrgAmt(long lChrgAmt) {
		this.lChrgAmt = lChrgAmt;
	}

	public long getlChrgAmt01() {
		return lChrgAmt01;
	}

	public void setlChrgAmt01(long lChrgAmt01) {
		this.lChrgAmt01 = lChrgAmt01;
	}

	public long getlChrgAmt02() {
		return lChrgAmt02;
	}

	public void setlChrgAmt02(long lChrgAmt02) {
		this.lChrgAmt02 = lChrgAmt02;
	}

	public Date getsChrgDateTime() {
		return sChrgDateTime;
	}

	public void setsChrgDateTime(Date sChrgDateTime) {
		this.sChrgDateTime = sChrgDateTime;
	}

	public Date getsValidDateTime() {
		return sValidDateTime;
	}

	public void setsValidDateTime(Date sValidDateTime) {
		this.sValidDateTime = sValidDateTime;
	}

	public String getsBillingCd() {
		return sBillingCd;
	}

	public void setsBillingCd(String sBillingCd) {
		this.sBillingCd = sBillingCd;
	}

	public String getsOrderNo() {
		return sOrderNo;
	}

	public void setsOrderNo(String sOrderNo) {
		this.sOrderNo = sOrderNo;
	}

	public String getsChrgingType() {
		return sChrgingType;
	}

	public void setsChrgingType(String sChrgingType) {
		this.sChrgingType = sChrgingType;
	}

	public String getsPmntNm() {
		return sPmntNm;
	}

	public void setsPmntNm(String sPmntNm) {
		this.sPmntNm = sPmntNm;
	}

	public String getsPattern() {
		return sPattern;
	}

	public void setsPattern(String sPattern) {
		this.sPattern = sPattern;
	}

	public String getsPayStorId() {
		return sPayStorId;
	}

	public void setsPayStorId(String sPayStorId) {
		this.sPayStorId = sPayStorId;
	}

	public String getsSellerName() {
		return sSellerName;
	}

	public void setsSellerName(String sSellerName) {
		this.sSellerName = sSellerName;
	}

	public String getsSellerContactInfo() {
		return sSellerContactInfo;
	}

	public void setsSellerContactInfo(String sSellerContactInfo) {
		this.sSellerContactInfo = sSellerContactInfo;
	}

	public String getsMrktContactInfo() {
		return sMrktContactInfo;
	}

	public void setsMrktContactInfo(String sMrktContactInfo) {
		this.sMrktContactInfo = sMrktContactInfo;
	}

	public String getsErrorCd() {
		return sErrorCd;
	}

	public void setsErrorCd(String sErrorCd) {
		this.sErrorCd = sErrorCd;
	}

	public String getsFiller1() {
		return sFiller1;
	}

	public void setsFiller1(String sFiller1) {
		this.sFiller1 = sFiller1;
	}

	

	@Override
	public String toString() {
		return "KRBody [log=" + log + ", sType=" + sType + ", sRecordSeq=" + sRecordSeq + ", sServiceType="
				+ sServiceType + ", sChrgId=" + sChrgId + ", sChrgNo=" + sChrgNo + ", sPayNo=" + sPayNo + ", lChrgAmt="
				+ lChrgAmt + ", lChrgAmt01=" + lChrgAmt01 + ", lChrgAmt02=" + lChrgAmt02 + ", sChrgDateTime="
				+ sChrgDateTime + ", sValidDateTime=" + sValidDateTime + ", sBillingCd=" + sBillingCd + ", sOrderNo="
				+ sOrderNo + ", sChrgingType=" + sChrgingType + ", sPmntNm=" + sPmntNm + ", sPattern=" + sPattern
				+ ", sPayStorId=" + sPayStorId + ", sSellerName=" + sSellerName + ", sSellerContactInfo="
				+ sSellerContactInfo + ", sMrktContactInfo=" + sMrktContactInfo + ", sErrorCd=" + sErrorCd
				+ ", sFiller1=" + sFiller1 + ", sFlag=" + sFlag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (lChrgAmt ^ (lChrgAmt >>> 32));
		result = prime * result + (int) (lChrgAmt01 ^ (lChrgAmt01 >>> 32));
		result = prime * result + (int) (lChrgAmt02 ^ (lChrgAmt02 >>> 32));
		result = prime * result + ((sBillingCd == null) ? 0 : sBillingCd.hashCode());
		result = prime * result + ((sChrgDateTime == null) ? 0 : sChrgDateTime.hashCode());
		result = prime * result + ((sChrgId == null) ? 0 : sChrgId.hashCode());
		result = prime * result + ((sChrgNo == null) ? 0 : sChrgNo.hashCode());
		result = prime * result + ((sChrgingType == null) ? 0 : sChrgingType.hashCode());
		result = prime * result + ((sErrorCd == null) ? 0 : sErrorCd.hashCode());
		result = prime * result + ((sFiller1 == null) ? 0 : sFiller1.hashCode());
		result = prime * result + ((sMrktContactInfo == null) ? 0 : sMrktContactInfo.hashCode());
		result = prime * result + ((sOrderNo == null) ? 0 : sOrderNo.hashCode());
		result = prime * result + ((sPattern == null) ? 0 : sPattern.hashCode());
		result = prime * result + ((sPayNo == null) ? 0 : sPayNo.hashCode());
		result = prime * result + ((sPayStorId == null) ? 0 : sPayStorId.hashCode());
		result = prime * result + ((sPmntNm == null) ? 0 : sPmntNm.hashCode());
		result = prime * result + ((sRecordSeq == null) ? 0 : sRecordSeq.hashCode());
		result = prime * result + ((sSellerContactInfo == null) ? 0 : sSellerContactInfo.hashCode());
		result = prime * result + ((sSellerName == null) ? 0 : sSellerName.hashCode());
		result = prime * result + ((sServiceType == null) ? 0 : sServiceType.hashCode());
		result = prime * result + ((sType == null) ? 0 : sType.hashCode());
		result = prime * result + ((sValidDateTime == null) ? 0 : sValidDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KRBody other = (KRBody) obj;
		if (lChrgAmt != other.lChrgAmt)
			return false;
		if (lChrgAmt01 != other.lChrgAmt01)
			return false;
		if (lChrgAmt02 != other.lChrgAmt02)
			return false;
		if (sBillingCd == null) {
			if (other.sBillingCd != null)
				return false;
		} else if (!sBillingCd.equals(other.sBillingCd))
			return false;
		if (sChrgDateTime == null) {
			if (other.sChrgDateTime != null)
				return false;
		} else if (!sChrgDateTime.equals(other.sChrgDateTime))
			return false;
		if (sServiceType == null) {
			if (other.sServiceType != null)
				return false;
		} else if (!sServiceType.equals(other.sServiceType))
			return false;
		if (sChrgId == null) {
			if (other.sChrgId != null)
				return false;
		} else if (!sChrgId.equals(other.sChrgId))
			return false;
		if (sChrgNo == null) {
			if (other.sChrgNo != null)
				return false;
		} else if (!sChrgNo.equals(other.sChrgNo))
			return false;
		if (sChrgingType == null) {
			if (other.sChrgingType != null)
				return false;
		} else if (!sChrgingType.equals(other.sChrgingType))
			return false;
		if (sErrorCd == null) {
			if (other.sErrorCd != null)
				return false;
		} else if (!sErrorCd.equals(other.sErrorCd))
			return false;
		if (sFiller1 == null) {
			if (other.sFiller1 != null)
				return false;
		} else if (!sFiller1.equals(other.sFiller1))
			return false;
		if (sMrktContactInfo == null) {
			if (other.sMrktContactInfo != null)
				return false;
		} else if (!sMrktContactInfo.equals(other.sMrktContactInfo))
			return false;
		if (sOrderNo == null) {
			if (other.sOrderNo != null)
				return false;
		} else if (!sOrderNo.equals(other.sOrderNo))
			return false;
		if (sPattern == null) {
			if (other.sPattern != null)
				return false;
		} else if (!sPattern.equals(other.sPattern))
			return false;
		if (sPayNo == null) {
			if (other.sPayNo != null)
				return false;
		} else if (!sPayNo.equals(other.sPayNo))
			return false;
		if (sPayStorId == null) {
			if (other.sPayStorId != null)
				return false;
		} else if (!sPayStorId.equals(other.sPayStorId))
			return false;
		if (sPmntNm == null) {
			if (other.sPmntNm != null)
				return false;
		} else if (!sPmntNm.equals(other.sPmntNm))
			return false;
		if (sRecordSeq == null) {
			if (other.sRecordSeq != null)
				return false;
		} else if (!sRecordSeq.equals(other.sRecordSeq))
			return false;
		if (sSellerContactInfo == null) {
			if (other.sSellerContactInfo != null)
				return false;
		} else if (!sSellerContactInfo.equals(other.sSellerContactInfo))
			return false;
		if (sSellerName == null) {
			if (other.sSellerName != null)
				return false;
		} else if (!sSellerName.equals(other.sSellerName))
			return false;
		if (sServiceType != other.sServiceType)
			return false;
		if (sType == null) {
			if (other.sType != null)
				return false;
		} else if (!sType.equals(other.sType))
			return false;
		if (sValidDateTime == null) {
			if (other.sValidDateTime != null)
				return false;
		} else if (!sValidDateTime.equals(other.sValidDateTime))
			return false;
		return true;
	}

}

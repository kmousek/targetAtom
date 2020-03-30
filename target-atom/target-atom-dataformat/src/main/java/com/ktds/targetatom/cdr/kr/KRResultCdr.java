package com.ktds.targetatom.cdr.kr;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.BeanCopyUtils;
import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

@FixedLengthRecord(header=KRHeader.class, footer=KRTailer.class)
public class KRResultCdr  extends ConmonCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=1, trim=true, defaultValue="1")
	String sRecType;
	
	@DataField(pos=2, length=2, trim=true, align="L", defaultValue="03")
	String sSourceFormat;
	
	@DataField(pos=4, length=20, trim=true, align="L")
	String sChrgId;
	
	@DataField(pos=24, length=14, trim=true, pattern="yyyyMMddHHmmss")
	Date sChrgDateTime;
	
	@DataField(pos=38, length=14, trim=true, pattern="yyyyMMddHHmmss")
	Date sValidDateTime;
	
/*	@DataField(pos=52, length=0, trim=true, align="L", defaultValue="")
	private char sProdCd;*/
	
	@DataField(pos=52, length=128, trim=true, align="L")
	String sPmntNm;
	
	@DataField(pos=180, length=8, trim=true, align="L")
	long lChrgAmt;
	
	@DataField(pos=188, length=8, trim=true, align="L")
	long lChrgAmt01;
	
	@DataField(pos=196, length=8, trim=true, align="L")
	long lChrgAmt02;
	
	@DataField(pos=204, length=10, trim=true, align="L")
	String sBillingCd;
	
	@DataField(pos=214, length=20, trim=true, align="L")
	String sChrgNo;
	
	@DataField(pos=234, length=5, trim=true, align="L")
	String sPattern;
	
	@DataField(pos=239, length=128, trim=true, align="L")
	String sOrderNo;

	
	public KRResultCdr() {
		super();
	}
	
	public KRResultCdr(KRBody org) {
		BeanCopyUtils.copyProperties(this, org);
		super.setCdrType(CdrType.OUTPUT);
	}

	public Logger getLog() {
		return log;
	}

	public String getsRecType() {
		return sRecType;
	}

	public String getsSourceFormat() {
		return sSourceFormat;
	}

	public String getsChrgId() {
		return sChrgId;
	}

	public Date getsChrgDateTime() {
		return sChrgDateTime;
	}

	public Date getsValidDateTime() {
		return sValidDateTime;
	}

	public String getsPmntNm() {
		return sPmntNm;
	}

	public long getlChrgAmt() {
		return lChrgAmt;
	}

	public long getlChrgAmt01() {
		return lChrgAmt01;
	}

	public long getlChrgAmt02() {
		return lChrgAmt02;
	}

	public String getsBillingCd() {
		return sBillingCd;
	}

	public String getsChrgNo() {
		return sChrgNo;
	}

	public String getsPattern() {
		return sPattern;
	}

	public String getsOrderNo() {
		return sOrderNo;
	}

	public void setsRecType(String sRecType) {
		this.sRecType = sRecType;
	}

	public void setsSourceFormat(String sSourceFormat) {
		this.sSourceFormat = sSourceFormat;
	}

	public void setsChrgId(String sChrgId) {
		this.sChrgId = sChrgId;
	}

	public void setsChrgDateTime(Date sChrgDateTime) {
		this.sChrgDateTime = sChrgDateTime;
	}

	public void setsValidDateTime(Date sValidDateTime) {
		this.sValidDateTime = sValidDateTime;
	}

	public void setsPmntNm(String sPmntNm) {
		this.sPmntNm = sPmntNm;
	}

	public void setlChrgAmt(long lChrgAmt) {
		this.lChrgAmt = lChrgAmt;
	}

	public void setlChrgAmt01(long lChrgAmt01) {
		this.lChrgAmt01 = lChrgAmt01;
	}

	public void setlChrgAmt02(long lChrgAmt02) {
		this.lChrgAmt02 = lChrgAmt02;
	}

	public void setsBillingCd(String sBillingCd) {
		this.sBillingCd = sBillingCd;
	}

	public void setsChrgNo(String sChrgNo) {
		this.sChrgNo = sChrgNo;
	}

	public void setsPattern(String sPattern) {
		this.sPattern = sPattern;
	}

	public void setsOrderNo(String sOrderNo) {
		this.sOrderNo = sOrderNo;
	}

	@Override
	public String toString() {
		return "KRResultCdr [sRecType=" + sRecType + ", sSourceFormat=" + sSourceFormat + ", sChrgId=" + sChrgId
				+ ", sChrgDateTime=" + sChrgDateTime + ", sValidDateTime=" + sValidDateTime + ", sPmntNm=" + sPmntNm
				+ ", lChrgAmt=" + lChrgAmt + ", lChrgAmt01=" + lChrgAmt01 + ", lChrgAmt02=" + lChrgAmt02
				+ ", sBillingCd=" + sBillingCd + ", sChrgNo=" + sChrgNo + ", sPattern=" + sPattern + ", sOrderNo="
				+ sOrderNo + "]";
	}	

}

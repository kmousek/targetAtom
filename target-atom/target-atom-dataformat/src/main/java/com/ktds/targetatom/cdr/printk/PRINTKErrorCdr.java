package com.ktds.targetatom.cdr.printk;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.BeanCopyUtils;
import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;
import com.ktds.targetatom.cdr.printk.PRINTKBody;

@FixedLengthRecord(header=PRINTKHeader.class, footer=PRINTKTailer.class)
public class PRINTKErrorCdr  extends ConmonCdr implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=60, trim=true, paddingChar=' ')
	String sMzFileId; 
	
	@DataField(pos=61, length=10, trim=true, align="R", paddingChar='0')
	int sCdrSeq; 
	
	@DataField(pos=71, length=40, trim=true, paddingChar=' ')
	String sCallingNo;
	
	@DataField(pos=111, length=64, trim=true, paddingChar=' ')
	String sCalledNo;
	
	@DataField(pos=175, length=8, trim=true, paddingChar=' ')
	String sCallStartDate;
	
	@DataField(pos=183, length=7, trim=true, paddingChar=' ')
	String sCallStartTime;
	
	@DataField(pos=190, length=8, trim=true, paddingChar=' ')
	String sCallEndDate;
	
	@DataField(pos=198, length=7, trim=true, paddingChar=' ')
	String sCallEndTime;
	
	@DataField(pos=205, length=3, trim=true, paddingChar=' ')
	String sMzErrorLevelCd;
	
	@DataField(pos=208, length=7, trim=true, paddingChar=' ')
	String sMzErrorCd01;
	
	@DataField(pos=215, length=7, trim=true, paddingChar=' ')
	String sMzErrorCd02;
	
	@DataField(pos=222, length=7, trim=true, paddingChar=' ')
	String sMzErrorCd03;
	
	@DataField(pos=229, length=7, trim=true, paddingChar=' ')
	String sMzErrorCd04;
	
	@DataField(pos=236, length=7, trim=true, paddingChar=' ')
	String sMzErrorCd05;
	
//	@DataField(pos=236, length=7, trim=true, paddingChar=' ')
//	String bRawCDR; // 원시 CDR
	
	public Logger getLog() {
		return log;
	}

	public String getsMzFileId() {
		return sMzFileId;
	}

	public void setsMzFileId(String sMzFileId) {
		this.sMzFileId = sMzFileId;
	}

	public int getsCdrSeq() {
		return sCdrSeq;
	}

	public void setsCdrSeq(int sCdrSeq) {
		this.sCdrSeq = sCdrSeq;
	}

	public String getsCallingNo() {
		return sCallingNo;
	}

	public void setsCallingNo(String sCallingNo) {
		this.sCallingNo = sCallingNo;
	}

	public String getsCalledNo() {
		return sCalledNo;
	}

	public void setsCalledNo(String sCalledNo) {
		this.sCalledNo = sCalledNo;
	}

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

	public String getsCallEndDate() {
		return sCallEndDate;
	}

	public void setsCallEndDate(String sCallEndDate) {
		this.sCallEndDate = sCallEndDate;
	}

	public String getsCallEndTime() {
		return sCallEndTime;
	}

	public void setsCallEndTime(String sCallEndTime) {
		this.sCallEndTime = sCallEndTime;
	}

	public String getsMzErrorLevelCd() {
		return sMzErrorLevelCd;
	}

	public void setsMzErrorLevelCd(String sMzErrorLevelCd) {
		this.sMzErrorLevelCd = sMzErrorLevelCd;
	}

	public String getsMzErrorCd01() {
		return sMzErrorCd01;
	}

	public void setsMzErrorCd01(String sMzErrorCd01) {
		this.sMzErrorCd01 = sMzErrorCd01;
	}

	public String getsMzErrorCd02() {
		return sMzErrorCd02;
	}

	public void setsMzErrorCd02(String sMzErrorCd02) {
		this.sMzErrorCd02 = sMzErrorCd02;
	}

	public String getsMzErrorCd03() {
		return sMzErrorCd03;
	}

	public void setsMzErrorCd03(String sMzErrorCd03) {
		this.sMzErrorCd03 = sMzErrorCd03;
	}

	public String getsMzErrorCd04() {
		return sMzErrorCd04;
	}

	public void setsMzErrorCd04(String sMzErrorCd04) {
		this.sMzErrorCd04 = sMzErrorCd04;
	}

	public String getsMzErrorCd05() {
		return sMzErrorCd05;
	}

	public void setsMzErrorCd05(String sMzErrorCd05) {
		this.sMzErrorCd05 = sMzErrorCd05;
	}

//	public String getbRawCDR() {
//		return bRawCDR;
//	}
//
//	public void setbRawCDR(String bRawCDR) {
//		this.bRawCDR = bRawCDR;
//	}
	
	public PRINTKErrorCdr() {
		super();
	}

	public PRINTKErrorCdr(PRINTKBody org) {
		BeanCopyUtils.copyProperties(this, org);
		super.setCdrType(CdrType.ERROR);
	}

	@Override
	public String toString() {
		return "PRINTKErrorCdr [sMzFileId=" + sMzFileId + ", sCdrSeq=" + sCdrSeq + ", sCallingNo=" + sCallingNo
				+ ", sCalledNo=" + sCalledNo + ", sCallStartDate=" + sCallStartDate + ", sCallStartTime="
				+ sCallStartTime + ", sCallEndDate=" + sCallEndDate + ", sCallEndTime=" + sCallEndTime
				+ ", sMzErrorLevelCd=" + sMzErrorLevelCd + ", sMzErrorCd01=" + sMzErrorCd01 + ", sMzErrorCd02="
				+ sMzErrorCd02 + ", sMzErrorCd03=" + sMzErrorCd03 + ", sMzErrorCd04=" + sMzErrorCd04 + ", sMzErrorCd05="
				+ sMzErrorCd05 + "]";
	}
}

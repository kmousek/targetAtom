package com.ktds.targetatom.cdr.volte;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.BeanCopyUtils;
import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

@FixedLengthRecord(header=VoLTEHeader.class, footer=VoLTETailer.class)
public class VoLTEErrorCdr extends ConmonCdr {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static final long serialVersionUID = 1L;
	
//	@DataField(pos=1, length=1, trim=true, defaultValue = "1")
//	String record_type;
//	
//	@DataField(pos=2, length=2, trim=true, defaultValue = "12")
//	String source_format;
//	
//	@DataField(pos=4, length=7, trim=true)
//	String sErrCd;
//	
//	@DataField(pos=11, length=3, trim=true)
//	String sErrLevelDivCd;
//	
//	@DataField(pos=14, length=10, trim=true, align="R", paddingChar='0')
//	int iErrPriority;
//	
//	@DataField(pos=24, length=11, trim=true)
//	String sOldErrCd;
	
	@DataField(pos=1, length=60, trim=true, align="L", paddingChar=' ')
	String sMzFileId; 
	
	@DataField(pos=61, length=10, trim=true, align="R", paddingChar='0')
	int sCdrSeq; 
	
	@DataField(pos=71, length=40, trim=true, align="L", paddingChar=' ')
	String sCallingNo;
	
	@DataField(pos=111, length=64, trim=true, align="L", paddingChar=' ')
	String sCalledNo;
	
	@DataField(pos=175, length=8, trim=true, align="L", paddingChar=' ')
	String sCallStartDate;
	
	@DataField(pos=183, length=7, trim=true, align="L", paddingChar=' ')
	String sCallStartTime;
	
	@DataField(pos=190, length=8, trim=true, align="L", paddingChar=' ')
	String sCallEndDate;
	
	@DataField(pos=198, length=7, trim=true, align="L", paddingChar=' ')
	String sCallEndTime;
	
	@DataField(pos=205, length=3, trim=true, align="L", paddingChar=' ')
	String sMzErrorLevelCd;
	
	@DataField(pos=208, length=7, trim=true, align="L", paddingChar=' ')
	String sMzErrorCd01;
	
	@DataField(pos=215, length=7, trim=true, align="L", paddingChar=' ')
	String sMzErrorCd02;
	
	@DataField(pos=222, length=7, trim=true, align="L", paddingChar=' ')
	String sMzErrorCd03;
	
	@DataField(pos=229, length=7, trim=true, align="L", paddingChar=' ')
	String sMzErrorCd04;
	
	@DataField(pos=236, length=7, trim=true, align="L", paddingChar=' ')
	String sMzErrorCd05;

	//++ [as-is] bytearray bRawCDR  : dynamic_size(remaining_size);   // 원본 CDR
	
	public VoLTEErrorCdr() {
		super.setCdrType(CdrType.ERROR);
	}
	
	/*public VoLTEErrorCdr(VoLTEBody org) {
		this.sMzFileId = org.getsMzFileId();
		this.sCdrSeq = org.getsCdrSeq();
		this.sCallingNo = org.getsCallingNo();
		this.sCalledNo = org.getsCalledNo();
		this.sCallStartDate = org.getsCallStartDate();
		this.sCallStartTime = org.getsCallStartTime();
		this.sCallEndDate = org.getsCallEndDate();
		this.sCallEndTime = org.getsCallEndTime();
		this.sMzErrorLevelCd = org.getsMzErrorLevelCd();
		this.sMzErrorCd01 = org.getsMzErrorCd01();
		this.sMzErrorCd02 = org.getsMzErrorCd02();
		this.sMzErrorCd03 = org.getsMzErrorCd03();
		this.sMzErrorCd04 = org.getsMzErrorCd04();
		this.sMzErrorCd05 = org.getsMzErrorCd05();
		super.setCdrType(CdrType.ERROR);
		
	}*/
	
	public VoLTEErrorCdr(VoLTEBody org) {
		BeanCopyUtils.copyProperties(this, org);
		super.setCdrType(CdrType.ERROR);
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

	@Override
	public String toString() {
		return "VoLTEErrorCdr [sMzFileId=" + sMzFileId + ", sCdrSeq=" + sCdrSeq + ", sCallingNo=" + sCallingNo
				+ ", sCalledNo=" + sCalledNo + ", sCallStartDate=" + sCallStartDate + ", sCallStartTime="
				+ sCallStartTime + ", sCallEndDate=" + sCallEndDate + ", sCallEndTime=" + sCallEndTime
				+ ", sMzErrorLevelCd=" + sMzErrorLevelCd + ", sMzErrorCd01=" + sMzErrorCd01 + ", sMzErrorCd02="
				+ sMzErrorCd02 + ", sMzErrorCd03=" + sMzErrorCd03 + ", sMzErrorCd04=" + sMzErrorCd04 + ", sMzErrorCd05="
				+ sMzErrorCd05 + "]";
	}
	
//	public String getRecord_type() {
//		return record_type;
//	}
//
//	public void setRecord_type(String record_type) {
//		this.record_type = record_type;
//	}
//
//	public String getSource_format() {
//		return source_format;
//	}
//
//	public void setSource_format(String source_format) {
//		this.source_format = source_format;
//	}
//
//	public String getsErrCd() {
//		return sErrCd;
//	}
//
//	public void setsErrCd(String sErrCd) {
//		this.sErrCd = sErrCd;
//	}
//
//	public String getsErrLevelDivCd() {
//		return sErrLevelDivCd;
//	}
//
//	public void setsErrLevelDivCd(String sErrLevelDivCd) {
//		this.sErrLevelDivCd = sErrLevelDivCd;
//	}
//
//	public int getiErrPriority() {
//		return iErrPriority;
//	}
//
//	public void setiErrPriority(int iErrPriority) {
//		this.iErrPriority = iErrPriority;
//	}
//
//	public String getsOldErrCd() {
//		return sOldErrCd;
//	}
//
//	public void setsOldErrCd(String sOldErrCd) {
//		this.sOldErrCd = sOldErrCd;
//	}
//
	
	

}

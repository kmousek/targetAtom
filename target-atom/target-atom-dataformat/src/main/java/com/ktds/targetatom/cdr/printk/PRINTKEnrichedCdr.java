package com.ktds.targetatom.cdr.printk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

public class PRINTKEnrichedCdr extends ConmonCdr {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	// List 상수 정의
//	public List<String> LST_DACOM_CDR;   	// listCreate(string,"002","300","388");
//	public List<String> LST_INSTNM_SPLT; 	// ltInstanceNameSplit  ex) W3G5MSC_WFL_Processing.P1_VOCIMX01  (Instance : P1_VOCIMX01 )
	public List<String> LST_ERR_CD;		 	// ltErrorCd
	public List<String> LST_MOBILE_PREFIX;	// listCreate(string,"010","011","012","016","017","018","019");
	
	// String 상수 정의
	public final String DACOM_CALL1 		= "002";
	public final String DACOM_CALL2 	    = "300";
	public final String DACOM_CALL3 	    = "388";
	public final String DUMMY_DATE  		= "19700101";
	public final String DUMMY_TIME0 		= "000000";
	public final String DUMMY_TIME1 		= "000011";
	
	String u_calling_number_4="";
	String u_call_start_date_3="";
	
	
	String sInstanceName="";
	String sUsageCall = "Y"; 				// 
	String sCallingNumber="";				// CALLING번호
	String sCallStartDate="";				// call_start_date		시화일자
	String sCallStartTime="";				// call_start_time		시화시간
	
	String sCarrier="";					// intl_prefix
	String sNationCode="";					// nation_code
	String gubun1="";						//
	String sCountryCode="";  				// intl_prefix_cd
	String sCalledNumber="";				// called_number        CALLED 번호
	
	
	String sMzFileId;
	int iCdrSeq;							// CDR 일련번호
	String sCallingNo;
	String sCalledNo;
	String iErrPriority;
//	String sErrLevelDivCd;
	String sMzErrorLevelCd;
	String sMzErrorCd01;
	String sMzErrorCd02;
	String sMzErrorCd03;
	String sMzErrorCd04;
	String sMzErrorCd05;
	String sCallStartDateTime="";			// call_start_date + call_start_time
	String sUseTime;							// use_time				사용시간
	
	int iCdrCnt; 						// CDR Count (Body Count)
//	int iTrailerRecordCnt;				// Trailer Count
	int chargePerMessage;				// charge_per_message
	int messageRate;					// message_rate
	int iErrCnt;						// error Count
	long chargeAmount;					// charge_amount 		과금액 2016.02.11 추가  [coll 파일에 사용]
	
	boolean bCJH_DACOM_CDR;
	boolean bDRLINE_CDR;
	
	// Output 기준정보 조회 - mimSet 으로 다른 Agent 에서 이용
	String sInputDirPath="";
	String sInputFileName="";
	String sFileSeq="";
	String sFormatId="";				// FormatId
	String sFileDate="";
	String sEdpCd="";
	
	// setComAuditErrorSearch
	String callingNumber4="";
	String callStartDate3="";
	String intlPrefix3="";
	String nationCode3="";
	String useTime3="";
	long chargeAmount3;
	int chargePerMessage3;
	int messageRate3;
	
	
	public PRINTKEnrichedCdr () {
		log.debug("creating PRINTKCommon");
		super.setCdrType(CdrType.ENRICHED);
		// list<string> LST_DACOM_CDR = listCreate(string,"002","300","388");
//		this.LST_DACOM_CDR = new ArrayList<String>();
//		this.LST_DACOM_CDR.add("002");
//		this.LST_DACOM_CDR.add("300");
//		this.LST_DACOM_CDR.add("388");
		
		// list<string> LST_MOBILE_PREFIX = listCreate(string,"010","011","012","016","017","018","019");
		this.LST_MOBILE_PREFIX = new ArrayList<String>();
		this.LST_MOBILE_PREFIX.add("010");
		this.LST_MOBILE_PREFIX.add("011");
		this.LST_MOBILE_PREFIX.add("012");
		this.LST_MOBILE_PREFIX.add("016");
		this.LST_MOBILE_PREFIX.add("017");
		this.LST_MOBILE_PREFIX.add("018");
		this.LST_MOBILE_PREFIX.add("019");
	}
	
	public boolean intContains(int[] intList, int value) {
//		log.debug("value={}", value);
		for (int i=0; i < intList.length; i++) {
			//if (intList[i] == Integer.parseInt(value.trim())) {
			if (intList[i] == value) {
				return true;
			}
		}
		
		return false;
	}
	
//	public List<String> getLST_DACOM_CDR() {
//		return LST_DACOM_CDR;
//	}
//
//	public void setLST_DACOM_CDR(List<String> lST_DACOM_CDR) {
//		LST_DACOM_CDR = lST_DACOM_CDR;
//	}

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

	public String getiErrPriority() {
		return iErrPriority;
	}

	public void setiErrPriority(String iErrPriority) {
		this.iErrPriority = iErrPriority;
	}

//	public String getsErrLevelDivCd() {
//		return sErrLevelDivCd;
//	}
//
//	public void setsErrLevelDivCd(String sErrLevelDivCd) {
//		this.sErrLevelDivCd = sErrLevelDivCd;
//	}

	public List<String> getLST_ERR_CD() {
		return LST_ERR_CD;
	}

	public void setLST_ERR_CD(List<String> lST_ERR_CD) {
		LST_ERR_CD = lST_ERR_CD;
	}

	public List<String> getLST_MOBILE_PREFIX() {
		return LST_MOBILE_PREFIX;
	}

	public void setLST_MOBILE_PREFIX(List<String> lST_MOBILE_PREFIX) {
		LST_MOBILE_PREFIX = lST_MOBILE_PREFIX;
	}

	public String getsInstanceName() {
		return sInstanceName;
	}

	public void setsInstanceName(String sInstanceName) {
		this.sInstanceName = sInstanceName;
	}

	public String getsUsageCall() {
		return sUsageCall;
	}

	public void setsUsageCall(String sUsageCall) {
		this.sUsageCall = sUsageCall;
	}

	public String getsCallingNumber() {
		return sCallingNumber;
	}

	public void setsCallingNumber(String sCallingNumber) {
		this.sCallingNumber = sCallingNumber;
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

	public String getsUseTime() {
		return sUseTime;
	}

	public void setsUseTime(String sUseTime) {
		this.sUseTime = sUseTime;
	}

	public String getsCarrier() {
		return sCarrier;
	}

	public void setsCarrier(String sCarrier) {
		this.sCarrier = sCarrier;
	}

	public String getsNationCode() {
		return sNationCode;
	}

	public void setsNationCode(String sNationCode) {
		this.sNationCode = sNationCode;
	}

	public String getGubun1() {
		return gubun1;
	}

	public void setGubun1(String gubun1) {
		this.gubun1 = gubun1;
	}

	public String getsCountryCode() {
		return sCountryCode;
	}

	public void setsCountryCode(String sCountryCode) {
		this.sCountryCode = sCountryCode;
	}

	public String getsCalledNumber() {
		return sCalledNumber;
	}

	public void setsCalledNumber(String sCalledNumber) {
		this.sCalledNumber = sCalledNumber;
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

	public String getsCallStartDateTime() {
		return sCallStartDateTime;
	}

	public void setsCallStartDateTime(String sCallStartDateTime) {
		this.sCallStartDateTime = sCallStartDateTime;
	}

	public int getiCdrCnt() {
		return iCdrCnt;
	}

	public void setiCdrCnt(int iCdrCnt) {
		this.iCdrCnt = iCdrCnt;
	}
	
	public String getsMzFileId() {
		return sMzFileId;
	}

	public void setsMzFileId(String sMzFileId) {
		this.sMzFileId = sMzFileId;
	}

	public int getiCdrSeq() {
		return iCdrSeq;
	}

	public void setiCdrSeq(int iCdrSeq) {
		this.iCdrSeq = iCdrSeq;
	}

	public int getChargePerMessage() {
		return chargePerMessage;
	}

	public void setChargePerMessage(int chargePerMessage) {
		this.chargePerMessage = chargePerMessage;
	}

	public int getMessageRate() {
		return messageRate;
	}

	public void setMessageRate(int messageRate) {
		this.messageRate = messageRate;
	}

	public int getiErrCnt() {
		return iErrCnt;
	}

	public void setiErrCnt(int iErrCnt) {
		this.iErrCnt = iErrCnt;
	}

	public long getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(long chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public boolean isbCJH_DACOM_CDR() {
		return bCJH_DACOM_CDR;
	}

	public void setbCJH_DACOM_CDR(boolean bCJH_DACOM_CDR) {
		this.bCJH_DACOM_CDR = bCJH_DACOM_CDR;
	}

	public boolean isbDRLINE_CDR() {
		return bDRLINE_CDR;
	}

	public void setbDRLINE_CDR(boolean bDRLINE_CDR) {
		this.bDRLINE_CDR = bDRLINE_CDR;
	}

	public String getsInputDirPath() {
		return sInputDirPath;
	}

	public void setsInputDirPath(String sInputDirPath) {
		this.sInputDirPath = sInputDirPath;
	}

	public String getsInputFileName() {
		return sInputFileName;
	}

	public void setsInputFileName(String sInputFileName) {
		this.sInputFileName = sInputFileName;
	}

	public String getsFileSeq() {
		return sFileSeq;
	}

	public void setsFileSeq(String sFileSeq) {
		this.sFileSeq = sFileSeq;
	}

	public String getsFormatId() {
		return sFormatId;
	}

	public void setsFormatId(String sFormatId) {
		this.sFormatId = sFormatId;
	}

	public String getsFileDate() {
		return sFileDate;
	}

	public void setsFileDate(String sFileDate) {
		this.sFileDate = sFileDate;
	}

	public String getsEdpCd() {
		return sEdpCd;
	}

	public void setsEdpCd(String sEdpCd) {
		this.sEdpCd = sEdpCd;
	}

	public String getCallingNumber4() {
		return callingNumber4;
	}

	public void setCallingNumber4(String callingNumber4) {
		this.callingNumber4 = callingNumber4;
	}

	public String getCallStartDate3() {
		return callStartDate3;
	}

	public void setCallStartDate3(String callStartDate3) {
		this.callStartDate3 = callStartDate3;
	}

	public String getIntlPrefix3() {
		return intlPrefix3;
	}

	public void setIntlPrefix3(String intlPrefix3) {
		this.intlPrefix3 = intlPrefix3;
	}

	public String getNationCode3() {
		return nationCode3;
	}

	public void setNationCode3(String nationCode3) {
		this.nationCode3 = nationCode3;
	}

	public String getUseTime3() {
		return useTime3;
	}

	public void setUseTime3(String useTime3) {
		this.useTime3 = useTime3;
	}

	public long getChargeAmount3() {
		return chargeAmount3;
	}

	public void setChargeAmount3(long chargeAmount3) {
		this.chargeAmount3 = chargeAmount3;
	}

	public int getChargePerMessage3() {
		return chargePerMessage3;
	}

	public void setChargePerMessage3(int chargePerMessage3) {
		this.chargePerMessage3 = chargePerMessage3;
	}

	public int getMessageRate3() {
		return messageRate3;
	}

	public void setMessageRate3(int messageRate3) {
		this.messageRate3 = messageRate3;
	}

	public Logger getLog() {
		return log;
	}

	public String getDACOM_CALL1() {
		return DACOM_CALL1;
	}

	public String getDACOM_CALL2() {
		return DACOM_CALL2;
	}

	public String getDACOM_CALL3() {
		return DACOM_CALL3;
	}

	public String getDUMMY_DATE() {
		return DUMMY_DATE;
	}

	public String getDUMMY_TIME0() {
		return DUMMY_TIME0;
	}

	public String getDUMMY_TIME1() {
		return DUMMY_TIME1;
	}
	
	@Override
	public String toString() {
		return "PRINTKEnrichedCdr [LST_MOBILE_PREFIX=" + LST_MOBILE_PREFIX
				+ ", DACOM_CALL1=" + DACOM_CALL1 + ", DACOM_CALL2=" + DACOM_CALL2 + ", DACOM_CALL3=" + DACOM_CALL3
				+ ", DUMMY_DATE=" + DUMMY_DATE + ", DUMMY_TIME0=" + DUMMY_TIME0 + ", DUMMY_TIME1=" + DUMMY_TIME1 
				+ ", sInstanceName=" + sInstanceName + ", sUsageCall=" + sUsageCall + ", sCallingNumber=" 
				+ sCallingNumber + ", sCallStartDate=" + sCallStartDate + ", sCallStartTime=" + sCallStartTime
				+ ", sCarrier=" + sCarrier + ", sNationCode=" + sNationCode + ", gubun1=" + gubun1 + ", sCountryCode=" 
				+ sCountryCode + ", sCalledNumber=" + sCalledNumber + ", sMzFileId=" + sMzFileId + ", iCdrSeq=" 
				+ iCdrSeq + ", sCallingNo=" + sCallingNo + ", sCalledNo=" + sCalledNo + ", iErrPriority=" + iErrPriority 
				+ ", sMzErrorLevelCd=" + sMzErrorLevelCd + ", sMzErrorCd01=" + sMzErrorCd01 + ", sMzErrorCd02=" 
				+ sMzErrorCd02 + ", sMzErrorCd03=" + sMzErrorCd03 + ", sMzErrorCd04=" + sMzErrorCd04 + ", sMzErrorCd05=" 
				+ sMzErrorCd05 + ", sCallStartDateTime=" + sCallStartDateTime + ", sUseTime=" + sUseTime + ", iCdrCnt=" 
				+ iCdrCnt + ", iCdrSeq=" + iCdrSeq + ", chargePerMessage=" + chargePerMessage + ", messageRate=" 
				+ messageRate + ", iErrCnt=" + iErrCnt + ", chargeAmount=" + chargeAmount + ", bCJH_DACOM_CDR=" 
				+ bCJH_DACOM_CDR + ", bDRLINE_CDR=" + bDRLINE_CDR + ", sInputDirPath=" + sInputDirPath 
				+ ", sInputFileName=" + sInputFileName + ", sFileSeq=" + sFileSeq + ", sFormatId=" + sFormatId 
				+ ", sFileDate=" + sFileDate + ", sEdpCd=" + sEdpCd + ", callingNumber4=" + callingNumber4 
				+ ", callStartDate3=" + callStartDate3 + ", intlPrefix3=" + intlPrefix3 + ", nationCode3=" + nationCode3 
				+ ", useTime3=" + useTime3 + ", chargeAmount3=" + chargeAmount3 + ", chargePerMessage3=" 
				+ chargePerMessage3 + ", messageRate3=" + messageRate3 + "]";
	}
		
}	

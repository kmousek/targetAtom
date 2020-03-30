package com.ktds.targetatom.cdr.intkt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

public class INTKTEnrichedCdr extends ConmonCdr {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	public List<String> LST_DACOM_CDR;
	public List<String> LST_MOBILE_PREFIX;

	
	// Wireless Constants/Variables
	public final int MAX_KTF_CTN_LENGTH  = 12;
	public final String PCX_PAD_CHAR     = "F";
	public final String SMS_PAD_CHAR     = "#";
	
    String sUsageCall;                       // WORK.usage_call;
    String sCallingNumber;                   // WORK.calling_number;
    String sCallStartDate;                   // WORK.call_start_date;
    String sCallStartTime;                   // WORK.call_start_time;
    String sCallTermDate;                    // WORK.call_term_date;
    String sCallTermTime;                    // WORK.call_term_time;
    String sCarrier;                         // WORK.carrier
    String sNationCode;                      // WORK.nation_code;
    String sCountryCode;                     // WORK.country_code;
    boolean bCJH_DACOM_CDR;                  // CJH_CDR, DACOM_CDR
    boolean bDRLINE_CDR;                     // DRLINE_CDR
    int     iCdrSeq;
	
	String sMzFileId;
	int sCdrSeq;
	String sCallingNo;
	String sCalledNo;
//	String sCallStartDate;  // 위에 기 선언 변수
//	String sCallStartTime;	// 위에 기 선언 변수
	String sCallEndDate;
	String sCallEndTime;
	String sMzErrorLevelCd;
	String sMzErrorCd01;
	String sMzErrorCd02;
	String sMzErrorCd03;
	String sMzErrorCd04;
	String sMzErrorCd05;

	
	
	public INTKTEnrichedCdr () {
		//log.debug("creating INTKTCommon");
		super.setCdrType(CdrType.ENRICHED);
		
		 // list<string> LST_DACOM_CDR = listCreate(string,"002","300","388");
		 this.LST_DACOM_CDR = new ArrayList<String>();
		 this.LST_DACOM_CDR.add("002");
		 this.LST_DACOM_CDR.add("300");
		 this.LST_DACOM_CDR.add("388");
		 
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
	

	public List<String> getLST_DACOM_CDR() {
		return LST_DACOM_CDR;
	}

	public void setLST_DACOM_CDR(List<String> lST_DACOM_CDR) {
		LST_DACOM_CDR = lST_DACOM_CDR;
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

	public String getsCallTermDate() {
		return sCallTermDate;
	}

	public void setsCallTermDate(String sCallTermDate) {
		this.sCallTermDate = sCallTermDate;
	}

	public String getsCallTermTime() {
		return sCallTermTime;
	}

	public void setsCallTermTime(String sCallTermTime) {
		this.sCallTermTime = sCallTermTime;
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

	public String getsCountryCode() {
		return sCountryCode;
	}

	public void setsCountryCode(String sCountryCode) {
		this.sCountryCode = sCountryCode;
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

	public int getiCdrSeq() {
		return iCdrSeq;
	}

	public void setiCdrSeq(int iCdrSeq) {
		this.iCdrSeq = iCdrSeq;
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

	public Logger getLog() {
		return log;
	}

	public int getMAX_KTF_CTN_LENGTH() {
		return MAX_KTF_CTN_LENGTH;
	}

	public String getPCX_PAD_CHAR() {
		return PCX_PAD_CHAR;
	}

	public String getSMS_PAD_CHAR() {
		return SMS_PAD_CHAR;
	}

	@Override
	public String toString() {
		return "INTKTEnrichedCdr [MAX_KTF_CTN_LENGTH = " + MAX_KTF_CTN_LENGTH + ", PCX_PAD_CHAR = " + PCX_PAD_CHAR + ", SMS_PAD_CHAR = " + SMS_PAD_CHAR + 
				", sUsageCall = " + sUsageCall + ", sCallingNumber = " + sCallingNumber + ", sCallStartDate = " + sCallStartDate + ", sCallStartTime = " + sCallStartTime +
				", sCallTermDate = " + sCallTermDate + ", sCallTermTime = " + sCallTermTime + ", sCarrier = " + sCarrier + ", sNationCode = " + sNationCode +
				", sCountryCode = " + sCountryCode + ", sCountryCode = " + sCountryCode + ", bCJH_DACOM_CDR = " + bCJH_DACOM_CDR + ", bDRLINE_CDR = " + bDRLINE_CDR +
				", iCdrSeq = " + iCdrSeq + ", sMzFileId = " + sMzFileId + ", sCdrSeq = " + sCdrSeq + ", sCallingNo = " + sCallingNo +
				", sCalledNo = " + sCalledNo + ", sCallEndDate = " + sCallEndDate + ", sCallEndTime = " + sCallEndTime + ", sMzErrorLevelCd = " + sMzErrorLevelCd +
				", sMzErrorCd01 = " + sMzErrorCd01 + ", sMzErrorCd02 = " + sMzErrorCd02 + ", sMzErrorCd03 = " + sMzErrorCd03 + ", sMzErrorCd04 = " + sMzErrorCd04 +
				", sMzErrorCd05 = " + sMzErrorCd05 + "]";
	}	
}	

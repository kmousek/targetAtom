package com.ktds.targetatom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ktds.targetatom.service.CollFilenameChk;
import com.ktds.targetatom.service.WlneFilenameSplit;

@Service
public class WlneFilenameCheck {
	
	public boolean chkFilenameRule(String collFileNm, String fileNmngRuleSbst, int fileNmLen) {
		
		if (collFileNm.length() != fileNmLen) {
			return false;
		}
		
		CollFilenameChk collfilenamechk = chkOrgFilename(collFileNm, fileNmngRuleSbst);
		
		if (collfilenamechk.bResult) {
			if (!fileNmngRuleSbst.equals("***")) {
				if (collFileNm.length() != fileNmLen) {
					return false;
				}
			}
		}

		return true;
	}
	
	public CollFilenameChk chkOrgFilename(String collFileNm, String fileNmngRule) {

		CollFilenameChk collFilenameChk = new CollFilenameChk();
		
		if (("***".equals(fileNmngRule)) || ("ERROR".equals(fileNmngRule))) {
			collFilenameChk.bResult = true;
			return collFilenameChk;
		}
		
		
		List<WlneFilenameSplit> ltOrgFilenameSplit = wlneSplitFilename(fileNmngRule);
		
		if (ltOrgFilenameSplit.size() <= 0) {
			collFilenameChk.bResult = false;
			collFilenameChk.sErrorCause = "파일명 Split 실패";
			
			return collFilenameChk;
		}
		
		int idx=0, subIdx=0, len = 0;
		String sChkfilename = collFileNm;
		String sChkIntm="";
		
		while (ltOrgFilenameSplit.size() > idx) {
			WlneFilenameSplit OrgInt = ltOrgFilenameSplit.get(idx);
			
			if (OrgInt.itemDiv.equals("O") || OrgInt.itemDiv.equals("?")) {
				len = OrgInt.itemNm.length();
				
				if (len <= 0) {
					collFilenameChk.bResult = false;
					collFilenameChk.sErrorCause = "원본 파일명에서 읽을 자리수가 0 이하";
					
					return collFilenameChk;
				}
			}
			else if (OrgInt.itemDiv.equals("*")) {
				subIdx = 0;
				len = 0;
				
				if (sChkfilename.length() <= 0) {
					collFilenameChk.bResult = false;
					collFilenameChk.sErrorCause = "* 이후 읽을 자리수가 0 이하";
					
					return collFilenameChk;
				}
				
				if (sChkfilename.lastIndexOf(".", 0) != -1 || sChkfilename.lastIndexOf("-", 0) != -1) {
					
					while (sChkfilename.length() > subIdx) {
						if (sChkfilename.substring(subIdx, subIdx+1).equals("-") || sChkfilename.substring(subIdx, subIdx+1).equals(".")) {
							subIdx = 9999999;
						}
						else {
							len++;
						}
						
						subIdx++;
					}
				}
				else {
					// 마지막 분리항목이 "*" 인경우 
					if (ltOrgFilenameSplit.size() == idx+1) {
						len = sChkfilename.length();
						
						if (len <= 0) {
							collFilenameChk.bResult = false;
							collFilenameChk.sErrorCause = "마지막 분리항목(*) 이후 읽을 자리수가 0 이하";
							
							return collFilenameChk;
						}
					}
					else {
						collFilenameChk.bResult = false;
						collFilenameChk.sErrorCause = "Split 항목 갯수가 맞지 않음";
						
						return collFilenameChk;
					}
				}
			}
			else {

				len = wlneRuleItemLength(OrgInt.itemNm);
			}
			
			if (sChkfilename.length() < len) {
				collFilenameChk.bResult = false;
				collFilenameChk.sErrorCause = "Split 항목 갯수가 맞지 않음";
				
				return collFilenameChk;
			}
			
			sChkIntm = sChkfilename.substring(0, len);
			OrgInt.itemValue = sChkIntm;
					
			sChkfilename = sChkfilename.substring(len, sChkfilename.length());
			
			if (OrgInt.itemDiv.equals("O")) {
				if (!OrgInt.itemDiv.equals(sChkIntm)) {
					collFilenameChk.bResult = false;
					collFilenameChk.sErrorCause = "원본파일명에서 추출한 값과 Rule 처리후 값 비교하여 다른 경우";
					
					return collFilenameChk;
				}
			}
			
			idx++;
		} // end of while loop
		
		collFilenameChk.bResult = true;
		CollFilenameChk.ltWlneFilenameSplit = ltOrgFilenameSplit;
		
		return collFilenameChk;
	}
	
	public List<WlneFilenameSplit> wlneSplitFilename(String fileNmngRule) {  // List<WlneFilenameSplit>
		int idx=0, iSeq=0;
		String sTmpFileNm="", startFlag="N";
		List<WlneFilenameSplit> ltWlneFilenameSplit = new ArrayList<WlneFilenameSplit>();  
		
		while (fileNmngRule.length() > idx) {
			if (fileNmngRule.substring(idx, idx+1).equals("{")) {
				startFlag = "Y"; 
	            sTmpFileNm = "";
			}
			else if (fileNmngRule.substring(idx, idx+1).equals("}")) {
				if (sTmpFileNm.length() > 0) {
					WlneFilenameSplit ruleSplit = new WlneFilenameSplit();
					
					iSeq = iSeq + 1;
					ruleSplit.seqNo = iSeq;
					ruleSplit.itemNm = sTmpFileNm.substring(1, sTmpFileNm.length());
					ruleSplit.itemDiv = sTmpFileNm.substring(0, 1);
					
					ltWlneFilenameSplit.add(ruleSplit);
				}
				else {
					ltWlneFilenameSplit.clear();
				}
				
				startFlag = "N";      
	            sTmpFileNm = "";
			}
			else {
				if ("Y".equals(startFlag)) {
					sTmpFileNm = sTmpFileNm + fileNmngRule.substring(idx, idx+1);
				}
				else {
					WlneFilenameSplit ruleSplit = new WlneFilenameSplit();
					
					iSeq = iSeq + 1;
					ruleSplit.seqNo = iSeq;
					ruleSplit.itemNm = fileNmngRule.substring(idx, idx+1);
					
					if ("?".equals(fileNmngRule.substring(idx, idx+1)) || "*".equals(fileNmngRule.substring(idx, idx+1))) {
						ruleSplit.itemDiv = fileNmngRule.substring(idx, idx+1);
					}
					else {
						ruleSplit.itemDiv = "O";
					}
					
					ltWlneFilenameSplit.add(ruleSplit);
					sTmpFileNm = "";
				}
			}
			
			idx++;
		}
		
		return ltWlneFilenameSplit;
	}
	
	int wlneRuleItemLength(String sItemNm) {
		int iResult=0;
		
		if (sItemNm.equals("STATUS") || sItemNm.equals("CLASS") || sItemNm.equals("ED1") || sItemNm.equals("ED2") ||
				sItemNm.equals("SYSNO1") || sItemNm.equals("SYSNO2") || sItemNm.equals("RETRY") || sItemNm.equals("TDCD") || 
				sItemNm.equals("MCODE") ) {
			iResult = 1;
		}
		else if (sItemNm.equals("2Y") || sItemNm.equals("YY") || sItemNm.equals("MM") || sItemNm.equals("DD") || sItemNm.equals("SEQNO2") || 
				sItemNm.equals("HH") || sItemNm.equals("MI") || sItemNm.equals("SS") || sItemNm.equals("EDCD") || 
				sItemNm.equals("SYSNO") || sItemNm.equals("AREA") || sItemNm.equals("GRPNO") || sItemNm.equals("RETRYTOINT") ) {
			iResult = 2;
		}
		else if (sItemNm.equals("SEQNO3") || sItemNm.equals("SERVICE") || sItemNm.equals("YMM")) {
			iResult = 3;
		}
		else if (sItemNm.equals("MMDD") || sItemNm.equals("YYYY") || sItemNm.equals("YYMM") || sItemNm.equals("OFFICE") || sItemNm.equals("SEQNO")) {
			iResult = 4;
		}
		else if (sItemNm.equals("YMMDD")) {
			iResult = 5;
		}
		else if (sItemNm.equals("YYMMDD") || sItemNm.equals("YYYYMM") || sItemNm.equals("HHMISS") || sItemNm.equals("XXYYMM-1")) {
			iResult = 6;
		}
		else if (sItemNm.equals("YYYYMMDD")) {
			iResult = 8;
		}
		else if (sItemNm.equals("YYYYMMDDHHMISS")) {
			iResult = 14;
		}
		
		return iResult;
	}
}

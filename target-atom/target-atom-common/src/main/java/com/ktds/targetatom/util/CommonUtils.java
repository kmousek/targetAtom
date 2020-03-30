package com.ktds.targetatom.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
	
	final static int MAX_KTF_CTN_LENGTH 	   = 12; 

	public static boolean isValidDate(String dateToValidate, String dateFormat) {
		if (dateToValidate == null) {
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		
		try {
			Date date = sdf.parse(dateToValidate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public static String wlssEditTelNo (String sTelNo, int iLen) {
	    int iLength;
	    // 널체크   
	    if (sTelNo == null || sTelNo == "") return "";

	    // 지정길이(iLen)가 11인 경우:(pprfc_format_calling, pprfc_format_callingN)

	    if (iLen == MAX_KTF_CTN_LENGTH - 1) {
	        // 숫자형문자열이 아닌부분이 있으면 그 이전까지만
	        String tempStr = sTelNo + "a";
	    	iLength = tempStr.indexOf("[^0-9]"); 
	        sTelNo = sTelNo.substring(0, iLength);

	        // 입력 전화번호가 12자리이면 두번째 자리부터 끝자리까지 리턴.
	        if (iLength == MAX_KTF_CTN_LENGTH) 
	        	return sTelNo.substring(1, iLength);
	        // 입력 전화번호가 11자리이면

	        else if (iLength == MAX_KTF_CTN_LENGTH - 1) {
	            String sTelNoStart = sTelNo.substring(0, 3);

	            // 앞 세자리가 "010","011", "016", "017", "018", "019"이면 첫자리부터 끝자리까지 리턴.
	            // 012 추가 : 2016.03.08

	            if (sTelNoStart == "010" || sTelNoStart == "011" ||  sTelNoStart == "012" ||
	                sTelNoStart == "016" || sTelNoStart == "017" ||
	                sTelNoStart == "018" || sTelNoStart == "019") {
	                return sTelNo;
	            }

	            // 아니면 다섯번째자리에 "0"을 삽입한후 두번째자리부터 끝자리까지 리턴.
	            else {
	                return sTelNo.substring(1, 4) + "0" + sTelNo.substring(4, iLength);
	            }
	        }

	        // 입력 전화번호가 10자리이면 네번째자리에 "0"을 삽입한후 전체를 리턴.
	        else if (iLength == 10) {
	            return sTelNo.substring(0, 3) + "0" + sTelNo.substring(3, iLength);
	        }
	        else return "";
	    }

	    // 지정길이(iLen)가 10인 경우:( pprfc_format_call_webi)
	    else if (iLen == MAX_KTF_CTN_LENGTH - 2) {
	    	String tempStr = sTelNo + "a";
	        // 숫자형문자열이 아닌부분이 있으면 그 이전까지만
	        iLength = tempStr.indexOf("[^0-9]");
	        sTelNo = sTelNo.substring(0, iLength);
	        // 입력 전화번호가 12자리이고 첫자리가 "0"이면
	        if (iLength == MAX_KTF_CTN_LENGTH && sTelNo.startsWith("0")) {
	            // 두번째 자리부터 세자리 저장
	            String sTelNoTwoWith = sTelNo.substring(1, 4);
	            // 두번째 자리부터 세자리가 "060"이면
	            if (sTelNoTwoWith == "060") {
	                // 다섯번째 자리가 "0"이면 다섯번째 자리의 "0"을 제거하고 두번째 자리부터 끝자리까지 리턴.(총10자리)
	                if (sTelNo.substring(4, 5) == "0") {
	                    return sTelNo.substring(1, 4) + sTelNo.substring(5, iLength);
	                }
	                // 아니면 두번째자리부터 끝자리까지 리턴.(총11자리)
	                else return sTelNo.substring(1, iLength);
	            }
	            // 두번째 자리부터 세자리가 "010","011", "016", "017", "018", "019"이면 두번째 자리부터 끝자리까지 리턴.(총 11자리)
	            // 012 추가 : 2016.03.08
	            if (sTelNoTwoWith == "010" || sTelNoTwoWith == "011" ||  sTelNoTwoWith == "012" ||
	                sTelNoTwoWith == "016" || sTelNoTwoWith == "017" ||
	                sTelNoTwoWith == "018" || sTelNoTwoWith == "019") {
	                return sTelNo.substring(1, iLength);
	            }
	            // 아니면 12자리 전체를 리턴.
	            else {
	                return sTelNo;
	            }
	        }
	        // 입력 전화번호가 12자리이고 첫자리가 "0"이 아니면 전체를 리턴
	        else if (iLength == 12) return sTelNo;
	        else return "";
	    }
	    else return "";

	}
	
	public static String comnPadChr(String inputString, int length, char padding, String type) {
		if (inputString.length() >= length) {
			return inputString; 
		}
		
		StringBuilder sb = new StringBuilder();
		if (type.equals("L")) {
			while (sb.length() < length - inputString.length()) {
				sb.append(padding);
			}
			sb.append(inputString);
		} else {
			sb.append(inputString);
			while (sb.length() < length) {
				sb.append(padding);
			}
		}
		return sb.toString();
	}
	
	
	public static int strREIndexOf(String inputString) {
		 
        Pattern p = Pattern.compile("([^0-9])");
   		Matcher matcher = p.matcher(inputString);
   		 
   		if (matcher.find()) {
   			
   			return matcher.start();
   		  
   		} else {
   			
   			return -1;
   		}
     } 
	
	 public static String comnSubstring(String sTgtString, int iStart, int iEnd) {
		 
		 if (sTgtString == null || sTgtString == "" || iStart < 0 || iEnd < 0 || sTgtString.length() < iStart || iStart >= iEnd) {
		 
			 return "";
			 
		 }
		 
		 else if ( sTgtString.length() < iEnd ) {		 
		 
			 iEnd=sTgtString.length();
			
		 }
		 
		 return sTgtString.substring(iStart, iEnd);
		 
	 }
	 
	public static boolean findCondition(String effectDate, String expireDate, String workDatetime) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date effect = dateFormat.parse(effectDate);
			Date expire = dateFormat.parse(expireDate);
			Date work = dateFormat.parse(workDatetime);
			
			if (effect.before(work) && expire.after(work) || (effect.equals(work) || (expire.equals(work)))) {
				return true;
			} else {
				return false;
			}
		} catch(ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void currentTime(String comment) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date time = new Date();
		
		String time1 = sdf.format(time);
		System.out.println(comment + " Time ::: " + time1);
	}
	
	/*
	 * Purpose:  국제사업자식별번호 반환
	 * Assumes:  Nothing
	 * Comments: 국제전화번호로 국제 사업자 식별번호 Return.
	 *           Input으로 국제 전화번호 코드(prefixCode)를 입력 받아 사업자자 식별 코드(carrierCode)를 반환한다. 
	 *           prefix코드가 "00"으로 시작하지 않으면 ""을 리턴.
	 *           prefix코드가 3자리일 경우 3자리 그대로 Return하고
	 *           prefix코드가 5자리일 경우 3번째 자리부터 3자리를 잘라서 Return한다.
	 *           예) intl_prefix : 001 => 001, 00345 => 345 
	 *           사업자식별번호(Prefix코드);
	 */
	public static String wlssExtrtCarrierCd (String sPrefixCd) {

	    if (sPrefixCd == null || sPrefixCd.equals("")) 
	    	return "";

	    //prefix코드가 "00"으로 시작하지 않으면 ""을 리턴.
	    if (!(sPrefixCd.startsWith("00"))) 
	    	return "";

	    // 다섯자리까지 숫자로 된 부분만 추출
	    sPrefixCd = sPrefixCd.substring(0, 5).toString();
	    
	    int iLength = sPrefixCd.lastIndexOf(sPrefixCd);
	    
	    // prefix코드가 3자리일 경우 3자리 그대로 Return하고
	    if (iLength == 3) {
	    	return sPrefixCd; 
	    // prefix코드가 5자리일 경우 3번째 자리부터 3자리를 잘라서 Return한다.
	    } else if (iLength >= 5) {
	    	return sPrefixCd.substring(2, 5);
	    } 
	    else 
	    	return "";
	} 
	
}

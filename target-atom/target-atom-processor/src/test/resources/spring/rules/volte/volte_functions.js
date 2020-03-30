Matcher = java.util.regex.Matcher;
Pattern = java.util.regex.Pattern;
StringBuilder = java.lang.StringBuilder;
CollectionUtils = org.apache.commons.collections.CollectionUtils;

function comnPadChr(inputString, padding, length, type) {
	if (inputString.length() >= length) {
		return inputString;
	}
	
	var sb = new StringBuilder();
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

function comnStrTrim(inputString) {
	inputString.trim();
}


function strREIndexOf(inputString) {		 
    var p = Pattern.compile("([^0-9])");
	var matcher = p.matcher(inputString);
	 
	if (matcher.find()) {   			
		return matcher.start();   		  
	} else {   			
		return -1;
	}
} 

function comnSubstring(sTgtString, iStart, iEnd) {
	 
	 if (sTgtString == null || sTgtString == "" || iStart < 0 || iEnd < 0 || sTgtString.length() < iStart || iStart >= iEnd) {	 
		 return "";		 
	 } else if ( sTgtString.length() < iEnd ) {		  
		 iEnd = sTgtString.length();
	 }
	 
	 return sTgtString.substring(iStart, iEnd);		 
}

function recordTrim(ex, className) {
	var bodyList = ex.getIn().getBody(ArrayList.class);
	var resultList = new ArrayList();
	var body;
	
	if(!CollectionUtils.isEmpty(bodyList)){
		for (var i = 0; i < bodyList.size(); i++) {
			body = bodyList[i];
			if (body.getClass().getSimpleName().equals(className))
				resultList.add(body);
		}
	}
	ex.getIn().setBody(resultList);
}

function isValidDate(sDateTime, dateFormat) {
	
	if (dateFormat == "yyyyMMddHHmmss") {
		var datetime_pattern = /^(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$/;
		
		if (datetime_pattern.test(sDateTime)) return true;
		else return false;
	}
	else {
		return false;
	}
	
	return false;
}

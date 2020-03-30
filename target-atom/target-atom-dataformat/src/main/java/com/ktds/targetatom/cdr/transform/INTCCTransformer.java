package com.ktds.targetatom.cdr.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.targetatom.cdr.common.TransformRecord;

public class INTCCTransformer implements TransformRecord{ 
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	// Adding carriage return
	
	public String setRecordStructure(String message) {
		return transformFixedLengthWithoutcrlf(message, 120, "UNIX");
	}

	@Override
	public String transformFixedLengthWithoutcrlf(String message, int recordLength, String crlf) {
		String tmpChar = "", tmpCrlf = "";
		int i;
		StringBuffer buff = new StringBuffer();
		
		if (crlf.equals("UNIX")) {
			tmpCrlf = UNIX_CRLF;
		} else {
			tmpCrlf = WINDOWS_CRLF;
		}
		
		for (i=0;i<message.length();i=i+recordLength) {
			tmpChar = message.substring(i, i+recordLength) + tmpCrlf;
			buff.append(tmpChar);
		}
		
		return buff.toString();
	}
}

package com.ktds.targetatom.cdr.common;

public interface TransformRecord {
	static final String UNIX_CRLF = "\r\n";
	static final String WINDOWS_CRLF = "\n";

	public String transformFixedLengthWithoutcrlf(String message, int recordLength, String crlf);
	
	
}

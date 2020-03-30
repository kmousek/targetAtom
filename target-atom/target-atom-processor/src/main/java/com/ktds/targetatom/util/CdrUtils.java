package com.ktds.targetatom.util;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CdrUtils {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final String UNIX_CRLF = "\r\n";
	private static final String WINDOWS_CRLF = "\n";
	
	/*
	 * 	Add carriage return and line feed, set header and trailer 
	 */
	public String addNewLine(Exchange exchange, String message) {
		
		ObjectHelper.notNull(exchange.getIn().getHeader("cdrHeaderLength", Integer.class), "cdr");
		ObjectHelper.notNull(exchange.getIn().getHeader("cdrRecordLength", Integer.class), "cdr");
		
		int cdrHeaderLength = exchange.getIn().getHeader("cdrHeaderLength", Integer.class);
		int cdrRecordLength = exchange.getIn().getHeader("cdrRecordLength", Integer.class);
		Boolean skipCdrHeader = exchange.getIn().getHeader("skipCdrHeader", Boolean.class);
		String cdr = exchange.getIn().getBody(String.class);
		if(log.isTraceEnabled()) {
			log.trace("cdrHeaderLength={}", cdrHeaderLength);
			log.trace("cdrRecordLength={}", cdrRecordLength);			
			log.trace("cdr={}", cdr);
		}

		
		ArrayList<String> recordList = new ArrayList<String>();
		int i = 0;
		String record = "";
		if(cdrHeaderLength > 0) {
			record = "HEADER" + cdr.substring(i, i += cdrHeaderLength) + UNIX_CRLF;
			if(!skipCdrHeader) {
				recordList.add(record);
			}
		}
		while(i < cdr.length()) {
			record = cdr.substring(i, i += cdrHeaderLength) + UNIX_CRLF;
			recordList.add(record);
		}
		recordList.remove(recordList.size() - 1);
		// camel-flatpack에서 아직 Trailer 지원을 하지 않으므로 삭제
//		record = "TRAILER" + record;
//		recordList.add(record);
		
		String result = "";
		for(i = 0; i < recordList.size(); i++) {
			result += recordList.get(i);
		}
		if(log.isTraceEnabled()) {
			log.trace("result={}", result);
		}
		return result;
	}

	/*
	 * Remove carriage return and line feed
	 */
	public String removeNewLine(String message) {
		return message.replace("\n", "").replace("\r", "");
	}

	/*
	 * Get extractValiationOutput
	 */
	/*public List<Map<String, Object>> extractOutput(ValidationResult message) {
		return message.getOutputList();
	}

	/*
	 * Get extractValiationError
	public List<Map<String, Object>> extractError(ValidationResult message) {
		return message.getErrorList();
	}*/
	
}

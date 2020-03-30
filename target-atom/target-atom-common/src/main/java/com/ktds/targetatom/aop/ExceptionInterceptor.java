package com.ktds.targetatom.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionInterceptor {
		Logger log = LoggerFactory.getLogger(this.getClass());
		private Exception e;
		
		public void logException(Exception e)  {
			log.warn("Exception occurred {}", e);
			this.e = e;
		}

		public Exception getE() {
			return e;
		}
}

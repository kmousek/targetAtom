package com.ktds.targetatom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownConfig {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Bean
	public TerminateBean getTerminateBean() {
		return new TerminateBean();
	}
}

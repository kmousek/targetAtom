package com.ktds.targetatom.service;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminateBean {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@PreDestroy
	public void onDestroy() throws Exception {
		log.warn("Spring Processor Container is destoryed!!!!");
	}
	
	@PreDestroy
	public void onShutDown() throws Exception {
		log.warn("Spring Processor Container is shutdown!!!!");
	}

}
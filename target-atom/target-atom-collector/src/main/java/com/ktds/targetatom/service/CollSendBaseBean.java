package com.ktds.targetatom.service;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.vo.CdrBaseInfo;

@Repository
public class CollSendBaseBean {
	
	private static List<CdrBaseInfo> sendBaseList = null;

	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ReferenceInfoDao dao;
	
	@Value("${localcollinfo.instanceName}")
	private String instanceId;
	
	public List<CdrBaseInfo> getSendBaseList() {
		log.debug("instanceId={}", instanceId);
		if(sendBaseList == null) {
			sendBaseList = dao.getCdrBaseList(instanceId);
		}
		return sendBaseList;
	}
	
}

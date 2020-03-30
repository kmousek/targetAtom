package com.ktds.targetatom.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.dao.ReferenceInfoSQL;
import com.ktds.targetatom.vo.CollectStrategyInfo;

@Service
public class ReferenceInfoService {
	@Autowired
	ReferenceInfoDao referenceInfoDao;
	
	List<CollectStrategyInfo> collectStrategyList = null;
	
	public List<CollectStrategyInfo> getCollectStrategyList(String wflowInstId) {
		if(collectStrategyList == null) {
			collectStrategyList = referenceInfoDao.getCollectStrategyList(wflowInstId);
		}
		return collectStrategyList;
	}	
}

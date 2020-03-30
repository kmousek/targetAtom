package com.ktds.targetatom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.vo.CdrProfReferenceInfo;

@Service
public class ProfReferenceInfoService {
	@Autowired
	ReferenceInfoDao referenceInfoDao;
	
	List<CdrProfReferenceInfo> cdrProfReferenceList = null;
	
	public List<CdrProfReferenceInfo> getCdrProfReferenceInfo() {
		if(cdrProfReferenceList == null) {
			cdrProfReferenceList = referenceInfoDao.getCdrProfReferenceInfo();
		}
		return cdrProfReferenceList;
	}	
}
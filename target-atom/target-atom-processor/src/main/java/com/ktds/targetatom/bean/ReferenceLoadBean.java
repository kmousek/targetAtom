package com.ktds.targetatom.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.util.CommonUtils;
import com.ktds.targetatom.vo.TbCalNoBasInfo;
import com.ktds.targetatom.vo.TbCalNoBasInfoKey;
import com.ktds.targetatom.vo.TbErrMappgBasInfo;
import com.ktds.targetatom.vo.TbErrMappgBasInfoKey;
import com.ktds.targetatom.vo.TbFeatrBasInfo;
import com.ktds.targetatom.vo.TbFeatrBasInfoKey;
import com.ktds.targetatom.vo.TbNoMovBizrBasInfo;
import com.ktds.targetatom.vo.TbPfixRgnBasInfo;
import com.ktds.targetatom.vo.TbProfBasInfo;
import com.ktds.targetatom.vo.TbProfBasInfoKey;
import com.ktds.targetatom.vo.WlessSpclNoBasInfo;
import com.ktds.targetatom.vo.WlessSpclNoBasInfoKey;

@Repository
public class ReferenceLoadBean {
	private MultiValuedMap<WlessSpclNoBasInfoKey, WlessSpclNoBasInfo> wlessSpclNoBasInfoListMap;
	private MultiValuedMap<String, TbPfixRgnBasInfo> tbPfixRgnBasInfoListMap;
	private MultiValuedMap<TbProfBasInfoKey, TbProfBasInfo> tbProfBasInfoListMap;
	private MultiValuedMap<TbCalNoBasInfoKey, TbCalNoBasInfo> tbCalNoBasInfoListMap;
	private MultiValuedMap<TbFeatrBasInfoKey, TbFeatrBasInfo> tbFeatrBasInfoListMap;
	private MultiValuedMap<String, TbNoMovBizrBasInfo> tbNoMovBizrBasInfoListMap;
	private MultiValuedMap<String, TbErrMappgBasInfo> tbErrMappgBasInfoListMap;
	private ArrayList<TbProfBasInfo> imsicheckList;
	
	private static List<WlessSpclNoBasInfo> wlessSpclNoBasInfoList = null;
	private static List<TbPfixRgnBasInfo> tbPfixRgnBasInfoList = null;
	private static List<TbProfBasInfo> tbProfBasInfoList = null;
	private static List<TbCalNoBasInfo> tbCalNoBasInfoList = null;
	private static List<TbFeatrBasInfo> tbFeatrBasInfoList = null;
	private static List<TbNoMovBizrBasInfo> tbNoMovBizrBasInfoList = null;
	private static List<TbErrMappgBasInfo> tbErrMappgBasInfoList = null;
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ReferenceInfoDao dao;
	
	public void loadReferenceData() {
		if(wlessSpclNoBasInfoListMap == null || tbPfixRgnBasInfoListMap == null || tbProfBasInfoListMap == null || tbCalNoBasInfoListMap == null ||
				tbFeatrBasInfoListMap == null || tbNoMovBizrBasInfoListMap == null || tbErrMappgBasInfoListMap == null) {
			log.info("Loading Reference Data.....");
			log.info("Loading TB_WLESS_SPCL_NO_BAS table....");
			wlessSpclNoBasInfoList = dao.getLoadWlessSpclNoBasInfo();
			wlessSpclNoBasInfoListMap = importWlessSpclNoBasInfo(wlessSpclNoBasInfoList);
			log.info("Loaded TB_WLESS_SPCL_NO_BAS table ::: {}", wlessSpclNoBasInfoListMap.size());
			
			log.info("Loading TB_PFIX_RGN_BAS table....");
			tbPfixRgnBasInfoList = dao.getLoadTbPfixRgnBasInfo();
			tbPfixRgnBasInfoListMap = importTbPfixRgnBasInfo(tbPfixRgnBasInfoList);
			log.info("Loaded TB_PFIX_RGN_BAS table ::: {}", tbPfixRgnBasInfoListMap.size());
			
			log.info("Loading TB_PROF_BAS table....");
			tbProfBasInfoList = dao.getLoadTbProfBasInfo();
			tbProfBasInfoListMap = importTbProfBasInfo(tbProfBasInfoList);
			log.info("Loaded TB_PROF_BAS table ::: {}", tbProfBasInfoListMap.size());
			
			log.info("Loading TB_CAL_NO_BAS table....");
			tbCalNoBasInfoList = dao.getLoadTbCalNoBasInfo();
			tbCalNoBasInfoListMap = importTbCalNoBasInfo(tbCalNoBasInfoList);
			log.info("Loaded TB_CAL_NO_BAS table ::: {}", tbCalNoBasInfoListMap.size());
			
			log.info("Loading TB_FEATR_BAS table....");
			tbFeatrBasInfoList = dao.getLoadTbFeatrBasInfo();
			tbFeatrBasInfoListMap = importTbFeatrBasInfo(tbFeatrBasInfoList);
			log.info("Loaded TB_FEATR_BAS table ::: {}", tbFeatrBasInfoListMap.size());
			
			log.info("Loading TB_NO_MOV_BIZR_BAS table....");
			tbNoMovBizrBasInfoList = dao.getLoadTbNoMovBizrBasInfo();
			tbNoMovBizrBasInfoListMap = importTbNoMovBizrBasInfo(tbNoMovBizrBasInfoList);
			log.info("Loaded TB_NO_MOV_BIZR_BAS table ::: {}", tbNoMovBizrBasInfoListMap.size());
			
			log.info("Loading TB_ERR_MAPPG_BAS table...");
			tbErrMappgBasInfoList = dao.getLoadTbErrMappgBasInfoList();
			tbErrMappgBasInfoListMap = importTbErrMappgBasInfo(tbErrMappgBasInfoList);
			log.info("Loaded TB_ERR_MAPPG_BAS table ::: {}", tbErrMappgBasInfoListMap.size());
		}
	}
	
	private MultiValuedMap<String, TbErrMappgBasInfo> importTbErrMappgBasInfo(
			List<TbErrMappgBasInfo> tbErrMappgBasInfoList) {
		MultiValuedMap<String, TbErrMappgBasInfo> tbErrMappgBasInfoListMap = new ArrayListValuedHashMap<String, TbErrMappgBasInfo>();
		for (TbErrMappgBasInfo record : tbErrMappgBasInfoList) {
			tbErrMappgBasInfoListMap.put(record.getsErrorDescription(), record);
		}
		return tbErrMappgBasInfoListMap;
	}

	private MultiValuedMap<String, TbNoMovBizrBasInfo> importTbNoMovBizrBasInfo(
			List<TbNoMovBizrBasInfo> tbNoMovBizrBasInfoList) {
		MultiValuedMap<String, TbNoMovBizrBasInfo> tbNoMovBizrBasInfoListMap = new ArrayListValuedHashMap<String, TbNoMovBizrBasInfo>();
		for (TbNoMovBizrBasInfo record : tbNoMovBizrBasInfoList) {
			tbNoMovBizrBasInfoListMap.put(record.getsNpCd(), record);
		}
		return tbNoMovBizrBasInfoListMap;
	}
	
	private MultiValuedMap<TbFeatrBasInfoKey, TbFeatrBasInfo> importTbFeatrBasInfo(
			List<TbFeatrBasInfo> tbFeatrBasInfoList) {
		MultiValuedMap<TbFeatrBasInfoKey, TbFeatrBasInfo> tbFeatrBasInfoListMap = new ArrayListValuedHashMap<TbFeatrBasInfoKey, TbFeatrBasInfo>();
		for (TbFeatrBasInfo record : tbFeatrBasInfoList) {
			TbFeatrBasInfoKey tbFeatrBasInfoKey = new TbFeatrBasInfoKey(record.getsFeatureFormatId(), record.getsFormatType(), record.getsFeatureNo());
			tbFeatrBasInfoListMap.put(tbFeatrBasInfoKey, record);
		}
		return tbFeatrBasInfoListMap;
	}
	
	private MultiValuedMap<TbCalNoBasInfoKey, TbCalNoBasInfo> importTbCalNoBasInfo(
			List<TbCalNoBasInfo> tbCalNoBasInfoList) {
		MultiValuedMap<TbCalNoBasInfoKey, TbCalNoBasInfo> tbCalNoBasInfoListMap = new ArrayListValuedHashMap<TbCalNoBasInfoKey, TbCalNoBasInfo>();
		for (TbCalNoBasInfo record : tbCalNoBasInfoList) {
			TbCalNoBasInfoKey tbCalNoBasInfoKey = new TbCalNoBasInfoKey(record.getsPrefix(), record.getsNxxNo(), record.getsLineNo());
			tbCalNoBasInfoListMap.put(tbCalNoBasInfoKey, record);
		}
		return tbCalNoBasInfoListMap;
	}
	
	private MultiValuedMap<TbProfBasInfoKey, TbProfBasInfo> importTbProfBasInfo(
			List<TbProfBasInfo> tbProfBasInfoList) {
		ArrayList<String> temp_check = new ArrayList<String>();
		ArrayList<TbProfBasInfo> imsi_check = new ArrayList<TbProfBasInfo>();
		temp_check.add("JASPER_CDR_KEEP_V");
		temp_check.add("BSSIOT_CDR_KEEP");
		temp_check.add("GTOUCH_CDR_KEEP");
		
		
		MultiValuedMap<TbProfBasInfoKey, TbProfBasInfo> tbProfBasInfoListMap = new ArrayListValuedHashMap<TbProfBasInfoKey, TbProfBasInfo>();
		for (TbProfBasInfo record : tbProfBasInfoList) {
			TbProfBasInfoKey tbProfBasInfoKey = new TbProfBasInfoKey(record.getsItemId(), record.getsItemIndi());
			tbProfBasInfoListMap.put(tbProfBasInfoKey, record);
			if (temp_check.contains(record.getsItemId())) {
				imsi_check.add(record);
			}
		}
		imsicheckList = imsi_check;
		return tbProfBasInfoListMap;
	}

	private MultiValuedMap<String, TbPfixRgnBasInfo> importTbPfixRgnBasInfo(
			List<TbPfixRgnBasInfo> tbPfixRgnBasInfoList) {
		MultiValuedMap<String, TbPfixRgnBasInfo> tbPfixRgnBasInfoListMap = new ArrayListValuedHashMap<String, TbPfixRgnBasInfo>();
		for (TbPfixRgnBasInfo record : tbPfixRgnBasInfoList) {
			tbPfixRgnBasInfoListMap.put(record.getsPfixIdfyNo(), record);
		}
		return tbPfixRgnBasInfoListMap;
	}	
	
	private MultiValuedMap<WlessSpclNoBasInfoKey, WlessSpclNoBasInfo> importWlessSpclNoBasInfo(
			List<WlessSpclNoBasInfo> wlessSpclNoBasInfoList) {
		MultiValuedMap<WlessSpclNoBasInfoKey, WlessSpclNoBasInfo> wlessSpclNoBasInfoListMap = new ArrayListValuedHashMap<WlessSpclNoBasInfoKey, WlessSpclNoBasInfo>();
		for (WlessSpclNoBasInfo record : wlessSpclNoBasInfoList) {
			WlessSpclNoBasInfoKey wlessSpclNoBasInfoKey = new WlessSpclNoBasInfoKey(record.getsIdfyTelNo(), record.getsPfxTelNo(), record.getsHhoStNo(), record.getsWlessMktDivCd());
			wlessSpclNoBasInfoListMap.put(wlessSpclNoBasInfoKey, record);
		}
		return wlessSpclNoBasInfoListMap;
	}
	
	public WlessSpclNoBasInfo getWlessSpclNoBasInfo(String sNpaNo, String chgsNxxNo, String chgsLineNo, String sMarketCd, String sCallStartDatetime) {
		WlessSpclNoBasInfoKey wlessSpclNoBasInfoKey = new WlessSpclNoBasInfoKey(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd);
		Collection<WlessSpclNoBasInfo> wlessSpclNoBasInfo = wlessSpclNoBasInfoListMap.get(wlessSpclNoBasInfoKey);
		
		for (WlessSpclNoBasInfo record : wlessSpclNoBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sCallStartDatetime)) {
				return record;
			}
		}
		return null;
	}
	
	public TbPfixRgnBasInfo getPfixRgnBasInfo(String sPrefix, String sCallStartDatetime) {
		
		Collection<TbPfixRgnBasInfo> tbPfixRgnBasInfo = tbPfixRgnBasInfoListMap.get(sPrefix);
		
		for (TbPfixRgnBasInfo record : tbPfixRgnBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sCallStartDatetime)) {
				return record;
			}
		}
		return null;		
	}
	
	public TbProfBasInfo getTbProfBasInfo(String sItemId, String sItemDivCd, String sWorkCallStartDatetime) {
		
		TbProfBasInfoKey tbProfBasInfoKey = new TbProfBasInfoKey(sItemId, sItemDivCd);		
		
		Collection<TbProfBasInfo> tbProfBasInfo = tbProfBasInfoListMap.get(tbProfBasInfoKey);
		
		for (TbProfBasInfo record : tbProfBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sWorkCallStartDatetime)) {
				return record;
			}
		}
		
		return null;		
	}
	
	public TbCalNoBasInfo getTbCalNoBasInfo(String sPrefix, String sNxxNo, String sLineNo, String sWorkCallStartDatetime) {
		TbCalNoBasInfoKey tbCalNoBasInfoKey = new TbCalNoBasInfoKey(sPrefix, sNxxNo, sLineNo);
		Collection<TbCalNoBasInfo> tbCalNoBasInfo = tbCalNoBasInfoListMap.get(tbCalNoBasInfoKey);
		
		for (TbCalNoBasInfo record : tbCalNoBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sWorkCallStartDatetime)) {
				return record;
			}
		}
		
		return null;		
	}
	
	public TbFeatrBasInfo getTbFeatrBasInfo(String sFeatureFormat, String sFeatureType, String sServiceType, String sWorkCallStartDatetime) {
		TbFeatrBasInfoKey tbFeatrBasInfoKey = new TbFeatrBasInfoKey(sFeatureFormat, sFeatureType, sServiceType);
		Collection<TbFeatrBasInfo> tbFeatrBasInfo = tbFeatrBasInfoListMap.get(tbFeatrBasInfoKey);
		
		for (TbFeatrBasInfo record : tbFeatrBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sWorkCallStartDatetime)) {
				return record;
			}
		}
		
		return null;		
	}
	
	public TbNoMovBizrBasInfo getTbNoMovBizrBasinfo(String sNpCd, String sCallStartDatetime) {
		Collection<TbNoMovBizrBasInfo> tbNoMovBizrBasInfo = tbNoMovBizrBasInfoListMap.get(sNpCd);
		
		for (TbNoMovBizrBasInfo record : tbNoMovBizrBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sCallStartDatetime)) {
				return record;
			}
		}
		
		return null;		
	}
	
	public TbErrMappgBasInfo getTbErrMappgBasInfo(String errorDescription, String sCallStartDatetime) {
		
		Collection<TbErrMappgBasInfo> tbErrMappgBasInfo = tbErrMappgBasInfoListMap.get(errorDescription);
		
		for (TbErrMappgBasInfo record : tbErrMappgBasInfo) {
			if (CommonUtils.findCondition(record.getsEffectDate(), record.getsExpireDate(), sCallStartDatetime)) {
				return record;
			}
		}
		
		return null;		
	}
	
	public ArrayList<TbProfBasInfo> getCdrProfReferenceInfo() {
		if (imsicheckList == null) {
			return null;
		}
		
		return imsicheckList;
	}
	
	public ArrayList listAdd(ArrayList<TbErrMappgBasInfoKey> ltErrorCd, String errorDescription) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
		Date time = new Date();
		String currentTime = format1.format(time);
		
		TbErrMappgBasInfo tbErrMappgBasInfo = getTbErrMappgBasInfo(errorDescription, currentTime);
		if (tbErrMappgBasInfo != null) {
			TbErrMappgBasInfoKey uErrCdOut = new TbErrMappgBasInfoKey(tbErrMappgBasInfo.getsCdrErrId(), tbErrMappgBasInfo.getsMzErrLevelDivCd(), tbErrMappgBasInfo.getiMzErrPrirtNo(), tbErrMappgBasInfo.getsOldMzErrId());  		
	    		
	        ltErrorCd.add(uErrCdOut);
	    }
		
		return ltErrorCd;
	}
}

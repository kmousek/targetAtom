package com.ktds.targetatom.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.targetatom.vo.*;

@Repository
public class ReferenceInfoDao {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private NamedParameterJdbcOperations db;
	private RowMapper<CamelRouteFileInfo> camelRouteInfoMapper = BeanPropertyRowMapper.newInstance(CamelRouteFileInfo.class);
	private RowMapper<CdrReferenceInfo> cdrReferenceInfoMapper = BeanPropertyRowMapper.newInstance(CdrReferenceInfo.class);
	private RowMapper<CollectStrategyInfo> collectStrategyInfoMapper = BeanPropertyRowMapper.newInstance(CollectStrategyInfo.class);
	private RowMapper<CdrBaseInfo> cdrBaseInfoMapper = BeanPropertyRowMapper.newInstance(CdrBaseInfo.class);
	private RowMapper<CdrProfReferenceInfo> CdrProfReferenceInfoMapper = BeanPropertyRowMapper.newInstance(CdrProfReferenceInfo.class);
	private RowMapper<TbPfixRgnBasInfo> tbPfixRgnBasInfoMapper = BeanPropertyRowMapper.newInstance(TbPfixRgnBasInfo.class);
	private RowMapper<WlessSpclNoBasInfo> wlessSpclNoBasInfoMapper = BeanPropertyRowMapper.newInstance(WlessSpclNoBasInfo.class);
	private RowMapper<IntlPfixBasInfo> intlPfixBaseInfoMapper = BeanPropertyRowMapper.newInstance(IntlPfixBasInfo.class);
	private RowMapper<IntlPfixChangeBasInfo> intlPfixChangeBaseInfoMapper = BeanPropertyRowMapper.newInstance(IntlPfixChangeBasInfo.class);
	private RowMapper<TbProfBasInfo> tbProfBasInfoMapper = BeanPropertyRowMapper.newInstance(TbProfBasInfo.class);
	private RowMapper<TbCalNoBasInfo> tbCalNoBasInfoMapper = BeanPropertyRowMapper.newInstance(TbCalNoBasInfo.class);
	private RowMapper<TbFeatrBasInfo> tbFeatrBasInfoMapper = BeanPropertyRowMapper.newInstance(TbFeatrBasInfo.class);
	private RowMapper<TbSvcPrvrBasInfo> tbSvcPrvrBasInfoMapper = BeanPropertyRowMapper.newInstance(TbSvcPrvrBasInfo.class);
	private RowMapper<TbNoMovBizrBasInfo> tbNoMovBizrBasInfoMapper = BeanPropertyRowMapper.newInstance(TbNoMovBizrBasInfo.class);
	private RowMapper<TbIntlPfixChangeBasInfo> tbIntlPfixChangeBasInfoMapper = BeanPropertyRowMapper.newInstance(TbIntlPfixChangeBasInfo.class);
	private RowMapper<TbErrMappgBasInfo> tbErrMappgBasInfoMapper = BeanPropertyRowMapper.newInstance(TbErrMappgBasInfo.class);
	
	@Autowired
	public ReferenceInfoDao(DataSource dataSource) {
		this.db = new NamedParameterJdbcTemplate(dataSource);
	}

//	@Value("${sql.fetchRoute}") 
//	private String fetchRoute;
	
	//@Transactional(readOnly=true)
	public List<CamelRouteFileInfo> getCamelRouteFileList(String format, String moduleName) {
//		log.debug("--> getCamelRouteFileList");
//		log.trace("   format={}", format);
		List<CamelRouteFileInfo> routeFileList= null;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("format", format);
			params.put("moduleName", moduleName);
			//Map<String, String> params = Collections.singletonMap("format", format);
			routeFileList = db.query(ReferenceInfoSQL.getCamelRouteFileList, params, camelRouteInfoMapper);
			return routeFileList;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", routeFileList);
		}
	}

	//@Transactional(readOnly=true)
	public List<CdrReferenceInfo> getCdrReferenceInfoList(String wflowInstId) {
//		log.debug("--> getCdrReferenceInfo");
//		log.trace("   wflowInstId={}", wflowInstId);
		List<CdrReferenceInfo> result= null;
		try {
			Map<String, String> params = Collections.singletonMap("wflowInstId", wflowInstId);
			result = db.query(ReferenceInfoSQL.getCdrReferenceInfo, params, cdrReferenceInfoMapper);
			return result;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", result);
		}
	}
	
	// Distributer
	//@Transactional(readOnly=true)
	public List<CdrReferenceInfo> getDistributerCdrReferenceInfoList(String wflowInstId) {
//		log.debug("--> getDistributerCdrReferenceInfo");
//		log.trace("   wflowInstId={}", wflowInstId);
		List<CdrReferenceInfo> result= null;
		try {
			Map<String, String> params = Collections.singletonMap("wflowInstId", wflowInstId);
			result = db.query(ReferenceInfoSQL.getDistributerCdrReferenceInfo, params, cdrReferenceInfoMapper);
			return result;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", result);
		}
	}

	//@Transactional(readOnly=true)
	public List<CollectStrategyInfo> getCollectStrategyList(String wflowInstId) {

		List<CollectStrategyInfo> result= null;
		
		try {
			Map<String, String> params = Collections.singletonMap("wflowInstId", wflowInstId);
			result = db.query(ReferenceInfoSQL.getCollectStrategyList, params, collectStrategyInfoMapper);
			return result;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", result);
		}
	}
	
	//@Transactional(readOnly=true)
	public List<CdrBaseInfo> getCdrBaseList(String wflowInstId) {
		log.debug("[knj]public List<CdrBaseInfo> getCdrBaseList(String wflowInstId) {");
		List<CdrBaseInfo> result= null;
		
		try {
			Map<String, String> params = Collections.singletonMap("wflowInstId", wflowInstId);
			log.debug("params={} ", params);
			result = db.query(ReferenceInfoSQL.getCdrBaseList, params, cdrBaseInfoMapper);
			return result;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", result);
		}
	}
	
	//@Transactional(readOnly=true)
	public List<CdrProfReferenceInfo> getCdrProfReferenceInfo() {

		List<CdrProfReferenceInfo> result= null;

		try {
			result = db.query(ReferenceInfoSQL.getCdrProfReferenceInfo, CdrProfReferenceInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} 
		finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", result);	
		}
		return result;
		
	}
	
	//@Transactional(readOnly=true)
	public TbPfixRgnBasInfo getPfixRgnBasInfo(String sPrefix, String sCallStartDatetime) {
	//public List<TbPfixRgnBasInfo> getPfixRgnBasInfo(String sPrefix, String sCallstartDatetime) {
//	log.debug("--> getPfixRgnReferenceInfo");
		//List<TbPfixRgnBasInfo> uTB_PFIX_RGN_BAS= null;
		TbPfixRgnBasInfo uTB_PFIX_RGN_BAS= null;
	
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("sPrefix", sPrefix);
			params.put("sCallStartDatetime", sCallStartDatetime);

			//uTB_PFIX_RGN_BAS = db.query(ReferenceInfoSQL.getTbPfixRgnBasInfo, params, tbPfixRgnBasInfoMapper);
			uTB_PFIX_RGN_BAS=db.queryForObject(ReferenceInfoSQL.getTbPfixRgnBasInfo, params, tbPfixRgnBasInfoMapper);
	
		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_PFIX_RGN_BAS");
			//		log.trace("   result={}", uTB_PFIX_RGN_BAS);
		}
		
		return uTB_PFIX_RGN_BAS;
	}
	
	//@Transactional(readOnly=true)
	public WlessSpclNoBasInfo getWlessSpclNoBasInfo(String sNpaNo, String sNxxNo, String sLineNo, String sMarketCd, String sCallStartDatetime) {
		//	log.debug("--> getWlessSpclNoBasInfo");
		
		WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS= null;
	
		try {

			Map<String, Object> params = new HashMap<>();
			params.put("sNpaNo", sNpaNo);
			params.put("sNxxNo", sNxxNo);
			params.put("sLineNo", sLineNo);
			params.put("sMarketCd", sMarketCd);
			params.put("sCallStartDatetime", sCallStartDatetime);
			
			uTB_WLESS_SPCL_NO_BAS=db.queryForObject(ReferenceInfoSQL.getWlessSpclNoBasInfo, params, wlessSpclNoBasInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_WLESS_SPCL_NO_BAS");
			//		log.trace("   result={}", uTB_WLESS_SPCL_NO_BAS);
		}
		return uTB_WLESS_SPCL_NO_BAS;
	}
	
	
	//@Transactional(readOnly=true)
	public IntlPfixBasInfo getIntlPrfixBasInfo(String sIntlPrefixCd, String sCommZoneCd) {
		//	log.debug("--> getIntlPrfixBasInfo");
		
		IntlPfixBasInfo uTB_INTL_PFIX_BAS= null;
	
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("sIntlPrefixCd", sIntlPrefixCd);
			params.put("sCommZoneCd", sCommZoneCd);
			
			uTB_INTL_PFIX_BAS=db.queryForObject(ReferenceInfoSQL.getIntlPrfixBasInfo, params, intlPfixBaseInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_INTL_PFIX_BAS");
			//		log.trace("   result={}", uTB_INTL_PFIX_BAS);
		}
		return uTB_INTL_PFIX_BAS;
	}
	
	//@Transactional(readOnly=true)
		public IntlPfixChangeBasInfo getIntlPrfixChangeBasInfo(String sCarrierCd, String sEdpCd, String sCallStartDatetime) {
			//	log.debug("--> getIntlPrfixChangeBasInfo");
			
			IntlPfixChangeBasInfo uTB_INTL_PFIX_CHANGE_BAS= null;
		
			try {
				
				Map<String, Object> params = new HashMap<>();
				params.put("sCarrierCd", sCarrierCd);
				params.put("sEdpCd", sEdpCd);
				params.put("sCallStartDatetime", sCallStartDatetime);
				
				uTB_INTL_PFIX_CHANGE_BAS=db.queryForObject(ReferenceInfoSQL.getIntlPrfixChangeBasInfo, params, intlPfixChangeBaseInfoMapper);

			} catch (Exception e) {
				//log.error("Exception occurred during select :", e);
				return null;
			} finally {
				//		log.debug("<-- uTB_INTL_PFIX_CHANGE_BAS");
				//		log.trace("   result={}", uTB_INTL_PFIX_CHANGE_BAS);
			}
			return uTB_INTL_PFIX_CHANGE_BAS;
		}
	
		
		
		//@Transactional(readOnly=true)
		public TbIntlPfixChangeBasInfo getTbIntlPfixChangeBasInfo(String sCarrierCode, String sEdpId, String sCallStartDate, String sCallStartTime) {
			//	log.debug("--> getTbIntlPfixChangeBasInfo");
				
			List<TbIntlPfixChangeBasInfo> uTB_INTL_PFIX_CHANGE_BAS= null;
			
			try {
					
				Map<String, Object> params = new HashMap<>();
				params.put("sCarrierCode", sCarrierCode);
				params.put("sEdpId", sEdpId);
				params.put("sCallStartDate", sCallStartDate);
				params.put("sCallStartTime", sCallStartTime);
					
				uTB_INTL_PFIX_CHANGE_BAS=(List<TbIntlPfixChangeBasInfo>) db.queryForObject(ReferenceInfoSQL.getTbIntlPfixChangeBasInfo, params, tbIntlPfixChangeBasInfoMapper);

			} catch (Exception e) {
				//log.error("Exception occurred during select :", e);
				return null;
			} finally {
				//		log.debug("<-- uTB_PROF_BAS");
				//		log.trace("   result={}", uTB_PROF_BAS);
			}

			return (TbIntlPfixChangeBasInfo) uTB_INTL_PFIX_CHANGE_BAS;
		}
		
	//@Transactional(readOnly=true)
	public TbProfBasInfo getTbProfBasInfo(String sItemId, String sItemDivCd, String sWorkCallStartDatetime) {
		//	log.debug("--> getTbProfBasInfo");
		
		TbProfBasInfo uTB_PROF_BAS= null;
	
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("sItemId", sItemId);
			params.put("sItemIndi", sItemDivCd);
			params.put("sCallStartDatetime", sWorkCallStartDatetime);
			
			uTB_PROF_BAS=db.queryForObject(ReferenceInfoSQL.getTbProfBasInfo, params, tbProfBasInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_PROF_BAS");
			//		log.trace("   result={}", uTB_PROF_BAS);
		}

		return uTB_PROF_BAS;
	}
	
	//@Transactional(readOnly=true)
	public TbCalNoBasInfo getTbCalNoBasInfo(String sPrefix, String sNxxNo, String sLineNo, String sWorkCallStartDatetime) {
		//	log.debug("--> getTbCalNoBasInfo");
		
		TbCalNoBasInfo uTB_CAL_NO_BAS= null;
	
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("sPrefix", sPrefix);
			params.put("sNxxNo", sNxxNo);
			params.put("sLineNo", sLineNo);
			params.put("sCallStartDatetime", sWorkCallStartDatetime);
			
			uTB_CAL_NO_BAS=db.queryForObject(ReferenceInfoSQL.getTbCalNoBasInfo, params, tbCalNoBasInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_CAL_NO_BAS");
			//		log.trace("   result={}", uTB_CAL_NO_BAS);
		}
		return uTB_CAL_NO_BAS;
	}
	
	//@Transactional(readOnly=true)
	public TbFeatrBasInfo getTbFeatrBasInfo(String sFeatureFormat, String sFeatureType, String sServiceType, String sWorkCallStartDatetime) {
		//	log.debug("--> getTbFeatrBasInfo");
		
		TbFeatrBasInfo uTB_FEATR_BAS= null;
	
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("sFeatureFormatId", sFeatureFormat);
			params.put("sFormatType", sFeatureType);
			params.put("sFeatureNo", sServiceType);
			params.put("sCallStartDatetime", sWorkCallStartDatetime);
			
			uTB_FEATR_BAS=db.queryForObject(ReferenceInfoSQL.getTbFeatrBasInfo, params, tbFeatrBasInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_FEATR_BAS");
			//		log.trace("   result={}", uTB_FEATR_BAS);
		}
		return uTB_FEATR_BAS;
	}
	
	
	//@Transactional(readOnly=true)
	public TbSvcPrvrBasInfo getTbSvcPrvrBasInfo(String sLspId) {

		TbSvcPrvrBasInfo uTB_SVC_PRVR_BAS = null;
		
		try {
			Map<String, String> params = Collections.singletonMap("sLspId", sLspId);
			uTB_SVC_PRVR_BAS = db.queryForObject(ReferenceInfoSQL.getTbSvcPrvrBasInfo, params, tbSvcPrvrBasInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- uTB_SVC_PRVR_BAS");
//			log.trace("   result={}", uTB_SVC_PRVR_BAS);
		}

		return uTB_SVC_PRVR_BAS;
	}
	
	
	//@Transactional(readOnly=true)
	public TbNoMovBizrBasInfo getTbNoMovBizrBasinfo(String sNpCd, String sCallStartDatetime) {
		//	log.debug("--> getTbNoMovBizrBasinfo");
		
		TbNoMovBizrBasInfo uTB_NO_MOV_BIZR_BAS= null;
	
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("sNpCd", sNpCd);
			params.put("sCallStartDatetime", sCallStartDatetime);
			
			uTB_NO_MOV_BIZR_BAS=db.queryForObject(ReferenceInfoSQL.getTbNoMovBizrBasinfo, params, tbNoMovBizrBasInfoMapper);

		} catch (Exception e) {
			//log.error("Exception occurred during select :", e);
			return null;
		} finally {
			//		log.debug("<-- uTB_NO_MOV_BIZR_BAS");
			//		log.trace("   result={}", uTB_NO_MOV_BIZR_BAS);
		}
		return uTB_NO_MOV_BIZR_BAS;
	}
	
	//@Transactional(readOnly=true)
	public List<TbErrMappgBasInfo> getTbErrMappgBasinfo(String neTypeId) {

		List<TbErrMappgBasInfo> result= null;

		try {
			Map<String, String> params = Collections.singletonMap("neTypeId", neTypeId);
			result = db.query(ReferenceInfoSQL.getTbErrMappgBasinfo, params, tbErrMappgBasInfoMapper);
			return result;
		} catch (DataAccessException e) {
			log.error("Exception occurred during select :", e);
			return null;
		} finally {
//			log.debug("<-- getCamelRouteFileList");
//			log.trace("   result={}", result);
		}
	}
	
	public List<WlessSpclNoBasInfo> getLoadWlessSpclNoBasInfo() {
		List<WlessSpclNoBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadWlessSpclNoBas, wlessSpclNoBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadWlessSpclNoBasInfo :", e);
			return null;
		}
		
		return result;
	}
	
	public List<TbPfixRgnBasInfo> getLoadTbPfixRgnBasInfo() {
		List<TbPfixRgnBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadTbPfixRgnBasInfo, tbPfixRgnBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadTbPfixRgnBasInfo :", e);
			return null;
		}
		
		return result;
	}
	
	public List<TbProfBasInfo> getLoadTbProfBasInfo() {
		List<TbProfBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadTbProfBasInfo, tbProfBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadTbProfBasInfo :", e);
			return null;
		}
		
		return result;
		
	}
	
	public List<TbCalNoBasInfo> getLoadTbCalNoBasInfo() {
		List<TbCalNoBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadTbCalNoBasInfo, tbCalNoBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadTbCalNoBasInfo :", e);
			return null;
		}
		
		return result;
	}
	
	public List<TbFeatrBasInfo> getLoadTbFeatrBasInfo() {
		List<TbFeatrBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadTbFeatrBasInfo, tbFeatrBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadTbFeatrBasInfo :", e);
			return null;
		}
		
		return result;
	}
	
	public List<TbNoMovBizrBasInfo> getLoadTbNoMovBizrBasInfo() {
		List<TbNoMovBizrBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadTbNoMovBizrBasinfo, tbNoMovBizrBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadTbNoMovBizrBasinfo :", e);
			return null;
		}
		
		return result;
		
	}
	
	public List<TbIntlPfixChangeBasInfo> getTbIntlPfixChangeBasInfo() {
		List<TbIntlPfixChangeBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getTbIntlPfixChangeBasInfo, tbIntlPfixChangeBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getTbIntlPfixChangeBasInfo :", e);
			return null;
		}
		
		return result;
		
	}

	public List<TbErrMappgBasInfo> getLoadTbErrMappgBasInfoList() {
		List<TbErrMappgBasInfo> result = null;
		
		try {
			result = db.query(ReferenceInfoSQL.getLoadTbErrMappgBasinfo, tbErrMappgBasInfoMapper);
		} catch (DataAccessException e) {
			log.error("Exception occurred during getLoadTbErrMappgBasinfo :", e);
			return null;
		}
		return result;
	}
}



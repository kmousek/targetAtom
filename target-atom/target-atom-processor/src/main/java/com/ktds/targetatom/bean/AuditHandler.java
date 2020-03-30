package com.ktds.targetatom.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ktds.targetatom.dao.AuditInfoDao;
import com.ktds.targetatom.dao.ReferenceInfoDao;
import com.ktds.targetatom.util.CommonUtils;
import com.ktds.targetatom.util.SpringBeanUtils;
import com.ktds.targetatom.vo.AuditInfo;
import com.ktds.targetatom.vo.IntlPfixBasInfo;
import com.ktds.targetatom.vo.IntlPfixChangeBasInfo;
import com.ktds.targetatom.vo.TbCalNoBasInfo;
import com.ktds.targetatom.vo.TbErrMappgBasInfoKey;
import com.ktds.targetatom.vo.TbFeatrBasInfo;
import com.ktds.targetatom.vo.TbNoMovBizrBasInfo;
import com.ktds.targetatom.vo.TbPfixRgnBasInfo;
import com.ktds.targetatom.vo.TbProfBasInfo;
import com.ktds.targetatom.vo.TbSvcPrvrBasInfo;
import com.ktds.targetatom.vo.WlessSpclNoBasInfo;

@Repository
public class AuditHandler {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AuditInfoDao auditDao;
	
	@Autowired
	ReferenceInfoDao referenceInfoDao;
	
	@Autowired
	ReferenceLoadBean referenceLoadBean; 
	
	@Value("${processor.delay}") 
	private int processorDelay;
	
	@Value("${message.collect.queue.id}")
	private String queueCollectId;
	
	@Value("${message.send.queue.id}")
	private String queueDistributerId;
	
	Map<String, String> mainTargetDirectoryMap = new HashMap<String, String>();
	Map<String, String> dropTargetDirectoryMap = new HashMap<String, String>();
	Map<String, String> errorTargetDirectoryMap = new HashMap<String, String>();
	String moduleType = "";
	//String kieSession = "";
	
	public AuditHandler() {
	}
	
	public AuditInfoDao getAuditInfoDao() {
		if(auditDao == null) {
			auditDao = (AuditInfoDao) SpringBeanUtils.getBean("auditDao");
		}
		return auditDao;
	}
	
//	@Bean
//	public String getKieSession() {
//		return kieSession;
//	}
	
//	public void setKieSession(String kieSession) {
//		this.kieSession = kieSession;
//	}
	
	public void setModuleType(String moduletype) {
		this.moduleType = moduletype;
	}
	
	public String getModuleType() {
		log.debug("moduleType ::: {}", moduleType.toString());
		return moduleType;
	}
	
	public Map<String, String> getMainTargetDirectoryMap() {
		log.debug("MainTargetDirectory ::: {}", mainTargetDirectoryMap.toString());
		return mainTargetDirectoryMap;
	}

	public void setMainTargetDirectoryMap(Map<String, String> mainTargetDirectoryMap) {
		this.mainTargetDirectoryMap = mainTargetDirectoryMap;
	}

	public Map<String, String> getDropTargetDirectoryMap() {
		log.debug("dropTargetDirectoryMap ::: {}", dropTargetDirectoryMap.toString());
		return dropTargetDirectoryMap;
	}

	public void setDropTargetDirectoryMap(Map<String, String> dropTargetDirectoryMap) {
		this.dropTargetDirectoryMap = dropTargetDirectoryMap;
	}

	public Map<String, String> getErrorTargetDirectoryMap() {
		log.debug("errorTargetDirectoryMap ::: {}", errorTargetDirectoryMap.toString());
		return errorTargetDirectoryMap;
	}

	public void setErrorTargetDirectoryMap(Map<String, String> errorTargetDirectoryMap) {
		this.errorTargetDirectoryMap = errorTargetDirectoryMap;
	}
	
	public void setTargetDirectory(Exchange exchange) throws Exception {
		String tempDirectory = exchange.getIn().getHeader("CamelFileParent").toString().replace('\\', '/');
		String fileName = exchange.getIn().getHeader("CamelFileName").toString();
		
		String targetNeid = CommonUtils.comnSubstring(tempDirectory, tempDirectory.lastIndexOf('/')+1, tempDirectory.length());
		
		
		exchange.getIn().setHeader("targetNeId", targetNeid);
		
		AuditInfo result = auditDao.findByFileName(fileName, queueCollectId);
		
		if (result == null) {
			Thread.sleep(processorDelay);
			result = auditDao.findByFileName(fileName, queueCollectId);
			
			if (result == null) {
				exchange.getIn().setHeader("file.existAudit",  "N");
				log.warn("{} File is not in Audit Table....", fileName);
				throw new FileNotFoundException("File Name { " + fileName + " } on ActiveMq Broker { " + queueCollectId + " } is not in Audit Table....");
			}
		}
		
		exchange.getIn().setHeader("file.existAudit", "Y");
		exchange.getIn().setHeader("file.identifier", result.getAditCretSeq());
		
		/*if (!getModuleType().equals("AMQ")) {
			if (mainTargetDirectoryMap == null) {
				log.error("mainTargetDirectoryMap is null!!!!");
				return;
			}*/
		
		if (!(mainTargetDirectoryMap.isEmpty() || mainTargetDirectoryMap == null)) {
			exchange.getIn().setHeader("file.targetDirectory", mainTargetDirectoryMap.get((String)targetNeid));
			log.debug("mainTargetDirectoryMap Directory ::: {}", mainTargetDirectoryMap.get((String)targetNeid));
		}
			
		if (!(dropTargetDirectoryMap.isEmpty() || dropTargetDirectoryMap == null)) {
			exchange.getIn().setHeader("file.dropTargetDirectory", dropTargetDirectoryMap.get((String)targetNeid));
			log.debug("dropTargetDirectoryMap Directory ::: {}", dropTargetDirectoryMap.get((String)targetNeid));
		}
		
		if (!(errorTargetDirectoryMap.isEmpty() || errorTargetDirectoryMap == null)) {
			exchange.getIn().setHeader("file.errorTargetDirectory", errorTargetDirectoryMap.get((String)targetNeid));
			log.debug("errorTargetDirectoryMap Directory ::: {}", errorTargetDirectoryMap.get((String)targetNeid));
		}
	}
	
	public void selectTarget(Exchange exchange) throws Exception {
		String tempDirectory = exchange.getIn().getHeader("CamelFileParent").toString().replace('\\', '/');
		String targetNeid = CommonUtils.comnSubstring(tempDirectory, tempDirectory.lastIndexOf('/')+1, tempDirectory.length());
		log.debug("TempDirectory :::: {}", tempDirectory);
		log.debug("targetNeid :::: {}", targetNeid);
				
		exchange.getIn().setHeader("targetNeId", targetNeid);
		
		File file = exchange.getIn().getBody(File.class);
		log.debug("file={}", file);
		if(file == null) {
			log.error("file is null, exchange.in.body={}", exchange.getIn().getBody());
		}
		String fileName = file.getName();
		String filePath = file.getParent().replaceFirst("C:", "").replaceFirst("D:", "").replaceFirst("E:", "").replace('\\', '/');
		
		AuditInfo result = auditDao.findByFileName(fileName, filePath);
		
		if (result == null) {
			Thread.sleep(processorDelay);
			result = auditDao.findByFileName(fileName, filePath);
			
			if (result == null) {
				exchange.getIn().setHeader("file.existAudit",  "N");
				log.warn("{} File is not in Audit Table....", fileName);
				return;
			}
		}
		
		exchange.getIn().setHeader("file.existAudit", "Y");
		exchange.getIn().setHeader("file.identifier", result.getAditCretSeq());
		
		if (mainTargetDirectoryMap == null) {
			log.error("mainTargetDirectoryMap is null!!!!");
			return;
		}
		
		if (!mainTargetDirectoryMap.isEmpty()) {
			exchange.getIn().setHeader("file.targetDirectory", mainTargetDirectoryMap.get((String)targetNeid));
			log.debug("mainTargetDirectoryMap Directory ::: {}", mainTargetDirectoryMap.get((String)targetNeid));
		}
		
		if (!dropTargetDirectoryMap.isEmpty()) {
			exchange.getIn().setHeader("file.dropTargetDirectory", dropTargetDirectoryMap.get((String)targetNeid));
			log.debug("dropTargetDirectoryMap Directory ::: {}", dropTargetDirectoryMap.get((String)targetNeid));
		}
		
		if (!errorTargetDirectoryMap.isEmpty()) {
			exchange.getIn().setHeader("file.errorTargetDirectory", errorTargetDirectoryMap.get((String)targetNeid));
			log.debug("errorTargetDirectoryMap Directory ::: {}", errorTargetDirectoryMap.get((String)targetNeid));
		}
		
		
	}
	
	public void updateStatusAudit(Exchange exchange, String sttus, 
			                String lastChgTrtrId, String lastChgPgmId) throws Exception {
		long aditCretSeq = Long.parseLong(exchange.getIn().getHeader("file.identifier").toString());
		AuditInfo auditInfo = new AuditInfo();
		
		auditInfo.setAditCretSeq(aditCretSeq);
		auditInfo.setSttus(sttus);
		auditInfo.setLastChgTrtrId(lastChgTrtrId);
		auditInfo.setLastChgPgmId(lastChgPgmId);
		
		auditDao.updateStatus(auditInfo);
	}
	
	public void updateAudit(Exchange exchange, String sttus, 
							String lastChgTrtrId, String lastChgPgmId, long rcrdCnt, long errCnt, long prcCnt) throws Exception {
		long aditCretSeq = Long.parseLong(exchange.getIn().getHeader("file.identifier").toString());
		//log.warn("updateAudit method :: {}", exchange.getIn().getBody(String.class));
		log.debug("rcrdCnt :: {}, errCnt :: {}, prcCnt :: {} ", rcrdCnt, errCnt, prcCnt);
		auditDao.update(aditCretSeq, sttus, lastChgTrtrId, lastChgPgmId, rcrdCnt, errCnt, prcCnt, lastChgPgmId);
	}
	
	public void insertAudit(Exchange exchange, String first_cret_pgm_id, String fileNm, String sttus, String moduleNm, long rcrdCnt,
			                  long errCnt, long prcCnt) throws Exception {
		
		long upAditSeq = Long.parseLong(exchange.getIn().getHeader("file.identifier").toString());
		
		String trmDirNm;
		if (queueDistributerId.equals(""))
			trmDirNm = exchange.getIn().getHeader("file.targetDirectory").toString();
		else
			trmDirNm = queueDistributerId;
		
		String orgnFileNm = exchange.getIn().getHeader("CamelFileName").toString();
		String fileType = "PRC_CMP";
		
		auditDao.insert(upAditSeq, fileNm, trmDirNm, orgnFileNm, sttus, moduleNm, rcrdCnt, errCnt, prcCnt, fileType);
	}
	
/*	public String insertAuditAndSendFile(Exchange exhange, String first_cret_pgm_id, String file_nm, String sttus, String module_nm, long rcrd_cnt,
            long err_cnt, long prc_cnt) throws Exception {

		long up_adit_seq = Long.parseLong(exhange.getIn().getHeader("file.identifier").toString());
		String trtm_dir_nm = exhange.getIn().getHeader("file.targetDirectory").toString();
		String orgn_file_nm = exhange.getIn().getHeader("CamelFileName").toString();
		String file_type = "PRC_CMP";

		DbProcessor.getInstance(exhange.getContext()).insertAudit(first_cret_pgm_id, up_adit_seq, file_nm, trtm_dir_nm, orgn_file_nm, sttus, module_nm, rcrd_cnt, err_cnt, prc_cnt, file_type);
		return "file:" + trtm_dir_nm + "?fileName=" + file_nm;
	}*/
	
	public String sendToDistributor(String trtmDirNm, String fileNm) {
		
		//log.debug("trtmDirNm ::: {}, fileNm ::: {}", trtmDirNm, fileNm);
		//String targetPath = "file:" + trtmDirNm + "?charset=iso-8859-1";
		//log.debug("targetPath ::: {}", targetPath);
		//return targetPath;
		//return "file:/WRK/SND/NRAT/VOVLTE/MOKD1?fileName=" + fileNm + "&charset=iso-8859-1";
		return "file:/WRK/SND/NRAT/VOVLTE/GURO1?charset=iso-8859-1";
	}
	
	public void insertFirstCollAditHst(Exchange exchange) {
		String fileOnlyName = exchange.getIn().getHeader("CamelFileName").toString();
		String parent = "";
		//if (!getModuleType().equals("AMQ")) {
		//	parent = exchange.getIn().getHeader("CamelFileParent").toString().replaceFirst("C:", "").replace('\\', '/');
		//} else {
			parent = queueCollectId;
		//}
			
		AuditInfo auditVo = new AuditInfo();
		auditVo.setFirstCretTrtrId("COL");
		auditVo.setLastChgTrtrId("MZN");
		auditVo.setFirstCretPgmId("MZN");
		auditVo.setLastChgPgmId("COL_KR");
		auditVo.setUpAditSeq(0);
		auditVo.setFileNm(fileOnlyName);
		auditVo.setTrmDirNm(parent.replaceAll("\\\\", "/"));
		auditVo.setOrgnFileNm(fileOnlyName);
		auditVo.setSttus("RD");
		auditVo.setModuleNm("COL_KR");
		auditVo.setFileType("PRC");
		
		Long result = auditDao.insert(auditVo); 

		return;
	}
	
	
	public TbProfBasInfo get_TB_PROF_BAS(String Imsi) {
		
		
		//List<CdrProfReferenceInfo> profreferenceInfos = referenceInfoDao.getCdrProfReferenceInfo();
		  ArrayList<TbProfBasInfo> profreferenceInfos = referenceLoadBean.getCdrProfReferenceInfo();
		
		  for (int i = 0; i < profreferenceInfos.size(); i++) {

			  if ( profreferenceInfos.get(i).getsReturnvalue().contains(Imsi) ) {
				  
				  return profreferenceInfos.get(i);
			  
			  } 		
		  }
		
		  return null;
	}
	
	
	public TbPfixRgnBasInfo get_TB_PFIX_RGN_BAS(String sCalledSvcNo, String sCallStartDatetime, String sFlag) {
		
		
		TbPfixRgnBasInfo tbPfixRgnBas=null;	
		
		if ( sCalledSvcNo == null || !StringUtils.isNumeric(sCalledSvcNo) || sCalledSvcNo.equals("") ) {
			return null;
		}
		
		int iNpaLength = 7;
		int iCalledSvcNoLeng = sCalledSvcNo.length();
		String sPrefix;
		boolean bLookupResult = false;

		if (iCalledSvcNoLeng < iNpaLength) {
	        iNpaLength = iCalledSvcNoLeng;
	    }

	    while(!bLookupResult && iNpaLength >= 2) {

	        sPrefix = CommonUtils.comnSubstring(sCalledSvcNo, 0, iNpaLength);
	        //sCallStartDatetime = sCallStartDatetime;
	   
	        //tbPfixRgnBas = referenceInfoDao.getPfixRgnBasInfo(sPrefix, sCallStartDatetime);
	        tbPfixRgnBas = referenceLoadBean.getPfixRgnBasInfo(sPrefix, sCallStartDatetime);
			//List<TbPfixRgnBasInfo> uTB_PFIX_RGN_BAS = audit.getPfixRgnBasInfo(sPrefix, sCallStartDatetime);
	       
	        if (tbPfixRgnBas != null) {

	            bLookupResult = true;

	        }
	        iNpaLength = iNpaLength - 1;
	    }

	    iNpaLength = iNpaLength + 1;
	    
	    if (bLookupResult) {
	        if (sFlag == "OTR") {
	            //uTB_PFIX_RGN_BAS.sSettlFeatureCd = uTB_PFIX_RGN_BAS.sSettlEtcFeatureId;
	        	tbPfixRgnBas.setsSettlFeatureCd(tbPfixRgnBas.getsSettlEtcFeatureId());
	        }
	        else { // "ORG" case 추가 20130121jek
	            //uTB_PFIX_RGN_BAS.sSettlFeatureCd = uTB_PFIX_RGN_BAS.sSettlFeatureCd;
				
	        }

	        if (tbPfixRgnBas.getsSettlFileName().equals("INN")) {
	        	
	        	tbPfixRgnBas.setsChrgOtherNo(CommonUtils.comnSubstring(sCalledSvcNo, iNpaLength, iCalledSvcNoLeng));
	        	
	        } else {

	            iNpaLength = 0;
	            int iLoopCnt = 0;
	            //int iNpaAreaLength = strLength(uTB_PFIX_RGN_BAS.sNpaAreaNo);
	            int iNpaAreaLength = tbPfixRgnBas.getsNpaAreaNo().length();

	            while (iLoopCnt < iNpaAreaLength) {
	                if (StringUtils.isNumeric(CommonUtils.comnSubstring(tbPfixRgnBas.getsNpaAreaNo(), iLoopCnt,iLoopCnt+1))) {
	                    iNpaLength = iNpaLength + 1;
	                }
	                iLoopCnt = iLoopCnt + 1;
	            }

	            if (iCalledSvcNoLeng < iNpaLength) {
	                iNpaLength = iCalledSvcNoLeng;
	            }
	            tbPfixRgnBas.setsChrgOtherNo(CommonUtils.comnSubstring(sCalledSvcNo, iNpaLength, iCalledSvcNoLeng));        

	        }
	        
	        return tbPfixRgnBas;

	    }
	    return null;
	}
	
	public WlessSpclNoBasInfo get_TB_WLESS_SPCL_NO_BAS(String sNpaNo, String sNxxNo, String sMarketCd, String sCallStartDatetime, String sFlag){
		
		int i, nxx_dgt, nxx_len;
	    String sLineNo;
	    String chgsNxxNo;
	    String chgsLineNo;
	    String sSPNM      = "1";
	    String sSPIP      = "2";
	    String sSPCP      = "3";


	    if ( sNpaNo == null || sNpaNo.equals("")) {

	        return null;

	    }

	    if (sNxxNo == null || sNxxNo.equals("")) {

	        sNxxNo = "";

	    }

	    //TB_WLESS_SPCL_NO_BAS_Intl uTB_WLESS_SPCL_NO_BAS = udrCreate(TB_WLESS_SPCL_NO_BAS_Intl);

	    if (sFlag == sSPCP) {

	        //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	        //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	        //uTB_WLESS_SPCL_NO_BAS.sNpaNo = sNpaNo;
	        //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnSubstring(sNxxNo,0,strLength(sNxxNo)>4 ? 4 : comnStrLength(sNxxNo));
	        //sLineNo = "";
	    	chgsNxxNo = CommonUtils.comnSubstring(sNxxNo, 0,sNxxNo.length()>4 ? 4 : sNxxNo.length());
	        sLineNo = "";

	        //if (comnStrLength(sNxxNo)>4) {
	        if (sNxxNo.length()>4) {

	            //sLineNo = comnSubstring(sNxxNo,4,comnStrLength(sNxxNo));
	            sLineNo = CommonUtils.comnSubstring(sNxxNo, 4, sNxxNo.length());

	        }

	        //if (comnStrLength(sLineNo) > 8) {
	        if (sLineNo.length() > 8) {

	            //uTB_WLESS_SPCL_NO_BAS.sLineNo = comnSubstring(sLineNo,0,8);
	        	chgsLineNo = CommonUtils.comnSubstring(sLineNo,0,8);

	        } else {

	            //uTB_WLESS_SPCL_NO_BAS.sLineNo = sLineNo;
	        	chgsLineNo = sLineNo;

	        }

	        //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	        WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	        //WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	        
	        
	        //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	        if (uTB_WLESS_SPCL_NO_BAS != null) {

	        	uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
		        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
		        uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
		        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
		        
	            return uTB_WLESS_SPCL_NO_BAS;

	        } 
	        
	        return null;
	    }

	    if (sFlag == sSPNM) {

	        nxx_dgt=4;
	        
	        //if(comnStrLength(sNxxNo)<nxx_dgt)nxx_dgt=comnStrLength(sNxxNo);
	        if(sNxxNo.length()<nxx_dgt)nxx_dgt=sNxxNo.length();


	        while(nxx_dgt >= 3) {
	        	
	            //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	            //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	            //uTB_WLESS_SPCL_NO_BAS.sNpaNo = sNpaNo;

	            sLineNo = "";
	            
	            //if (comnStrLength(sNxxNo)>nxx_dgt) {
	            if (sNxxNo.length()>nxx_dgt) {

	                //sLineNo = comnSubstring(sNxxNo,nxx_dgt,comnStrLength(sNxxNo));
	                sLineNo = CommonUtils.comnSubstring(sNxxNo,nxx_dgt,sNxxNo.length());
//	                System.out.println("==============nxx_dgt"+nxx_dgt);
//	            	System.out.println("==============sNxxNo.length()"+sNxxNo.length());
//	            	System.out.println("==============sLineNo"+sLineNo);
	            }


	            //if (comnStrLength(sLineNo) > 8) {
	            if (sLineNo.length() > 8) {

	                //uTB_WLESS_SPCL_NO_BAS.sLineNo = comnSubstring(sLineNo,0,8);
	            	chgsLineNo = CommonUtils.comnSubstring(sLineNo,0,8);

	            } else {

	            	//uTB_WLESS_SPCL_NO_BAS.sLineNo =sLineNo;
	            	chgsLineNo = sLineNo;

	            }

	            i = 0;

	            ////ASIS 주석처리 // while( i<nxx_dgt && isStrDigitCheck(strSubstring(sNxxNo,i,i+1)) ) {
	            //while( i<nxx_dgt && strREIndexOf(comnSubstring(sNxxNo, i, i + 1), "[^0-9]") == -1 ) {
	            while( i<nxx_dgt && StringUtils.isNumeric(CommonUtils.comnSubstring(sNxxNo,i, i + 1)) ) {

	                i = i + 1;

	            }

	            nxx_len = i;
	            
	            //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnPadChr(comnSubstring(sNxxNo,0,nxx_len),4," ","L");
	            chgsNxxNo = CommonUtils.comnPadChr(CommonUtils.comnSubstring(sNxxNo,0,nxx_len), 4, ' ', "L");
	           
	            //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	            WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	            //WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
		        
	            
	            
	            //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	            if (uTB_WLESS_SPCL_NO_BAS != null) {

	            	uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
			        uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
			        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
			        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
			        
	                return uTB_WLESS_SPCL_NO_BAS;

	            }

	            nxx_dgt = nxx_dgt - 1 ;          
	        }
	    }

	    nxx_dgt=4;
	    
	    i = 0;
	    //System.out.println("=============bbbsNxxNo1"+sNxxNo);
	    if(sNxxNo.length() < nxx_dgt) nxx_dgt = sNxxNo.length();

	    //while( i<nxx_dgt && isStrDigitCheck(strSubstring(sNxxNo,i,i+1)) ) {
	    //while( i<nxx_dgt && strREIndexOf(comnSubstring(sNxxNo, i, i + 1), "[^0-9]") == -1 ) {
	    while( i<nxx_dgt && StringUtils.isNumeric(CommonUtils.comnSubstring(sNxxNo,i, i + 1)) ) {
	    	//System.out.println("===============isNumerici"+StringUtils.isNumeric(CommonUtils.comnSubstring(sNxxNo, i, i + 1)));
	         i = i + 1;
	         //System.out.println("===============i"+i);
	    }

	    nxx_len = i;
	    //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	    //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	    //uTB_WLESS_SPCL_NO_BAS.sNpaNo = sNpaNo;
	    //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnPadChr(comnSubstring(sNxxNo,0,nxx_len),4," ","L");
	    //uTB_WLESS_SPCL_NO_BAS.sLineNo = "$$$$";
	    //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	    chgsNxxNo = CommonUtils.comnPadChr(CommonUtils.comnSubstring(sNxxNo, 0,nxx_len), 4, ' ',"L");
	    chgsLineNo = "$$$$";
	    
//	    System.out.println("=============bbbsNxxNo2"+sNxxNo);
//	    System.out.println("=============bbbsnxx_dgt"+nxx_dgt);
//	    System.out.println("=============bbbsnxx_len"+nxx_len);
	    
	    WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	    //WlessSpclNoBasInfo uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	    
	    
	    //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	    if (uTB_WLESS_SPCL_NO_BAS != null ) {

	    	uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
	    	uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
	        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
	        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
	        
	        return uTB_WLESS_SPCL_NO_BAS;

	    }


	    if (sFlag == sSPIP || sFlag == sSPNM) {

	        i = nxx_len;

	        while( i != 0) {	    	
		    	
	            //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	            //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	            //uTB_WLESS_SPCL_NO_BAS.sNpaNo = sNpaNo;
	            //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnPadChr(comnSubstring(sNxxNo,0,i),4," ", "L"); 
	            //uTB_WLESS_SPCL_NO_BAS.sLineNo = "$$$$";
	            //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	        	chgsNxxNo = CommonUtils.comnPadChr(CommonUtils.comnSubstring(sNxxNo, 0,i), 4, ' ',"L");
	        	chgsLineNo = "$$$$";
	        	
	   			//uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	        	uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);

	            //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	            if (uTB_WLESS_SPCL_NO_BAS != null ) {

	            	uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
	            	uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
			        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
			        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
			        
	                return uTB_WLESS_SPCL_NO_BAS;

	            }

	            i = i - 1;
	        }
	    }

	    if (sFlag == sSPIP || sFlag == sSPNM) {

	        //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	        //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	        //uTB_WLESS_SPCL_NO_BAS.sNpaNo = sNpaNo;
	        //uTB_WLESS_SPCL_NO_BAS.sNxxNo = "$$$$";
	        //uTB_WLESS_SPCL_NO_BAS.sLineNo = "$$$$";
	        //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	    	chgsNxxNo = "$$$$";
	    	chgsLineNo = "$$$$";
			//uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
			uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);

			
	        //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	        if (uTB_WLESS_SPCL_NO_BAS != null ) {

	        	uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
				uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
		        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
		        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
	        	
	            return uTB_WLESS_SPCL_NO_BAS;

	        }
	    }

	    if (sFlag == sSPNM) {

	        nxx_dgt=4;

	        //if(comnStrLength(sNxxNo)<nxx_dgt)nxx_dgt=comnStrLength(sNxxNo);
	        if(sNxxNo.length()<nxx_dgt)nxx_dgt=sNxxNo.length();

	        while(nxx_dgt >= 3) {

	            sLineNo = "";

	            //if (comnStrLength(sNxxNo)>4) {
	            if (sNxxNo.length()>4) {

	                //sLineNo = comnSubstring(sNxxNo,4,comnStrLength(sNxxNo));
	                sLineNo = CommonUtils.comnSubstring(sNxxNo, 4,sNxxNo.length());

	            }


	            //if (comnStrLength(sLineNo) > 8) {
	            if (sLineNo.length() > 8) {

	                //uTB_WLESS_SPCL_NO_BAS.sLineNo = comnSubstring(sLineNo,0,8);
	            	chgsLineNo = CommonUtils.comnSubstring(sLineNo, 0,8);

	            } else {

	               // uTB_WLESS_SPCL_NO_BAS.sLineNo = sLineNo;
	            	chgsLineNo = sLineNo;

	            }

	            i = 0;

	            //while( i<nxx_dgt && isStrDigitCheck(strSubstring(sNxxNo,i,i+1)) ) {
	            //while( i<nxx_dgt && strREIndexOf(comnSubstring(sNxxNo, i, i + 1), "[^0-9]") == -1 ) {
	            while( i<nxx_dgt && StringUtils.isNumeric(CommonUtils.comnSubstring(sNxxNo, i, i + 1)) ) {

	                 i = i + 1;
	            }

	            nxx_len = i;
	            //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	            //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	            //uTB_WLESS_SPCL_NO_BAS.sNpaNo = "$$$$";
	            //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnPadChr(comnSubstring(sNxxNo,0,nxx_len),4, " ","L"); 
	            //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	 			sNpaNo = "$$$$";
	 			chgsNxxNo = CommonUtils.comnPadChr(CommonUtils.comnSubstring(sNxxNo, 0,nxx_len), 4, ' ', "L");
	   			//uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
	   			uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);

	   			
	            //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	            if (uTB_WLESS_SPCL_NO_BAS != null) {

	            	uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
		   			uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
			        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
			        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
			        
	                return uTB_WLESS_SPCL_NO_BAS;
	            }

	            //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	            //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	            //uTB_WLESS_SPCL_NO_BAS.sNpaNo = "$$$$";
	            //uTB_WLESS_SPCL_NO_BAS.sLineNo = "$$$$";
	            //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnPadChr(comnSubstring(sNxxNo,0,nxx_len),4, " ","L"); 
	            //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	            sNpaNo = "$$$$";
	            chgsLineNo = "$$$$";
	            chgsNxxNo = CommonUtils.comnPadChr(CommonUtils.comnSubstring(sNxxNo, 0,nxx_len), 4, ' ', "L");
				//uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
				uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);

				
	            //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	            if (uTB_WLESS_SPCL_NO_BAS != null ) {

	            	uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
		   			uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
			        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
			        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);

	                return uTB_WLESS_SPCL_NO_BAS;
	            }

	           nxx_dgt = nxx_dgt - 1;
	        }
	    }         

	    // ----------------------------------------------------------
	    // Legacy MAF : line_no check - [DR-2013-48616] 2013.12.21
	    // MZN : 2016.06.30
	    // ----------------------------------------------------------

	    nxx_dgt=4;
	    int line_no_len = 4;
	    i = 0;

	    //if(comnStrLength(sNxxNo)<nxx_dgt)nxx_dgt=comnStrLength(sNxxNo);
	    if(sNxxNo.length()<nxx_dgt)nxx_dgt=sNxxNo.length();

	    //while( i<nxx_dgt && strREIndexOf(comnSubstring(sNxxNo, i, i + 1), "[^0-9]") == -1 ) {
	    while( i<nxx_dgt && StringUtils.isNumeric(CommonUtils.comnSubstring(sNxxNo, i, i + 1)) ) {
	    
	         i = i + 1;
	    }    

	    nxx_len = i;
	    sLineNo = "";


	    //if (comnStrLength(sNxxNo)>4) {
	    if (sNxxNo.length()>4) {

	        //sLineNo = comnSubstring(sNxxNo,4,comnStrLength(sNxxNo));
	        sLineNo = CommonUtils.comnSubstring(sNxxNo, 4,sNxxNo.length());

	    }

	    i = 0;

	    //while( i<line_no_len && strREIndexOf(comnSubstring(sLineNo, i, i + 1), "[^0-9]") == -1 ) {
	    while( i<line_no_len && StringUtils.isNumeric(CommonUtils.comnSubstring(sLineNo, i, i + 1)) ) {

	        i = i + 1;

	    }

	    line_no_len = i;
	    
	    while(line_no_len >= 1) {
	    	
	        //uTB_WLESS_SPCL_NO_BAS.sCallStartDatetime = sCallStartDatetime;
	        //uTB_WLESS_SPCL_NO_BAS.sMarketCd = sMarketCd;
	        //uTB_WLESS_SPCL_NO_BAS.sNpaNo = sNpaNo;
	        //uTB_WLESS_SPCL_NO_BAS.sLineNo = comnSubstring(sLineNo,0,line_no_len); 
	        //uTB_WLESS_SPCL_NO_BAS.sNxxNo = comnPadChr(comnSubstring(sNxxNo,0,nxx_len),4, " ","L"); 
	        //wlssLookup_TB_WLESS_SPCL_NO_BAS(uTB_WLESS_SPCL_NO_BAS);
	    	chgsLineNo = CommonUtils.comnSubstring(sLineNo, 0,line_no_len);
	        chgsNxxNo = CommonUtils.comnPadChr(CommonUtils.comnSubstring(sNxxNo, 0,nxx_len), 4,' ', "L");
			//uTB_WLESS_SPCL_NO_BAS = referenceInfoDao.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);
			uTB_WLESS_SPCL_NO_BAS = referenceLoadBean.getWlessSpclNoBasInfo(sNpaNo, chgsNxxNo, chgsLineNo, sMarketCd, sCallStartDatetime);


	        //if (uTB_WLESS_SPCL_NO_BAS.lRowCnt > 0) {
	        if (uTB_WLESS_SPCL_NO_BAS != null ) {

            	uTB_WLESS_SPCL_NO_BAS.setsMarketCd(sMarketCd);
	   			uTB_WLESS_SPCL_NO_BAS.setsNpaNo(sNpaNo);
		        uTB_WLESS_SPCL_NO_BAS.setsLineNo(chgsLineNo);
		        uTB_WLESS_SPCL_NO_BAS.setsNxxNo(chgsNxxNo);
		        
	            return uTB_WLESS_SPCL_NO_BAS;

	        }

	        line_no_len = line_no_len - 1;


	    }


	    return null;
		
	}
	
	public IntlPfixBasInfo get_TB_INTL_PFIX_BAS (String sCalledNumber) {
		

	    if( sCalledNumber ==  null || sCalledNumber ==  "" || sCalledNumber.equals("") ) {

	        log.debug("sEdpCd is NULL");

	        return null;
	    }

	    //TB_INTL_PFIX_BAS_Intl uTB_INTL_PFIX_BAS =  udrCreate(TB_INTL_PFIX_BAS_Intl);

	    int i = 0;
	    int j = 0;
	    int iCountryCdLength = 3;
	    int iZoneCdLength    = 4;
	    int iCalledNumberLength;

	    String sTmpCountryCode, sTmpZoneCode;
	    String sTmpCountryCode1;

	    // 번호가 없을 경우 return


	    // 번호가 0  으로 시작하면 return
	    //if (comnSubstring(sCalledNumber,0,1) == "0") {
	    if (CommonUtils.comnSubstring(sCalledNumber, 0,1) == "0") {

	        return null;

	    }


	    //iCalledNumberLength = comnStrLength(sCalledNumber);
	    iCalledNumberLength = sCalledNumber.length();
	    // djpark - 7자리가 안되는 called_number 가 들어오면 space 값을 채워 7자리 만듦
	    // 7자리가 되지 않으면 outOfRangeIndex Exception 발생함.

	    if (iCalledNumberLength < 7) {

	        int z = 7 - iCalledNumberLength;
	        int y = 0;
	        while( y < z) {

	           sCalledNumber = sCalledNumber + " ";

	            y = y + 1;

	        }

	    }


	    //lookup the INTL_PREFIX based on country code, zone code
	    // 공통팀에서 제공한 lookup 함수는 데이터가 조회되지 않았을 경우. 모든 udr  을(PK 포함) null 로 함 .
	    // comLookup_TB_INTL_PFIX_BAS  호출 전 PK가 누락되지 않도록 주의.
	  
	    i = 0;

	    while(i < iCountryCdLength){

	        //sTmpCountryCode = comnSubstring(sCalledNumber, 0, 1+i);       
	        //sTmpCountryCode1 = comnPadChr(sTmpCountryCode,3," ","L");
	        sTmpCountryCode = CommonUtils.comnSubstring(sCalledNumber, 0, 1+i);       
	        sTmpCountryCode1 = CommonUtils.comnPadChr(sTmpCountryCode, 3, ' ', "L");
	        
	        
	        j=0;

	        while(j < iZoneCdLength) {

	            //uTB_INTL_PFIX_BAS.sIntlPrefixCd = sTmpCountryCode1;
	            //sTmpZoneCode = comnSubstring(sCalledNumber,1+i,2+i+j);
	            //uTB_INTL_PFIX_BAS.sCommZoneCd = comnPadChr(sTmpZoneCode,4," ","L");
	            //wlssLookup_TB_INTL_PFIX_BAS(uTB_INTL_PFIX_BAS);
	        		  
	            sTmpZoneCode = CommonUtils.comnSubstring(sCalledNumber, 1+i,2+i+j);
	            //sCommZoneCd = CommonUtils.padString(sTmpZoneCode, ' ',4,"L");
	            IntlPfixBasInfo uTB_INTL_PFIX_BAS = referenceInfoDao.getIntlPrfixBasInfo(sTmpCountryCode1, CommonUtils.comnPadChr(sTmpZoneCode, 4, ' ',"L"));

	            //if (uTB_INTL_PFIX_BAS.lRowCnt > 0){
	            if (uTB_INTL_PFIX_BAS != null){

	                return  uTB_INTL_PFIX_BAS;

	            }                

	            j = j+1;

	        }

	        i=i+1;

	    }


	    //lookup the INTL_PREFIX based on country code, zone code="*"

	    i = 0;

	    while(i < iCountryCdLength){

	        //sTmpCountryCode = comnSubstring(sCalledNumber,0,1+i);
	        //uTB_INTL_PFIX_BAS.sIntlPrefixCd = comnPadChr(sTmpCountryCode,3," ","L");
	        //uTB_INTL_PFIX_BAS.sCommZoneCd = "*";
	        sTmpCountryCode = CommonUtils.comnSubstring(sCalledNumber, 0,1+i);
	        //sIntlPrefixCd = CommonUtils.padString(sTmpCountryCode, ' ',3,"L");
			//sCommZoneCd = "*";

			//wlssLookup_TB_INTL_PFIX_BAS(uTB_INTL_PFIX_BAS);
	        IntlPfixBasInfo uTB_INTL_PFIX_BAS = referenceInfoDao.getIntlPrfixBasInfo(CommonUtils.comnPadChr(sTmpCountryCode, 3, ' ', "L"), "*");


	        //if (uTB_INTL_PFIX_BAS.lRowCnt > 0) {
	        if (uTB_INTL_PFIX_BAS != null) {

	            //if (uTB_INTL_PFIX_BAS.sSubIntlPrefixCd == null){
	            String sSubIntlPrefixCd = uTB_INTL_PFIX_BAS.getsSubIntlPrefixCd();
	            if (sSubIntlPrefixCd == null || sSubIntlPrefixCd.equals("")){

	                //uTB_INTL_PFIX_BAS.sIntlPrefixCd = sSubIntlPrefixCd;
	                uTB_INTL_PFIX_BAS.setsIntlPrefixCd(sSubIntlPrefixCd);

	            }

	            return  uTB_INTL_PFIX_BAS;
	        }                

	        i=i+1;
	    }

	    return null;
	}
	
	public IntlPfixChangeBasInfo get_TB_INTL_PFIX_CHANGE_BAS(String sCarrierCd, String sEdpCd, String sCallStartDatetime) {

	    if( sCarrierCd ==  null || sCarrierCd ==  "" || sCarrierCd.equals("") ) {

	        return null;

	    }


	    if( sEdpCd ==  null || sEdpCd ==  "" || sEdpCd .equals("") ) {

	        return null;

	    }

	    if( sCallStartDatetime  ==  null || sCallStartDatetime  ==  "" || sCallStartDatetime.equals("") ) {

	        return null;
	    }

	    //TB_INTL_PFIX_CHANGE_BAS_Intl uTB_INTL_PFIX_CHANGE_BAS =  udrCreate(TB_INTL_PFIX_CHANGE_BAS_Intl);
	    
	    //uTB_INTL_PFIX_CHANGE_BAS.sCarrierCd         = sCarrierCd;
	    //uTB_INTL_PFIX_CHANGE_BAS.sEdpCd             = sEdpCd;
	    //uTB_INTL_PFIX_CHANGE_BAS.sCallStartDatetime = sCallStartDatetime;

	    //wlssLookup_TB_INTL_PFIX_CHANGE_BAS(uTB_INTL_PFIX_CHANGE_BAS);

	    IntlPfixChangeBasInfo uTB_INTL_PFIX_CHANGE_BAS = referenceInfoDao.getIntlPrfixChangeBasInfo(sCarrierCd, sEdpCd, sCallStartDatetime);


	    //if (uTB_INTL_PFIX_CHANGE_BAS.lRowCnt > 0) {
	    if (uTB_INTL_PFIX_CHANGE_BAS != null) {

	        return uTB_INTL_PFIX_CHANGE_BAS;

	    }

	    return null;

	}
	
	public TbProfBasInfo get_TB_PROF_BAS(String sItemId, String sItemDivCd, String sWorkCallStartDatetime) {

	    if( sItemId  ==  null || sItemId  ==  "" || sItemId.equals("") ) {

	        log.debug("sItemId is NULL");
	        return null;

	    }


	    if( sItemDivCd  ==  null || sItemDivCd  ==  "" || sItemDivCd.equals("") ) {

	        log.debug("sItemDivCd is NULL");

	        return null;
	    }


	    if( sWorkCallStartDatetime  ==  null || sWorkCallStartDatetime  ==  "" || sWorkCallStartDatetime.equals("") ) {

	        log.debug("sWorkCallStartDatetime is NULL");
	        return null;

	    }

	    //TB_PROF_BAS_Intl  uTB_PROF_BAS  = udrCreate(TB_PROF_BAS_Intl);

	    //uTB_PROF_BAS.sItemId            = sItemId;
	    //uTB_PROF_BAS.sItemIndi          = sItemDivCd;
	    //uTB_PROF_BAS.sCallStartDatetime = sWorkCallStartDatetime;

	    //wlssLookup_TB_PROF_BAS(uTB_PROF_BAS);
	    TbProfBasInfo uTB_PROF_BAS = referenceLoadBean.getTbProfBasInfo(sItemId, sItemDivCd, sWorkCallStartDatetime);
		//TbProfBasInfo uTB_PROF_BAS = referenceInfoDao.getTbProfBasInfo(sItemId, sItemDivCd, sWorkCallStartDatetime);

	    //if (uTB_PROF_BAS.lRowCnt > 0) {
	    if (uTB_PROF_BAS != null ) {

	       return uTB_PROF_BAS;

	    }

	    return null;

	}
	
	public TbCalNoBasInfo get_TB_CAL_NO_BAS(String sPrefix, String sNxxNo, String sLineNo, String sWorkCallStartDatetime){
		

	    //TB_CAL_NO_BAS_Intl uTB_CAL_NO_BAS = udrCreate(TB_CAL_NO_BAS_Intl);

	    if( sPrefix ==  null || sPrefix == "" || sPrefix.equals("") ) {

	        log.debug("sPrefix is NULL");
	        return null;
	    }

	    if( sNxxNo ==  null || sNxxNo ==  "" || sNxxNo.equals("") ) {
	    
	    	log.debug("sNxxNo is NULL");
	        return null;
	    }

	    if( sLineNo ==  null || sLineNo ==  "" || sLineNo.equals("") ) {

	    	log.debug("sLineNo is NULL");
	        return null;
	    }

	    if( sWorkCallStartDatetime ==  null || sWorkCallStartDatetime ==  "" || sWorkCallStartDatetime.equals("") ) {
	    
	    	log.debug("sWorkCallStartDatetime is NULL");
	        return null;
	    }


	    //uTB_CAL_NO_BAS.sPrefix = sPrefix;
	    //uTB_CAL_NO_BAS.sNxxNo  = sNxxNo;
	    //uTB_CAL_NO_BAS.sLineNo = sLineNo;
	    //uTB_CAL_NO_BAS.sCallStartDatetime = sWorkCallStartDatetime;

	    //wlssLookup_TB_CAL_NO_BAS(uTB_CAL_NO_BAS);
	    //TbCalNoBasInfo uTB_CAL_NO_BAS = referenceInfoDao.getTbCalNoBasInfo(sPrefix, sNxxNo, sLineNo, sWorkCallStartDatetime);
	    TbCalNoBasInfo uTB_CAL_NO_BAS = referenceLoadBean.getTbCalNoBasInfo(sPrefix, sNxxNo, sLineNo, sWorkCallStartDatetime);

	    //debug(uTB_CAL_NO_BAS);

	    //if ( uTB_CAL_NO_BAS.lRowCnt > 0 ) {
	    if ( uTB_CAL_NO_BAS != null ) {

	          return uTB_CAL_NO_BAS;
	          
	    } else {

	        //uTB_CAL_NO_BAS.sPrefix = sPrefix;
	        //uTB_CAL_NO_BAS.sNxxNo  = sNxxNo;
	        //uTB_CAL_NO_BAS.sLineNo = "$$$$"; // 첫번째 조회시 데이터가 없을 때 "$$$$" 값으로 다시 조회함.
	        sLineNo = "$$$$";
	        //uTB_CAL_NO_BAS.sCallStartDatetime = sWorkCallStartDatetime;


	 		//wlssLookup_TB_CAL_NO_BAS(uTB_CAL_NO_BAS);
	        TbCalNoBasInfo temp_uTB_CAL_NO_BAS = referenceLoadBean.getTbCalNoBasInfo(sPrefix, sNxxNo, sLineNo, sWorkCallStartDatetime);
	  		//TbCalNoBasInfo temp_uTB_CAL_NO_BAS = referenceInfoDao.getTbCalNoBasInfo(sPrefix, sNxxNo, sLineNo, sWorkCallStartDatetime);


	        //if ( uTB_CAL_NO_BAS.lRowCnt > 0 ) {
	        if ( temp_uTB_CAL_NO_BAS != null  ) {

	               return temp_uTB_CAL_NO_BAS;

	        }
	    }

	   return null;

	}
	
	public TbFeatrBasInfo get_TB_FEATR_BAS(String sFeatureFormat, String sFeatureType, String sServiceType, String sWorkCallStartDatetime) {
			

		   //TB_FEATR_BAS_Intl uTB_FEATR_BAS = udrCreate(TB_FEATR_BAS_Intl);

		    //uTB_FEATR_BAS.sFeatureFormatId     = sFeatureFormat;
		    //uTB_FEATR_BAS.sFormatType       = sFeatureType;
		    //uTB_FEATR_BAS.sFeatureNo        = sServiceType;
		    //uTB_FEATR_BAS.sCallStartDatetime = sWorkCallStartDatetime;

		    //wlssLookup_TB_FEATR_BAS(uTB_FEATR_BAS);
			//	TbFeatrBasInfo uTB_FEATR_BAS = referenceInfoDao.getTbFeatrBasInfo(sFeatureFormat, sFeatureType, sServiceType, sWorkCallStartDatetime);
				TbFeatrBasInfo uTB_FEATR_BAS = referenceLoadBean.getTbFeatrBasInfo(sFeatureFormat, sFeatureType, sServiceType, sWorkCallStartDatetime);

		    //if (uTB_FEATR_BAS.lRowCnt > 0) {
		    if (uTB_FEATR_BAS != null ) {

		       return uTB_FEATR_BAS;
		    }

		   return null;
	}
	
	public TbSvcPrvrBasInfo get_TB_SVC_PRVR_BAS(String sp_code){
		

	    //TB_SVC_PRVR_BAS_Intl uTB_SVC_PRVR_BAS = udrCreate(TB_SVC_PRVR_BAS_Intl);
	    //uTB_SVC_PRVR_BAS.sIspId = sp_code;
	    //wlssLookup_TB_SVC_PRVR_BAS(uTB_SVC_PRVR_BAS);
	    TbSvcPrvrBasInfo uTB_SVC_PRVR_BAS = referenceInfoDao.getTbSvcPrvrBasInfo(sp_code);

	    //if ( uTB_SVC_PRVR_BAS.lRowCnt > 0 ) {
	    if ( uTB_SVC_PRVR_BAS != null ) {

	          return uTB_SVC_PRVR_BAS;
	    }

	   return null;

	}


	public TbNoMovBizrBasInfo get_TB_NO_MOV_BIZR_BAS(String sNpCd, String CallStartDatetime, String sSpIndi, String sFlag, String sCalledSvcNo) {
		


	    //TB_NO_MOV_BIZR_BAS_Intl  uTB_NO_MOV_BIZR_BAS  = udrCreate(TB_NO_MOV_BIZR_BAS_Intl);
	    //uTB_NO_MOV_BIZR_BAS.sNpCd = sNpCd;
	    //uTB_NO_MOV_BIZR_BAS.sCallStartDatetime = CallStartDatetime;
	    //wlssLookup_TB_NO_MOV_BIZR_BAS(uTB_NO_MOV_BIZR_BAS);
	    //TbNoMovBizrBasInfo uTB_NO_MOV_BIZR_BAS = referenceInfoDao.getTbNoMovBizrBasinfo(sNpCd, CallStartDatetime);
	    TbNoMovBizrBasInfo uTB_NO_MOV_BIZR_BAS = referenceLoadBean.getTbNoMovBizrBasinfo(sNpCd, CallStartDatetime);
	    
	    


	    //if (uTB_NO_MOV_BIZR_BAS.lRowCnt > 0) {
	    if (uTB_NO_MOV_BIZR_BAS != null ) {
	    
	        if (sSpIndi != "Y") {
	        
	            if (sFlag == "OTR") {

	                //if ((sNpCd == "03") && comnSubstring(sCalledSvcNo,0,2) != "02") {
	                if ((sNpCd == "03") && CommonUtils.comnSubstring(sCalledSvcNo, 0,2) != "02") {

	                    //uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd = "WPNSEL";
	                    uTB_NO_MOV_BIZR_BAS.setsSettlFeatureCd("WPNSEL");
	                }

	                else {

	                    //uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSubSettlFeatureId;
	                    uTB_NO_MOV_BIZR_BAS.setsSettlFeatureCd(uTB_NO_MOV_BIZR_BAS.getsSubSettlFeatureId());

	                }

	            }

	            else {

	                if ((sNpCd == "03") && CommonUtils.comnSubstring(sCalledSvcNo, 0,2) != "02") {

	                    //uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd = "PNSEL";
	                    uTB_NO_MOV_BIZR_BAS.setsSettlFeatureCd("PNSEL");

	                }

	                else {

	                    //uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd = uTB_NO_MOV_BIZR_BAS.sSettlFeatureCd;
	                    uTB_NO_MOV_BIZR_BAS.setsSettlFeatureCd(uTB_NO_MOV_BIZR_BAS.getsSubSettlFeatureId());

	                }

	            }

	        }

	        return uTB_NO_MOV_BIZR_BAS;

	    }

	    return null;
	}
	
	public ArrayList<TbErrMappgBasInfoKey> listAdd(ArrayList<TbErrMappgBasInfoKey> ltErrorCd, String errorDescription) {
		ArrayList<TbErrMappgBasInfoKey> tmpArray = referenceLoadBean.listAdd(ltErrorCd, errorDescription);
		return tmpArray;
	}
	
	public ArrayList<TbErrMappgBasInfoKey> sort(ArrayList<TbErrMappgBasInfoKey> ltErrorCd) {
		Collections.sort(ltErrorCd);
		return ltErrorCd;
	}
}

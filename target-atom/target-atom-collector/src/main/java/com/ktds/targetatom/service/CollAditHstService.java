package com.ktds.targetatom.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ktds.targetatom.dao.AuditInfoDao;
import com.ktds.targetatom.vo.AuditInfo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class CollAditHstService {
	
	@Autowired
	AuditInfoDao dao;
	
	@Value("${module.type}")
	private String moduleType;
	
	private final Logger log = LogManager.getLogger(this.getClass());
	public void insertFirstCollAditHst(Exchange exchange, String pModuleNm, long pUpAditSeq, String pFileNm, String pTrmDirNm, String pOrgnFileNm, String pSttus, String pFileType) throws Exception {
		
//		CollAditHstVO inAditVo = new CollAditHstVO();
		AuditInfo auditVo = new AuditInfo();

//		inAditVo.setModuleNm(pModuleNm);
//		inAditVo.setUpAditSeq(pUpAditSeq);
//		inAditVo.setFileNm(pFileNm);
//		inAditVo.setTrmDirNm(pTrmDirNm.replaceAll("\\\\", "/"));
//		inAditVo.setOrgnFileNm(pOrgnFileNm);
//		inAditVo.setSttus(pSttus);
//		inAditVo.setFileType(pFileType);
		
		auditVo.setFirstCretTrtrId("COL");
		auditVo.setLastChgTrtrId("MZN");
		auditVo.setFirstCretPgmId("MZN");
		auditVo.setLastChgPgmId(pModuleNm);
		auditVo.setUpAditSeq(pUpAditSeq);
		auditVo.setFileNm(pFileNm);
		auditVo.setTrmDirNm(pTrmDirNm.replaceAll("\\\\", "/"));
		auditVo.setOrgnFileNm(pOrgnFileNm);
		auditVo.setSttus(pSttus);
		auditVo.setModuleNm(pModuleNm);
		auditVo.setFileType(pFileType);
		
//		collAditHstDao.insertFirstCollAditHst(inAditVo);
		Long result = dao.insert(auditVo);  // retured new adit_cret_seq 
		
//		if (inAditVo.getNewAditCretSeq() > 0) {
//			exchange.getIn().setHeader("aditSeq", inAditVo.getNewAditCretSeq());
//		}
		
		if (result > 0) {
			exchange.getIn().setHeader("aditSeq", result);
		}

		return;
	}
	
	public void uptCollComplete(Exchange exchange) throws Exception {
		//System.out.println("toFilePath : " + exchange.getIn().getHeader("toFilePath") + " rawFilePath : " + exchange.getIn().getHeader("rawFilePath") + " aditSeq : " + exchange.getIn().getHeader("aditSeq"));
		
		// 최초 수집데이터 상태 update
//		CollAditHstVO upAditVo = new CollAditHstVO();
		AuditInfo auditVo = new AuditInfo();
		
		Message inMsg = exchange.getIn();
		log.info("exchange.Header.toFilePath : {}", inMsg.getHeader("toFilePath").toString());
		log.info("exchange.Body : {}", inMsg.getBody().toString());
//		upAditVo.setAditCretSeq((long) inMsg.getHeader("aditSeq"));
//		upAditVo.setFileNm((String) inMsg.getHeader("CamelFileNameOnly"));
//		upAditVo.setSttus("CO");
		
		auditVo.setAditCretSeq((long) inMsg.getHeader("aditSeq"));
		auditVo.setSttus("CO");
		auditVo.setLastChgTrtrId("TEST");
		auditVo.setLastChgPgmId("MZN");
		
//		collAditHstDao.updateCollAditSttus(upAditVo);
		int result = dao.updateStatus(auditVo);

		// processor에서 처리할 CDR파일정보 insert
		AuditInfo inAditVo = new AuditInfo();
		inAditVo.setFirstCretTrtrId("COL");
		inAditVo.setLastChgTrtrId("MZN");
		inAditVo.setFirstCretPgmId("MZN");
		inAditVo.setLastChgPgmId("COL_KR");
		inAditVo.setFileNm((String)inMsg.getHeader("CamelFileName"));
		
		log.info("moduleType is {} ", moduleType);
		if (moduleType.equals("amq")) {
			log.info("queName is {} ", inMsg.getHeader("queName"));
			inAditVo.setTrmDirNm((String)inMsg.getHeader("queName"));
		}
		else {
			log.debug("queName is {} ", inMsg.getHeader("toFilePath").toString());
			inAditVo.setTrmDirNm((String)inMsg.getHeader("toFilePath"));
		}
		inAditVo.setOrgnFileNm((String)inMsg.getHeader("orgnFileName"));
		inAditVo.setSttus("RD");
		inAditVo.setModuleNm("COL_KR");
		inAditVo.setFileType("PRC");
		inAditVo.setAditCretSeq((long) inMsg.getHeader("aditSeq"));
		
//		collAditHstDao.insertCollAditHst(inAditVo);
		result = dao.insertDownStreamAuditInfo(inAditVo);
		
		// arch 디렉토리 파일생성정보 insert
		inAditVo.setTrmDirNm((String)inMsg.getHeader("rawFilePath"));
		inAditVo.setSttus("CO");
		inAditVo.setFileType("COL_RAW");
		
//		collAditHstDao.insertCollAditHst(inAditVo);
		result = dao.insertDownStreamAuditInfo(inAditVo);
//System.out.println("collector 종료시간==============> ["+getTime()+"]");
		return;
	}
	
	public long getCollAditFileCnt(String collFileNm, String moduleNm) throws Exception {
		
//		CollAditHstVO vo = new CollAditHstVO();
		AuditInfo vo = new AuditInfo();
		
		vo.setOrgnFileNm(collFileNm);  // collFileNm
		vo.setModuleNm(moduleNm);
		
		return dao.getAditDupFileCnt(vo);
	}
	
	
	public String getTime() {
	    SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
	    return f.format(new Date());
	  } // getTime
	
	
	/* knj test용 */
	public void initAdit() throws Exception {		
		int result = dao.deleteAll();  
		return;
	}	
}

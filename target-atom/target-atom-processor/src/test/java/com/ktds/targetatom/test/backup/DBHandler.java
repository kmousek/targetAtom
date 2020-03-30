package com.ktds.targetatom.test.backup;
/*package com.ktds.targetatom.bean;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ktds.targetatom.router.InitialRouter;

import com.ktds.targetatom.vo.AuditInfo;
import com.ktds.targetatom.vo.CamelRouteFileInfo;
import com.ktds.targetatom.vo.CdrReferenceInfo;
import com.ktds.targetatom.common.RouteLoader;
import com.ktds.targetatom.dao.AuditInfoDao;
import com.ktds.targetatom.dao.DbProcessor;
import com.ktds.targetatom.dao.ReferenceInfoDao;

public class DBHandler {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ReferenceInfoDao referenceDao;
	
	@Autowired
	private AuditInfoDao auditDao;
	
	@Value("${processor.delay}") 
	private int processor_delay;
	
	@Value("${processor.instance.id")
	private String instanceId;
	
	public void getReferenceInfo(CamelContext camelContext) throws Exception {
		Map<String, String> target_directory = new HashMap<String, String>();
		List<CdrReferenceInfo> getCdrReferenceInfoList = referenceDao.getCdrReferenceInfoList(instanceId);
		
		for (CdrReferenceInfo row : getCdrReferenceInfoList) {
			String sourceDirectory = "file:" + row.getCdrFileColecDirNm() + "?noop=false&charset=iso-8859-1";
			target_directory.put(row.getCdrFileNmngRuleSbst().substring(0, 6), row.getTrmDirNm());
			
			camelContext.addRoutes(new InitialRouter(camelContext, sourceDirectory, "direct:source"));
		}
		
		DbProcessor.getInstance(camelContext).setToFilePath(target_directory);	
	}
	
	public void selectTarget(Exchange exchange) throws Exception {
		String target_neid = exchange.getIn().getHeader("CamelFileName").toString().substring(0, 6);
		exchange.getIn().setHeader("targetNeId", target_neid);
		
		File file = exchange.getIn().getBody(File.class);
		
		String fileName = file.getName();
		String filePath = file.getParent().replaceFirst("C:", "");
		
		AuditInfo result = auditDao.findByFileName(fileName, filePath);
		
		if (result == null) {
			Thread.sleep(processor_delay);
			result = auditDao.findByFileName(fileName, filePath);
			
			if (result == null) {
				exchange.getIn().setHeader("file.existAudit",  "N");
				log.warn("{} File is not in Audit Table....", fileName);
				return;
			}
		}
		
		exchange.getIn().setHeader("file.existAudit", "Y");
		exchange.getIn().setHeader("file.identifier", result.getAditCretSeq());
		
		Map<String, String> targetDirectory = DbProcessor.getInstance(exchange.getContext()).getToFilePath();
		log.info("Target Directory ::: {}", targetDirectory.get((String)target_neid));
		
		exchange.getIn().setHeader("file.targetDirectory", targetDirectory.get((String)target_neid));

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
		
		auditDao.update(aditCretSeq, sttus, lastChgTrtrId, lastChgPgmId, rcrdCnt, errCnt, prcCnt);
	}
	
	public void insertAudit(Exchange exhange, String first_cret_pgm_id, String fileNm, String sttus, String moduleNm, long rcrdCnt,
			                  long errCnt, long prcCnt) throws Exception {
		
		long upAditSeq = Long.parseLong(exhange.getIn().getHeader("file.identifier").toString());
		String trmDirNm = exhange.getIn().getHeader("file.targetDirectory").toString();
		String orgnFileNm = exhange.getIn().getHeader("CamelFileName").toString();
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
	}
	
	public String sendToDistributor(String trtmDirNm, String fileNm) {
		return "file:" + trtmDirNm + "?fileName=" + fileNm;
	}
}*/

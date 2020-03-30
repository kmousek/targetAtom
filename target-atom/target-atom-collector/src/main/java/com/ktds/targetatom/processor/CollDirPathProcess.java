package com.ktds.targetatom.processor;

import java.io.File;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
//import java.util.Properties;

//import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
//import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ktds.targetatom.service.CollAditHstService;
import com.ktds.targetatom.service.CollSendBaseBean;
import com.ktds.targetatom.vo.CdrBaseInfo;
import com.ktds.targetatom.service.WlneFilenameCheck;


public class CollDirPathProcess implements Processor {
	
	@Autowired
	WlneFilenameCheck wlneFilenameCheck;
	
	@Autowired
	CollAditHstService collAditHstService;
	
	@Autowired
	CollSendBaseBean collSendBaseBean;
	
	@Value("${module.type}")
	private String moduleType;
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);
		String sPrcDirNm="", sArchDirNm="", sErrDirNm="";
		String sCollFileNm = "", sFileNmngRuleSbst="", sFileRename="";
		int iFileNmLen=0;
		boolean bFileNmRule=false, bChkResult=false;
		
		//CamelContext camelContext = exchange.getContext();
        //ProducerTemplate producer = camelContext.createProducerTemplate();
		
		WlneFilenameCheck wlneFilenameCheck = new WlneFilenameCheck();
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
		Date currentTime = new Date ();
		String sysDate = mSimpleDateFormat.format (currentTime);

		if (file!=null) {
			String sDirFullNm = file.getPath().replaceAll("\\\\", "/");
			String sDirPath = sDirFullNm.substring(0, sDirFullNm.lastIndexOf("/")).replaceAll("D:", "");

			List<CdrBaseInfo> collDirList = collSendBaseBean.getSendBaseList();

			sCollFileNm = exchange.getIn().getHeader("CamelFileNameOnly").toString();

			for (int idx=0; idx < collDirList.size(); idx++) {
				CdrBaseInfo collDirVo = collDirList.get(idx);
				log.debug("collDirVo["+idx+"] : {} ", collDirVo);
				log.debug("sDirPath  : {} ", sDirPath);
				log.debug("collDirVo.getCdrFileColecDirNm()  : {} ", collDirVo.getCdrFileColecDirNm());
				log.debug("collDirVo.getTrmDirNm()  : {}", collDirVo.getTrmDirNm());
				
				// ���۵��丮 set
				if (collDirVo.getDssId().startsWith("PRC") && collDirVo.getCdrFileColecDirNm().equals(sDirPath)) {
					sPrcDirNm = collDirVo.getTrmDirNm();
					log.debug("[knj]if (collDirVo.getDssId().startsWith(\"PRC\") && collDirVo.getCdrFileColecDirNm().equals(sDirPath)) {");
					log.debug("sPrcDirNm : {} ", sPrcDirNm);
					log.debug("moduleType : {} ", moduleType);
					log.info("trm que nm is {} ", collDirVo.getTrmQueNm());
					if (moduleType.equals("amq")) {
						log.info("collDirVo.getTrmQueNm() is {} ", collDirVo.getTrmQueNm());
						exchange.getIn().setHeader("queName", collDirVo.getTrmQueNm());
					}
				}
				else if (collDirVo.getDssId().equals("COL_ARCH") && collDirVo.getCdrFileColecDirNm().equals(sDirPath)) {
					sArchDirNm = collDirVo.getTrmDirNm().replace("yyyymmdd", sysDate);
				}
				else if (collDirVo.getDssId().equals("COL_ERR") && collDirVo.getCdrFileColecDirNm().equals(sDirPath)) {
					sErrDirNm = collDirVo.getTrmDirNm().replace("yyyymmdd", sysDate);
				}
				
				if (!bFileNmRule && collDirVo.getCdrFileColecDirNm().equals(sDirPath)) {
					sFileNmngRuleSbst = collDirVo.getCdrFileNmngRuleSbst();
					iFileNmLen = collDirVo.getFileNmLen();
					bFileNmRule = true;
				}
			}
			
			exchange.getIn().setHeader("fileChkResult", "CHK_SUCCESS");
			exchange.getIn().setHeader("orgnFileName", exchange.getIn().getHeader(Exchange.FILE_NAME, String.class));
			
			//exchange.getIn().setHeader("JMSXGroupID", "test1");
			//exchange.getIn().setHeader("JMSXGroupSeq", 3);
			
			// ���ϸ� �ߺ� üũ
			long retval = collAditHstService.getCollAditFileCnt(sCollFileNm, "COL_KR");
			
			if (retval > 0) {
				if ("Y".equals(collDirList.get(0).getDuplFileColecYn())) {
					//logger.info("sCollFileNm : "+exchange.getIn().getHeader(Exchange.FILE_NAME, String.class));
					//logger.info("fileSplitList : "+fileSplitList.toString());
					List<String> fileSplitList = Arrays.asList(sCollFileNm.split("\\."));
					sFileRename = fileSplitList.get(0) + "_S" + retval + "." + fileSplitList.get(fileSplitList.size()-1);
					exchange.getIn().setHeader(Exchange.FILE_NAME, sFileRename);
				}
				else {
					//exchange.getIn().setHeader("fileChkResult", "CHK_FAILURE");
				}
			}
			else {
				// CDR
				if (bFileNmRule) {
					bChkResult = wlneFilenameCheck.chkFilenameRule(sCollFileNm, sFileNmngRuleSbst, iFileNmLen);
					
					if (!bChkResult) {
						exchange.getIn().setHeader("fileChkResult", "CHK_FAILURE");
					}
				}
			}
			
			// FIN���� Move
			//logger.info("FILE_PARENT : "+exchange.getIn().getHeader(Exchange.FILE_PARENT, String.class));
			//logger.info("DAT_FILE : "+exchange.getIn().getHeader(Exchange.FILE_NAME_ONLY, String.class));
			String sFinFilePath = exchange.getIn().getHeader(Exchange.FILE_PARENT, String.class);
			String sFinFileName = exchange.getIn().getHeader(Exchange.FILE_NAME_ONLY, String.class);
			
			
			if ("Y".equals(collDirList.get(0).getFnsFileCretYn())) {
				if ("1".equals(collDirList.get(0).getFnsFileDivCd())) {
					sFinFileName = sFinFileName.replace(".DAT", ".FIN");
				}
				else if ("2".equals(collDirList.get(0).getFnsFileDivCd())) {
					sFinFileName = sFinFileName.replace(".DAT", ".DAT.FIN");
				}
			}
			
			//logger.info("FIN_FILENAME : "+sFinFileName);
			
			exchange.getIn().setHeader("finFileProc", "moveArch");
			//exchange.getIn().setHeader("finFileCollPath", sFinFilePath);
			//exchange.getIn().setHeader("finFileName", sFinFileName);
			//exchange.getIn().setHeader("finFileTrmPath", sArchDirNm);
			exchange.setProperty("finFileCollPath", sFinFilePath);
			exchange.setProperty("finFileName", sFinFileName);
			exchange.setProperty("finFileTrmPath", sArchDirNm);

		}

		exchange.getIn().setHeader("toFilePath", sPrcDirNm);
		exchange.getIn().setHeader("rawFilePath", sArchDirNm);
		exchange.getIn().setHeader("errFilePath", sErrDirNm);
		
		log.debug("sPrcDirNm : {} ", sPrcDirNm);
		log.debug("exchange.toFilePath : {} ", exchange.getIn().getHeader("toFilePath").toString());
	
	}
}
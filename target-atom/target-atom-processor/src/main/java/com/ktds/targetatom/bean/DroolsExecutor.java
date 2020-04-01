package com.ktds.targetatom.bean;

import java.io.File;
import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ktds.targetatom.cdr.common.CdrType;
import com.ktds.targetatom.cdr.common.ConmonCdr;

@Service
public class DroolsExecutor {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${processor.format}")
	private String formatName;

//	@Autowired
//	private KieContainer kieContainer;
//	Logger logger = LoggerFactory.getLogger(DroolsExecutor.class);

	@Autowired
	AuditHandler auditHandler;

	String recordClassName = "";

	@SuppressWarnings("unchecked")
	public void droolsExecution(Exchange ex)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// String sessionId = auditHandler.getKieSession();
		int totalCount = 0, successCount = 0, errorCount = 0;
		// log.debug("KieSession ::: {}", sessionId);
		ArrayList<?> bodyList = (ArrayList<?>) ex.getIn().getBody();
		ArrayList resultList = new ArrayList();
		
		log.debug("bodyList ::: {}", bodyList);

		if (!CollectionUtils.isEmpty(bodyList)) {

			KieServices kieServices = KieServices.Factory.get();
			KieFileSystem kfs = kieServices.newKieFileSystem();

			// File dir = new File("C:/Users/ktds/Desktop/VolTE");
			//File dir = new File("C:/Users/ktds/Desktop/INTCC");
			//D:\kmousek\workspaces\target-atom\target-atom\target-atom\target-atom-processor\src\main\resources\externalDRL\volte
			File dir = new File("src/main/resources/externalDRL/volte");
			
			// File homedir = new File(System.getProperty("user.home"));
			System.out.println("SystemProperty : " + System.getProperty("user.home"));
			// File dir = new File(homedir, "VoLTE");

			// File homedir = new File(System.getProperty("user.dir"));
			// System.out.println("SystemProperty : " + System.getProperty("user.dir"));
			// File dir = new File("/home/targetatom2/VoLTE" + File.separator +
			// "volteEnrich.drl");
			// File dir = new File("/home/kubernetes8002/VoLTE");
			// System.out.println("1dIR : " + dir.getAbsolutePath());
			// System.out.println("dirExist : "+ dir.exists() + "dirExist2 : " +
			// dir0.isDirectory() + "dirExist3 : " + dir0.getAbsolutePath().isEmpty());

			// kfs.write(ResourceFactory.newFileResource(dir.getAbsolutePath()));
			// File dir1 = new File("/home/targetatom2/VoLTE" + File.separator +
			// "volteValidate.drl");
			// kfs.write(ResourceFactory.newFileResource(dir1.getAbsolutePath()));

			if (dir.exists()) {
				File[] files = dir.listFiles();
				// String files[] = dir.list();
				System.out.println("files.length : " + files.length);
				if (files.length > 0) {
					for (File f : files) {
						// for (String f : files) {
						System.out.println("DroolsfilePATH : " + f.getAbsoluteFile());
						kfs.write(ResourceFactory.newFileResource(f.getAbsoluteFile()));
						// System.out.println("DroolsfilePATH : " + f);
						// kfs.write(ResourceFactory.newFileResource(f));
					}
				}
			} else
				System.out.println("File Not Exist");

			// kfs.write(ResourceFactory.newFileResource(new
			// File("C:/Users/ktds/Desktop/VolTE/volteEnrich.drl")));
			// kfs.write(ResourceFactory.newFileResource(new
			// File("C:/Users/ktds/Desktop/VolTE/volteValidate.drl")));
			KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
			KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
			// KieSessionConfiguration conf = SessionConfiguration.();
			KieSession kieSession = kieContainer.newKieSession();

			// KieSession kieSession = kieContainer.newKieSession(sessionId);
			// StatelessKieSession kSession =
			// kieContainer.newStatelessKieSession(sessionId);

			log.warn(">>>>> Drools Start");
			for (Object body : bodyList) {
				recordClassName = body.getClass().getName();
				kieSession.setGlobal("audit", auditHandler);
				kieSession.insert(body);
				kieSession.fireAllRules();
			}
			log.warn(">>>>> Drools End");

			/*
			 * for (Object record : kieSession.getObjects()) { totalCount++ ;
			 * resultList.add(record); VoLTEBody newBody = (VoLTEBody) record; if
			 * (newBody.getsMzErrorCd01() != null) { VoLTEErrorCdr result = new
			 * VoLTEErrorCdr(newBody); resultList.add(result); errorCount++; } else {
			 * VoLTEResultCdr result = new VoLTEResultCdr(newBody); resultList.add(result);
			 * successCount++; } }
			 */

			for (Object record : kieSession.getObjects()) {
				if (record.getClass().getName().equals(recordClassName)) {
					totalCount++;
				} else {
					Class<? extends ConmonCdr> theClass = (Class<? extends ConmonCdr>) Class
							.forName(record.getClass().getName());
					if (theClass.cast(record).getCdrType() == CdrType.OUTPUT) {
						successCount++;
					} else {
						errorCount++;
					}
				}
				resultList.add(record);
			}

			log.warn(">>>>> Result Mapping End");

			kieSession.dispose();
		}

		/*
		 * for (Object record : resultList) { log.warn("Message ::: {}",
		 * record.toString()); }
		 */

		log.warn("Total Count :::: {}, Success Count ::: {}, Error Count ::: {}", totalCount, successCount, errorCount);

		ex.getIn().setHeader("file.total.count", totalCount);

		ex.getIn().setHeader("file.success.count", successCount);

		ex.getIn().setHeader("file.error.count", errorCount);
		ex.getIn().setBody(resultList);

	}

	public void recordTrim(Exchange ex, String className) {
		ArrayList<?> bodyList = (ArrayList<?>) ex.getIn().getBody();
		ArrayList resultList = new ArrayList();

		log.debug("Body size ::: {}", resultList.size());
		
		if (!CollectionUtils.isEmpty(bodyList)) {
			for (Object body : bodyList) {
				log.debug("body.getClass().getSimpleName() ::: {}", body.getClass().getSimpleName());
				if (body.getClass().getSimpleName().equals(className))
					resultList.add(body);
			}
		}

		log.debug("Body size ::: {}", resultList.size());
		ex.getIn().setBody(resultList);
	}
}

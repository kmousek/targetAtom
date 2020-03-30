package com.ktds.targetatom.test.backup;
/*package com.ktds.targetatom.bean;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.flatpack.DataSetList;
import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
//import org.kie.api.runtime.rule.QueryResults;
//import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ktds.targetatom.drools.CdrInfo;
import com.ktds.targetatom.drools.ValidationResult;

@Repository
public class CdrProcessor {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private KieContainer kieContainer;
	Logger logger = LoggerFactory.getLogger(CdrProcessor.class);

	public ValidationResult validate(Exchange ex) {

		DataSetList list = ex.getIn().getBody(DataSetList.class);
		log.info("marshal result count={}", list.size());
		log.info("marshal error count={}", list.getErrorCount());
		log.info("list={}", list);
		for(Object error : list.getErrors()) {
			log.info("marshal error={}", error);
		}
		log.info("record={}", list.getRecord());
		ValidationResult result = new ValidationResult();
//		List<Map<String, Object>> outputList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> errorList = new ArrayList<Map<String, Object>>();
		System.setProperty("drools.dateformat", "yyyyMMddHHmmss");
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
 		kbuilder.add(new ClassPathResource("spring/rules/volte/volte.drl", getClass()), ResourceType.DRL);	
		if(kbuilder.hasErrors()) {
			if(kbuilder.getErrors().size() > 0) {
				for(KnowledgeBuilderError kerror : kbuilder.getErrors()) {
					log.error("Errors in parsing the rule : \n{}", kerror);
				}
			}
		}
		KieBase kbase = kbuilder.newKieBase();
		KieSession kieSession = kbase.newKieSession();

		for (int i = 0; i < list.size(); i++) {

			CdrInfo cdrInfo = new CdrInfo(list.get(i));
			if(log.isTraceEnabled()) {
				log.trace("cdrInfo[{}]={}", i, cdrInfo);
			}
			log.debug("cdrInfo[{}].service_download_data_octects={}", i, cdrInfo.getInput().get("service_download_data_octects"));
			// Skip header(임시)
//			if(TypeUtils.toInt(cdrInfo.getInput().get("record_type")) == 1) {
//				log.debug("record[{}] is header", i);
//				continue;
//			}
//			if(TypeUtils.toInt(cdrInfo.getInput().get("service_download_data_octects")) > 0) {
//				log.info("normal cdr");
//			} else {
//				log.info("error cdr");
//			}
			kieSession.insert(cdrInfo);
			kieSession.fireAllRules();
//			kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("VOLTE"), 1);
			
			if(!cdrInfo.getOutput().isEmpty()) {
				log.info("extract output={}", cdrInfo.getError());
				result.getOutputList().add(cdrInfo.getOutput());
			} else if(!cdrInfo.getError().isEmpty()) {
				if(log.isTraceEnabled()) {
					log.trace("extract output={}", cdrInfo.getError());
				}
				result.getErrorList().add(cdrInfo.getError());
			} else {
				log.info("skip row={}", cdrInfo.getInput());
			}
			
//			kieSession.dispose();			
		} 

		return result;
	}
//	
//	@SuppressWarnings("unchecked")
//	public void validateParsing(Exchange exchange) throws Exception {
//    	Collection<KRBody> fixed = new ArrayList<KRBody>();
//    	if(exchange.getIn().getBody().getClass().isAssignableFrom(KRBody.class)) {
//    		fixed.add((KRBody) exchange.getIn().getBody());
//    	} else {
//    		fixed = (Collection<KRBody>) exchange.getIn().getBody();
//    	}
//    	
//    	
//    	if(fixed.size() > 0) {
//    		exchange.getIn().setHeader("parsing.size", fixed.size());
//    	} else {
//    		throw new Exception();
//    	}
//    }	
//
//	private static void prepareKRResultFormat(KRBody fixed, KRResultFormat results) {
//
//		results.setsSaId(fixed.getsChrgId());
//		results.setsCallStartDt(fixed.getsChrgDateTime());
//		results.setsCallEndDt(fixed.getsValidDateTime());
//		results.setsPmntNm(fixed.getsPmntNm()) ;
//		results.setsPrice(fixed.getlChrgAmt()) ;
//		results.setsInvAmount(fixed.getlChrgAmt01()) ;
//		results.setsDcAmount(fixed.getlChrgAmt02()) ;
//		results.setsSerialNo(fixed.getsBillingCd());
//		results.setsPvsId(fixed.getsChrgNo());
//		results.setsPattern(fixed.getsPattern());
//		results.setsRequestBillSeqNo(fixed.getsOrderNo());
//	
//	}
	
}
*/
package com.ktds.targetatom.test.backup;
/*package com.ktds.targetatom.bean;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.camel.Exchange;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.rule.QueryResults;
//import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.targetatom.ktds.dao.kr.input.KRBody;
import com.targetatom.ktds.dao.kr.output.KRErrorFormat;
import com.targetatom.ktds.dao.kr.output.KRResultFormat;

public class ProcFormat {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static Collection<KRErrorFormat> errored;
	
	@Autowired
	private KieContainer kieContainer;
	Logger logger = LoggerFactory.getLogger(ProcFormat.class);

//	@Autowired
//	public ProcFormat(KieContainer kieContainer) {
//		this.kieContainer = kieContainer;
//	}
	
	@SuppressWarnings("unchecked")
	public Collection<?> processFormat(Exchange ex, char ch){
		
		if(errored == null) errored = new ArrayList<KRErrorFormat>();
		
        if ( ch == 'E') {
        	
        	Collection<KRErrorFormat> err = new ArrayList<KRErrorFormat>();
        	err.addAll(errored);
        	errored.clear();
        	
        	return err;
        	
        } else {
        	
		int success_count = 0, total_count = 0, error_count = 0;
		
		Collection<KRBody> fixed = new ArrayList<KRBody>();
		Collection<KRResultFormat> results = new ArrayList<KRResultFormat>();

		
		if(ex.getIn().getBody() != null){
			if(ex.getIn().getBody().getClass().isAssignableFrom(KRBody.class)){
				
				fixed.add((KRBody) ex.getIn().getBody());
			}else{
				
				fixed = (Collection<KRBody>) ex.getIn().getBody();
			}
		}
		
   		if(!CollectionUtils.isEmpty(fixed)){
			
   			System.setProperty("drools.dateformat", "yyyyMMddHHmmss");
			
			KieSession kieSession = kieContainer.newKieSession("rulesSession");
   			
			for (KRBody krbody : fixed) {
				total_count++;
				KRResultFormat KRResultFormat = new KRResultFormat();
				
				krbody.setsFlag(false);
				
				
				kieSession.insert(krbody);
				
				//kieSession.getAgenda().getAgendaGroup("KR").setFocus();
				//kieSession.fireAllRules(1);
				kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("KR"), 1);
				
				if (krbody.issFlag()) {
					
					continue;
					
				/*	QueryResults sessionERR = kieSession.getQueryResults("getErrorObject");
					
					for (QueryResultsRow row : sessionERR) {
						KRErrorFormat insertErr = (KRErrorFormat) row.get("$insertedErrorObject");
					//	System.out.println("#########print getObjects ===> " + insertErr.toString() );
						errored.add(insertErr);
						error_count++;
					} 
	
			    } else {
			    	
					prepareKRResultFormat(krbody, KRResultFormat);
			    	results.add(KRResultFormat);
			    	success_count++;	
				
			    }
			}
			
			kieSession.getObjects().forEach(object -> {
				if (object instanceof KRErrorFormat) {
					KRErrorFormat insertErr = (KRErrorFormat) object;
						    				
					  errored.add(insertErr);

				}
			});
			
			error_count=errored.size();
			kieSession.dispose();			
		} 

		logger.info("file total_count ::: " + total_count);
		ex.getIn().setHeader("file.total.count", total_count);
		
		logger.info("file success_count ::: " + success_count);
		ex.getIn().setHeader("file.success.count", success_count);
		
		logger.info("file error_count ::: " + error_count);
		ex.getIn().setHeader("file.error.count", error_count);
		
		return results;
        }   
	}
	
	@SuppressWarnings("unchecked")
	public void validateParsing(Exchange exchange) throws Exception {
    	Collection<KRBody> fixed = new ArrayList<KRBody>();
    	if(exchange.getIn().getBody().getClass().isAssignableFrom(KRBody.class)) {
    		fixed.add((KRBody) exchange.getIn().getBody());
    	} else {
    		fixed = (Collection<KRBody>) exchange.getIn().getBody();
    	}
    	
    	
    	if(fixed.size() > 0) {
    		exchange.getIn().setHeader("parsing.size", fixed.size());
    	} else {
    		throw new Exception();
    	}
    }	

	private static void prepareKRResultFormat(KRBody fixed, KRResultFormat results) {

		results.setsSaId(fixed.getsChrgId());
		results.setsCallStartDt(fixed.getsChrgDateTime());
		results.setsCallEndDt(fixed.getsValidDateTime());
		results.setsPmntNm(fixed.getsPmntNm()) ;
		results.setsPrice(fixed.getlChrgAmt()) ;
		results.setsInvAmount(fixed.getlChrgAmt01()) ;
		results.setsDcAmount(fixed.getlChrgAmt02()) ;
		results.setsSerialNo(fixed.getsBillingCd());
		results.setsPvsId(fixed.getsChrgNo());
		results.setsPattern(fixed.getsPattern());
		results.setsRequestBillSeqNo(fixed.getsOrderNo());
	
	}
	
}*/

package com.ktds.targetatom.test.backup;
/*package com.ktds.targetatom.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.camel.Exchange;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.rule.QueryResults;
//import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.ktds.targetatom.service.ProfReferenceInfoService;
import com.ktds.targetatom.vo.CdrProfReferenceInfo;
import com.targetatom.ktds.dao.volte.input.VoLTEBody;
import com.targetatom.ktds.dao.volte.output.VoLTEErrorFormat;
import com.targetatom.ktds.dao.volte.output.VoLTEResultFormat;

public class ProcFormat_testVoLTE {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static Collection<VoLTEErrorFormat> errored;
	
	@Autowired
	private KieContainer kieContainer;
	Logger logger = LoggerFactory.getLogger(ProcFormat_testVoLTE.class);

	@Autowired
	ProfReferenceInfoService profReferenceInfoService;
	
	
	@SuppressWarnings("unchecked")
	public Collection<?> procFormat_testVoLTE(Exchange ex, char ch){
		
		if(errored == null) errored = new ArrayList<VoLTEErrorFormat>();
		
        if ( ch == 'E') {
        	
        	Collection<VoLTEErrorFormat> err = new ArrayList<VoLTEErrorFormat>();
        	err.addAll(errored);
        	errored.clear();
        	
        	return err;
        	
        } else {
        	
		int success_count = 0, total_count = 0, error_count = 0;
		
		Collection<VoLTEBody> fixed = new ArrayList<VoLTEBody>();
		Collection<VoLTEResultFormat> results = new ArrayList<VoLTEResultFormat>();

		
		if(ex.getIn().getBody() != null){
			if(ex.getIn().getBody().getClass().isAssignableFrom(VoLTEBody.class)){
				
				fixed.add((VoLTEBody) ex.getIn().getBody());
			}else{
				
				fixed = (Collection<VoLTEBody>) ex.getIn().getBody();
			}
		}
		
   		if(!CollectionUtils.isEmpty(fixed)){
			
   			System.setProperty("drools.dateformat", "yyyyMMddHHmmss");
			
			KieSession kieSession = kieContainer.newKieSession("rulesSession");
			
			List<CdrProfReferenceInfo> profreferenceInfos = profReferenceInfoService.getCdrProfReferenceInfo();
			
				
				  for (int i = 0; i < profreferenceInfos.size(); i++) {

				 	CdrProfReferenceInfo profreferenceInfo = profreferenceInfos.get(i);
				 	kieSession.insert(profreferenceInfo);
					/*
					  if (!profreferenceInfo.getProfItemId().equals("AAA")) {
					  System.out.println(profreferenceInfo.getProfItemId() +
					  profreferenceInfo.getProfItemDivId() + profreferenceInfo.getProfItemVal()); }
					 
					}
   			
			for (VoLTEBody VoLTEBody : fixed) {
				total_count++;
				VoLTEResultFormat VoLTEResultFormat = new VoLTEResultFormat();
				
				VoLTEBody.setsFlag(false);
				
				kieSession.insert(VoLTEBody);
				kieSession.fireAllRules(1);
				
				
				if (VoLTEBody.issFlag()) {
					
					continue;
					
				/*	QueryResults sessionERR = kieSession.getQueryResults("getErrorObject");
					
					for (QueryResultsRow row : sessionERR) {
						VoLTEErrorFormat insertErr = (VoLTEErrorFormat) row.get("$insertedErrorObject");
					//	System.out.println("#########print getObjects ===> " + insertErr.toString() );
						errored.add(insertErr);
						error_count++;
					} 
	
			    } else {
			    	
					prepareVoLTEResultFormat(VoLTEBody, VoLTEResultFormat);
			    	results.add(VoLTEResultFormat);
			    	success_count++;	
				
			    }
			}
			
			kieSession.getObjects().forEach(object -> {
				if (object instanceof VoLTEErrorFormat) {
					VoLTEErrorFormat insertErr = (VoLTEErrorFormat) object;
						    				
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
    	Collection<VoLTEBody> fixed = new ArrayList<VoLTEBody>();
    	if(exchange.getIn().getBody().getClass().isAssignableFrom(VoLTEBody.class)) {
    		fixed.add((VoLTEBody) exchange.getIn().getBody());
    	} else {
    		fixed = (Collection<VoLTEBody>) exchange.getIn().getBody();
    	}
    	
    	
    	if(fixed.size() > 0) {
    		exchange.getIn().setHeader("parsing.size", fixed.size());
    	} else {
    		throw new Exception();
    	}
    }	

	private static void prepareVoLTEResultFormat(VoLTEBody fixed, VoLTEResultFormat results) {

		results.setCharging_number(fixed.getCharging_number());
		results.setWstype(fixed.getWstype());
		results.setService_start_date(fixed.getService_start_date());
		results.setService_start_time(fixed.getService_start_time());
		results.setService_end_date(fixed.getService_end_date());
		results.setService_end_time(fixed.getService_end_time());
		results.setService_time_duration(fixed.getService_time_duration());
		results.setImsi(fixed.getImsi());
		results.setSubscriber_ip_address(fixed.getSubscriber_ip_address());
		results.setTerminating_inter_operator_identifier(fixed.getTerminating_inter_operator_identifier());
		results.setHd_call(fixed.getHd_call());
		results.setSupplementary_services_code_1(fixed.getSupplementary_services_code_1());
		results.setSupplementary_services_code_2(fixed.getSupplementary_services_code_2());
		results.setService_start_3gpp_user_location_info(fixed.getService_start_3gpp_user_location_info());
		results.setService_category_id(fixed.getService_category_id());
		results.setApplication_release_indicator(fixed.getApplication_release_indicator());
		results.setService_charging_type(fixed.getService_charging_type());
		results.setService_prepaid_airtime_charge(fixed.getService_prepaid_airtime_charge());
		results.setService_prepaid_count_charge(fixed.getService_prepaid_count_charge());
		results.setService_prepaid_information_charge(fixed.getService_prepaid_information_charge());
		results.setService_contents_count(fixed.getService_contents_count());
		results.setRouting_number(fixed.getRouting_number());
		results.setService_called_information(fixed.getService_called_information());
		results.setSgsn_mcc_mnc(fixed.getSgsn_mcc_mnc());
		results.setCalling_asserted_identity(fixed.getCalling_asserted_identity());
		
		
		
		
	
	}
	
}*/

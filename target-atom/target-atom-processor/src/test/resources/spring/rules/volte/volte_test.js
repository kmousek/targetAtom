CdrInfo = com.ktds.targetatom.drools.CdrInfo;
ValidationResult = com.ktds.targetatom.drools.ValidationResult;
CdrUtils = com.ktds.targetatom.util.CdrUtils;
TypeUtils = com.ktds.targetatom.util.TypeUtils;
DataSetList = org.apache.camel.component.flatpack.DataSetList;

var list = exchange.in.getBody(DataSetList.class);
var result = new ValidationResult();

for(var i = 0; i < list.size(); i++) {
	var cdrInfo = new CdrInfo(list[i]);
	if(cdrInfo.input["service_download_data_octects"] > 0) {
		cdrInfo.output = cdrInfo.input;
     	result.outputList.add(cdrInfo);
    } else { 
		cdrInfo.error = cdrInfo.input;
     	result.errorList.add(cdrInfo);
 	}
}

result;

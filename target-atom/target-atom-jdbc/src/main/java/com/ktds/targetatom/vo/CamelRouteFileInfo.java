package com.ktds.targetatom.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelRouteFileInfo extends BaseInfo {
	private String format;
	private String routeFile;
	private String moduleName;
	private String moduleType;
	private String kieSession;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRouteFile() {
		return routeFile;
	}

	public void setRouteFile(String routeFile) {
		this.routeFile = routeFile;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getKieSession() {
		return kieSession;
	}

	public void setKieSession(String kieSession) {
		this.kieSession = kieSession;
	}

	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
}

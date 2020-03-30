package com.ktds.targetatom.vo;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseInfo {

	private Date firstCretDt;
	private String firstCretTrtrId;
	private String firstCretPgmId;

	private Date lastChgDt;
	private String lastChgTrtrId;
	private String lastChgPgmId;

	public BaseInfo() {
		firstCretDt = new Date();
		firstCretTrtrId = "MZN";
		firstCretPgmId = "MZN";
		lastChgDt = new Date();
		lastChgTrtrId = "MZN";
		lastChgPgmId = "MZN";
	}
	public Date getFirstCretDt() {
		return firstCretDt;
	}

	public void setFirstCretDt(Date firstCretDt) {
		this.firstCretDt = firstCretDt;
	}

	public String getFirstCretTrtrId() {
		return firstCretTrtrId;
	}

	public void setFirstCretTrtrId(String firstCretTrtrId) {
		this.firstCretTrtrId = firstCretTrtrId;
	}

	public Date getLastChgDt() {
		return lastChgDt;
	}

	public void setLastChgDt(Date lastChgDt) {
		this.lastChgDt = lastChgDt;
	}

	public String getLastChgTrtrId() {
		return lastChgTrtrId;
	}

	public void setLastChgTrtrId(String lastChgTrtrId) {
		this.lastChgTrtrId = lastChgTrtrId;
	}

	public String getFirstCretPgmId() {
		return firstCretPgmId;
	}

	public void setFirstCretPgmId(String firstCretPgmId) {
		this.firstCretPgmId = firstCretPgmId;
	}

	public String getLastChgPgmId() {
		return lastChgPgmId;
	}

	public void setLastChgPgmId(String lastChgPgmId) {
		this.lastChgPgmId = lastChgPgmId;
	}
	
	@Override
	public String toString() {
			String thisObject = ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
			return thisObject;
	}
}
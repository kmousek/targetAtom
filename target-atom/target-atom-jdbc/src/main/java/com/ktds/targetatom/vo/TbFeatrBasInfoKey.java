package com.ktds.targetatom.vo;

public class TbFeatrBasInfoKey {
	String sFeatureFormatId;
	String sFormatType;
	String sFeatureNo;
	
	public TbFeatrBasInfoKey(String sFeatureFormatId, String sFormatType, String sFeatureNo) {
		super();
		this.sFeatureFormatId = sFeatureFormatId;
		this.sFormatType = sFormatType;
		this.sFeatureNo = sFeatureNo;
	}

	public String getsFeatureFormatId() {
		return sFeatureFormatId;
	}

	public String getsFormatType() {
		return sFormatType;
	}

	public String getsFeatureNo() {
		return sFeatureNo;
	}

	public void setsFeatureFormatId(String sFeatureFormatId) {
		this.sFeatureFormatId = sFeatureFormatId;
	}

	public void setsFormatType(String sFormatType) {
		this.sFormatType = sFormatType;
	}

	public void setsFeatureNo(String sFeatureNo) {
		this.sFeatureNo = sFeatureNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sFeatureFormatId == null) ? 0 : sFeatureFormatId.hashCode());
		result = prime * result + ((sFeatureNo == null) ? 0 : sFeatureNo.hashCode());
		result = prime * result + ((sFormatType == null) ? 0 : sFormatType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbFeatrBasInfoKey other = (TbFeatrBasInfoKey) obj;
		if (sFeatureFormatId == null) {
			if (other.sFeatureFormatId != null)
				return false;
		} else if (!sFeatureFormatId.equals(other.sFeatureFormatId))
			return false;
		if (sFeatureNo == null) {
			if (other.sFeatureNo != null)
				return false;
		} else if (!sFeatureNo.equals(other.sFeatureNo))
			return false;
		if (sFormatType == null) {
			if (other.sFormatType != null)
				return false;
		} else if (!sFormatType.equals(other.sFormatType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbFeatrBasInfoKey [sFeatureFormatId=" + sFeatureFormatId + ", sFormatType=" + sFormatType
				+ ", sFeatureNo=" + sFeatureNo + "]";
	}
	
	
	
}
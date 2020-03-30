package com.ktds.targetatom.vo;

public class TbCalNoBasInfoKey {
	String sPrefix;
	String sNxxNo;
	String sLineNo;
	
	public TbCalNoBasInfoKey(String sPrefix, String sNxxNo, String sLineNo) {
		super();
		this.sPrefix = sPrefix;
		this.sNxxNo = sNxxNo;
		this.sLineNo = sLineNo;
	}

	public String getsPrefix() {
		return sPrefix;
	}

	public String getsNxxNo() {
		return sNxxNo;
	}

	public String getsLineNo() {
		return sLineNo;
	}

	public void setsPrefix(String sPrefix) {
		this.sPrefix = sPrefix;
	}

	public void setsNxxNo(String sNxxNo) {
		this.sNxxNo = sNxxNo;
	}

	public void setsLineNo(String sLineNo) {
		this.sLineNo = sLineNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sLineNo == null) ? 0 : sLineNo.hashCode());
		result = prime * result + ((sNxxNo == null) ? 0 : sNxxNo.hashCode());
		result = prime * result + ((sPrefix == null) ? 0 : sPrefix.hashCode());
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
		TbCalNoBasInfoKey other = (TbCalNoBasInfoKey) obj;
		if (sLineNo == null) {
			if (other.sLineNo != null)
				return false;
		} else if (!sLineNo.equals(other.sLineNo))
			return false;
		if (sNxxNo == null) {
			if (other.sNxxNo != null)
				return false;
		} else if (!sNxxNo.equals(other.sNxxNo))
			return false;
		if (sPrefix == null) {
			if (other.sPrefix != null)
				return false;
		} else if (!sPrefix.equals(other.sPrefix))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbCalNoBasInfoKey [sPrefix=" + sPrefix + ", sNxxNo=" + sNxxNo + ", sLineNo=" + sLineNo + "]";
	}
	
	
	
}

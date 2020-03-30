package com.ktds.targetatom.vo;

public class WlessSpclNoBasInfoKey {
	String sIdfyTelNo;
	String sPfxTelNo;
	String sHhoStNo;
	String sWlessMktDivCd;
	String sEffectDate;
	
	public WlessSpclNoBasInfoKey(String sIdfyTelNo, String sPfxTelNo, String sHhoStNo, String sWlessMktDivCd) {
		super();
		this.sIdfyTelNo = sIdfyTelNo;
		this.sPfxTelNo = sPfxTelNo;
		this.sHhoStNo = sHhoStNo;
		this.sWlessMktDivCd = sWlessMktDivCd;
	}

	public String getsIdfyTelNo() {
		return sIdfyTelNo;
	}

	public String getsPfxTelNo() {
		return sPfxTelNo;
	}

	public String getsHhoStNo() {
		return sHhoStNo;
	}

	public String getsWlessMktDivCd() {
		return sWlessMktDivCd;
	}

	public void setsIdfyTelNo(String sIdfyTelNo) {
		this.sIdfyTelNo = sIdfyTelNo;
	}

	public void setsPfxTelNo(String sPfxTelNo) {
		this.sPfxTelNo = sPfxTelNo;
	}

	public void setsHhoStNo(String sHhoStNo) {
		this.sHhoStNo = sHhoStNo;
	}

	public void setsWlessMktDivCd(String sWlessMktDivCd) {
		this.sWlessMktDivCd = sWlessMktDivCd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sEffectDate == null) ? 0 : sEffectDate.hashCode());
		result = prime * result + ((sHhoStNo == null) ? 0 : sHhoStNo.hashCode());
		result = prime * result + ((sIdfyTelNo == null) ? 0 : sIdfyTelNo.hashCode());
		result = prime * result + ((sPfxTelNo == null) ? 0 : sPfxTelNo.hashCode());
		result = prime * result + ((sWlessMktDivCd == null) ? 0 : sWlessMktDivCd.hashCode());
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
		WlessSpclNoBasInfoKey other = (WlessSpclNoBasInfoKey) obj;
		if (sEffectDate == null) {
			if (other.sEffectDate != null)
				return false;
		} else if (!sEffectDate.equals(other.sEffectDate))
			return false;
		if (sHhoStNo == null) {
			if (other.sHhoStNo != null)
				return false;
		} else if (!sHhoStNo.equals(other.sHhoStNo))
			return false;
		if (sIdfyTelNo == null) {
			if (other.sIdfyTelNo != null)
				return false;
		} else if (!sIdfyTelNo.equals(other.sIdfyTelNo))
			return false;
		if (sPfxTelNo == null) {
			if (other.sPfxTelNo != null)
				return false;
		} else if (!sPfxTelNo.equals(other.sPfxTelNo))
			return false;
		if (sWlessMktDivCd == null) {
			if (other.sWlessMktDivCd != null)
				return false;
		} else if (!sWlessMktDivCd.equals(other.sWlessMktDivCd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WlessSpclNoBasInfoKey [sIdfyTelNo=" + sIdfyTelNo + ", sPfxTelNo=" + sPfxTelNo + ", sHhoStNo=" + sHhoStNo
				+ ", sWlessMktDivCd=" + sWlessMktDivCd + ", sEffectDate=" + sEffectDate + "]";
	}
	
	
}

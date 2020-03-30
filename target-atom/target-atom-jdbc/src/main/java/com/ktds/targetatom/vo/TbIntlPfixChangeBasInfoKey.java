package com.ktds.targetatom.vo;

public class TbIntlPfixChangeBasInfoKey {
	String sCarrierCode;
//	String sNationCode;
	String sCallStartDate;
	String sCallStartTime;
	String sEdpId;
	
	public TbIntlPfixChangeBasInfoKey(String sCarrierCode, String sCallStartDate, String sCallStartTime, String sEdpId) {
		super();
		this.sCarrierCode = sCarrierCode;
//		this.sNationCode = sNationCode;
		this.sCallStartDate = sCallStartDate;
		this.sCallStartTime = sCallStartTime;
		this.sEdpId = sEdpId;
	}

	public String getsCarrierCode() {
		return sCarrierCode;
	}

	public void setsCarrierCode(String sCarrierCode) {
		this.sCarrierCode = sCarrierCode;
	}
	
	public String getsEdpId() {
		return sEdpId;
	}

	public void setsEdpId(String sEdpId) {
		this.sEdpId = sEdpId;
	}

//	public String getsNationCode() {
//		return sNationCode;
//	}
//
//	public void setsNationCode(String sNationCode) {
//		this.sNationCode = sNationCode;
//	}

	public String getsCallStartDate() {
		return sCallStartDate;
	}

	public void setsCallStartDate(String sCallStartDate) {
		this.sCallStartDate = sCallStartDate;
	}

	public String getsCallStartTime() {
		return sCallStartTime;
	}

	public void setsCallStartTime(String sCallStartTime) {
		this.sCallStartTime = sCallStartTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sCarrierCode == null) ? 0 : sCarrierCode.hashCode());
		result = prime * result + ((sEdpId == null) ? 0 : sEdpId.hashCode());
//		result = prime * result + ((sNationCode == null) ? 0 : sNationCode.hashCode());
		result = prime * result + ((sCallStartDate == null) ? 0 : sCallStartDate.hashCode());
		result = prime * result + ((sCallStartTime == null) ? 0 : sCallStartTime.hashCode());
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
		TbIntlPfixChangeBasInfoKey other = (TbIntlPfixChangeBasInfoKey) obj;
		if (sCarrierCode == null) {
			if (other.sCarrierCode != null)
				return false;
		} else if (!sCarrierCode.equals(other.sCarrierCode))
			return false;
		if (sEdpId == null) {
			if (other.sEdpId != null)
				return false;
		} else if (!sEdpId.equals(other.sEdpId))
			return false;
//		if (sNationCode == null) {
//			if (other.sNationCode != null)
//				return false;
//		} else if (!sNationCode.equals(other.sNationCode))
//			return false;
		if (sCallStartDate == null) {
			if (other.sCallStartDate != null)
				return false;
		} else if (!sCallStartDate.equals(other.sCallStartDate))
			return false;
		if (sCallStartTime == null) {
			if (other.sCallStartTime != null)
				return false;
		} else if (!sCallStartTime.equals(other.sCallStartTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbIntlPfixChangeBasInfoKey [sCarrierCode=" + sCarrierCode + ", sCallStartDate=" + sCallStartDate + ", sCallStartTime=" + sCallStartTime + ", sEdpId=" + sEdpId + "]";
	}

}

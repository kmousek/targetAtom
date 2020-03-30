package com.ktds.targetatom.vo;

public class TbProfBasInfoKey {
	String sItemId;
	String sItemIndi;
	
	public TbProfBasInfoKey(String sItemId, String sItemIndi) {
		super();
		this.sItemId = sItemId;
		this.sItemIndi = sItemIndi;
	}
	
	public String getsItemId() {
		return sItemId;
	}
	public String getsItemIndi() {
		return sItemIndi;
	}
	public void setsItemId(String sItemId) {
		this.sItemId = sItemId;
	}
	public void setsItemIndi(String sItemIndi) {
		this.sItemIndi = sItemIndi;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sItemId == null) ? 0 : sItemId.hashCode());
		result = prime * result + ((sItemIndi == null) ? 0 : sItemIndi.hashCode());
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
		TbProfBasInfoKey other = (TbProfBasInfoKey) obj;
		if (sItemId == null) {
			if (other.sItemId != null)
				return false;
		} else if (!sItemId.equals(other.sItemId))
			return false;
		if (sItemIndi == null) {
			if (other.sItemIndi != null)
				return false;
		} else if (!sItemIndi.equals(other.sItemIndi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbProfBasInfoKey [sItemId=" + sItemId + ", sItemIndi=" + sItemIndi + "]";
	}

}

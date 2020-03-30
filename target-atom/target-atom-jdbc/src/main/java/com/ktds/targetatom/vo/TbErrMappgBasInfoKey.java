package com.ktds.targetatom.vo;

public class TbErrMappgBasInfoKey implements Comparable<TbErrMappgBasInfoKey> {

	public String sErrCd;
	public String sErrLevelDivCd;
	public int    iErrPriority;
	public String sOldErrCd;

	public TbErrMappgBasInfoKey() {
		
	}
	
	public TbErrMappgBasInfoKey(String sErrCd, String sErrLevelDivCd, int iErrPriority, String sOldErrCd) {
		super();
		this.sErrCd = sErrCd;
		this.sErrLevelDivCd = sErrLevelDivCd;
		this.iErrPriority = iErrPriority;
		this.sOldErrCd = sOldErrCd;
	}

	@Override
	public String toString() {
		return "TbErrMappgBasInfoKey [sErrCd=" + sErrCd + ", sErrLevelDivCd=" + sErrLevelDivCd + ", iErrPriority="
				+ iErrPriority + ", sOldErrCd=" + sOldErrCd + "]";
	}

	@Override
	public int compareTo(TbErrMappgBasInfoKey o) {
		if (this.iErrPriority < o.iErrPriority) {
            return -1;
        } else if (this.iErrPriority > o.iErrPriority) {
            return 1;
        }
		return 0;
	}

}

package com.ktds.targetatom.service;

import java.util.ArrayList;
import java.util.List;

public class CollFilenameChk {
	
	public boolean bResult;
	public String  sErrorCause;
	public static List<WlneFilenameSplit> ltWlneFilenameSplit = new ArrayList<WlneFilenameSplit>(); 
	
	@Override
	public String toString() {
		return "CollFilenameChk [bResult=" + bResult + ", sErrorCause=" + sErrorCause
				+ "]";
	}
}

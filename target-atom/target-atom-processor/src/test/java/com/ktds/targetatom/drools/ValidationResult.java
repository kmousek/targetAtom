package com.ktds.targetatom.drools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidationResult {
  List<Map<String, Object>> outputList;
  List<Map<String, Object>> errorList;
  
	public ValidationResult() {
    outputList = new ArrayList<Map<String, Object>>();
    errorList = new ArrayList<Map<String, Object>>();
  }


	/**
	 * @return the outputList
	 */
	public List<Map<String, Object>> getOutputList() {
		return outputList;
	}


	/**
	 * @param outputList the outputList to set
	 */
	public void setOutputList(List<Map<String, Object>> outputList) {
		this.outputList = outputList;
	}


	/**
	 * @return the errorList
	 */
	public List<Map<String, Object>> getErrorList() {
		return errorList;
	}


	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(List<Map<String, Object>> errorList) {
		this.errorList = errorList;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidationResult [");
		if (outputList != null)
			builder.append("outputList=").append(outputList).append(", ");
		if (errorList != null)
			builder.append("errorList=").append(errorList);
		builder.append("]");
		return builder.toString();
	}
}

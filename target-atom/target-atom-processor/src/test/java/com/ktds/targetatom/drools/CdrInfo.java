package com.ktds.targetatom.drools;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CdrInfo {
	private Map<String, Object> input;
  private Map<String, Object> output;
  private Map<String, Object> error;
  

  public CdrInfo(Map<String, Object> input) {
  	this.input = input;
    output = new HashMap<String, Object>();
    error = new HashMap<String, Object>();
  }
  

	/**
	 * @return the input
	 */
	public Map<String, Object> getInput() {
		return input;
	}


	/**
	 * @param input the input to set
	 */
	public void setInput(Map<String, Object> input) {
		this.input = input;
	}


	/**
	 * @return the output
	 */
	public Map<String, Object> getOutput() {
		return output;
	}


	/**
	 * @param output the output to set
	 */
	public void setOutput(Map<String, Object> output) {
		this.output = output;
	}


	/**
	 * @return the error
	 */
	public Map<String, Object> getError() {
		return error;
	}


	/**
	 * @param error the error to set
	 */
	public void setError(Map<String, Object> error) {
		this.error = error;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 50;
		StringBuilder builder = new StringBuilder();
		builder.append("CdrInfo [\ninput=").append(input != null ? toString(input.entrySet(), maxLen) : null)
				.append(", \noutput=").append(output != null ? toString(output.entrySet(), maxLen) : null).append(", \nerror=")
				.append(error != null ? toString(error.entrySet(), maxLen) : null).append("]");
		return builder.toString();
	}


	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", \n");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
}

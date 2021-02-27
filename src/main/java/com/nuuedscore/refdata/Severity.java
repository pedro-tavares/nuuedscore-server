package com.nuuedscore.refdata;

/**
 * Severity enum 
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum Severity {
	
	INFO("INFO");

	private String severity;

	Severity(String severity) {
		this.severity = severity;
	}

	public String severity() {
		return severity;
	}
}
package com.nuuedscore.refdata;

/**
 * Score enum 
 * Constants that classify a Score
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum RefScore {
	
	LOW("Low"), 
	High("High");

	private String value;

	RefScore(String value) {
		this.value = value;
	}

	public String gender() {
		return value;
	}
}
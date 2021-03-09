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
	
	LOW("LOW"), 
	HIGH("HIGH");

	private String value;

	RefScore(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
	
	public static RefScore get(String value) {
		for(RefScore r: RefScore.values()) {
			if (r.value.equals(value)) {
				return r;
			}
		}
		return null;
	}
}
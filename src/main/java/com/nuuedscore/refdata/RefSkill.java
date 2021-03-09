package com.nuuedscore.refdata;

/**
 * Skill enum 
 * Constants that classify a Skill
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
public enum RefSkill {
	
	SOFT("SOFT"), 
	HARD("HARD");

	private String value;

	RefSkill(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
	
	public static RefSkill get(String value) {
		for(RefSkill r: RefSkill.values()) {
			if (r.value.equals(value)) {
				return r;
			}
		}
		return null;
	}
}
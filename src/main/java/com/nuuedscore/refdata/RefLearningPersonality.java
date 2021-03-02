package com.nuuedscore.refdata;

/**
 * LearningPersonality enum 
 * Constants that classify a Persons Learning Personality
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum RefLearningPersonality {
	
	UNKNOWN("Unknown"),
	VISUAL("Visual"), 
	AURAL("Aural"), 
	VERBAL("Verbal"),
	SOCIAL("Social"),
	LOGICAL("Logical"),
	PHYSICAL("Physical"),
	SOLITARY("Solitary"),
	EGP("EGP");

	private String value;

	RefLearningPersonality(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
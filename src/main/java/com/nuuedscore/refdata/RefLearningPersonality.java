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
	
	VISUAL("Visual"), 
	AURAL("Aural"), 
	VERBAL("Verbal"),
	SOCIAL("Social"),
	LOGICAL("Logical"),
	PHYSICAL("Physical"),
	SOLITARY("Solitary"),
	EGP("EGP");

	private String learningPersonality;

	RefLearningPersonality(String learningPersonality) {
		this.learningPersonality = learningPersonality;
	}

	public String learningPersonality() {
		return learningPersonality;
	}
}
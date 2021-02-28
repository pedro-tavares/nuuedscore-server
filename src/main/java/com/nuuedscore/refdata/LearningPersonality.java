package com.nuuedscore.refdata;

/**
 * LearningPersonality enum 
 * Constants that classify a Persons Learning Personality
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum LearningPersonality {
	
	VISUAL("VISUAL"), 
	AURAL("AURAL"), 
	VERBAL("VERBAL"),
	SOCIAL("SOCIAL"),
	LOGICAL("LOGICAL"),
	PHYSICAL("PHYSICAL"),
	SOLITARY("SOLITARY"),
	ALL("ALL");

	private String learningPersonality;

	LearningPersonality(String learningPersonality) {
		this.learningPersonality = learningPersonality;
	}

	public String learningPersonality() {
		return learningPersonality;
	}
}
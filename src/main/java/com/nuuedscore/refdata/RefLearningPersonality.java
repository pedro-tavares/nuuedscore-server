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
	
	VISUAL("VISUAL"), 
	AURAL("AURAL"), 
	VERBAL("VERBAL"),
	SOCIAL("SOCIAL"),
	LOGICAL("LOGICAL"),
	PHYSICAL("PHYSICAL"),
	SOLITARY("SOLITARY"),
	ALL("ALL");

	private String learningPersonality;

	RefLearningPersonality(String learningPersonality) {
		this.learningPersonality = learningPersonality;
	}

	public String learningPersonality() {
		return learningPersonality;
	}
}
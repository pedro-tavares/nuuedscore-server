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
	
	TODO_LEARNING_PERSONALITY("TODO_LEARNING_PERSONALITY"),
	VISUAL("Visual"), 
	AURAL("Aural"), 
	VERBAL("Verbal"),
	SOCIAL("Social"),
	LOGICAL("Logical"),
	KINESTHETIC("Kinesthetic"),
	SOLITARY("Solitary"),
	EGP("EGP");

	private String value;

	RefLearningPersonality(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static RefLearningPersonality get(String value) {
		for(RefLearningPersonality r: RefLearningPersonality.values()) {
			if (r.value.equals(value)) {
				return r;
			}
		}
		return null;
	}

}
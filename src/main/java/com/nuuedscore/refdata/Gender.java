package com.nuuedscore.refdata;

/**
 * Gender enum 
 * Constants that classify a Persons gender
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum Gender {
	
	FEMALE("Female"), 
	MALE("Male"),
	OTHER("Other");

	private String gender;

	Gender(String gender) {
		this.gender = gender;
	}

	public String gender() {
		return gender;
	}
}
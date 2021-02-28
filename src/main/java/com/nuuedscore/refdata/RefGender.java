package com.nuuedscore.refdata;

/**
 * Gender enum 
 * Constants that classify a Persons gender
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum RefGender {
	
	FEMALE("Female"), 
	MALE("Male"),
	OTHER("Other");

	private String gender;

	RefGender(String gender) {
		this.gender = gender;
	}

	public String gender() {
		return gender;
	}
}
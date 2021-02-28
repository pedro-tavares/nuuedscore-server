package com.nuuedscore.refdata;

/**
 * Person Status enum 
 * Constants that classify a Persons situation
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum RefPersonStatus {
	
	ACTIVE("Active"), 
	INACTIVE("Inactive");

	private String status;

	RefPersonStatus(String status) {
		this.status = status;
	}

	public String status() {
		return status;
	}
}
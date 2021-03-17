package com.nuuedscore.refdata;

/**
 * Subject enum 
 * Constants that classify a Subject
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
public enum RefSubject {
	
	TODO_SUBJECT("TODO_TODO_SUBJECT");

	private String value;

	RefSubject(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static RefSubject get(String value) {
		for(RefSubject r: RefSubject.values()) {
			if (r.value.equals(value)) {
				return r;
			}
		}
		return null;
	}

}
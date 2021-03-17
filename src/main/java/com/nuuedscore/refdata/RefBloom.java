package com.nuuedscore.refdata;

/**
 * Bloom enum 
 * Constants that classify as per Bloom`s Taxonomy
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public enum RefBloom {
	
	TODO_BLOOM("TODO_BLOOM"),
	UNDERSTAND_REMEMBER("Understand/Remember"), 
	ANALYZE_APPLY("Analyze/Apply"),
	EVALUATE_CREATE("Evaluate/Create");

	private String value;

	RefBloom(String value) {
		this.value = value;
	}

	public String gender() {
		return value;
	}

	public static RefBloom get(String value) {
		for(RefBloom r: RefBloom.values()) {
			if (r.value.equals(value)) {
				return r;
			}
		}
		return null;
	}

}
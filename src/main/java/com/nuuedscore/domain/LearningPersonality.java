package com.nuuedscore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Learning Personality Entity  
 *
 * Classification of a Persons Learning
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class LearningPersonality extends BaseDomain {

	private static final long serialVersionUID = 8277228433832974219L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public LearningPersonality() {
        super();
    }

    public LearningPersonality(final String name) {
        super();
        this.name = name;
    }
	
}

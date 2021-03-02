package com.nuuedscore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Topic Entity  
 *
 * A Topic
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class Topic extends BaseDomain {
	
	private static final long serialVersionUID = -6192821537329632294L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
	
    public Topic(final String name) {
        super();
        this.name = name;
    }
	
}

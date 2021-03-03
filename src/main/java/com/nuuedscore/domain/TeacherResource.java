package com.nuuedscore.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.nuuedscore.refdata.RefBloom;
import com.nuuedscore.refdata.RefLearningPersonality;
import com.nuuedscore.refdata.RefScore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Teacher Resource
 * Any Resource for a Teacher 
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class TeacherResource extends BaseDomain {
	
	private static final long serialVersionUID = -6185731271053302664L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	private String topic;
	@Enumerated(EnumType.STRING)
	private RefScore score;
	@Enumerated(EnumType.STRING)
	@Column(name = "learning_personality")
	private RefLearningPersonality learningPersonality;
	@Enumerated(EnumType.STRING)
	private RefBloom bloom;
	private String subject;
	private String name;
    private String resource;
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    public TeacherResource(String topic, RefScore score, RefLearningPersonality learningPersonality, RefBloom bloom, String subject, String name, String resource) {
    	this.topic = topic;
    	this.score = score;
    	this.learningPersonality = learningPersonality;
    	this.bloom = bloom;
    	this.subject = subject;
    	this.resource = resource;
    }
    
	/*
	 * LifeCycle
	 */
	@PrePersist
	public void prePersist() {
	    log.info("prePersist...");
	    this.setCreatedOn(LocalDateTime.now());
	}
    
}
package com.nuuedscore.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Site
 * The composition of a url endpoint, its current and previous status 
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
public class Site extends BaseDomain {
	
	private static final long serialVersionUID = -6185731271053302664L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	private String name;
    private String url;
    @Column(name = "status_code")
    private Integer statusCode;
    @Column(name = "previous_status_code")
    private Integer previousStatusCode;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    public Site(String name, String url) {
    	this.name = name;
    	this.url = url;
    	statusCode = HttpStatus.OK.value();
    	previousStatusCode = HttpStatus.OK.value();
    	createdOn = LocalDateTime.now();
    }

    public void setStatusCode(Integer statusCode) {
    	this.previousStatusCode = this.statusCode;
    	this.statusCode = statusCode;
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
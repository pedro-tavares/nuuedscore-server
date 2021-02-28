package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuuedscore.domain.LearningPersonality;

/**
 * LearningPersonality Repository
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface LearningPersonalityRepository extends JpaRepository<LearningPersonality, Long> {

	LearningPersonality findByName(String name);

}
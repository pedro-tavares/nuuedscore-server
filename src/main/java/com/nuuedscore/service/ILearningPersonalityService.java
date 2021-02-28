package com.nuuedscore.service;

import java.util.List;

import com.nuuedscore.domain.LearningPersonality;

/**
 * Learning Personality Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface ILearningPersonalityService {

	List<LearningPersonality> findAll();
}

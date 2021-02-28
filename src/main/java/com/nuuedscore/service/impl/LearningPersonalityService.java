package com.nuuedscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.LearningPersonality;
import com.nuuedscore.repository.LearningPersonalityRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.ILearningPersonalityService;

import lombok.extern.slf4j.Slf4j;

/**
 * Learning Personality Service Implementaton
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Service
public class LearningPersonalityService extends BaseService implements ILearningPersonalityService {
	
    @Autowired
    private LearningPersonalityRepository learningPersonalityRepository;
	
	@Override
	public List<LearningPersonality> findAll() {
		return (List<LearningPersonality>) learningPersonalityRepository.findAll();
	}

}

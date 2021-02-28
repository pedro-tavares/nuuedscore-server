package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.LearningPersonality;
import com.nuuedscore.service.ILearningPersonalityService;

import lombok.extern.slf4j.Slf4j;

/**
 * Learning Personality Front Controller
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@RestController
@RequestMapping("/learning_personality")
public class LearningPersonalityController extends BaseController {
	
	@Autowired
	ILearningPersonalityService learningPersonalityService;

	/**
	 * Get all 
	 * 
	 * @return all the People
	 */
    @GetMapping("/")
    public ResponseEntity<List<LearningPersonality>> all() {
        return new ResponseEntity<List<LearningPersonality>>((List<LearningPersonality>) learningPersonalityService.findAll(), HttpStatus.OK);
    }
 
}

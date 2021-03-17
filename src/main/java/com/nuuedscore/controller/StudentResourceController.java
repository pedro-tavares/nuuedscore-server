package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.StudentResource;
import com.nuuedscore.service.impl.StudentResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * Student Resource Front Controller
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
@Slf4j
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student_resource")
public class StudentResourceController extends BaseController {
	
	@Autowired
	StudentResourceService studentResourceService;

	/**
	 * Get all 
	 * 
	 */
    @GetMapping("/list")
    public ResponseEntity<List<StudentResource>> list() {
    	log.info("list");
    	return new ResponseEntity<List<StudentResource>>(studentResourceService.findAll(), HttpStatus.OK);
    }
 
}

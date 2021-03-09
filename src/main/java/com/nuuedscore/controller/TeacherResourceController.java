package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.TeacherResource;
import com.nuuedscore.service.impl.TeacherResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * Teacher Resource Front Controller
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teacher_resource")
public class TeacherResourceController extends BaseController {
	
	@Autowired
	TeacherResourceService teacherResourceService;

	/**
	 * Get all 
	 * 
	 */
    @GetMapping("/")
    public ResponseEntity<List<TeacherResource>> all() {
        return new ResponseEntity<List<TeacherResource>>(teacherResourceService.findAll(), HttpStatus.OK);
    }
 
}

package com.nuuedscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.StudentResource;
import com.nuuedscore.repository.StudentResourceRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IStudentResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * Student Resource Service Implementation
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
@Slf4j
@Service
public class StudentResourceService extends BaseService implements IStudentResourceService {
	
    @Autowired
    private StudentResourceRepository studentResourceRepository;
	
	@Override
	public List<StudentResource> findAll() {
		return (List<StudentResource>) studentResourceRepository.findAll();
	}

}

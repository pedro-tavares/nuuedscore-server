package com.nuuedscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.TeacherResource;
import com.nuuedscore.repository.TeacherResourceRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.ITeacherResourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * Teacher Resource Service Implementation
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
@Slf4j
@Service
public class TeacherResourceService extends BaseService implements ITeacherResourceService {
	
    @Autowired
    private TeacherResourceRepository teacherResourceRepository;
	
	@Override
	public List<TeacherResource> findAll() {
		return (List<TeacherResource>) teacherResourceRepository.findAll();
	}

}

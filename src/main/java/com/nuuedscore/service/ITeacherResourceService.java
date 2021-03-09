package com.nuuedscore.service;

import java.util.List;

import com.nuuedscore.domain.TeacherResource;

/**
 * Teacher Resource Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface ITeacherResourceService {

	List<TeacherResource> findAll();
}

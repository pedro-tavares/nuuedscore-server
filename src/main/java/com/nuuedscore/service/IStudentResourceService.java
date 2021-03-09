package com.nuuedscore.service;

import java.util.List;

import com.nuuedscore.domain.StudentResource;

/**
 * Student Resource Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface IStudentResourceService {

	List<StudentResource> findAll();
}

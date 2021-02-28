package com.nuuedscore.service;

import java.util.List;

import com.nuuedscore.domain.Role;

/**
 * Role Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface IRoleService {

	List<Role> findAll();
}

package com.nuuedscore.service;

import java.util.List;

import com.nuuedscore.domain.Privilege;

/**
 * Privilege Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface IPrivilegeService {

	List<Privilege> findAll();
}

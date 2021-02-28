package com.nuuedscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.Role;
import com.nuuedscore.repository.RoleRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IRoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * Role Service Implementaton
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Service
public class RoleService extends BaseService implements IRoleService {
	
    @Autowired
    private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}

}

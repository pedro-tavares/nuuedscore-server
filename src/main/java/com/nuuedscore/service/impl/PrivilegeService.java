package com.nuuedscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.Privilege;
import com.nuuedscore.repository.PrivilegeRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IPrivilegeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Privilege Service Implementaton
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Service
public class PrivilegeService extends BaseService implements IPrivilegeService {
	
    @Autowired
    private PrivilegeRepository privilegeRepository;
	
	@Override
	public List<Privilege> findAll() {
		return (List<Privilege>) privilegeRepository.findAll();
	}

}

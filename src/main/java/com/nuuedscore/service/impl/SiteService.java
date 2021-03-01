package com.nuuedscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.Site;
import com.nuuedscore.repository.SiteRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.ISiteService;

import lombok.extern.slf4j.Slf4j;

/**
 * Site Service Implementaton
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Service
public class SiteService extends BaseService implements ISiteService {
	
    @Autowired
    private SiteRepository siteRepository;
	
	@Override
	public List<Site> findAll() {
		return (List<Site>) siteRepository.findAll();
	}

}

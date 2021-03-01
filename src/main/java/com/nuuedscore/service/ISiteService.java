package com.nuuedscore.service;

import java.util.List;

import com.nuuedscore.domain.Site;

/**
 * Site Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface ISiteService {

	List<Site> findAll();
}

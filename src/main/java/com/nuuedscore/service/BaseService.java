package com.nuuedscore.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Base Service  
 *
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Service
public class BaseService implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("{} is running...", this.getClass().getSimpleName());
	}

}

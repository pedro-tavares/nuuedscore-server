package com.nuuedscore.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Domain Initializer Component  
 *
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Component
public class DomainInitializer  implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("Initializing DOMAIN...", this.getClass().getSimpleName());
	}

	
}

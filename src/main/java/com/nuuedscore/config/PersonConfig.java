package com.nuuedscore.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nuuedscore.domain.Person;
import com.nuuedscore.exception.PersonEmailCannotBeNullException;
import com.nuuedscore.exception.PersonExistsException;
import com.nuuedscore.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

/**
 * Person Configuration 
 * Configure Person related artifacts
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
@Slf4j
@Configuration
public class PersonConfig implements ApplicationContextAware {

	@Autowired
	IPersonService personService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("init:Lets create a few people...");

		try {
			personService.register(new Person("Derrick", "Oneal", "doneal@nuueducation.com", "password"));
			personService.register(new Person("Derrick", "Hardy", "dphardy66@gmail.com", "password"));
			personService.register(new Person("Yalonda", "Keaton", "ykeaton.nuuedscore@gmail.com", "password"));
			personService.register(new Person("The", "Writer", "pedro.javalabs@gmail.com", "javalabs"));
		
		} catch (PersonEmailCannotBeNullException | PersonExistsException e) {
			log.debug(ExceptionUtils.getStackTrace(e));	
		} 
	}
	
}

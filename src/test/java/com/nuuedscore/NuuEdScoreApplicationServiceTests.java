package com.nuuedscore;

import java.util.Random;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.nuuedscore.domain.Person;
import com.nuuedscore.exception.PersonEmailCannotBeNullException;
import com.nuuedscore.exception.PersonExistsException;
import com.nuuedscore.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

/**
 * NuuED SCORE Application Service Tests   
 *
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@SpringBootTest
class NuuEdScoreApplicationServiceTests {

	static Random random = new Random();
	
	@Autowired
	IPersonService personService;
	
	@Test
	@Order(1)
	void contextLoads() {}

	@Test
	@Order(2)
	void testPersonSignUp() {
		Person person = new Person(Math.abs(random.nextInt()) + "@mail");
		try {
			personService.signUp(person);
			
		} catch (PersonEmailCannotBeNullException | PersonExistsException e) {
			log.error(ExceptionUtils.getMessage(e));
		}
	}

}

package com.nuuedscore.service;

import java.util.List;
import java.util.Optional;

import com.nuuedscore.domain.Person;
import com.nuuedscore.exception.PersonEmailCannotBeNullException;
import com.nuuedscore.exception.PersonExistsException;

/**
 * Person Service Interface
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface IPersonService {

	Person register(Person person) throws PersonEmailCannotBeNullException, PersonExistsException;
	Person login(Person person);
	Person save(Person person);

	Optional<Person> findById(Long id);	
	Person findByEmail(String email);

	List<Person> findAll();
}

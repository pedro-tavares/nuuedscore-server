package com.nuuedscore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.Person;
import com.nuuedscore.exception.PersonEmailCannotBeNullException;
import com.nuuedscore.exception.PersonExistsException;
import com.nuuedscore.refdata.NuuEdScoreConstants;
import com.nuuedscore.repository.PersonRepository;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

/**
 * Person Service Implementaton
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Service
public class PersonService extends BaseService implements IPersonService, UserDetailsService {
	
    @Autowired
    private PersonRepository personRepository;
	
	@Bean
	private PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	public void setup() {
		log.info("setup");
	}

	@Override
	public Person signUp(Person person) throws PersonEmailCannotBeNullException, PersonExistsException {
		log.info("signUp:{}", person.getEmail());
		
		if (person.getEmail() == null) {
			throw new PersonEmailCannotBeNullException("Person email cannot be null.");
		}
		if (emailExists(person.getEmail())) {
			throw new PersonExistsException("Account with email already exists for:" + person.getEmail());
		}
		Person signedUpPerson = this.save(person);
		log.info("signedUpPerson:{}", signedUpPerson);
		
		return signedUpPerson;
	}
    
	public Person save(Person person) {
		log.info("save:{}{}", person.isNew() ? "NEW " : "", person.toString());
		
		String password = person.isNew() ? NuuEdScoreConstants.DEFAULT_PASSWORD : person.getPassword();
		person.setPassword(encoder().encode(password));

		return personRepository.saveAndFlush(person);
	}

	private boolean emailExists(final String email) {
        return personRepository.findByEmail(email) != null;
    }

	@Override
	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}
	
	@Override
	public Person findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	@Override
	public List<Person> findAll() {
		return (List<Person>) personRepository.findAll();
	}

	/**
	 * Override Security Core
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return personRepository.findByUsername(username);
	}
	
}

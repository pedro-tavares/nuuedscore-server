package com.nuuedscore.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nuuedscore.domain.Person;
import com.nuuedscore.domain.Privilege;
import com.nuuedscore.domain.Role;
import com.nuuedscore.exception.PersonEmailCannotBeNullException;
import com.nuuedscore.exception.PersonExistsException;
import com.nuuedscore.refdata.NuuEdScoreConstants;
import com.nuuedscore.repository.PersonRepository;
import com.nuuedscore.repository.RoleRepository;
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

	@Autowired
	private RoleRepository roleRepository;

	@Bean
	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Person register(Person person) throws PersonEmailCannotBeNullException, PersonExistsException {
		log.info("register:{}", person.getEmail());

		if (person.getEmail() == null) {
			throw new PersonEmailCannotBeNullException("Person email cannot be null.");
		}
		if (emailExists(person.getEmail())) {
			throw new PersonExistsException("Account with email already exists for:" + person.getEmail());
		}
		
	    person.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

		log.info("register WITH Roles:{}", person.toString());
	    
		Person registeredPerson = this.save(person);
		log.info("registeredPerson:{}", registeredPerson);

		return registeredPerson;
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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Person user = personRepository.findByEmail(email);
		if (user == null) {
			return new org.springframework.security.core.userdetails.User(
					" ", " ", true, true, true, true,
					getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER")))
			);
		}

		return new org.springframework.security.core.userdetails.User(
			user.getEmail(), 
			user.getPassword(),
			user.isEnabled(), 
			true, 
			true, 
			true, 
			getAuthorities(user.getRoles())
		);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
	
}

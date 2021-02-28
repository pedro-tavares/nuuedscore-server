package com.nuuedscore.component;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.nuuedscore.domain.Privilege;
import com.nuuedscore.domain.Role;
import com.nuuedscore.repository.PrivilegeRepository;
import com.nuuedscore.repository.RoleRepository;

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
public class DomainInitializer implements ApplicationContextAware {

	@Autowired
	private RoleRepository roleRepository;

	@SuppressWarnings("unused")
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("Initializing DOMAIN...", this.getClass().getSimpleName());

		// Security defaults
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		// Application
		createRoleIfNotFound("ROLE_CURATOR");
		createRoleIfNotFound("ROLE_STUDENT");		
	}

	private Role createRoleIfNotFound(String roleName) {
		return createRoleIfNotFound(roleName, Collections.emptyList()); // no privilege as all privilege for now
	}
	
	private Role createRoleIfNotFound(String roleName, Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(roleName);
		if (role == null) {
			role = new Role(roleName);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}
}
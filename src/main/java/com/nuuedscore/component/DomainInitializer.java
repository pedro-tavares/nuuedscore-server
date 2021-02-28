package com.nuuedscore.component;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.nuuedscore.domain.LearningPersonality;
import com.nuuedscore.domain.Privilege;
import com.nuuedscore.domain.Role;
import com.nuuedscore.repository.LearningPersonalityRepository;
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

	@Autowired
	private LearningPersonalityRepository learningPersonalityRepository;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("Initializing DOMAIN...", this.getClass().getSimpleName());

		/*
		 * ROLE
		 */
		// Security defaults
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		// Application
		createRoleIfNotFound("ROLE_CURATOR");
		createRoleIfNotFound("ROLE_STUDENT");
		
		/*
		 * PRIVILEGE
		 */
		
		/*
		 * LEARNING PERSONALITY 
		 */
		createLearningPersonalityIfNotFound("VISUAL");
		createLearningPersonalityIfNotFound("AURAL");
		createLearningPersonalityIfNotFound("VERBAL");
		createLearningPersonalityIfNotFound("SOCIAL");
		createLearningPersonalityIfNotFound("LOGICAL");
		createLearningPersonalityIfNotFound("PHYSICAL");
		createLearningPersonalityIfNotFound("SOLITARY");
	}

	/*
	 * ROLE
	 */
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
	
	/*
	 * PRIVILEGE
	 */
	//TODO
	
	/*
	 * LEARNING PERSONALITY
	 */
	private LearningPersonality createLearningPersonalityIfNotFound(String name) {
		LearningPersonality learningPersonality = learningPersonalityRepository.findByName(name);
		if (learningPersonality == null) {
			learningPersonality = new LearningPersonality(name);
			learningPersonalityRepository.save(learningPersonality);
		}
		return learningPersonality;
	}

}

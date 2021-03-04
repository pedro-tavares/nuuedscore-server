package com.nuuedscore.component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.nuuedscore.domain.LearningPersonality;
import com.nuuedscore.domain.Privilege;
import com.nuuedscore.domain.Role;
import com.nuuedscore.domain.StudentResource;
import com.nuuedscore.refdata.RefLearningPersonality;
import com.nuuedscore.repository.LearningPersonalityRepository;
import com.nuuedscore.repository.PrivilegeRepository;
import com.nuuedscore.repository.RoleRepository;
import com.nuuedscore.repository.StudentResourceRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;

/**
 * Domain Startup Component
 *
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Component
public class DomainStartupComponent implements ApplicationContextAware {

	@Autowired
	private RoleRepository roleRepository;

	@SuppressWarnings("unused")
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	LearningPersonalityRepository learningPersonalityRepository;

	@Autowired
	StudentResourceRepository studentResourceRepository;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("Starting DOMAIN...", this.getClass().getSimpleName());

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
		for (RefLearningPersonality learningPersonality: RefLearningPersonality.values()) {
			createLearningPersonalityIfNotFound(learningPersonality.value());
		}
		
		/*
		 * DOMAIN 
		 */
		createStudentDomain();
	}

	/**
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
	
	/**
	 * PRIVILEGE
	 */
	//TODO

	/**
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

	/**
	 * STUDENT DOMAIN
	 */
	private void createStudentDomain() {
		log.info("createDomain:Reading STUDENT Domain Data...");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		List<StudentResource> resourceList = new ArrayList<StudentResource>();
		
		String path = System.getProperty("user.dir") + "/src/main/resources/";
		
		try (CSVReader reader = new CSVReader(new FileReader(path + "NuuEdScore - STUDENT Resources - RAW.txt"))) {
			log.info("Reading STUDENT Resources...");
			
			List<String[]> r = reader.readAll();
			r.forEach(s -> {
				log.info("{},{},{},{},{},{},{}", s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
				resourceList.add(new StudentResource(s[0], s[1], s[2], s[3], s[4], s[5], s[6]));
			});
			
			log.info("Importing {} STUDENT Resources...", resourceList.size());

			int i=1;
			for (StudentResource sr: resourceList) {
				if (studentResourceRepository.findByResource(sr.getResource()) == null) {
					log.info(i++ + "Saving STUDENT Resource:{}", sr.getResource());
					studentResourceRepository.save(sr);
				}
			}

			stopWatch.stop();
			log.info("Import DONE in {} (s)", stopWatch.getTotalTimeSeconds());

		} catch (IOException | CsvException e) {
			log.info(ExceptionUtils.getStackTrace(e));
		}
	}

}

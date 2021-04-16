package com.nuuedscore.component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import com.nuuedscore.domain.TeacherResource;
import com.nuuedscore.refdata.RefLearningPersonality;
import com.nuuedscore.repository.LearningPersonalityRepository;
import com.nuuedscore.repository.PrivilegeRepository;
import com.nuuedscore.repository.RoleRepository;
import com.nuuedscore.repository.StudentResourceRepository;
import com.nuuedscore.repository.TeacherResourceRepository;
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

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	LearningPersonalityRepository learningPersonalityRepository;

	@Autowired
	StudentResourceRepository studentResourceRepository;

	@Autowired
	TeacherResourceRepository teacherResourceRepository;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {	
		log.info("Starting DOMAIN...", this.getClass().getSimpleName());

		/*
		 * PRIVILEGE
		 */
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		/*
		 * ROLE
		 */
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);        
              
		// Security defaults
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
		// Application
		createRoleIfNotFound("ROLE_CURATOR", adminPrivileges);
		createRoleIfNotFound("ROLE_STUDENT", Arrays.asList(readPrivilege));
		      
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
		//createTeacherDomain();
	}

	/**
	 * ROLE
	 */
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
	private Privilege createPrivilegeIfNotFound(String privilegeName) {
        Privilege privilege = privilegeRepository.findByName(privilegeName);
        if (privilege == null) {
            privilege = new Privilege(privilegeName);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

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
		
		List<StudentResource> studentResourceList = new ArrayList<StudentResource>();
		
		String path = System.getProperty("user.dir") + "/src/main/resources/";
		
		try (CSVReader reader = new CSVReader(new FileReader(path + "NuuEdScore - STUDENT Resources.txt"))) {
			log.info("Reading STUDENT Resources...");
			
			List<String[]> r = reader.readAll();
			r.forEach(s -> {
				log.info("{},{},{},{},{},{},{}", s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
				studentResourceList.add(new StudentResource(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]));
			});
			
			log.info("Importing {} STUDENT Resources...", studentResourceList.size());

			int imported=0;
			for (StudentResource sr: studentResourceList) {
				if (studentResourceRepository.findByResource(sr.getResource()) == null) {
					log.info(++imported + "->Saving STUDENT Resource:{}", sr.toString());
					studentResourceRepository.save(sr);
				}
			}

			stopWatch.stop();
			log.info("DONE: Imported {} STUDENT Resources in {} (s)", imported, stopWatch.getTotalTimeSeconds());

		} catch (IOException | CsvException e) {
			log.info(ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * TEACHER DOMAIN
	 */
	private void createTeacherDomain() {
		log.info("createTeacherDomain:Reading TEACHER Domain Data...");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		List<TeacherResource> teacherResourceList = new ArrayList<TeacherResource>();
		
		String path = System.getProperty("user.dir") + "/src/main/resources/";
		
		try (CSVReader reader = new CSVReader(new FileReader(path + "NuuEdScore - TEACHER Resources - RAW.txt"))) {
			log.info("Reading TEACHER Resources...");
			
			List<String[]> r = reader.readAll();
			r.forEach(s -> {
				log.info("{},{},{},{},{},{}", s[0], s[1], s[2], s[3], s[4], s[5]);
				teacherResourceList.add(new TeacherResource(s[0], s[1], s[2], s[3], s[4], s[5]));
			});
			
			log.info("Importing {} TEACHER Resources...", teacherResourceList.size());

			int imported=0;
			for (TeacherResource tr: teacherResourceList) {
				if (teacherResourceRepository.findByResource(tr.getResource()) == null) {
					log.info(++imported + "->Saving TEACHER Resource:{}", tr.toString());
					teacherResourceRepository.save(tr);
				}
			}

			stopWatch.stop();
			log.info("DONE: Imported {} TEACHER Resources in {} (s)", imported, stopWatch.getTotalTimeSeconds());

		} catch (IOException | CsvException e) {
			log.info(ExceptionUtils.getStackTrace(e));
		}
	}
	
}

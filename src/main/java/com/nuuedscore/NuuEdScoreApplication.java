package com.nuuedscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * NuuED SCORE Server Application
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@SpringBootApplication
public class NuuEdScoreApplication {

	public static void main(String[] args) {
		log.info("NuuED SCORE by JavaLabs - v0.1 BETA");
		log.info("Reactive AI - While some watched from far behind... Learn as Apply. Refuse to Stay Down.");
		log.info("user.home @ {}", System.getProperty("user.home"));
		log.info("user.dir @ {}", System.getProperty("user.dir"));
		
		ApplicationContext applicationContext = SpringApplication.run(NuuEdScoreApplication.class, args);
		
		// Debug Loaded Beans in Context
		log.debug("\n\n\nSPRING Loaded Beans in Context:");
		for (String name: applicationContext.getBeanDefinitionNames()) {
			log.debug(name);
		}		
	}
}
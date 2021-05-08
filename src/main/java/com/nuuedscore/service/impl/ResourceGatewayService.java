package com.nuuedscore.service.impl;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IResourceGatewayService;

import lombok.extern.slf4j.Slf4j;

/**
 * Resource Gateway Service
 * 
 * @author PATavares
 * @since April 2021
 * 
 */
@Slf4j
@EnableScheduling
@Service
public class ResourceGatewayService extends BaseService implements IResourceGatewayService {

	private boolean CONNECTED;

	@Scheduled(fixedDelay = 4000)
	private void gate() {
		log.info(".");
		connect();
	}

	private void connect() {
		if (!CONNECTED) {
			log.info("CONNECTING RESOURCE GATEWAY...");
		} 
	}

}

package com.nuuedscore.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IResourceGatewayService;
import com.nuuedscore.util.JSONUtils;

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

	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(5))
			.build();

	private String TOKEN_URL = "https://dev-auth.act-et.org/oauth/token";

	private boolean CONNECTED;

	@Scheduled(fixedDelay = 5000)
	private void gate() {
		//log.info(".");
		if (!CONNECTED) {
			connect();
		}
	}

	private void connect() {
		if (!CONNECTED) {
			log.info("CONNECTING RESOURCE GATEWAY...");
		}
		authenticate();
	}

	private void authenticate() {
		log.info("AUTHENTICATING...");
		
		Map<String, String> map = new HashMap<>();
		map.put("client_id", "GL3nYdxNixGsLw0oW2CrwMLNBL5f92E5");
		map.put("client_secret", "cDYAbwvXdk3IHkHvWPnni_DuTz2Cu7lbKFEM_D6eJIMV58sAfGnMv0ybqhUPkrud");
		map.put("audience", "dev-minerva");
		map.put("grant_type", "client_credentials");
	
		String request_json = JSONUtils.mapToJson(map);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(request_json))
                .uri(URI.create(TOKEN_URL))
                .setHeader("User-Agent", "RAI Client") // add request header
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpStatus.OK.value()) {
				CONNECTED = true;
				log.info("CONNECTED to ACT Gateway");
				log.info("{}\n{}", response.statusCode(), response.body());	
			}
			
		} catch (IOException | InterruptedException e) {
			log.error("ERROR CONNECTING to ACT Gateway:{}", e.getMessage());
		}
	}

}

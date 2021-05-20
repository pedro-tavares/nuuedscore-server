package com.nuuedscore.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
//import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.nuuedscore.dto.ACTToken;
import com.nuuedscore.http.NuuEdHTTP;
import com.nuuedscore.json.JSON;
import com.nuuedscore.service.BaseService;
import com.nuuedscore.service.IResourceGatewayService;

import lombok.extern.slf4j.Slf4j;

/**
 * Resource Gateway Service
 * 
 * @author PATavares
 * @since May 2021
 * 
 */
@Slf4j
@EnableScheduling
@Service
public class ResourceACTGatewayService extends BaseService implements IResourceGatewayService {

	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(5)).build();

	private StopWatch stopWatch = new StopWatch();

	private String TOKEN_URL = "https://dev-auth.act-et.org/oauth/token";
	private String RESOURCE_URL = "https://dev-minerva.act-et.org/ims/rs/v1p0/resources";
	private String SUBJECT_URL = "https://dev-minerva.act-et.org/ims/rs/v1p0/subject";
	
	private ACTToken ACT_TOKEN;
	
	private boolean CONNECTED;

	@Scheduled(fixedDelay = 5000)
	private void gate() {
		//log.info(".");
		if (!CONNECTED) {
			connect();
		}
	}

	public void connect() {
		stopWatch.start();	
		if (!CONNECTED) {
			log.info("CONNECTING TO ACT RESOURCES...");
		}
		authenticate();
	}

	private void authenticate() {
		log.info("AUTHENTICATING TO ACT...");

		Map<String, String> map = new HashMap<>();
		map.put("client_id", "GL3nYdxNixGsLw0oW2CrwMLNBL5f92E5");
		map.put("client_secret", "cDYAbwvXdk3IHkHvWPnni_DuTz2Cu7lbKFEM_D6eJIMV58sAfGnMv0ybqhUPkrud");
		map.put("audience", "dev-minerva");
		map.put("grant_type", "client_credentials");

		String request_json = JSON.mapToJson(map);

		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(request_json))
				.uri(URI.create(TOKEN_URL))
				.setHeader(NuuEdHTTP.USER_AGENT, NuuEdHTTP.NUUEDSCORE_CLIENT) 
				.header(NuuEdHTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == HttpStatus.OK.value()) {
				stopWatch.stop();
				CONNECTED = true;
				log.info("AUHTENTICATED to ACT in {}ms", stopWatch.getTotalTimeMillis());
				log.info("{}", response.statusCode());
				//log.info("{}", response.body());
				
				ACT_TOKEN = JSON.mapper.readValue(response.body(), ACTToken.class);
				
				log.info("ACTtoken ACCESS_TOKEN:");
				log.info("{}", ACT_TOKEN.getAccessToken());
				
				log.info("ACTtoken SCOPE:");
				String scopes[] = ACT_TOKEN.getScope().split("AND");
				for (int i=0; i<scopes.length; i++) {
					log.info(scopes[i]);
				}
					
				searchResourcesSync();
			}
			
		} catch (IOException | InterruptedException e) {
			log.error("ERROR CONNECTING to ACT:{}", e.getMessage());
		}
	}

	/**
	 * EXAMPLES:
	 * search:
	 * FILTER = "?https://lor.act-et.org/ims/rs/v1p0/resources?filter=extensions.secure_url='true' AND (extensions.breakframe='false' OR extensions.breakframe='NULL') AND search='math'&limit=25&extensions.expandObjectives=true";
	 * launch
	 * FILTER = "/1069377/lti/proxy";
	 */
	public void searchResourcesSync() {
		log.info("Searching Resources...");
		
		// search URL
//		String FILTER = "";
		//String FILTER = "?filter=search~'english'";
		//String FILTER = "?filter=learningResourceType='Assessment/Formative'";
		String FILTER = "?filter=learningResourceType='Media/Video'";
		
		String URIString = RESOURCE_URL + FILTER;
		log.info("\n\n{}\n", URIString); 
		
		HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URIString))
                .header(NuuEdHTTP.USER_AGENT, NuuEdHTTP.NUUEDSCORE_CLIENT) 
                .header("Authorization", "Bearer " + ACT_TOKEN.getAccessToken())
                .header("user_id", NuuEdHTTP.NUUEDSCORE_CLIENT)
                .build();

		//log.info("\nREQUEST HEADERS:\n{}", request.headers().toString());
		log.info("REQUEST:\n{}", request.toString());
		
                HttpResponse<String> response;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
	        HttpHeaders headers = response.headers();
	        headers.map().forEach((k, v) -> log.info(k + ":" + v));

	        log.info("{}", response.statusCode());
			//log.info("{}", response.body());
		
		} catch (IOException | InterruptedException e) {
			log.info(ExceptionUtils.getStackTrace(e));
		}
	}
	
/*	
	private void searchResourcesAsync() {
		log.info("Searching Resources...");
		
		//post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + finalToken);

		 HttpRequest request = HttpRequest.newBuilder()
	                .GET()
	                .uri(URI.create(RESOURCE_URL))
	                .header(NuuEdHTTP.USER_AGENT, NuuEdHTTP.NUUEDSCORE_CLIENT) 
	                .header("Authorization", "Bearer " + ACT_TOKEN)
	                .build();

	        CompletableFuture<HttpResponse<String>> response =
	                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

	        String result = null;
			try {
				result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
				log.info(result);		
				
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				log.error("ERROR SEARCHING RESOURCES on ACT Gateway:{}", e.getMessage());
			}
	}
*/
}

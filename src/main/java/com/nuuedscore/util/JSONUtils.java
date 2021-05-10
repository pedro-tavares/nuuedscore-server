package com.nuuedscore.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Server Utilities
 *
 * @author PATavares
 * @since Apr 2021
 * 
 */
@Slf4j
public class JSONUtils {

	private static ObjectMapper mapper = new ObjectMapper();
	
	static public String mapToJson(Map<String, String> map) {
		String result;
		try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			log.error("Converting MAP to JSON:{}", e.getMessage());
			result = "RAI_JSON_ERROR";
		}	
		return result;
	}
	
}

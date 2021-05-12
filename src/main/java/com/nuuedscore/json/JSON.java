package com.nuuedscore.json;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * JSON Adapter
 *
 * @author PATavares
 * @since Apr 2021
 * 
 */
@Slf4j
@Component
public class JSON implements ApplicationContextAware {

	public static ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("JSON Mapper...", this.getClass().getSimpleName());

		//mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);	
	}
	
	static public String mapToJson(Map<String, String> map) {
		String result;
		try {
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			log.error("Converting MAP to JSON:{}", e.getMessage());
			result = "MAP_TO_JSON_ERROR";
		}	
		return result;
	}
	
	static public Map<String, String> jsonToMap(String json) {
		Map<String, String> result = null;
		try {
			result = mapper.readValue(json, Map.class);
		} catch (JsonProcessingException e) {
			log.error("Converting JSON to MAP:{}", e.getMessage());
			result = new TreeMap<String, String>();
			result.put("ERROR", "JSON TO_MAP_ERROR");
			return result;
		}
		return result;
	}
	
}

package com.nuuedscore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Configuration 
 * 
 * Enable Cross Origin Resource Sharing for client connections to the microservice
 * https://developer.mozilla.org/es/docs/Web/HTTP/Access_control_CORS
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow all
        	.allowedMethods("GET", "POST");
    }
}
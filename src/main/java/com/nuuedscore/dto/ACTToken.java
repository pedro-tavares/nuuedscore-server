package com.nuuedscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * ACT Token  
 *
 * @author PATavares
 * @since May 2021
 * 
 */
@Slf4j
@Data
public class ACTToken {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("expires_in")
	private String expiresIn;
	@JsonProperty("token_type")
	private String tokenType;
	
}

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
public class ACTAuthId {

	@JsonProperty("client_id")
	private String clientId;
	@JsonProperty("client_secret")
	private String clientSecret;
	@JsonProperty("audience")
	private String expiresIn;
	@JsonProperty("grant_type")
	private String grantType;	

}

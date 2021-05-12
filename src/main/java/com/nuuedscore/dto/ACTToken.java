package com.nuuedscore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ACT Token  
 *
 * @author PATavares
 * @since May 2021
 * 
 */
public class ACTToken {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("expires_in")
	private String expiresIn;

	@JsonProperty("token_type")
	private String tokenType;
	
	@Override
	public String toString() {
		return "ACTToken [accessToken=" + accessToken + ", scope=" + scope + ", expiresIn=" + expiresIn + ", tokenType="
				+ tokenType + "]";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
}

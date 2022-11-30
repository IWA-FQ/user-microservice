package com.polylink.usermicroservice.payload.response;

import com.polylink.usermicroservice.dto.ResponseUser;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private ResponseUser user;
	private List<String> roles;

	public JwtResponse(String accessToken, ResponseUser user, List<String> roles) {
		this.token = accessToken;
		this.user = user;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public ResponseUser getUser() {
		return user;
	}
	public void setUser(ResponseUser user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}
}

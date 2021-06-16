package com.Toucomex.Importation_Toucomex.Auth.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtResponse {
	private List<String> roles;
	private final String email;
	private String token;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
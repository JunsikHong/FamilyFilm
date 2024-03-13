package com.jun.familyfilm.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class JwtTokenAuthentication extends AbstractAuthenticationToken{

	private static final long serialVersionUID = 1L;
	private final Object principal;
	private Object credentials;

	public JwtTokenAuthentication(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // 필수 필드 설정
	}
	

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}


}

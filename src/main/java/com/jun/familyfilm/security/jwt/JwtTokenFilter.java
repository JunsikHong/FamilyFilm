package com.jun.familyfilm.security.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter (JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Authorization 추출
		String authorizationHeader = request.getHeader("Authorization");
		
		// Authorization 검사
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 토큰 추출
		String token = authorizationHeader.split(" ")[1];
		String refreshToken = request.getHeader("refreshToken").split(" ")[1];
		System.out.println(refreshToken + "============================================");
		
		// 토큰 검사
		if(jwtTokenProvider.validateToken(token)) {
			if(!jwtTokenProvider.validateToken(refreshToken)) {
				String inputId = jwtTokenProvider.getUser_id(token);
				if(inputId != null) {
					Authentication authentication = jwtTokenProvider.getAuthentication(token);
					Authentication realAuthentication = jwtTokenProvider.authenticate(authentication);
					SecurityContextHolder.getContext().setAuthentication(realAuthentication);
				}
			}
			filterChain.doFilter(request, response);
			return;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// loginId 추출
		String inputId = jwtTokenProvider.getUser_id(token);

		// loginUser 정보로 UsernamePasswordAuthenticationToken 발급
		if(inputId != null) {
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			Authentication realAuthentication = jwtTokenProvider.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(realAuthentication);
		}

		filterChain.doFilter(request, response);

	}
}

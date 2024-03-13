package com.jun.familyfilm.security.jwt;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.jun.familyfilm.command.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider implements AuthenticationProvider{

	//시크릿키
	private final String jwtSecretKey = "familyfilmsecretkeyfamilyfilmsecretkeyfamilyfilmsecretkeyfamilyfilmsecretkeyfamilyfilmsecretkey123";

	//일반토큰 만료시간
	private final long accessTokenValid = 1000L * 60 * 60;
	
	//refresh토큰 만료시간
	private final long refreshTokenValid = 1000L * 60 * 60 * 60 * 60;

	//토큰 발행
	public String[] createToken (UserVO userVO) {
		Date now = new Date();
		
		String accessToken = Jwts.builder()
				.setSubject(userVO.user_id)
				.setIssuedAt(new Date())
				.setExpiration(new Date(now.getTime() + accessTokenValid))
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey)
				.compact();
		
		String refreshToken = Jwts.builder()
				.setSubject(userVO.user_id)
				.setIssuedAt(new Date())
				.setExpiration(new Date(now.getTime() + refreshTokenValid))
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey)
				.compact();
		
		String[] tokens = {"Bearer " + accessToken, "Bearer " + refreshToken};
		return tokens;
	}

	//토큰에서 user_id 추출
	public String getUser_id(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	//토큰 만료 여부 확인
	public boolean validateToken(String jwtToken) {
		try {        
			Date expirationDate = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken).getBody().getExpiration();
			return expirationDate.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public Authentication getAuthentication(String token) {
		// 토큰에서 필요한 정보 추출
		String user_id = Jwts.parser()
				.setSigningKey(jwtSecretKey) // 서명 키 설정
				.parseClaimsJws(token)
				.getBody().getSubject();
		
		// Authentication 객체 생성 및 반환
		JwtTokenAuthentication jwtTokenAuthentication = new JwtTokenAuthentication(user_id, null);

		return jwtTokenAuthentication;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtTokenAuthentication authenticationToken = (JwtTokenAuthentication) authentication;
        String principal = (String) authenticationToken.getPrincipal();
		JwtTokenAuthentication authenticated = new JwtTokenAuthentication(principal, null);
		return authenticated;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return ClassUtils.isAssignable(JwtTokenAuthentication.class, authentication);
	}

}

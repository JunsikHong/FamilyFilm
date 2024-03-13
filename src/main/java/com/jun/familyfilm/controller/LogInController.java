package com.jun.familyfilm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jun.familyfilm.command.UserVO;
import com.jun.familyfilm.login.service.logInService;
import com.jun.familyfilm.security.jwt.JwtTokenAuthentication;
import com.jun.familyfilm.security.jwt.JwtTokenProvider;

@RestController
public class LogInController {
	
	@Autowired
	private logInService logInService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping (value = "/login")
	public ResponseEntity<Integer> login (@RequestBody Map<String, Object> data) {
		if ((String)data.get("user_pw") == null || (String)data.get("user_pw") == "") {
	        return ResponseEntity.ok().body(0);
	    }
		
		UserVO userVO = new UserVO();
		userVO = userVO.builder()
				.user_id((String)data.get("user_id"))
				.user_pw((String)data.get("user_pw"))
				.build();
		
		UserVO result = logInService.login(userVO.getUser_id());
		if(result != null && bCryptPasswordEncoder.matches(userVO.user_pw, result.user_pw)) {
			String[] tokens = new JwtTokenProvider().createToken(userVO);
			return ResponseEntity.ok().header("Authorization", tokens[0]).header("refreshtoken", tokens[1]).body(1);
		} else {
			return ResponseEntity.ok().body(0);
		}
	}

	//아이디 중복검사
	@PostMapping(value = "/idDuplicateCheck")
	public ResponseEntity<Integer> idDuplicateCheck (@RequestBody Map<String, Object> data) {
		int result = logInService.idDuplicateCheck((String)data.get("user_id"));
		return ResponseEntity.ok().body(result);
	}

	//회원가입 
	@PostMapping (value ="/join")
	public ResponseEntity<Integer> join (@RequestBody Map<String, Object> data) {
		UserVO userVO = new UserVO();
		userVO = userVO.builder()
				.user_id((String)data.get("user_id"))
				.user_pw(bCryptPasswordEncoder.encode((String)data.get("user_pw")))
				.user_name((String)data.get("user_name"))
				.user_birth((String)data.get("user_birth"))
				.build();
		int result = logInService.join(userVO);
		return ResponseEntity.ok().body(result);
	}
}

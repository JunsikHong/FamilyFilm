package com.jun.familyfilm.login.service;

import com.jun.familyfilm.command.UserVO;

public interface logInService {

	public UserVO login (String user_id); //로그인
	public int join (UserVO userVO); //회원가입
	public int idDuplicateCheck (String user_id); //아이디 중복검사
}

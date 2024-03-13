package com.jun.familyfilm.login.service;

import org.apache.ibatis.annotations.Mapper;

import com.jun.familyfilm.command.UserVO;

@Mapper
public interface logInMapper {

	public UserVO login (String user_id); //로그인
	public int join (UserVO userVO); //회원가입
	public int idDuplicateCheck (String user_id); //아이디 중복검사
}

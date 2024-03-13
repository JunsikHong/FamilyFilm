package com.jun.familyfilm.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.familyfilm.command.UserVO;

@Service("logInService")
public class logInServiceImpl implements logInService{

	@Autowired
	private logInMapper logInMapper;

	@Override
	public UserVO login(String user_id) {
		return logInMapper.login(user_id);
	}

	@Override
	public int join(UserVO userVO) {
		return logInMapper.join(userVO);
	}

	@Override
	public int idDuplicateCheck(String user_id) {
		return logInMapper.idDuplicateCheck(user_id);
	}
}

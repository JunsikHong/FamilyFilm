package com.jun.familyfilm.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.familyfilm.command.NotificationVO;
import com.jun.familyfilm.notification.service.notificationService;
import com.jun.familyfilm.security.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private notificationService notificationService;
	
	@Autowired
	public JwtTokenProvider jwtTokenProvider;
	
	@PostMapping(value = "/selectNotification")
	public ResponseEntity<ArrayList<NotificationVO>> selectNotification(@RequestHeader("Authorization") String authorization) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		ArrayList<NotificationVO> result = notificationService.selectNotification(user_id);
		return ResponseEntity.ok().body(result);
	}
}

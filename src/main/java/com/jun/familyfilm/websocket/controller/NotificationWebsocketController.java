package com.jun.familyfilm.websocket.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import com.jun.familyfilm.command.NotificationVO;
import com.jun.familyfilm.command.UserVO;
import com.jun.familyfilm.notification.service.notificationService;
import com.jun.familyfilm.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationWebsocketController {

	@Autowired
	@Qualifier("notificationService")
	public notificationService notificationService;

	@Autowired
	public JwtTokenProvider jwtTokenProvider;
	
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@EventListener(SessionConnectEvent.class)
	public void onConnect(SessionConnectEvent event) {
		System.out.println("웹소켓 연결완료");
	}
	
	@EventListener(SessionConnectEvent.class)
	public void onPublish(SessionConnectEvent event) {
		System.out.println("구독완료");
	}

	@MessageMapping("/recieve/{token}")
	public void recieve(@DestinationVariable String token) {
		String user_id = jwtTokenProvider.getUser_id(token.split(" ")[1]);
		ArrayList<NotificationVO> list = notificationService.selectNotification(user_id);
		simpMessagingTemplate.convertAndSend("recieve/" + token, list);
	}
	
	@MessageMapping("/submit/{input_family_group_num}")
	public void submit(@DestinationVariable String input_family_group_num,
					   NotificationVO notificationVO) {
		String result = "";
		
		//user 존재 여부
		String user_id = notificationVO.getUser_id();
		
		UserVO isExistUser = notificationService.findUserId(user_id);
		if(isExistUser == null || isExistUser.getUser_id() == "") {
			result = "아이디가 존재하지 않습니다.";
			simpMessagingTemplate.convertAndSend("/invite/" + input_family_group_num, result);
			return;
		}
		
		//familyname 조회
		String family_group_num = notificationVO.getFamily_group_num();
		String family_name = notificationService.findFamilyName(family_group_num).getFamily_name();
		
		//invite 알람
		NotificationVO newNotificationVO = new NotificationVO();
		newNotificationVO.setFamily_group_num(family_group_num);
		newNotificationVO.setUser_id(user_id);
		newNotificationVO.setNotification_type("1");
		newNotificationVO.setNotification_title(user_id + "님에게 " + family_name + "으로 초대요청을 보냈습니다.");
		int temp = notificationService.invite(newNotificationVO);
		
		if(temp == 0) {
			result = "초대요청을 보내지 못했습니다.";
			simpMessagingTemplate.convertAndSend("/invite/" + input_family_group_num, result);
			return;
		} else {
			result = "초대요청을 보냈습니다.";
			simpMessagingTemplate.convertAndSend("/invite/" + input_family_group_num, result);			
			return;
		}
	}
	
	@MessageMapping("/inviteAccept")
	public void inviteAccept(NotificationVO notificationVO) {
		int result = notificationService.inviteAccept(notificationVO.getNotification_proper_num());		
		if(result == 1) {
			simpMessagingTemplate.convertAndSend("/notification/recieve/", "초대요청을 수락하였습니다.");
		} else {
			simpMessagingTemplate.convertAndSend("/notification/recieve/", "수락 실패");
		}
	}
	
	@MessageMapping("/inviteDeny")
	public void inviteDeny(NotificationVO notificationVO) {
		int result = notificationService.inviteDeny(notificationVO.getNotification_proper_num());
		if(result == 1) {
			simpMessagingTemplate.convertAndSend("/notification/recieve/", "초대요청을 거절하였습니다.");
		} else {
			simpMessagingTemplate.convertAndSend("/notification/recieve/", "거절 실패");
		}
	}
}

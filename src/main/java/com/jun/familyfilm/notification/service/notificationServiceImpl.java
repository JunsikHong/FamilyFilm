package com.jun.familyfilm.notification.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.familyfilm.command.FamilyVO;
import com.jun.familyfilm.command.NotificationVO;
import com.jun.familyfilm.command.UserVO;

@Service("notificationService")
public class notificationServiceImpl implements notificationService{

	@Autowired
	private notificationMapper notificationMapper;

	@Override
	public int invite(NotificationVO notificationVO) {
		return notificationMapper.invite(notificationVO);
	}

	@Override
	public UserVO findUserId(String user_id) {
		return notificationMapper.findUserId(user_id);
	}

	@Override
	public FamilyVO findFamilyName(String family_group_num) {
		return notificationMapper.findFamilyName(family_group_num);
	}

	@Override
	public ArrayList<NotificationVO> selectNotification(String user_id) {
		return notificationMapper.selectNotification(user_id);
	}

	@Override
	public int inviteAccept(String notification_proper_num) {
		return notificationMapper.inviteAccept(notification_proper_num);
	}

	@Override
	public int inviteDeny(String notification_proper_num) {
		return notificationMapper.inviteDeny(notification_proper_num);
	} 
}

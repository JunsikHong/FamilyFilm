package com.jun.familyfilm.notification.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.jun.familyfilm.command.FamilyVO;
import com.jun.familyfilm.command.NotificationVO;
import com.jun.familyfilm.command.UserVO;

@Mapper
public interface notificationMapper {

	public int invite(NotificationVO notificationVO);
	public UserVO findUserId(String user_id);
	public FamilyVO findFamilyName(String family_group_num);
	public ArrayList<NotificationVO> selectNotification(String user_id);
	public int inviteAccept(String notification_proper_num);//초대 수락
	public int inviteDeny(String notification_proper_num);//초대 거부
}

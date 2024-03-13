package com.jun.familyfilm.notification.service;

import java.util.ArrayList;

import com.jun.familyfilm.command.FamilyVO;
import com.jun.familyfilm.command.NotificationVO;
import com.jun.familyfilm.command.UserVO;

public interface notificationService {

	public int invite(NotificationVO notificationVO); //초대하기
	public UserVO findUserId(String user_id); //초대하는 상대방 조회
	public FamilyVO findFamilyName(String family_group_num); //familyname 조회
	public ArrayList<NotificationVO> selectNotification(String user_id); //유저아이디 기반으로 알림 리스트 가져오기
	public int inviteAccept(String notification_proper_num);//초대 수락
	public int inviteDeny(String notification_proper_num);//초대 거부
}

package com.jun.familyfilm.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationVO {

	private String notification_proper_num;
	private String notification_title;
	private String notification_content;
	private String notification_type;
	private String notification_read_yn;
	private String notification_invite_yn;
	private String notification_date;
	private String user_id;
	private String family_proper_num;
	private String family_group_num;
	
	//join
	private String family_name;
	
}

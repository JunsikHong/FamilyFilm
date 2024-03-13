package com.jun.familyfilm.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleVO {

	private String schedule_proper_num;
	private String schedule_title;
	private String schedule_date;
	private String schedule_color;
	private String schedule_ar;
	private String schedule_ar_zonecode;
	private String schedule_ar_detail;
	private String schedule_notification_yn;
	private String family_proper_num;
	private String family_group_num;
	private String user_id;
}

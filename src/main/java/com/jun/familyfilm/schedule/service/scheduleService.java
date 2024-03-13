package com.jun.familyfilm.schedule.service;

import java.util.ArrayList;

import com.jun.familyfilm.command.ScheduleVO;

public interface scheduleService {

	public ArrayList<ScheduleVO> getSchedulePreviewList(String family_group_num, int month);
	public ArrayList<ScheduleVO> getScheduleDetail(String family_proper_num, String scheduleDate);
	public int saveNewSchedule(ScheduleVO scheduleVO);
	public int saveModifySchedule(ScheduleVO scheduleVO);
	public int deleteSchedule(String schedule_proper_num);
}

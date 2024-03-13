package com.jun.familyfilm.schedule.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jun.familyfilm.command.ScheduleVO;

@Mapper
public interface scheduleMapper {

	public ArrayList<ScheduleVO> getSchedulePreviewList(@Param("family_group_num") String family_group_num,
														@Param("month") int month);
	public ArrayList<ScheduleVO> getScheduleDetail(@Param("family_group_num") String family_group_num,
												   @Param("scheduleDate") String scheduleDate);
	public int saveNewSchedule(ScheduleVO scheduleVO);
	public int saveModifySchedule(ScheduleVO scheduleVO);
	public int deleteSchedule(String schedule_proper_num);
}

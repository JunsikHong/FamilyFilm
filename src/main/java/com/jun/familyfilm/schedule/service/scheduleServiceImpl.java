package com.jun.familyfilm.schedule.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.familyfilm.command.ScheduleVO;

@Service("scheduleService")
public class scheduleServiceImpl implements scheduleService{

	@Autowired
	private scheduleMapper scheduleMapper;
	
	@Override
	public ArrayList<ScheduleVO> getSchedulePreviewList(String family_group_num, int month) {
		return scheduleMapper.getSchedulePreviewList(family_group_num, month);
	}

	@Override
	public ArrayList<ScheduleVO> getScheduleDetail(String family_group_num, String scheduleDate) {
		return scheduleMapper.getScheduleDetail(family_group_num, scheduleDate);
	}

	@Override
	public int saveNewSchedule(ScheduleVO scheduleVO) {
		return scheduleMapper.saveNewSchedule(scheduleVO);
	}

	@Override
	public int saveModifySchedule(ScheduleVO scheduleVO) {
		return scheduleMapper.saveModifySchedule(scheduleVO);
	}

	@Override
	public int deleteSchedule(String schedule_proper_num) {
		return scheduleMapper.deleteSchedule(schedule_proper_num);
	}

}

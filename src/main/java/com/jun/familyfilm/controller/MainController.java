package com.jun.familyfilm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jun.familyfilm.command.FamilyVO;
import com.jun.familyfilm.command.MemoVO;
import com.jun.familyfilm.command.ScheduleVO;
import com.jun.familyfilm.memo.service.memoService;
import com.jun.familyfilm.schedule.service.scheduleService;
import com.jun.familyfilm.security.jwt.JwtTokenProvider;
import com.jun.familyfilm.family.service.familyService;

@RestController
public class MainController {

	@Autowired
	@Qualifier("memoService")
	private memoService memoService;

	@Autowired
	@Qualifier("scheduleService")
	private scheduleService scheduleService;

	@Autowired
	@Qualifier("familyService")
	private familyService familyService;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//family 그룹 여부
	@PostMapping (value = "/isExistFamily")
	public ResponseEntity<ArrayList<FamilyVO>> isExistFamily(@RequestHeader("Authorization") String authorization) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		ArrayList<FamilyVO> list = familyService.isExistFamily(user_id);
		return ResponseEntity.ok().body(list);
	}

	//family 등록
	@PostMapping (value = "/registFamily")
	public ResponseEntity<String> registFamily(@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, Object> data) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		FamilyVO familyVO = new FamilyVO();
		if(data.get("family_group_num") != null && !data.get("family_group_num").equals((Object)"")) {
			familyVO.setFamily_group_num((String)data.get("family_group_num"));
		}
		familyVO = familyVO.builder()
				.family_name((String)data.get("family_name"))
				.family_group_pw((String)data.get("family_group_pw"))
				.family_introduce((String)data.get("family_introduce"))
				.family_relationship_proper_num((String)data.get("family_relationship_proper_num"))
				.user_id(user_id)
				.build();
		String result = familyService.registFamily(familyVO);
		return ResponseEntity.ok().body(result);
	}

	//초대응답 -> family 등록
	@PostMapping (value = "/inputRelationY")
	public ResponseEntity<Integer> inputRelationY (@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, Object> data) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		FamilyVO familyVO = new FamilyVO();
		familyVO = familyVO.builder()
				.family_group_num((String)data.get("family_group_num"))
				.family_relationship_proper_num((String)data.get("family_relationship_proper_num"))
				.user_id(user_id)
				.build();
		int result = familyService.inputRelationY(familyVO);
		return ResponseEntity.ok().body(result);
	}
	
	//가족정보
	@PostMapping (value = "/getFamilyInfo")
	public ResponseEntity<FamilyVO> getFamilyInfo(@RequestBody Map<String, Object> data) {
		FamilyVO familyInfo = familyService.getFamilyInfo((String)data.get("family_group_num"));
		return ResponseEntity.ok().body(familyInfo);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//main 화면 메모 정보 불러오기
	@PostMapping (value = "/getMemo")
	public ResponseEntity<ArrayList<MemoVO>> getMemo(@RequestBody Map<String, Object> data) {
		String family_group_num = (String)data.get("family_group_num");
		ArrayList<MemoVO> memoList = new ArrayList<>(memoService.getMemoPreviewList(family_group_num));
		return ResponseEntity.ok().body(memoList);
	}

	//main 화면 메모 정보 more 불러오기
	@PostMapping (value = "/getMoreMemo")
	public ResponseEntity<ArrayList<MemoVO>> getMoreMemo(@RequestBody Map<String, Object> data) {
		ArrayList<MemoVO> memoList = new ArrayList<>(memoService.getMoreMemoPreviewList((Integer)data.get("memoLength")));
		return ResponseEntity.ok().body(memoList);
	}

	//main 화면 메모 상세 정보 불러오기
	@PostMapping (value = "/getMemoDetail")
	public ResponseEntity<MemoVO> getMemoDetail(@RequestBody Map<String, Object> data) {
		MemoVO memo = memoService.getMemoDetail((String)data.get("memoProperNum"));
		return ResponseEntity.ok().body(memo);
	}

	@PostMapping (value = "/modifyMemo")
	public ResponseEntity<Integer> modifyMemo(@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, Object> data) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		MemoVO memoVO = new MemoVO();
		memoVO.setMemo_proper_num((String)data.get("memo_proper_num"));
		memoVO.setMemo_title((String)data.get("memo_title"));
		memoVO.setMemo_content((String)data.get("memo_content"));
		memoVO.setUser_id(user_id);
		int result = memoService.modifyMemo(memoVO);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping (value = "/createMemo")
	public ResponseEntity<Integer> createMemo(@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, Object> data) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		MemoVO memoVO = new MemoVO();
		memoVO.setMemo_title((String)data.get("memo_title"));
		memoVO.setMemo_content((String)data.get("memo_content"));
		memoVO.setFamily_group_num((String)data.get("family_group_num"));
		memoVO.setUser_id(user_id);
		int result = memoService.createMemo(memoVO);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping (value = "/deleteMemo")
	public ResponseEntity<Integer> deleteMemo(@RequestBody Map<String, Object> data) {
		MemoVO memoVO = new MemoVO();
		memoVO.setMemo_proper_num((String)data.get("memo_proper_num"));
		int result = memoService.deleteMemo(memoVO);
		return ResponseEntity.ok().body(result);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//main 화면 스케쥴 정보 불러오기
	@PostMapping (value = "/getSchedule")
	public ResponseEntity<ArrayList<ScheduleVO>> getSchedule(@RequestBody Map<String, Object> data) {
		String family_group_num = (String)data.get("family_group_num");
		int month = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			Date date = sdf.parse((String)data.get("scheduleDate"));
			month = date.getMonth()+1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<ScheduleVO> scheduleList = new ArrayList<>(scheduleService.getSchedulePreviewList(family_group_num, month));
		return ResponseEntity.ok().body(scheduleList);
	}

	//main 화면 스케쥴 상세 정보 불러오기
	@PostMapping (value = "/getScheduleDetail")
	public ResponseEntity<ArrayList<ScheduleVO>> getScheduleDetail(@RequestBody Map<String, Object> data) {
		System.out.println((String)data.get("scheduleDate") + "==================");
		ArrayList<ScheduleVO> scheduleList = scheduleService.getScheduleDetail((String)data.get("family_group_num"), (String)data.get("scheduleDate"));
		return ResponseEntity.ok().body(scheduleList);
	}

	//main 화면 새로운 스케쥴 저장
	@PostMapping(value = "/saveNewSchedule")
	public ResponseEntity<Integer> saveNewSchedule(@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, Object> data) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		ScheduleVO scheduleVO = new ScheduleVO();
		scheduleVO.setSchedule_title((String)data.get("schedule_title"));
		scheduleVO.setSchedule_date((String)data.get("schedule_date"));
		scheduleVO.setSchedule_ar((String)data.get("schedule_location"));
		scheduleVO.setSchedule_color((String)data.get("schedule_color"));
		scheduleVO.setFamily_group_num((String)data.get("family_group_num"));
		scheduleVO.setUser_id(user_id);
		int result = scheduleService.saveNewSchedule(scheduleVO);		
		return ResponseEntity.ok().body(result);
	}

	//main 화면 새로운 스케쥴 저장
	@PostMapping(value = "/saveModifySchedule")
	public ResponseEntity<Integer> saveModifySchedule(@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, Object> data) {
		String user_id = new JwtTokenProvider().getUser_id(authorization.split(" ")[1]);
		ScheduleVO scheduleVO = new ScheduleVO();
		scheduleVO.setSchedule_proper_num((String)data.get("schedule_proper_num"));
		scheduleVO.setSchedule_title((String)data.get("schedule_title"));
		scheduleVO.setSchedule_date((String)data.get("schedule_date"));
		scheduleVO.setSchedule_ar((String)data.get("schedule_location"));
		scheduleVO.setSchedule_color((String)data.get("schedule_color"));
		scheduleVO.setFamily_group_num((String)data.get("family_group_num"));
		scheduleVO.setUser_id(user_id);
		int result = scheduleService.saveModifySchedule(scheduleVO);		
		return ResponseEntity.ok().body(result);
	}

	//main 화면 스케쥴 삭제
	@PostMapping(value = "/deleteSchedule")
	public ResponseEntity<Integer> deleteSchedule(@RequestBody Map<String, Object> data) {
		int result = scheduleService.deleteSchedule((String)data.get("schedule_proper_num"));
		return ResponseEntity.ok().body(result);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

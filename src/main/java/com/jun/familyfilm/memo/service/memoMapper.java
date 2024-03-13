package com.jun.familyfilm.memo.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.jun.familyfilm.command.MemoVO;

@Mapper
public interface memoMapper {

	public ArrayList<MemoVO> getMemoPreviewList(String family_group_num); //memo 5개 가져오기
	public ArrayList<MemoVO> getMoreMemoPreviewList(Integer memoLength); //memo 더 가져오기
	public MemoVO getMemoDetail(String memo_proper_num); //memo 상세정보 가져오기
	public int modifyMemo(MemoVO memoVO); //memo 수정
	public int createMemo(MemoVO memoVO); //memo 추가
	public int deleteMemo(MemoVO memoVO); //memo 삭제
}

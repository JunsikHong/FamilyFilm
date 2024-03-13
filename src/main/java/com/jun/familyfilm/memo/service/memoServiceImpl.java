package com.jun.familyfilm.memo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.familyfilm.command.MemoVO;

@Service("memoService")
public class memoServiceImpl implements memoService{

	@Autowired
	private memoMapper memoMapper; 
	
	//memo 5개 가져오기
	@Override
	public ArrayList<MemoVO> getMemoPreviewList(String family_group_num) {
		return memoMapper.getMemoPreviewList(family_group_num);
	}

	@Override
	public MemoVO getMemoDetail(String memo_proper_num) {
		return memoMapper.getMemoDetail(memo_proper_num);
	}

	@Override
	public ArrayList<MemoVO> getMoreMemoPreviewList(Integer memoLength) {
		return memoMapper.getMoreMemoPreviewList(memoLength + 12);
	}

	@Override
	public int modifyMemo(MemoVO memoVO) {
		return memoMapper.modifyMemo(memoVO);
	}

	@Override
	public int createMemo(MemoVO memoVO) {
		return memoMapper.createMemo(memoVO);
	}

	@Override
	public int deleteMemo(MemoVO memoVO) {
		return memoMapper.deleteMemo(memoVO);
	}

}

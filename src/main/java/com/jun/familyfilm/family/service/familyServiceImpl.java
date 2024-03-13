package com.jun.familyfilm.family.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.familyfilm.command.FamilyVO;

@Service("familyService")
public class familyServiceImpl implements familyService{

	@Autowired
	private familyMapper familyMapper;

	@Override
	public ArrayList<FamilyVO> isExistFamily(String user_id) {
		return familyMapper.isExistFamily(user_id);
	}

	@Override
	public String registFamily(FamilyVO familyVO) {
		int result = familyMapper.registFamily(familyVO);
		if(result == 1) {
			return familyMapper.getFamilyGroupNum(familyVO.getUser_id());
		} else {
			return "fail";			
		}
	}

	@Override
	public int inputRelationY(FamilyVO familyVO) {
		FamilyVO getFamilyVO = familyMapper.getFamilyInfo(familyVO.getFamily_group_num());
		getFamilyVO.setFamily_relationship_proper_num(familyVO.getFamily_relationship_proper_num());
		getFamilyVO.setUser_id(familyVO.getUser_id());
		return familyMapper.inputRelationY(getFamilyVO);
	}

	@Override
	public FamilyVO getFamilyInfo(String family_group_num) {
		return familyMapper.getFamilyInfo(family_group_num);
	}
}

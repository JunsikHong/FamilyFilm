package com.jun.familyfilm.family.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.jun.familyfilm.command.FamilyVO;

@Mapper
public interface familyMapper {

	public ArrayList<FamilyVO> isExistFamily(String user_id);
	public int registFamily(FamilyVO familyVO);
	public String getFamilyGroupNum(String user_id);
	public FamilyVO getFamilyInfo(String family_group_num);
	public int inputRelationY(FamilyVO familyVO);
}

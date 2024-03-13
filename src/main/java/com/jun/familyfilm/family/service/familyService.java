package com.jun.familyfilm.family.service;

import java.util.ArrayList;

import com.jun.familyfilm.command.FamilyVO;

public interface familyService {

	public ArrayList<FamilyVO> isExistFamily(String user_id);
	public String registFamily(FamilyVO familyVO);
	public int inputRelationY(FamilyVO familyVO);
	public FamilyVO getFamilyInfo(String family_group_num);
}

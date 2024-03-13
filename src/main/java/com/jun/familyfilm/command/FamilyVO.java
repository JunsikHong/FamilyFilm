package com.jun.familyfilm.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyVO {

	private String family_proper_num;
	private String family_group_num;
	private String family_group_pw;
	private String family_name;
	private String family_introduce;
	private String user_id;
	private String family_relationship_proper_num;
}

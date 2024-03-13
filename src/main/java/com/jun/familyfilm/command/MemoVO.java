package com.jun.familyfilm.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoVO {

	private String memo_proper_num;
	private String memo_group;
	private String memo_title;
	private String memo_content;
	private String memo_share_yn;
	private String memo_important_yn;
	private String memo_create_date;
	private String memo_modify_date;
	private String family_proper_num;
	private String family_group_num;
	private String user_id;
	
	//join
	private String family_relationship_proper_num;
	
}

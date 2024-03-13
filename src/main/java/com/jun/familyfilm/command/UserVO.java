package com.jun.familyfilm.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {

	public String user_proper_num;
	public String user_id;
	public String user_pw;
	public String user_name;
	public String user_birth;
	public String user_email;
	public String user_phone;
	public String user_ar;
	public String user_ar_zonecode;
	public String user_ar_detail;
	public String user_joindate;
	public String user_delete_yn;
	
}

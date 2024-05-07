package com.kosa.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberVO {

	private int member_no;
	private String member_name;
	private String reg_no;
	private String tel_no;
	private String email;
	private String id;
	private String pwd;
}

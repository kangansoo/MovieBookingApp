package com.kosa.member.controller;

import java.sql.SQLException;

public interface MemberController {
	// 로그인
	void login(String id, String pwd) throws SQLException;

}

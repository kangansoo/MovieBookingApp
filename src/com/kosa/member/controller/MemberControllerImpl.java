package com.kosa.member.controller;

import java.sql.SQLException;

import com.kosa.member.dao.MemberDAO;
import com.kosa.member.dao.MemberDAOImpl;

public class MemberControllerImpl implements MemberController {
	private MemberDAO memberDAO;

	public MemberControllerImpl() {
		memberDAO = new MemberDAOImpl();
	}

	@Override
	public void login(String id, String pwd) throws SQLException {
		memberDAO.findByMemberId(id, pwd);
	}

}

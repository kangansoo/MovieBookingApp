package com.kosa.member.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kosa.common.base.DBConnection;
import com.kosa.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;

	public MemberDAOImpl() {
		conn = DBConnection.getConnection();
	}

	@Override
	public List<MemberVO> selectMember() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMember() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMember() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMember() throws SQLException {
		// TODO Auto-generated method stub

	}

	// 로그인
	@Override
	public void findByMemberId(String id, String pwd) throws SQLException {
		String query = "{ call login_proc(?, ?) }";
		cstmt = conn.prepareCall(query);
		cstmt.setString(1, id);
		cstmt.setString(2, pwd);

		cstmt.executeQuery();
	}

}

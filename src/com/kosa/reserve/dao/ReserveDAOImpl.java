package com.kosa.reserve.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.kosa.common.base.DBConnection;
import com.kosa.member.vo.MemberVO;

public class ReserveDAOImpl implements ReserveDAO{
	
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	public ReserveDAOImpl() {
		conn = DBConnection.getConnection();
	}
	
	@Override
	public int insertReservation(int reserveQuantity, int memberNum) throws SQLException {
		int reserveNo = 0;
		int memberNo = 1;
	    // 프로시저 호출 방식 수정
	    String query = "{ call INSERT_RESERVE(?, ?, ?) }"; // OUT 매개변수 설정
	    cstmt = conn.prepareCall(query);
	    cstmt.setInt(1, reserveQuantity);
	    cstmt.setInt(2, memberNo);
	    cstmt.registerOutParameter(3, Types.INTEGER);
	    cstmt.execute(); // 프로시저 실행

	    // OUT 매개변수에서 결과 가져오기
	    reserveNo = cstmt.getInt(3);

	    cstmt.close();
	    // movieInfoMap 반환
	    return reserveNo;
	}
	public static void main(String[] args) {
		ReserveDAOImpl test = new ReserveDAOImpl();
		MemberVO member = new MemberVO();
		int memberNum=member.getMemberNo();
		try {
			System.out.println(test.insertReservation(1, memberNum));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

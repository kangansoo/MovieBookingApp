package com.kosa.reserve.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.kosa.common.base.DBConnection;
import com.kosa.member.vo.MemberVO;

public class ReserveDAOImpl implements ReserveDAO {

	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;

	public ReserveDAOImpl() {
		conn = DBConnection.getConnection();
	}

	@Override
	public int insertReservation(int reserveQuantity, int memberNo) throws SQLException {
		int reserveNo = 0;
//		int memberNo = 1;
		// 프로시저 호출 방식 수정
		String query = "{ call ticket_logic_pkg.INSERT_RESERVE(?, ?, ?) }"; // OUT 매개변수 설정
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

	// 상영 DATE, TIME 상영관 이름으로 SCHEDULE_NO 가져오기
	@Override
	public int getScheduleNo(String scheduleDate, String scheduleTime, String screenName) throws SQLException {
		// 프로시저 호출을 위한 CallableStatement 생성
		String sql = "{ call ticket_logic_pkg.Get_Schedule_No(?, ?, ?, ?) }";
		cstmt = conn.prepareCall(sql);

		// 입력 파라미터 설정
		cstmt.setString(1, scheduleDate);
		cstmt.setString(2, scheduleTime);
		cstmt.setString(3, screenName);

		// 출력 파라미터 설정
		cstmt.registerOutParameter(4, Types.INTEGER);

		// 프로시저 실행
		cstmt.execute();

		// 출력 파라미터 값 가져오기
		return cstmt.getInt(4);
	}

	@Override
	public int selectCharge(int reserveNo, int memberNo) throws SQLException {
		int charge = 0;
		String sql = "{ call get_charge(?, ?, ?)}";
		cstmt = conn.prepareCall(sql);

		// 입력 파라미터 설정
		cstmt.setInt(1, reserveNo);
		cstmt.setInt(2, memberNo);

		// 출력 파라미터 설정
		cstmt.registerOutParameter(3, Types.INTEGER);

		// 프로시저 실행
		cstmt.execute();
		
		charge = cstmt.getInt(3);
		
		return charge;
	}

}

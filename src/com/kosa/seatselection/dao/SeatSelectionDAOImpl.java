package com.kosa.seatselection.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kosa.common.base.DBConnection;

import oracle.jdbc.OracleTypes;

public class SeatSelectionDAOImpl implements SeatSelectionDAO {
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;

	public SeatSelectionDAOImpl() {
		conn = DBConnection.getConnection();
	}

	@Override
	public List<String> selectSeat(String movieTitle, String selectedDate, String screenName, String time)
			throws SQLException {
		List<String> selectedSeat = new ArrayList<>();
		String query = "{call SelectedSeats(?, ?, ?, ?, ?)}";
		cstmt = conn.prepareCall(query);
		cstmt.setString(1, movieTitle);
		cstmt.setString(2, selectedDate);
		cstmt.setString(3, screenName);
		cstmt.setString(4, time);
		cstmt.registerOutParameter(5, OracleTypes.CURSOR);
		cstmt.execute(); // 프로시저 실행

		rs = (ResultSet) cstmt.getObject(5);
		while (rs.next()) {
			String selectedRow = rs.getString(1);
			String selectedColumn = rs.getString(2);
			String selectedSeats = selectedRow + " " + selectedColumn;
			// 각 결과 값을 각각의 리스트에 추가
			selectedSeat.add(selectedSeats);
		}
		cstmt.close();

		return selectedSeat;
	}
	//좌석선택 (DB INSERT)
	@Override
	public HashMap<String, Integer> insertSeat(String row, int columnNum, String screenName) throws SQLException {
		HashMap<String, Integer> resultMap = new HashMap<>();
		String sql = "{ call INSERT_SEAT(?, ?, ?, ?, ?) }";
		cstmt = conn.prepareCall(sql);
		// 입력 파라미터 설정
		cstmt.setString(1, row);
		cstmt.setInt(2, columnNum);
		cstmt.setString(3, screenName);

		// 출력 파라미터 설정
		cstmt.registerOutParameter(4, Types.INTEGER);
		cstmt.registerOutParameter(5, Types.INTEGER);

		// 프로시저 실행
		cstmt.execute();

		// 출력 파라미터 값 가져오기
		int seatNo = cstmt.getInt(4);
		int screenNo = cstmt.getInt(5);

		// 결과를 HashMap에 저장
		resultMap.put("seatNo", seatNo);
		resultMap.put("screenNo", screenNo);

		return resultMap;
	}

}

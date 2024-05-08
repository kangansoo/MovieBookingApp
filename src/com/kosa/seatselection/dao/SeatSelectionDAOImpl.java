package com.kosa.seatselection.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	        String selectedSeats = selectedRow + " " +selectedColumn;
	        // 각 결과 값을 각각의 리스트에 추가
	        selectedSeat.add(selectedSeats);
	    }
	    cstmt.close();
		
		return selectedSeat;
	}

}

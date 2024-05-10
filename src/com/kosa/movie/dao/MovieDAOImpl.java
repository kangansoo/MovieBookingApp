package com.kosa.movie.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kosa.common.base.DBConnection;

import oracle.jdbc.OracleTypes;

public class MovieDAOImpl implements MovieDAO {
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	public MovieDAOImpl() {
		conn = DBConnection.getConnection();
	}

	@Override
	public List<String> selectMovie(String selectedDate) throws SQLException {
	    List<String> movieTitles = new ArrayList<>();
	    // 프로시저 호출 방식 수정
	    String query = "{ call GET_MOVIES_BY_DATE(?, ?) }"; // OUT 매개변수 설정
	    cstmt = conn.prepareCall(query);
	    cstmt.setString(1, selectedDate); // p_schedule_date
        cstmt.registerOutParameter(2, OracleTypes.CURSOR); 
	    cstmt.execute(); // 프로시저 실행

	    // OUT 매개변수에서 결과 가져오기
	    rs = (ResultSet) cstmt.getObject(2);
	    while (rs.next()) {
	        String movieTitle = rs.getString(1);
	        
	        // 각 결과 값을 각각의 리스트에 추가
	        movieTitles.add(movieTitle);
	    }
	    cstmt.close();
	    // movieInfoMap 반환
	    return movieTitles;
	}

	@Override
	public List<String> selectScreenSchedule(String seletedDate, String movieTitle) throws SQLException {
		List<String> screenSchedule = new ArrayList<>();
		String query = "{ call GET_SCREEN_SCHEDULE_FOR_MOVIE(?, ?, ?) }"; // OUT 매개변수 설정
	    cstmt = conn.prepareCall(query);
	    cstmt.setString(1, seletedDate);
	    cstmt.setString(2, movieTitle);
        cstmt.registerOutParameter(3, OracleTypes.CURSOR); 
	    cstmt.execute(); // 프로시저 실행
		
	    rs = (ResultSet) cstmt.getObject(3);
	    while (rs.next()) {
	    	String screenName = rs.getString("screen_name");
	    	String scheduleTime = rs.getString("schedule_time");
	        String screenScheduleInfo = screenName+" "+scheduleTime;
	        // 각 결과 값을 각각의 리스트에 추가
	        screenSchedule.add(screenScheduleInfo);
	    }
	    cstmt.close();
		return screenSchedule;
	}

	
	
	@Override
	public int selectMovieNo(String movieTitle) throws SQLException {
		int movieNo=0;
		String query = "{ call ticket_logic_pkg.get_movie_no(?, ?)}";
		cstmt = conn.prepareCall(query);
	    cstmt.setString(1, movieTitle);
        cstmt.registerOutParameter(2, Types.INTEGER); 
        cstmt.execute();
        movieNo = cstmt.getInt(2);
        cstmt.close();
		return movieNo;
	}

}

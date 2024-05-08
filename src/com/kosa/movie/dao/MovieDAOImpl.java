package com.kosa.movie.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public Map<String, List<String>> selectMovie(String selectedDate) throws SQLException {
	    Map<String, List<String>> movieInfoMap = new HashMap<>();
	    List<String> movieTitles = new ArrayList<>();
	    List<String> screenNames = new ArrayList<>();
	    List<String> scheduleTimes = new ArrayList<>();

	    // 프로시저 호출 방식 수정
	    String query = "{ call GET_MOVIE_SCREEN_SCHEDULE(?, ?) }"; // OUT 매개변수 설정
	    cstmt = conn.prepareCall(query);
	    cstmt.setString(1, "2024-05-10"); // p_schedule_date
        cstmt.registerOutParameter(2, OracleTypes.CURSOR); 
	    cstmt.execute(); // 프로시저 실행

	    // OUT 매개변수에서 결과 가져오기
	    rs = (ResultSet) cstmt.getObject(2);
	    while (rs.next()) {
	        String movieTitle = rs.getString(1);
	        String screenName = rs.getString(2);
	        String scheduleTime = rs.getString(3);
	        
	        // 각 결과 값을 각각의 리스트에 추가
	        movieTitles.add(movieTitle);
	        screenNames.add(screenName);
	        scheduleTimes.add(scheduleTime);
	    }

	    // movieInfoMap에 각 리스트 추가
	    movieInfoMap.put("movieTitles", movieTitles);
	    movieInfoMap.put("screenNames", screenNames);
	    movieInfoMap.put("scheduleTimes", scheduleTimes);

	    // movieInfoMap 반환
	    return movieInfoMap;
	}
	public static void main(String[] args) {
		MovieDAOImpl movie = new MovieDAOImpl();
		try {
			System.out.println(movie.selectMovie("2024-05-10"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package com.kosa.request.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.common.base.DBConnection;
import com.kosa.request.vo.RequestVO;

import oracle.jdbc.OracleTypes;

public class RequestDAOImpl implements RequestDAO {
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;

	public RequestDAOImpl() {
		conn = DBConnection.getConnection();
	}

	@Override
	public List<String> findRequestedMovie() throws SQLException {
        List<String> requestedMovies = new ArrayList<>();
        // 프로시저 호출 방식 수정
        String query = "{ call movie_request_pkg.show_request_movie_proc(?) }"; // OUT 매개변수 설정
        cstmt = conn.prepareCall(query);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR); // OUT 매개변수 등록
        cstmt.execute(); // 프로시저 실행

        // OUT 매개변수에서 결과 가져오기
        rs = (ResultSet) cstmt.getObject(1);
        while (rs.next()) {
            String movieTitle = rs.getString(1);
            int requestCount = rs.getInt(2);
            String movieInfo = movieTitle + "           신청 수 : " + requestCount;
            requestedMovies.add(movieInfo); // 영화 제목과 request_count를 조합하여 리스트에 추가
        }
        return requestedMovies;
    }

	@Override
	public void addRequestCount(String selectedMovie) throws SQLException {
		String query = "{ call movie_request_pkg.add_request_count_proc(?) }";
		cstmt = conn.prepareCall(query);
		cstmt.setString(1, selectedMovie);
		cstmt.executeUpdate();
	}
}

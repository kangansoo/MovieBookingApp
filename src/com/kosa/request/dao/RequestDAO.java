package com.kosa.request.dao;

import java.sql.SQLException;
import java.util.List;

import com.kosa.request.vo.RequestVO;

public interface RequestDAO {
	List<String> findRequestedMovie() throws SQLException;
	
	public void addRequestCount(String selectedMovie) throws SQLException;

}

package com.kosa.movie.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.kosa.movie.vo.MovieVO;

public interface MovieDAO {
	public Map<String, List<String>> selectMovie(String selectedDate) throws SQLException;
	
}

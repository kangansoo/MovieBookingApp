package com.kosa.movie.dao;

import java.sql.SQLException;
import java.util.List;

public interface MovieDAO {
	public List<String> selectMovie(String selectedDate) throws SQLException;

	public List<String> selectScreenSchedule(String seletedDate, String movieTitle) throws SQLException;

	public int selectMovieNo(String movieTitle) throws SQLException;
}

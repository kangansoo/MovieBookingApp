package com.kosa.movie.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MovieController {
	public List<String> getMovieList(String selectDate) throws SQLException;
	public List<String> getScreenScheduleList(String seletedDate, String movieTitle) throws SQLException;
	public int getMovieNo(String movieTitle);
}

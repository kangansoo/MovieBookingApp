package com.kosa.movie.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MovieController {
	public Map<String, List<String>> getMovieList(String selectDate) throws SQLException;
}

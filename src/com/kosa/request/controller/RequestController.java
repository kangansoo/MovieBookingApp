package com.kosa.request.controller;

import java.sql.SQLException;
import java.util.List;

public interface RequestController {
	public List<String> requestedMovieList() throws SQLException;
	
	public void requestMovie(String selectedMovie) throws SQLException;
}

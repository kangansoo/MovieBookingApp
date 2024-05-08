package com.kosa.movie.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kosa.movie.dao.MovieDAO;
import com.kosa.movie.dao.MovieDAOImpl;

public class MovieControllerImpl implements MovieController {
	private MovieDAO movieDAO = new MovieDAOImpl();

	@Override
	public Map<String, List<String>> getMovieList(String selectDate) {
	    Map<String, List<String>> movieList = new HashMap<>();
	    try {
	        MovieDAOImpl movieDAO = new MovieDAOImpl();
	        movieList = movieDAO.selectMovie(selectDate);
	    } catch (SQLException e) {
	        // SQLException 처리
	        e.printStackTrace();
	    }
	    return movieList;
	}

}

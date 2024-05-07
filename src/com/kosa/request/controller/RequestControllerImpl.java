package com.kosa.request.controller;

import java.sql.SQLException;
import java.util.List;

import com.kosa.request.dao.RequestDAO;
import com.kosa.request.dao.RequestDAOImpl;

public class RequestControllerImpl implements RequestController{
	private RequestDAO requestDAO;
	
	public RequestControllerImpl() {
		requestDAO = new RequestDAOImpl();
	}
	
	@Override
	public List<String> requestedMovieList() throws SQLException {
		List<String> requestedMovies = requestDAO.findRequestedMovie();
        return requestedMovies;
	}

	@Override
	public void requestMovie(String selectedMovie) throws SQLException {
		requestDAO.addRequestCount(selectedMovie);
		
	}

}

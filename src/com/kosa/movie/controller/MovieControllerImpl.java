package com.kosa.movie.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.movie.dao.MovieDAO;
import com.kosa.movie.dao.MovieDAOImpl;

public class MovieControllerImpl implements MovieController {
   private MovieDAO movieDAO = new MovieDAOImpl();

   @Override
   public List<String> getMovieList(String selectDate) {
       List<String> movieList = new ArrayList<>();
       try {
           MovieDAOImpl movieDAO = new MovieDAOImpl();
           movieList = movieDAO.selectMovie(selectDate);
       } catch (SQLException e) {
           // SQLException 처리
           e.printStackTrace();
       }
       return movieList;
   }

   @Override
   public List<String> getScreenScheduleList(String seletedDate, String movieTitle) throws SQLException {
      List<String> screenScheduleInfo = new ArrayList<>();
      try {
           MovieDAOImpl movieDAO = new MovieDAOImpl();
           screenScheduleInfo = movieDAO.selectScreenSchedule(seletedDate, movieTitle);
       } catch (SQLException e) {
           // SQLException 처리
           e.printStackTrace();
       }
      return screenScheduleInfo;
   }

	@Override
	public int getMovieNo(String movieTitle) {
		int movieNo = 0;
		MovieDAOImpl movieDAO = new MovieDAOImpl();
		try {
			movieNo = movieDAO.selectMovieNo(movieTitle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieNo;
	}

}
package com.kosa.seatselection.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.movie.dao.MovieDAOImpl;
import com.kosa.seatselection.dao.SeatSelectionDAO;
import com.kosa.seatselection.dao.SeatSelectionDAOImpl;

public class SeatSelectionControllerImpl implements SeatSelectionController {
	private SeatSelectionDAO seatDAO = new SeatSelectionDAOImpl();
	
	@Override
	public List<String> selectedSeat(String movieTitle, String selectedDate, String screenName, String time)
			throws SQLException {
		List<String> selectedSeatList = new ArrayList<>();
		try {
			SeatSelectionDAO seatDAO = new SeatSelectionDAOImpl();
	        selectedSeatList = seatDAO.selectSeat(movieTitle, selectedDate, screenName, time);
	    } catch (SQLException e) {
	        // SQLException 처리
	        e.printStackTrace();
	    }
	    return selectedSeatList;
	}

}

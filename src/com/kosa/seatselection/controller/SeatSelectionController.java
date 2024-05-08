package com.kosa.seatselection.controller;

import java.sql.SQLException;
import java.util.List;

public interface SeatSelectionController {
	public List<String> selectedSeat(String movieTitle, String selectedDate, String screenName, String time) throws SQLException;
}

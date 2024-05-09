package com.kosa.seatselection.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface SeatSelectionDAO {
	List<String> selectSeat(String selectedDate, String movieTitle, String screenName, String time) throws SQLException;

	HashMap<String, Integer> insertSeat(String row, int column, String screenName) throws SQLException;
}

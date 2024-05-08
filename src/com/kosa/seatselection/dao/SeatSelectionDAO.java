package com.kosa.seatselection.dao;

import java.sql.SQLException;
import java.util.List;

public interface SeatSelectionDAO {
	public List<String> selectSeat(String selectedDate, String movieTitle, String screenName, String time) throws SQLException;
}

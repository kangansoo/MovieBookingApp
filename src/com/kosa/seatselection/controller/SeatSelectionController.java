package com.kosa.seatselection.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface SeatSelectionController {
	List<String> selectedSeat(String movieTitle, String selectedDate, String screenName, String time);
	//좌석 선택
	HashMap<String, Integer> chooseSeat(String row, int columnNum, String screenName);
}

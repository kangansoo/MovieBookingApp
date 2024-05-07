package com.kosa.reserve.dao;

import java.sql.SQLException;
import java.util.List;

public interface ReserveDAO {
	public void saveReservation(int reserveQuantity, int memberNo) throws SQLException;
	
	public List<String> selectReservation(int memberNo) throws SQLException;
	
}

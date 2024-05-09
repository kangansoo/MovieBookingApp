package com.kosa.reserve.controller;

import java.sql.SQLException;

import com.kosa.reserve.dao.ReserveDAO;
import com.kosa.reserve.dao.ReserveDAOImpl;

public class ReserveControllerImpl implements ReserveController {
	ReserveDAO reserveDAO;
	
	public ReserveControllerImpl() {
		reserveDAO = new ReserveDAOImpl();
	}
	
	@Override
	public int getReserveNo(int reserveQuantity, int memberNo) throws SQLException {
		return reserveDAO.insertReservation(reserveQuantity, memberNo);
	}

}

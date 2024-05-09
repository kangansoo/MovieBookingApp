package com.kosa.reserve.controller;

import java.sql.SQLException;

public interface ReserveController {
	int getReserveNo(int reserveQuantity, int memberNo) throws SQLException;

}

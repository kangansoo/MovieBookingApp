package com.kosa.reserve.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.reserve.vo.ReserveVO;

import oracle.jdbc.OracleTypes;

public class ReserveDAOImpl implements ReserveDAO{

	@Override
	public void saveReservation(int reserveQuantity, int memberNo) throws SQLException {
		
	}

	@Override
	public List<ReserveVO> selectReservation(int memberNo) throws SQLException {
		return null;
	}

}

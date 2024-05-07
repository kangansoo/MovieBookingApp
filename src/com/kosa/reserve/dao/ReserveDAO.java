package com.kosa.reserve.dao;

import java.sql.SQLException;
import java.util.List;

import com.kosa.reserve.vo.ReserveVO;

public interface ReserveDAO {
	public void saveReservation(int reserveQuantity, int memberNo) throws SQLException;
	
	public List<ReserveVO> selectReservation(int memberNo) throws SQLException;
	
}

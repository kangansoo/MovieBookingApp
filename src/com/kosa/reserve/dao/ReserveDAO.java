package com.kosa.reserve.dao;

import java.sql.SQLException;

import com.kosa.member.vo.MemberVO;

public interface ReserveDAO {
	public int insertReservation(int reserveQuantity, int memberVO) throws SQLException;
	
	
}

package com.kosa.ticket.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kosa.common.base.DBConnection;
import com.kosa.reserve.dao.ReserveDAO;

public class TicketDAOImpl implements TicketDAO {
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;

	public TicketDAOImpl() {
		conn = DBConnection.getConnection();
		
	}
	
	@Override
	public void insertTicket(int reserveNo, int scheduleNo, int screenNo, int seatNo) throws SQLException {
		String query = "{call insert_ticket(?, ?, ?, ?)}";
		cstmt = conn.prepareCall(query);
		cstmt.setInt(1, reserveNo);
		cstmt.setInt(2, scheduleNo);
		cstmt.setInt(3, screenNo);
		cstmt.setInt(4, seatNo);
		cstmt.execute();
		cstmt.close();
	}

}

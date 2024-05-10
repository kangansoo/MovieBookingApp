package com.kosa.ticket.dao;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {
	// insert ticket table
	void insertTicket(int reserveNo, int scheduleNo, int screenNo, int seatNo) throws SQLException;
	public List<List<String>> selectTicket(int memberNo) throws SQLException;

}

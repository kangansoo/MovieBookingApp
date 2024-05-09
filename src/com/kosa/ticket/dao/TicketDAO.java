package com.kosa.ticket.dao;

import java.sql.SQLException;

public interface TicketDAO {
	// insert ticket table
	void insertTicket(int reserveNo, int scheduleNo, int screenNo, int seatNo) throws SQLException;
}

package com.kosa.ticket.controller;

import java.sql.SQLException;

public interface TicketController {

	// 예매티켓 테이블 추가
	void addTicketUsingReservationDetails() throws SQLException;
	// 예매티켓 조회
	void findTicket() throws SQLException;
	
	// 
}

package com.kosa.ticket.controller;

import java.sql.SQLException;

public interface TicketController {

	// 예매티켓 테이블 추가
	void addTicket(int reserveNo, int scheduleNo, int screenNo, int seatNo);
}

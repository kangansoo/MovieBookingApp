package com.kosa.ticket.controller;

import java.sql.SQLException;
import java.util.List;

public interface TicketController {

	// 예매티켓 테이블 추가
	void addTicket(int reserveNo, int scheduleNo, int screenNo, int seatNo);
	List<List<String>> getTicketInfo(int memberNo);
}

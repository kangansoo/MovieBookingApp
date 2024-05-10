package com.kosa.ticket.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.ticket.dao.TicketDAO;
import com.kosa.ticket.dao.TicketDAOImpl;

public class TicketControllerImpl implements TicketController {
	private TicketDAO ticketDAO = new TicketDAOImpl();
	
	@Override
	public void addTicket(int reserveNo, int scheduleNo, int screenNo, int seatNo) {
		try {
			ticketDAO.insertTicket(reserveNo, scheduleNo, screenNo, seatNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<List<String>> getTicketInfo(int memberNo) {
		List<List<String>> ticketList = new ArrayList<>();
		try {
			ticketList = ticketDAO.selectTicket(memberNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticketList;
	}

}

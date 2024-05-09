package com.kosa.ticket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketVO {
	private int ticketNo;
	private int seatNo;
	private int reserveNo;
	private int scheduleNo;
	private int screenNo;

}

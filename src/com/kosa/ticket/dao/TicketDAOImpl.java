package com.kosa.ticket.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.common.base.DBConnection;

import oracle.jdbc.OracleTypes;

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

	@Override
	public List<List<String>> selectTicket(int memberNo) throws SQLException {
	    List<List<String>> ticketInfoList = new ArrayList<>();
	    String query = "{call get_ticket_info(?, ?)}";
	    cstmt = conn.prepareCall(query);
	    cstmt.setInt(1, memberNo);
	    cstmt.registerOutParameter(2, OracleTypes.CURSOR);
	    cstmt.execute();

	    // 프로시저에서 반환된 커서를 가져옴
	    ResultSet rs = (ResultSet) cstmt.getObject(2);
	    
	    // 커서에서 결과를 읽어서 리스트에 추가
	    while (rs.next()) {
	        List<String> ticketInfo = new ArrayList<>();
	        ticketInfo.add(rs.getString("MOVIE_TITLE")); // Movie Title
	        ticketInfo.add(rs.getString("SCHEDULE_DATE")); // Schedule Date
	        ticketInfo.add(rs.getString("SCHEDULE_TIME")); // Schedule Time
	        ticketInfo.add(rs.getString("SCREEN_NAME")); // Screen Name
	        ticketInfo.add(rs.getString("SEAT")); // Seat
	        ticketInfoList.add(ticketInfo);
	    }

	    cstmt.close();
	    
	    return ticketInfoList;
	}

}

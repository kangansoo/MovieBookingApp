package com.kosa.schedule.scheduledao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.kosa.common.base.DBConnection;

public class ScheduleDAOImpl implements ScheduleDAO {
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;

	public ScheduleDAOImpl() {
		conn = DBConnection.getConnection();
	}
	
	@Override
	public int getScheduleNo(String scheduleDate, String scheduleTime, String screenName) throws SQLException {
		int scheduleNo = 0;
		String query = "{call Get_Schedule_No(?,?,?,?)}";
		cstmt = conn.prepareCall(query);
		cstmt.setString(1, scheduleDate);
		cstmt.setString(2, scheduleTime);
		cstmt.setString(3, screenName);
		cstmt.registerOutParameter(4, Types.INTEGER);
		cstmt.execute();
		
		scheduleNo = cstmt.getInt(4);

		cstmt.close();
		return scheduleNo;
	}

}

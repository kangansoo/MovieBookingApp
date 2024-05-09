package com.kosa.schedule.scheduledao;

import java.sql.SQLException;

public interface ScheduleDAO {
	public int getScheduleNo(String scheduleDate, String scheduleTime, String screenName) throws SQLException;
}

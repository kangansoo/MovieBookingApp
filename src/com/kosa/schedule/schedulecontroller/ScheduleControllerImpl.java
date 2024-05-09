package com.kosa.schedule.schedulecontroller;

import java.sql.SQLException;

import com.kosa.schedule.scheduledao.ScheduleDAO;
import com.kosa.schedule.scheduledao.ScheduleDAOImpl;

public class ScheduleControllerImpl implements ScheduleController {
	ScheduleDAO scheduleDAO;
	
	public ScheduleControllerImpl() {
		scheduleDAO = new ScheduleDAOImpl();
	}
	
	@Override
	public int requestScheduleNo(String scheduleDate, String scheduleTime, String screenName) {
		int scheduleNo = 0;
		try {
			scheduleNo = scheduleDAO.getScheduleNo(scheduleDate, scheduleTime, screenName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scheduleNo;
	}

}

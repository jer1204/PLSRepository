package com.pointwest.java.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.beans.Seat;
import com.pointwest.java.dao.SeatDao;
import com.pointwest.java.utils.PLSException;

public class SeatManager {
	public static Logger log = Logger.getLogger(SeatManager.class);
	public List<Seat> getBldgInfo() throws PLSException {
		log.info("START");
		List<Seat> seatList = null;
		SeatDao seatDao = new SeatDao();
		seatList = seatDao.getBldgInfo();
		log.info("END");
		return seatList;

	}

	public List<Employee> getLocationAndFloor(String location, String floor) throws PLSException {
		log.info("START");
		List<Employee> employeeList = null;
		SeatDao seatPlanDao = new SeatDao();
		employeeList = seatPlanDao.getLocationAndFloor(location, floor);
		log.info("END");
		return employeeList;
	}

	public List<Employee> getLocationAndFloorAndQuadrant(String location, String floor, String quadrant)
			throws PLSException {
		log.info("START");
		List<Employee> employeeList = null;
		SeatDao seatPlanDao = new SeatDao();
		employeeList = seatPlanDao.getLocationAndFloorAndQuadrant(location, floor, quadrant);
		log.info("END");
		return employeeList;
	}

	public List<Seat> getSeatPlan(String location, String floor) throws PLSException {
		log.info("START");
		List<Seat> seatList = null;
		SeatDao seatPlanDao = new SeatDao();
		seatList = seatPlanDao.getSeatPlan(location, floor);
		log.info("END");
		return seatList;
	}

}

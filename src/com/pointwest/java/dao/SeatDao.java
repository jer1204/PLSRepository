package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.beans.Project;
import com.pointwest.java.beans.Seat;
import com.pointwest.java.utils.Constants;
import com.pointwest.java.utils.EmployeeQueries;
import com.pointwest.java.utils.PLSException;

public class SeatDao extends DAO {
	public static Logger log = Logger.getLogger(SeatDao.class);
	public List<Seat> getBldgInfo() throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Seat seat = null;
		List<Seat> seatList = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.BUILDING_INFO);
			resultSet = statement.executeQuery();

			seatList = new ArrayList<Seat>();
			
			while (resultSet.next()) {
				seat = new Seat();
				String buildingID = resultSet.getString("bldg_id");
				String floorNumber = resultSet.getString("floor_number");
				String quadrant = resultSet.getString("quadrant");

				
				seat.setBuildingID(buildingID);
				seat.setQuadrant(quadrant);
				seat.setFloorNumber(floorNumber);
				
				seatList.add(seat);
			}
			log.info("END");
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.SQL_ERROR_MESSAGE);
			throw ex;
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return seatList;
	}
	
	public List<Employee> getLocationAndFloor(String location, String floor) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Employee> empList = null;
		Seat seat = null;
		Employee emp = null;
		Project proj = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.INFO_BY_LOCATION);
			statement.setString(1, location);
			statement.setString(2, floor);
			resultSet = statement.executeQuery();
			
			empList = new ArrayList<Employee>();
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String employeeID = resultSet.getString("emp_id");
				String shift = resultSet.getString("shift");
				String projectName = resultSet.getString("proj_alias");
				String buildingID = resultSet.getString("bldg_id");
				String floorNumber = resultSet.getString("floor_number");
				String quadrant = resultSet.getString("quadrant");
				String rowNumber = resultSet.getString("row_number");
				String columnNumber = resultSet.getString("column_number");
				String phoneLocal = resultSet.getString("local");

				emp = new Employee();
				seat = new Seat();
				proj = new Project();
				emp.setFirstName(firstName);
				emp.setLastName(lastName);
				emp.setEmpID(employeeID);
				emp.setShift(shift);
				seat.setBuildingID(buildingID);
				seat.setColumnNumber(columnNumber);
				seat.setFloorNumber(floorNumber);
				seat.setPhoneLocal(phoneLocal);
				seat.setQuadrant(quadrant);
				seat.setRowNumber(rowNumber);
				emp.setSeat(seat);
				proj.setProjectAlias(projectName);
				emp.setProject(proj);
				
				empList.add(emp);
			}
			log.info("END");
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.SQL_ERROR_MESSAGE);
			throw ex;
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return empList;
	}
	
	public List<Employee> getLocationAndFloorAndQuadrant(String location, String floor, String quadrant) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Employee> empList = null;
		Seat seat = null;
		Employee emp = null;
		Project proj = null;

		try {
			log.info("Seat Dao getLocationAndFloorAndQuadrant method - START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.INFO_BY_LOCATION_AND_QUADRANT);
			statement.setString(1, location);
			statement.setString(2, floor);
			statement.setString(3, quadrant);
			resultSet = statement.executeQuery();
			
			empList = new ArrayList<Employee>();
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String employeeID = resultSet.getString("emp_id");
				String shift = resultSet.getString("shift");
				String projectName = resultSet.getString("proj_alias");
				String buildingID = resultSet.getString("bldg_id");
				String floorNumber = resultSet.getString("floor_number");
				String quadrantVal = resultSet.getString("quadrant");
				String rowNumber = resultSet.getString("row_number");
				String columnNumber = resultSet.getString("column_number");
				String phoneLocal = resultSet.getString("local");

				emp = new Employee();
				seat = new Seat();
				proj = new Project();
				emp.setFirstName(firstName);
				emp.setLastName(lastName);
				emp.setEmpID(employeeID);
				emp.setShift(shift);
				seat.setBuildingID(buildingID);
				seat.setColumnNumber(columnNumber);
				seat.setFloorNumber(floorNumber);
				seat.setPhoneLocal(phoneLocal);
				seat.setQuadrant(quadrantVal);
				seat.setRowNumber(rowNumber);
				emp.setSeat(seat);
				proj.setProjectAlias(projectName);
				emp.setProject(proj);
				
				empList.add(emp);
			}
			log.info("Seat Dao getLocationAndFloorAndQuadrant method - END");
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.SQL_ERROR_MESSAGE);
			throw ex;
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return empList;
	}
	
	public List<Seat> getSeatPlan(String location, String floor) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Seat> seatList = null;
		Seat seat = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.SEATPLAN_INFO);
			statement.setString(1, location);
			statement.setString(2, floor);
			resultSet = statement.executeQuery();
			
			seatList = new ArrayList<Seat>();
			while (resultSet.next()) {
				String quadrant = resultSet.getString("quadrant");
				String rowNumber = resultSet.getString("row_number");
				String columnNumber = resultSet.getString("column_number");
				String buildingID = resultSet.getString("bldg_id");
				String floorNumber = resultSet.getString("floor_number");
				
				seat = new Seat();
				seat.setColumnNumber(columnNumber);
				seat.setQuadrant(quadrant);
				seat.setRowNumber(rowNumber);
				seat.setBuildingID(buildingID);
				seat.setFloorNumber(floorNumber);
				
				seatList.add(seat);
			}
			log.info("END");
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.SQL_ERROR_MESSAGE);
			throw ex;
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return seatList;
	}
	
}

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

public class EmployeeDao extends DAO {
	public static Logger log = Logger.getLogger(EmployeeDao.class);

	public Employee verifyUser(String username, String password) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Employee employee = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.VERIFY_USER);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();

			employee = new Employee();
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String appRole = resultSet.getString("role");
				employee.setAppRole(appRole);
				employee.setFirstName(firstName);
				employee.setLastName(lastName);
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
		return employee;
	}

	public List<Employee> getEmployeeInfoByID(String inputSearch) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Seat seat = null;
		Employee emp = null;
		Project proj = null;
		List<Employee> employeeList = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.INFO_BY_ID);
			statement.setString(1, "%" + inputSearch.trim() + "%");
			resultSet = statement.executeQuery();

			employeeList = new ArrayList<Employee>();
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String employeeID = resultSet.getString("emp_id");
				String shift = resultSet.getString("shift");
				String projectName = resultSet.getString("proj_alias").replace("BogusProject", "No Project");
				String buildingID = resultSet.getString("bldg_id");
				String floorNumber = resultSet.getString("floor_number");
				String quadrant = resultSet.getString("quadrant");
				String rowNumber = resultSet.getString("row_number");
				String columnNumber = resultSet.getString("column_number");
				String phoneLocal = resultSet.getString("local");

				String[] projectNameArray = projectName.split(",");
				if (projectNameArray.length > 1) {
					if (projectName.contains("No Project")) {
						projectName = projectName.replace("No Project", "");
						projectName = projectName.replace(" , ", "");
					}
				}

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

				employeeList.add(emp);
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
		return employeeList;
	}

	public List<Employee> getEmployeeInfoByName(String inputSearch) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Seat seat = null;
		Employee emp = null;
		Project proj = null;
		List<Employee> employeeList = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.INFO_BY_NAME);
			statement.setString(1, "%" + inputSearch + "%");
			resultSet = statement.executeQuery();

			employeeList = new ArrayList<Employee>();
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String employeeID = resultSet.getString("emp_id");
				String shift = resultSet.getString("shift");
				String projectName = resultSet.getString("proj_alias").replace("BogusProject", "No Project");
				String buildingID = resultSet.getString("bldg_id");
				String floorNumber = resultSet.getString("floor_number");
				String quadrant = resultSet.getString("quadrant");
				String rowNumber = resultSet.getString("row_number");
				String columnNumber = resultSet.getString("column_number");
				String phoneLocal = resultSet.getString("local");
				
				String[] projectNameArray = projectName.split(",");
				if (projectNameArray.length > 1) {
					if (projectName.contains("No Project")) {
						projectName = projectName.replace("No Project", "");
						projectName = projectName.replace(" , ", "");
					}
				}
				
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

				employeeList.add(emp);
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

		return employeeList;
	}

	public List<Employee> getEmployeeInfoByProject(String inputSearch) throws PLSException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Seat seat = null;
		Employee emp = null;
		Project proj = null;
		List<Employee> employeeList = null;

		try {
			log.info("START");
			connection = getConnection();
			statement = connection.prepareStatement(EmployeeQueries.INFO_BY_PROJECT);
			statement.setString(1, "%" + inputSearch.trim() + "%");
			resultSet = statement.executeQuery();

			employeeList = new ArrayList<Employee>();
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
				
				String[] projectNameArray = projectName.split(",");
				if (projectNameArray.length > 1) {
					if (projectName.contains("No Project")) {
						projectName = projectName.replace("No Project", "");
						projectName = projectName.replace(" , ", "");
					}
				}
				
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

				employeeList.add(emp);
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
		return employeeList;
	}

}

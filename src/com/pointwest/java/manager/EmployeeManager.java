package com.pointwest.java.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.dao.EmployeeDao;
import com.pointwest.java.utils.PLSException;

public class EmployeeManager {
	public static Logger log = Logger.getLogger(EmployeeManager.class);

	public List<Employee> searchEmployeeID(String inputSearch) throws PLSException {
		List<Employee> employeeList = null;
		log.info("START");

		EmployeeDao employeeDao = new EmployeeDao();
		employeeList = employeeDao.getEmployeeInfoByID(inputSearch);

		log.info("END");
		return employeeList;
	}

	public List<Employee> searchName(String inputSearch) throws PLSException {
		List<Employee> employeeList = null;
		log.info("START");

		EmployeeDao employeeDao = new EmployeeDao();
		employeeList = employeeDao.getEmployeeInfoByName(inputSearch);

		log.info("END");

		return employeeList;
	}

	public List<Employee> searchProject(String inputSearch) throws PLSException {
		List<Employee> employeeList = null;
		log.info("START");

		EmployeeDao employeeDao = new EmployeeDao();
		employeeList = employeeDao.getEmployeeInfoByProject(inputSearch);

		log.info("END");
		return employeeList;
	}

}

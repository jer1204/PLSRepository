package com.pointwest.java.manager;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.dao.EmployeeDao;
import com.pointwest.java.utils.PLSException;

public class LoginManager {
	public static Logger log = Logger.getLogger(LoginManager.class);

	public Employee verifyUser(String username, String password) throws PLSException {
		log.info("START");
		Employee employee = null;
		EmployeeDao employeeDao = new EmployeeDao();
		employee = employeeDao.verifyUser(username, password);
		log.info("END");
		return employee;
	}
}

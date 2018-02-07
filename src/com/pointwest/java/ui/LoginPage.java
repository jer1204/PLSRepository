package com.pointwest.java.ui;

import java.io.File;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.main.PLSMain;
import com.pointwest.java.manager.LoginManager;
import com.pointwest.java.utils.Constants;
import com.pointwest.java.utils.PLSException;

public class LoginPage {
	public static Logger log = Logger.getLogger(LoginPage.class);

	public Employee showLogin() {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		Employee employee = null;
		boolean loginChecker = true;
		try {
			
			while (loginChecker) {

				System.out.println("--------------------");
				System.out.println("|LOGIN              |");
				System.out.println("--------------------");
				System.out.print("Username: ");
				String username = scan.nextLine();
				System.out.print("Password: ");
				String password = scan.nextLine();

				LoginManager loginManager = new LoginManager();
				employee = loginManager.verifyUser(username, password);

				if (employee.getFirstName() != null && employee.getLastName() != null) {
					log.info("Valid Credentials");
					loginChecker = false;
				} else {
					loginChecker = true;
					log.info("Invalid Credentials");
					System.out.println(Constants.INVALID_CREDENTIALS_ERROR_MESSAGE);
				}
			}
			log.info("END");

		} catch (PLSException e) {
			System.out.println(e.getDisplayMessage());
		}
		return employee;
	}
}

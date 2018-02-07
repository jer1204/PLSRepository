package com.pointwest.java.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.utils.Constants;

public class MainMenuPage {
	public static Logger log = Logger.getLogger(MainMenuPage.class);

	public String ShowMainMenuPage(Employee emp) {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		String choice = "";
		boolean toContinue = true;
		if (emp != null) {
			while (toContinue) {
				System.out.println("\n ##HOME##");
				System.out.println("--------------------");
				System.out.println(
						"Welcome " + emp.getFirstName() + " " + emp.getLastName() + " [" + emp.getAppRole() + "]");
				System.out.println("--------------------");
				System.out.println("Menu:");
				System.out.println("[1] Search ");
				System.out.println("[2] View SeatPlan");
				System.out.println("[3] Logout");
				System.out.print("Choice: ");
				choice = scan.nextLine();
				switch (choice) {
				case "1":
					choice = "search";
					toContinue = false;
					break;
				case "2":
					choice = "view";
					toContinue = false;
					break;
				case "3":
					choice = "logout";
					log.info("LOGOUT");
					System.out.println(Constants.LOGOUT_MESSAGE);
					toContinue = false;
					break;
				default:
					System.out.println(Constants.INVALID_INPUT_FROM_1_TO_3_ERROR_MESSAGE);
					break;
				}
			}
		}
		log.info("END");
		return choice;
	}
}

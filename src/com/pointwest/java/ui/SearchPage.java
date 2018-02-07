package com.pointwest.java.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.manager.EmployeeManager;
import com.pointwest.java.utils.Constants;
import com.pointwest.java.utils.PLSException;

public class SearchPage {
	public static Logger log = Logger.getLogger(SearchPage.class);

	public List<Employee> displaySearchMenu(String title) {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		List<Employee> empList = null;
		boolean toContinueSearch = true;

		while (toContinueSearch) {
			System.out.println(title);
			System.out.println("MENU: ");
			System.out.println("[1] By Employee ID");
			System.out.println("[2] By Name");
			System.out.println("[3] By Project");
			System.out.print("Choice: ");
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				empList = searchChoiceMenu(Constants.EMPLOYEE_ID_HEADER, choice);
				toContinueSearch = false;
				break;
			case "2":
				empList = searchChoiceMenu(Constants.NAME_HEADER, choice);
				toContinueSearch = false;
				break;
			case "3":
				empList = searchChoiceMenu(Constants.PROJECT_HEADER, choice);
				toContinueSearch = false;
				break;
			default:
				System.out.println(Constants.INVALID_INPUT_FROM_1_TO_3_ERROR_MESSAGE);
				break;
			}
		}
		log.info("END");
		return empList;
	}

	public List<Employee> searchChoiceMenu(String searchChoice, String choice) {
		log.info("User chose " + choice);
		log.info("START");
		Scanner scan = new Scanner(System.in);
		List<Employee> empList = null;
		boolean toContinue = true;
		try {
			continueEnterInput: while (toContinue) {
				System.out.println("##SEARCH-By " + searchChoice + "##");
				System.out.print("Enter " + searchChoice + ": ");
				String inputSearch = scan.nextLine();
				if (searchChoice.matches("EMPLOYEE ID")) {
					if (!inputSearch.matches(".*\\d+.*") || inputSearch.matches(".*\\W+.*")) {
						System.out.println("Invalid input! It can only contains digits.");
						continue continueEnterInput;
					}
				} else if (searchChoice.matches("NAME")) {
					if (inputSearch.matches(".*\\d+.*")) {
						System.out.println("Invalid input! Name should only contain letters");
						continue continueEnterInput;
					}
				}

				EmployeeManager empManager = new EmployeeManager();
				switch (choice) {
				case "1":

					empList = empManager.searchEmployeeID(inputSearch);
					toContinue = false;
					break;

				case "2":
					empList = empManager.searchName(inputSearch);
					toContinue = false;
					break;

				case "3":
					empList = empManager.searchProject(inputSearch);
					toContinue = false;
					break;
				}

			}
		} catch (PLSException e) {
			System.out.println(e.getDisplayMessage());
		}
		log.info("END");
		return empList;
	}

	public boolean displayAction() {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		String choice = "";
		boolean toContinueSearch = true;
		boolean toContinueLoop = true;
		while (toContinueLoop) {
			System.out.println("ACTIONS: [1] Search Again \t [2] Home");
			System.out.print("Choice: ");
			choice = scan.nextLine();

			switch (choice) {
			case "1":
				toContinueSearch = true;
				toContinueLoop = false;
				break;
			case "2":
				toContinueSearch = false;
				toContinueLoop = false;
				break;
			default:
				System.out.println(Constants.INVALID_INPUT_FROM_1_TO_2_ERROR_MESSAGE);
				break;
			}
		}
		log.info("END");
		return toContinueSearch;
	}
}

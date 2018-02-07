package com.pointwest.java.ui;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.beans.Seat;
import com.pointwest.java.manager.SeatManager;
import com.pointwest.java.utils.Constants;
import com.pointwest.java.utils.PLSException;

public class ViewPage {
	public static Logger log = Logger.getLogger(ViewPage.class);

	public String displayViewMenu() {
		log.info("START");
		String choice = "";
		Scanner scan = new Scanner(System.in);
		boolean toContinue = true;

		while (toContinue) {
			System.out.println("##VIEW SEATPLAN##");
			System.out.println("MENU: ");
			System.out.println("[1] By Location - Floor Level");
			System.out.println("[2] By Quadrant");
			System.out.println("[3] By Employee");
			System.out.print("Choice: ");
			choice = scan.nextLine();

			switch (choice) {
			case "1":
				showAvailableLocations();
				toContinue = false;
				break;
			case "2":
				showAvailableLocations();
				toContinue = false;
				break;
			case "3":
				toContinue = false;
				break;
			default:
				System.out.println(Constants.INVALID_INPUT_FROM_1_TO_3_ERROR_MESSAGE);
				break;
			}
		}
		log.info("END");
		return choice;

	}

	private void showAvailableLocations() {
		log.info("START");
		List<Seat> seatList = null;
		SeatManager seatManager = new SeatManager();
		try {
			seatList = seatManager.getBldgInfo();
			if (seatList != null) {
				System.out.println(
						"Available Locations \t \t Available Floor Per Location \t \t  Available Quadrant per Floor");
				for (Seat seat : seatList) {
					System.out.println(
							String.format("%-43s", seat.getBuildingID()) + String.format("%-35s", seat.getFloorNumber())
									+ String.format("%-25s", seat.getQuadrant()));
				}
			}
		} catch (PLSException e) {
			System.out.println(e.getDisplayMessage());
		}
		log.info("END");
	}

	public String displayViewByChoice(String choice) {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		String inputString = "";
		if (choice.equals("1")) {
			System.out.print("Enter Location: ");
			inputString = scan.nextLine();
			System.out.print("Enter Floor Level: ");
			inputString = inputString + "-" + scan.nextLine();
		} else {
			System.out.print("Enter Location: ");
			inputString = scan.nextLine();
			System.out.print("Enter Floor Level: ");
			inputString = inputString + "-" + scan.nextLine();
			System.out.print("Enter Quadrant: ");
			inputString = inputString + "-" + scan.nextLine();

		}
		log.info("END");
		return inputString;
	}

	public String getEmployeeToView(List<Employee> employeeList) {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		String stringVal = "";
		boolean cont = true;
		String viewChoice = "";
		while (cont) {
			if (employeeList != null) {
				System.out.print("Enter the result no. of the employee  you want to view: ");
				viewChoice = scan.nextLine();
				if (viewChoice.matches(".*\\d+.*")) {
					if (Integer.parseInt(viewChoice) <= (employeeList.size()) && Integer.parseInt(viewChoice) > 0) {
						Employee employee = employeeList.get(Integer.parseInt(viewChoice) - 1);
						String location = employee.getSeat().getBuildingID();
						String floor = employee.getSeat().getFloorNumber();
						String quadrant = employee.getSeat().getQuadrant();
						String firstName = employee.getFirstName();
						String lastName = employee.getLastName();
						stringVal = location + "-" + floor + "-" + quadrant + "-" + firstName + "-" + lastName;
						cont = false;
					} else {
						System.out.println("Input is out of range!");
					}
				} else {
					System.out.println("Input is invalid");
				}
			} else {
				cont = false;
			}
		}
		log.info("END");
		return stringVal;
	}

	public boolean displayActionView() {
		log.info("START");
		Scanner scan = new Scanner(System.in);
		String choice = "";
		boolean toContinueView = false;

		boolean toContinueLoop = true;
		while (toContinueLoop) {
			System.out.println("ACTIONS: [1] View seatplan again  [2] Home");
			System.out.print("Choice: ");
			choice = scan.nextLine();
			switch (choice) {
			case "1":
				toContinueView = true;
				toContinueLoop = false;
				break;
			case "2":
				toContinueView = false;
				toContinueLoop = false;
				break;
			default:
				System.out.println(Constants.INVALID_INPUT_FROM_1_TO_2_ERROR_MESSAGE);
				break;
			}
		}
		log.info("END");
		return toContinueView;
	}
}

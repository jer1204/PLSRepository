package com.pointwest.java.ui;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.utils.Constants;

public class SearchResultsPage {
	public static Logger log = Logger.getLogger(SearchResultsPage.class);

	public void displayResults(List<Employee> employeeList) {
		log.info("START");
		int counter = 1;
		if (employeeList != null) {
			System.out.println("##SEARCH RESULT - (" + employeeList.size() + ")##");
			System.out.print("---------------------------------------------------------------------------------------");
			System.out.print(
					"--------------------------------------------------------------------------------------- \n");
			System.out.print("EMPLOYEE ID \t | \t FIRSTNAME \t | \t LASTNAME \t | \t SEAT \t | \t LOCAL \t | \t");
			System.out.print("SHIFT \t | \t PROJECTS \n");
			System.out.print("---------------------------------------------------------------------------------------");
			System.out.print(
					"--------------------------------------------------------------------------------------- \n");
		} else {
			System.out.print("---------------------------------------------------------------------------------------");
			System.out.print(
					"--------------------------------------------------------------------------------------- \n");
			System.out.print("EMPLOYEE ID \t | \t FIRSTNAME \t | \t LASTNAME \t | \t SEAT \t | \t LOCAL \t | \t");
			System.out.print("SHIFT \t | \t PROJECTS \n");
			System.out.print("---------------------------------------------------------------------------------------");
			System.out.print(
					"--------------------------------------------------------------------------------------- \n");
		}

		if (employeeList == null || employeeList.isEmpty()) {
			System.out.print("\n \t \t \t \t \t \t No Results Found \n");
		} else {
			for (Employee list : employeeList) {
				String employeeId = list.getEmpID();
				String firstName = list.getFirstName();
				String lastName = list.getLastName();
				String shift = list.getShift();
				String project = list.getProject().getProjectAlias();
				String buildingID = list.getSeat().getBuildingID();
				String floorNumber = list.getSeat().getFloorNumber();
				String areaCode = list.getSeat().getQuadrant();
				String rowNumber = list.getSeat().getRowNumber();
				String seatNumber = list.getSeat().getColumnNumber();
				String phoneLocal = list.getSeat().getPhoneLocal();

				String seat = buildingID + floorNumber + "F" + areaCode + rowNumber + "-" + seatNumber;

				System.out.print(String.format("%-5s", "[" + counter + "] \t"));
				System.out.print(String.format("%-20s", employeeId));
				System.out.print(String.format("%-20s", firstName));
				System.out.print(String.format("%-20s", lastName));
				System.out.print(String.format("%-22s", seat));
				System.out.print(String.format("%-15s", phoneLocal));
				System.out.print(String.format("%-15s", shift));
				System.out.print(String.format("%-15s", project) + "\n");
				counter++;
			}
		}
		System.out.print("---------------------------------------------------------------------------------------");
		System.out.print("--------------------------------------------------------------------------------------- \n");
		log.info("END");
	}

}

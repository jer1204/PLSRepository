package com.pointwest.java.ui;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.beans.Seat;
import com.pointwest.java.manager.SeatManager;
import com.pointwest.java.utils.PLSException;

public class SeatPlanResultsPage {
	public static Logger log = Logger.getLogger(SeatPlanResultsPage.class);

	public void displaySeatPlan(String stringValues) {
		log.info("START");
		List<Employee> employeeList = null;
		List<Seat> seatList = null;
		String[] stringArray = stringValues.split("-");
		String firstNameVal = "";
		String lastNameVal = "";
		String location = "";
		String floor = "";
		String quadrant = "";
		SeatManager seatManager = new SeatManager();
		try {
			seatList = seatManager.getSeatPlan(stringArray[0], stringArray[1]);
			if (stringArray.length == 2) {
				location = stringArray[0];
				floor = stringArray[1];
				employeeList = seatManager.getLocationAndFloor(location, floor);
			} else if (stringArray.length == 3) {
				location = stringArray[0];
				floor = stringArray[1];
				quadrant = stringArray[2];
				employeeList = seatManager.getLocationAndFloorAndQuadrant(location, floor, quadrant);
			} else if (stringArray.length == 5) {
				location = stringArray[0];
				floor = stringArray[1];
				quadrant = stringArray[2];
				firstNameVal = stringArray[3];
				lastNameVal = stringArray[4];
				employeeList = seatManager.getLocationAndFloorAndQuadrant(location, floor, quadrant);
			}

			if (employeeList == null || employeeList.isEmpty()) {
				System.out.println(
						"-------------------------------------------------------------------------------------------");
				System.out.println("\t \t \t \t \tNO RESULTS FOUND");
				System.out.println(
						"-------------------------------------------------------------------------------------------");
			} else {

				for (int x = 1; x < 7; x++) {
					String seatPerRowAB = "";
					String namePerRowAB = "";
					String localNumPerRowAB = "";
					String seatPerRowCD = "";
					String namePerRowCD = "";
					String localNumPerRowCD = "";

					if (x == 1) {
						System.out.println(
								"-------------------------------------------------------------------------------------------"
										+ "-------------------------------------------------------------------------------------------");
						System.out.println("                           \t \t \t \t    WELCOME " + location.toUpperCase()
								+ " " + floor + "F");
						System.out.println(
								"-------------------------------------------------------------------------------------------"
										+ "-------------------------------------------------------------------------------------------");
						System.out.println("                                   \t \t \t | Entrance |");
						System.out.println("                                    \t \t \t ---------- ");
						System.out.println("\t \t \t \t  QUADRANT A \t  \t \t \t \t \t \t QUADRANT B \n");
					} else if (x == 4) {
						System.out.println(
								"-------------------------------------------------------------------------------------------"
										+ "-------------------------------------------------------------------------------------------");
						System.out.println("\t \t \t \t  QUADRANT C \t  \t \t \t \t \t \t QUADRANT D");
						System.out.println(
								"-------------------------------------------------------------------------------------------"
										+ "-------------------------------------------------------------------------------------------");
					}

					for (Seat seat : seatList) {
						String seatQuadrantVal = seat.getQuadrant();
						String seatRowNumVal = seat.getRowNumber();
						String seatColNumVal = seat.getColumnNumber();
						String seatBldgId = seat.getBuildingID();
						String seatfloorNum = seat.getFloorNumber();
						String seatValue = "";

						boolean empExist = false;

						for (Employee emp : employeeList) {
							String firstName = emp.getFirstName();
							String lastName = emp.getLastName();
							String buildingID = emp.getSeat().getBuildingID();
							String floorNumber = emp.getSeat().getFloorNumber();
							String quadrantValList = emp.getSeat().getQuadrant();
							String rowNumber = emp.getSeat().getRowNumber();
							String columnNumber = emp.getSeat().getColumnNumber();
							String phoneLocal = emp.getSeat().getPhoneLocal();
							seatValue = buildingID + floorNumber + "F" + quadrantValList + rowNumber + "-"
									+ columnNumber;
							String name = "";
							if (firstName.equalsIgnoreCase(firstNameVal) && lastName.equalsIgnoreCase(lastNameVal)) {
								name = "**" + firstName + ", " + lastName + "**";
							} else {
								name = firstName + ", " + lastName;
							}
							if (quadrantValList.equalsIgnoreCase(seatQuadrantVal)
									&& rowNumber.equalsIgnoreCase(seatRowNumVal)
									&& columnNumber.equalsIgnoreCase(seatColNumVal)
									&& x == Integer.parseInt(seatRowNumVal) && (seatQuadrantVal.equalsIgnoreCase("A")
											|| seatQuadrantVal.equalsIgnoreCase("B"))) {

								empExist = true;
								seatPerRowAB += String.format("%-30s", seatValue);
								namePerRowAB += String.format("%-30s", name);
								localNumPerRowAB += String.format("%-30s", phoneLocal);
							} else if (quadrantValList.equalsIgnoreCase(seatQuadrantVal)
									&& rowNumber.equalsIgnoreCase(seatRowNumVal)
									&& columnNumber.equalsIgnoreCase(seatColNumVal) && x > 3
									&& x == Integer.parseInt(seatRowNumVal) + 3
									&& (seatQuadrantVal.equalsIgnoreCase("C")
											|| seatQuadrantVal.equalsIgnoreCase("D"))) {

								empExist = true;
								seatPerRowCD += String.format("%-30s", seatValue);
								namePerRowCD += String.format("%-30s", name);
								localNumPerRowCD += String.format("%-30s", phoneLocal);
							}
						}
						if (empExist == false && x == Integer.parseInt(seatRowNumVal)
								&& (seatQuadrantVal.equalsIgnoreCase("A") || seatQuadrantVal.equalsIgnoreCase("B"))) {
							seatValue = seatBldgId + seatfloorNum + "F" + seatQuadrantVal + seatRowNumVal + "-"
									+ seatColNumVal;

							seatPerRowAB += String.format("%-30s", seatValue);
							namePerRowAB += String.format("%-30s", "");
							localNumPerRowAB += String.format("%-30s", "");
						} else if (empExist == false && x > 3 && x == Integer.parseInt(seatRowNumVal) + 3
								&& (seatQuadrantVal.equalsIgnoreCase("C") || seatQuadrantVal.equalsIgnoreCase("D"))) {
							seatValue = seatBldgId + seatfloorNum + "F" + seatQuadrantVal + seatRowNumVal + "-"
									+ seatColNumVal;

							seatPerRowCD += String.format("%-30s", seatValue);
							namePerRowCD += String.format("%-30s", "");
							localNumPerRowCD += String.format("%-30s", "");
						}

					}
					if (x < 4) {
						System.out.println(seatPerRowAB);
						System.out.println(namePerRowAB);
						System.out.println(localNumPerRowAB);
					} else if (x > 3 && x < 7) {
						System.out.println(seatPerRowCD);
						System.out.println(namePerRowCD);
						System.out.println(localNumPerRowCD);
					}

				}

			}
		} catch (PLSException e) {
			System.out.println(e.getDisplayMessage());
		}
		log.info("END");
	}
}

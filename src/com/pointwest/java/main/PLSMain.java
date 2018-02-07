package com.pointwest.java.main;

import java.util.List;

import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

import com.pointwest.java.beans.Employee;
import com.pointwest.java.ui.LoginPage;
import com.pointwest.java.ui.MainMenuPage;
import com.pointwest.java.ui.SearchPage;
import com.pointwest.java.ui.SearchResultsPage;
import com.pointwest.java.ui.SeatPlanResultsPage;
import com.pointwest.java.ui.ViewPage;
import com.pointwest.java.utils.Constants;

public class PLSMain {

	public static void main(String[] args) {
		boolean toContinue = false;
		Employee emp = null;
		LoginPage loginPage = new LoginPage();
		emp = loginPage.showLogin();
		if (emp != null) {
			toContinue = true;
		}
		List<Employee> empList = null;

		while (toContinue) {
			MainMenuPage mainManu = new MainMenuPage();
			String choiceValue = mainManu.ShowMainMenuPage(emp);
			switch (choiceValue) {
			case "search":
				boolean toContinueSearch = true;
				while (toContinueSearch) {
					SearchPage searchPage = new SearchPage();
					SearchResultsPage searchResultsPage = new SearchResultsPage();
					empList = searchPage.displaySearchMenu(Constants.SEARCH_HEADER);
					searchResultsPage.displayResults(empList);
					toContinueSearch = searchPage.displayAction();
				}

				break;
			case "view":
				boolean toContinueView = true;
				String viewChoiceValue = "";
				while (toContinueView) {
					ViewPage viewPage = new ViewPage();
					SeatPlanResultsPage seatPlan = new SeatPlanResultsPage();
					viewChoiceValue = viewPage.displayViewMenu();
					if (viewChoiceValue.equals("3")) {
						SearchPage searchPage = new SearchPage();
						SearchResultsPage searchResultsPage = new SearchResultsPage();
						empList = searchPage.displaySearchMenu(Constants.VIEW_SEATPLAN_HEADER_BY_EMPLOYEE);
						searchResultsPage.displayResults(empList);
						if (empList != null) {
							String inputValue = viewPage.getEmployeeToView(empList);
							seatPlan.displaySeatPlan(inputValue);
						}
						toContinueView = viewPage.displayActionView();

					} else {
						String inputValue = viewPage.displayViewByChoice(viewChoiceValue);
						seatPlan.displaySeatPlan(inputValue);
						toContinueView = viewPage.displayActionView();
					}
				}
				break;
			case "logout":
				toContinue = false;

				break;
			}
		}
	}
}

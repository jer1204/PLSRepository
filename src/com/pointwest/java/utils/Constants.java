package com.pointwest.java.utils;

public interface Constants {

	public static final String URL = "jdbc:mysql://localhost:3306/plsdb";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	public static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "\n ***Invalid username or password. please try again.*** \n";
	public static final String INVALID_INPUT_FROM_1_TO_3_ERROR_MESSAGE = "***Please choose from 1 - 3 only!***";
	public static final String INVALID_INPUT_FROM_1_TO_2_ERROR_MESSAGE = "***Please choose from 1 - 2 only!***";
	public static final String LOGOUT_MESSAGE = "\n *** YOU HAVE SUCCESSFULLY LOGOUT! *** \n";
	public static final String EMPLOYEE_ID_HEADER = "EMPLOYEE ID";
	public static final String NAME_HEADER = "NAME";
	public static final String PROJECT_HEADER = "PROJECT";
	public static final String SEARCH_HEADER = "##SEARCH##";
	public static final String VIEW_SEATPLAN_HEADER = "##VIEW SEATPLAN##";
	public static final String VIEW_SEATPLAN_HEADER_BY_EMPLOYEE = "##VIEW SEATPLAN - By Employee ##";
	public static final String CONNECTION_ERROR_MESSAGE = "**There was an error while connecting to the server. Please contact your service provider.**";
	public static final String SQL_ERROR_MESSAGE = "**There must be something wrong with the input you entered. Please contact the developer.**";
}

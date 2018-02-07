package com.pointwest.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.pointwest.java.utils.Constants;
import com.pointwest.java.utils.PLSException;

public abstract class DAO {
	public static Logger log = Logger.getLogger(DAO.class);
	protected Connection getConnection() throws SQLException, PLSException {
		Connection connection = null;

		try {
			log.info("START");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
			log.info("END");
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.CONNECTION_ERROR_MESSAGE);
			throw ex;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.CONNECTION_ERROR_MESSAGE);
			throw ex;
		}

		return connection;
	}

	protected static void closeResultSet(ResultSet resultSet) throws PLSException {

		try {
			if (resultSet != null) {
				log.info("Resultset closed");
				resultSet.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.CONNECTION_ERROR_MESSAGE);
			throw ex;
		}
	}

	protected static void closeStatement(Statement statement) throws PLSException {

		try {

			if (statement != null) {
				log.info("Statement closed");
				statement.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.CONNECTION_ERROR_MESSAGE);
			throw ex;
		}
	}

	protected static void closeConnection(Connection connection) throws PLSException {

		try {
			
			if (connection != null) {
				log.info("Connection closed");
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			PLSException ex = new PLSException(Constants.CONNECTION_ERROR_MESSAGE);
			throw ex;
		}
	}
}

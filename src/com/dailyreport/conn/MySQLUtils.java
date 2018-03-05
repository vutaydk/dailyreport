package com.dailyreport.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLUtils {

	private Connection connection = null;
	
	public Connection getMySQLConnection(){
		// Note: Change the connection parameters accordingly.
		String hostName = "localhost";
		String dbName = "bookstore";
		String userName = "root";
		String password = "1111";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	private Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// URL Connection for MySQL:
			// Example:
			// jdbc:mysql://localhost:3306/simplehr
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

			connection = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;

	}

}

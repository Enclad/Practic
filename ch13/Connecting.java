package ch13;

import java.sql.*;

class Connecting {
	
	private static final String URL = "jdbc:mysql://localhost:3400/videos";
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
}

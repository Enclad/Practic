package ch13;

import java.sql.*;

class Mod {
	
	public static void deleteFilmsReleasedBeforeDate(Connection connection, int years_ago) throws SQLException {
		String sql = "DELETE FROM films WHERE release_date < date('now', '-? years')";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, years_ago);
		statement.executeUpdate();
	}
	
}

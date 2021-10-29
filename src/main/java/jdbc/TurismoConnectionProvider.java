package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TurismoConnectionProvider {
	private static String url = "jdbc:sqlite:C:\\Users\\rodri\\Documents\\DataBase\\TurismoEnLaTierraMedia.db";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{
		if(connection == null) {
			connection = DriverManager.getConnection(url);
		}
	return connection;
	}
}
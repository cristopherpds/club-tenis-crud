package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String URL = "jdbc:mysql://localhost:3306/clube3";

	public static Connection createConnectionToMySQL() throws SQLException {

		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = createConnectionToMySQL();

		if (conn != null) {
			System.out.println("Conexão realizada com sucesso!");
			conn.close();
		} else {
			System.out.println("Não foi possível realizar a conexão!");
		}
	}
}

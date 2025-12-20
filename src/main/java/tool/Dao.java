package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;

public class Dao {

	public Connection getConnection() throws SQLException, NamingException {
		try {
			Class.forName("org.postgresql.Driver");

			String url = System.getenv("DATABASE_URL"); // Render の環境変数
			return DriverManager.getConnection(url);

		} catch (ClassNotFoundException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}
}

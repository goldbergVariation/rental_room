package tool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {
	private static DataSource ds;

	public Connection getConnection() throws SQLException,NamingException {
		if (ds == null) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/rental_room");
		}
		return ds.getConnection();
	}

}
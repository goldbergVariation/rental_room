package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    private static final String URL = System.getenv("DATABASE_URL");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}

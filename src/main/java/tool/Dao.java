package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");

        return DriverManager.getConnection(url, user, password);
    }
}

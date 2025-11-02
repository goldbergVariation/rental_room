package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    private static final String DATABASE_URL = System.getenv("DATABASE_URL");

    public Connection getConnection() throws SQLException {
        // Render の postgres://... を JDBC が理解できる形式に変換
        String jdbcUrl = DATABASE_URL.replaceFirst("^postgres://", "jdbc:postgresql://");
        return DriverManager.getConnection(jdbcUrl);
    }
}
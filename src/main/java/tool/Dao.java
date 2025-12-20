package tool;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    public Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        String url = System.getenv("DATABASE_URL");  // Render の環境変数
        return DriverManager.getConnection(url);
    }
}

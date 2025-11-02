package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    public Connection getConnection() throws SQLException {
        String rawUrl = System.getenv("DATABASE_URL");
        if (rawUrl == null) {
            throw new SQLException("環境変数 DATABASE_URL が設定されていません");
        }

        // postgres://user:pass@host:port/dbname → jdbc:postgresql://host:port/dbname
        String jdbcUrl = rawUrl.replaceFirst("^postgres://", "jdbc:postgresql://");

        // ユーザー名とパスワードを抽出
        String[] parts = jdbcUrl.split("@");
        String creds = parts[0].split("//")[1]; // user:pass
        String rest = parts[1]; // host:port/db

        String[] userPass = creds.split(":");
        String user = userPass[0];
        String pass = userPass[1];

        String url = "jdbc:postgresql://" + rest;

        return DriverManager.getConnection(url, user, pass);
    }
}

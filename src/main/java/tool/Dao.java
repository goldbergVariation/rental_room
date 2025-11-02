package tool;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    public Connection getConnection() throws SQLException {
        // ドライバの明示ロード（Tomcatの環境で確実にするため）
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC ドライバが見つかりません", e);
        }

        String raw = System.getenv("DATABASE_URL");
        if (raw == null || raw.isBlank()) {
            throw new SQLException("環境変数 DATABASE_URL が設定されていません");
        }

        // Render 等で来る形式:
        // postgres://user:pass@host:port/dbname
        // or
        // postgresql://user:pass@host:port/dbname
        // URI でパースして JDBC 形式に変換する（堅牢）
        try {
            // URI は "postgres://" や "postgresql://" を受け取れる
            URI uri = new URI(raw);

            String userInfo = uri.getUserInfo(); // "user:pass"
            if (userInfo == null || !userInfo.contains(":")) {
                throw new SQLException("DATABASE_URL に user:password が含まれていません");
            }
            String[] up = userInfo.split(":", 2);
            String user = up[0];
            String pass = up[1];

            String host = uri.getHost();
            int port = uri.getPort(); // -1 の場合もある
            String path = uri.getPath(); // "/dbname"
            if (host == null || path == null || path.length() < 2) {
                throw new SQLException("DATABASE_URL の形式が不正です: " + raw);
            }

            StringBuilder jdbc = new StringBuilder();
            jdbc.append("jdbc:postgresql://");
            jdbc.append(host);
            if (port != -1) {
                jdbc.append(":").append(port);
            }
            jdbc.append(path); // path includes leading '/'

            // 追加のクエリパラメータがあれば付ける（例: ?sslmode=require）
            String query = uri.getQuery();
            if (query != null && !query.isBlank()) {
                jdbc.append("?").append(query);
            }

            String jdbcUrl = jdbc.toString();
            return DriverManager.getConnection(jdbcUrl, user, pass);

        } catch (Exception e) {
            if (e instanceof SQLException) throw (SQLException)e;
            throw new SQLException("DATABASE_URL の解析に失敗しました: " + e.getMessage(), e);
        }
    }
}

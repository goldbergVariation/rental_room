package tool;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {

    private static DataSource ds;

    public Connection getConnection() throws SQLException, NamingException {

        // ★ ローカル優先：ENVがlocal（または未設定）ならJNDIへ
        String env = System.getenv("ENV"); // 例: local / prod
        boolean isLocal = (env == null || env.isBlank() || "local".equalsIgnoreCase(env));

        if (isLocal) {
            return getJndiConnection();
        }

        // ★ 本番（Render等）：DATABASE_URL で JDBC 接続
        String url = System.getenv("DATABASE_URL");
        if (url == null || url.isBlank()) {
            throw new SQLException("DATABASE_URL が未設定です（ENV=prod の想定）");
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQLドライバが見つかりません", e);
        }

        if (url.startsWith("jdbc:")) {
            return DriverManager.getConnection(url);
        }

        if (url.startsWith("postgresql://") || url.startsWith("postgres://")) {
            try {
                URI uri = new URI(url);

                String userInfo = uri.getUserInfo(); // "user:pass"
                String username = null;
                String password = null;
                if (userInfo != null) {
                    String[] parts = userInfo.split(":", 2);
                    username = parts.length > 0 ? parts[0] : null;
                    password = parts.length > 1 ? parts[1] : null;
                }

                String host = uri.getHost();
                int port = (uri.getPort() == -1) ? 5432 : uri.getPort();
                String db = uri.getPath(); // "/rental_room_db"

                String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + db;
                if (username != null && password != null) {
                    jdbcUrl += "?user=" + username + "&password=" + password;
                }

                return DriverManager.getConnection(jdbcUrl);

            } catch (URISyntaxException e) {
                throw new SQLException("DATABASE_URLの形式が不正です: " + url, e);
            }
        }

        throw new SQLException("DATABASE_URLが未対応の形式です: " + url);
    }

    private Connection getJndiConnection() throws NamingException, SQLException {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/rental_room");
        }
        return ds.getConnection();
    }
}

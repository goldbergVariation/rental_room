package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import bean.Manager;
import tool.Dao;

public class ManagerDao extends Dao {

	public Manager getManager(String loginId) throws NamingException, SQLException {
		String sql = "SELECT * FROM managers where manager_login_id=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					Manager manager = new Manager();
					manager.setId(rs.getInt("manager_id"));
					manager.setLoginId(rs.getString("manager_login_id"));
					manager.setNickName(rs.getString("manager_nickname"));
					manager.setPassword(rs.getString("manager_password"));
					manager.setRole(rs.getString("manager_role"));
					manager.setEmail(rs.getString("manager_email"));
					manager.setCreatedAt(rs.getTimestamp("manager_created_at").toLocalDateTime());

					return manager;
				} else {
					return null;
				}
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	// 管理者のログインIDで、データーベースに管理者情報があるか探すメソッド
	public String getManagerLoginId(String loginId) throws NamingException, SQLException {
		String sql = "SELECT * FROM managers where manager_login_id=?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					Manager manager = new Manager();
					manager.setId(rs.getInt("manager_id"));
					manager.setLoginId(rs.getString("manager_login_id"));
					manager.setPassword(rs.getString("manager_password"));
					manager.setRole(rs.getString("manager_role"));

					// LocalDateTime で取得
					manager.setCreatedAt(rs.getTimestamp("manager_created_at").toLocalDateTime());

					return manager.getLoginId();
				} else {

					return null;
				}
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public boolean isEmail(String email) throws NamingException, SQLException {
		String sql = "SELECT * FROM managers where manager_email =?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery();) {

				return rs.next();
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public boolean isLoginId(String loginId) throws NamingException, SQLException {
		String sql = "SELECT * FROM managers where manager_login_id=?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			try (ResultSet rs = st.executeQuery();) {

				return rs.next();
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	// フォームから入力されたID、パスワードをデーターベースのManagersテーブルに入れるメソッド
	public boolean insertManager(Manager manager) throws NamingException, SQLException {
		String sql = "insert into managers (manager_login_id, manager_nickname, manager_password, manager_email) values(?,?,?,?) ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, manager.getLoginId());
			st.setString(2, manager.getNickName());
			st.setString(3, manager.getPassword());
			st.setString(4, manager.getEmail());
			int line = st.executeUpdate();

			return line > 0 ? true : false;
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}

	}
}
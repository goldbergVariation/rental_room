package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import bean.User;
import tool.Dao;

public class UserDao extends Dao {

	public User getUser(String loginId) throws NamingException, SQLException {
		String sql = "SELECT:: * FROM users where user_login_id=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("user_id"));
					user.setLoginId(rs.getString("user_login_id"));
					user.setNickName(rs.getString("user_nickname"));
					user.setPassword(rs.getString("user_password"));
					user.setRole(rs.getString("user_role"));
					user.setStatus(rs.getString("user_status"));
					user.setEmail(rs.getString("user_email"));
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());

					return user;
				} else {
					return null;
				}
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public User getUser(int id) throws NamingException, SQLException {
		String sql = "SELECT * FROM users where user_id=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("user_id"));
					user.setLoginId(rs.getString("user_login_id"));
					user.setNickName(rs.getString("user_nickname"));
					user.setRole(rs.getString("user_role"));
					user.setStatus(rs.getString("user_status"));
					user.setEmail(rs.getString("user_email"));
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());
					return user;
				} else {
					return null;
				}
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public boolean isLoginId(String loginId) throws NamingException, SQLException {
		String sql = "SELECT * FROM users where user_login_id=?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			try (ResultSet rs = st.executeQuery();) {

				return rs.next();
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public boolean isEmail(String email) throws NamingException, SQLException {
		String sql = "SELECT * FROM users where user_email =?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery();) {

				return rs.next();
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public boolean insertUser(User user) throws NamingException, SQLException {
		String sql = "insert into users(user_login_id,user_nickname,user_password, user_email)values(?,?,?,?)";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, user.getLoginId());
			st.setString(2, user.getNickName());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			int line = st.executeUpdate();

			return line > 0 ? true :false;
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public User getUser(String loginId, String password, String nickname) throws NamingException, SQLException {
		String sql = "SELECT * FROM users where user_login_id=? and user_password=? and user_nickname=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			st.setString(2, password);
			st.setString(3, nickname);
			st.setString(3, nickname);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("user_id"));
					user.setLoginId(rs.getString("user_login_id"));
					user.setNickName(rs.getString("user_nickname"));
					user.setPassword(rs.getString("user_password"));
					user.setRole(rs.getString("user_role"));
					user.setEmail(rs.getString("user_email"));
					user.setStatus(rs.getString("user_status"));

					// LocalDateTime で取得
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());

					return user;
				} else {
					return null;
				}
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public boolean cancelUser(int userId) throws NamingException, SQLException {
		String sql = "UPDATE users SET user_status = '退会済' WHERE user_id = ?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, userId);
			int result = st.executeUpdate();
			// 0より大きければture、小さければfalse
			return result > 0 ? true : false;
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

}

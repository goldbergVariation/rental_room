package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import bean.User;
import tool.Dao;

public class UserDao extends Dao {

	public User getUser(String loginId, String password) throws Exception {

		String sql = "SELECT * FROM users where user_login_id=? and user_password=?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			st.setString(2, password);
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

					// LocalDateTime で取得
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());

					return user;
				} else {

					return null;
				}
			}
		}
	}

	public User getUser(int id) throws Exception {

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
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());
					return user;
				} else {
					return null;
				}
			}
		}
	}

	public String getUserLoginId(String loginId) throws SQLException {
		String sql = "SELECT * FROM users where user_login_id=?";

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
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());

					return user.getLoginId();
				} else {
					return null;
				}
			}
		} catch (NamingException e) {
			throw new SQLException("データソースの取得に失敗しました", e);
		}
	}

	public int insertUser(User user) throws Exception {

		String sql = "insert into users(user_login_id,user_nickname,user_password, user_email)values(?,?,?,?)";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, user.getLoginId());
			st.setString(2, user.getNickName());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			int line = st.executeUpdate();

			return line;
		}
	}

	public User getUser(String loginId, String password, String nickname) throws Exception {

		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM users where user_login_id=? and user_password=? and user_nickname=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			st.setString(2, password);
			st.setString(3, nickname);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("user_id"));
					user.setLoginId(rs.getString("user_login_id"));
					user.setNickName(rs.getString("user_nickname"));
					user.setPassword(rs.getString("user_password"));
					user.setRole(rs.getString("user_role"));
					user.setStatus(rs.getString("user_status"));

					// LocalDateTime で取得
					user.setCreatedAt(rs.getTimestamp("user_created_at").toLocalDateTime());

					return user;
				} else {
					return null;
				}
			}
		}
	}

	public boolean cancelUser(int userId) throws Exception {

		String sql = "UPDATE users SET user_status = '退会済' WHERE user_id = ?";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, userId);
			int result = st.executeUpdate();
			// 0より大きければture、小さければfalse
			return result > 0 ? true : false;
		}
	}

}

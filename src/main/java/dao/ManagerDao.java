package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Manager;
import tool.Dao;

public class ManagerDao extends Dao {

	public Manager getManager(String loginId, String password) throws Exception {

		String sql = "SELECT * FROM managers where manager_login_id=? and manager_password=?";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			st.setString(2, password);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					Manager manager = new Manager();
					manager.setId(rs.getInt("manager_id"));
					manager.setLoginId(rs.getString("manager_login_id"));
					manager.setNickName(rs.getString("manager_nickname"));
					manager.setPassword(rs.getString("manager_password"));
					manager.setRole(rs.getString("manager_role"));
					
                // LocalDateTime で取得
                manager.setCreatedAt(rs.getTimestamp("manager_created_at").toLocalDateTime());
					
					return manager;
				} else {

					return null;
				}
			}
		}
	}
	
	
	//管理者のログインIDで、データーベースに管理者情報があるか探すメソッド
	public String getManagerLoginId(String loginId) throws Exception {

		String sql = "SELECT * FROM managers where manager_login_id=?";
		System.out.println(2);
		
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, loginId);
			try (ResultSet rs = st.executeQuery();) {

				System.out.println(3);
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
		}
	}

	
	//フォームから入力されたID、パスワードをデーターベースのManagersテーブルに入れるメソッド
	public boolean insertManager(Manager manager) throws Exception {

		String sql = "insert into managers(manager_login_id,manager_nickname,manager_password)values(?,?,?)";

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, manager.getLoginId());
			st.setString(2, manager.getNickName());			
			st.setString(3, manager.getPassword());
			int line = st.executeUpdate();

			return line > 0 ? true: false;

		}
	}
	
}

	
	






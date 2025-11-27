package guest;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class UserSignupAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String LoginId = request.getParameter("loginid");
			String password = request.getParameter("password");
			String nickName = request.getParameter("nickname");

			User u = new User();
			u.setLoginId(LoginId);
			u.setPassword(password);
			u.setNickName(nickName);

			UserDao dao = new UserDao();
			dao.insertUser(u);

			return "redirect:/guest/user_signup_finish.jsp";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("error_message", "empty_propertyid");				
		request.setAttribute("forward_page", "/rental_room/guest/top.jsp");	
		request.setAttribute("button", "利用者新規登録画面へ");
		return "/common/system_error.jsp";
	}
}

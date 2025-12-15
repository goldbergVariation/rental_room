package manager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import bean.Manager;
import dao.ManagerDao;
import tool.Action;

public class ManagerSignupAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String loginId = request.getParameter("loginId");
			String nickName = request.getParameter("nickName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			int maxlength = 20;
			int minlength = 4;

			String[] var = { loginId, nickName, password, email };

			List<String> list = new ArrayList<>(Arrays.asList(var));
			for (String str : list) {
				if (StringUtils.isBlank(str)) {
					request.setAttribute("error_message", "empty");
					request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
					request.setAttribute("button", "管理者新規登録へ");
					return "/common/input_error.jsp";
				}
			}

			if (!(loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					&& password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					&& (nickName.length() >= 1 && nickName.length() <= 20)
					&& (email.length() >= 4 && email.length() <= 50))) {

				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録へ");
				return "/common/input_error.jsp";
			}

			Manager manager = new Manager();
			manager.setLoginId(loginId);
			manager.setPassword(password);
			manager.setNickName(nickName);
			manager.setEmail(email);

			ManagerDao dao = new ManagerDao();
			if (dao.insertManager(manager)) {
				return "redirect:/manager/manager_signup_complete.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("error_message", "empty_propertyid");
		request.setAttribute("forward_page", "/rental_room/guest/top.jsp");
		request.setAttribute("button", "管理者新規登録画面へ");
		return "/common/system_error.jsp";
	}
}

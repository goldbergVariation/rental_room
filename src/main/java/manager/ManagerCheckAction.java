package manager;

import java.sql.SQLException;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import dao.ManagerDao;
import tool.Action;

public class ManagerCheckAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String loginId = request.getParameter("loginId");
			String nickName = request.getParameter("nickName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			// 「空白 or 空文字 or null」なら true。
			if (Stream.of(loginId, password, nickName, email).anyMatch(StringUtils::isBlank)) {
				request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録");
				return "/common/input_error.jsp";
			}

			int maxlength = 20;
			int minlength = 4;

			if (!(loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					&& password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					&& (nickName.length() >= 1 && nickName.length() <= 20)
					&& (email.length() >= 4 && email.length() <= 50))) {

				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録");
				return "/common/input_error.jsp";
			} 

			ManagerDao dao = new ManagerDao();
			boolean isLoginId = dao.isLoginId(loginId);

			// ID 重複確認
			if (isLoginId){
				request.setAttribute("error_message", "duplicate_loginId");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録へ");
				return "/common/input_error.jsp";
			}
			
			boolean isEmail = dao.isEmail(email);

			// Email 重複確認
			if (isEmail) {
				request.setAttribute("error_message", "duplicate_email");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録へ");
				return "/common/input_error.jsp";
			}

			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("nickName", nickName);
			request.setAttribute("email", email);

			return "manager_signup_confirm.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

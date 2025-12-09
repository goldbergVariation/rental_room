package guest;

import java.sql.SQLException;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import dao.UserDao;
import tool.Action;

public class UserCheckAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String loginId = request.getParameter("loginid");
			String password = request.getParameter("password");
			String nickName = request.getParameter("nickname");
			String email = request.getParameter("email");

			int maxlength = 20;
			int minlength = 4;

			// 「空白 or 空文字 or null」なら true。
			if (Stream.of(loginId, password, nickName, email).anyMatch(StringUtils::isBlank)) {

				request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp");
				request.setAttribute("button", "利用者新規登録へ");
				return "/common/input_error.jsp";
			}

			if (!(loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					&& password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					&& (nickName.length() >= 1 && nickName.length() <= 20)
					&& (email.length() >= 4 && email.length() <= 50))) {

				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp");
				request.setAttribute("button", "利用者新規登録へ");
				return "/common/input_error.jsp";
			} 

			UserDao dao = new UserDao();
			String userLoginId = dao.getUserLoginId(loginId);

			if (userLoginId != null) {// ID重複確認
				request.setAttribute("error_message", "duplicate");
				request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp");
				request.setAttribute("button", "利用者新規登録へ");
				return "/common/input_error.jsp";
			}

			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("nickName", nickName);
			request.setAttribute("email", email);

			return "user_signup_confirm.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

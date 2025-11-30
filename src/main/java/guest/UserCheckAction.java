package guest;

import java.sql.SQLException;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import dao.UserDao;
import tool.Action;

public class UserCheckAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			String loginId = request.getParameter("loginid");
			String password = request.getParameter("password");
			String nickName = request.getParameter("nickname");

			UserDao dao = new UserDao();
			String userLoginId = dao.getUserLoginId(loginId);

			int maxlength = 20;
			int minlength = 4;

			// 「空白 or 空文字 or null」なら true。
			if (Stream.of(loginId, password, nickName).anyMatch(StringUtils::isBlank)) {
				// true)

				request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp");
				request.setAttribute("button", "利用者新規登録へ");
				return "/common/input_error.jsp";

			// 4文字以上20文字未満のチェック
			} else if (!loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					|| !password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					|| !(nickName.length() <= 1 || maxlength >= nickName.length())) {

				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp");
				request.setAttribute("button", "利用者新規登録へ");
				return "/common/input_error.jsp";

			} else if (userLoginId != null) {// ID重複確認

				request.setAttribute("error_message", "duplicate");
				request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp");
				request.setAttribute("button", "利用者新規登録へ");
				return "/common/input_error.jsp";

			}

			session.setAttribute("loginId", loginId);
			session.setAttribute("password", password);
			String hidePass = "*".repeat(password.length());
			session.setAttribute("hidePass", hidePass);
			session.setAttribute("nickName", nickName);

			return "user_signup_confirm.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

package manager;

import java.sql.SQLException;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import dao.ManagerDao;
import tool.Action;

public class ManagerCheckAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			String loginId = request.getParameter("loginId");
			String nickName = request.getParameter("nickName");
			String password = request.getParameter("password");

			if (StringUtils.isBlank(loginId)) {
				request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録");
				return "/common/input_error.jsp";
			}

			System.out.println(1);
			ManagerDao dao = new ManagerDao();
			String managerLoginId = dao.getManagerLoginId(loginId);

			System.out.println(2);
			int maxlength = 20;
			int minlength = 4;

			// if(loginId==null || loginId.trim().isEmpty()){

			// 「空白 or 空文字 or null」なら true。
			if (Stream.of(loginId, password, nickName).anyMatch(StringUtils::isBlank)) {
				// true)
				System.out.println(3);

				request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録");
				return "/common/input_error.jsp";

				// 4文字以上20文字未満のチェック
			} else if (!loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					|| !password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					|| !(nickName.length() <= 1 || maxlength >= nickName.length())) {

				System.out.println(4);
				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録");
				return "/common/input_error.jsp";

			} else if (managerLoginId != null) {// ID重複確認

				System.out.println(3);
				request.setAttribute("error_message", "duplicate");
				request.setAttribute("forward_page", "/rental_room/manager/manager_signup.jsp");
				request.setAttribute("button", "管理者新規登録");
				return "/common/input_error.jsp";

			}

			session.setAttribute("loginId", loginId);
			session.setAttribute("password", password);
			String hidePass = "*".repeat(password.length());
			session.setAttribute("hidePass", hidePass);
			session.setAttribute("nickName", nickName);

			return "manager_signup_confirm.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}
			// メモ
			// String hidePass=StringUtils.repeat("*",password.length());
			// StringUtilsでnullを回避できるが使用方法が分からない
			// StringUtils.isBlank(null); // true
			// StringUtils.isBlank(""); // true
			// StringUtils.isBlank(" "); // true
			// メモ終わり

			// User user=dao.getUser(loginId,password,nickName);
package manager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Manager;
import dao.ManagerDao;
import tool.Action;

public class ManagerSignupAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String loginId = request.getParameter("loginId");
			String nickName = request.getParameter("nickName");
			String password = request.getParameter("password");

			Manager m = new Manager();
			m.setLoginId(loginId);
			m.setNickName(nickName);
			m.setPassword(password);

			ManagerDao dao = new ManagerDao();
			boolean result = dao.insertManager(m);

			if (result) {
				return "manager_signup_complete.jsp";
			}

			return "/common/system_error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/common/system_error.jsp";
		}
	}
}

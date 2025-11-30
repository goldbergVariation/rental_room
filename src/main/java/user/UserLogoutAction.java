package user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;

public class UserLogoutAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response){

		try {

			HttpSession session = request.getSession();

			if (session.getAttribute("account") != null) {
				session.invalidate();//これでセッション全部消える
				return "/guest/user_logout.jsp";

			}
			
		} catch (IllegalStateException e) {	

			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}

		return "/common/system_error.jsp";

	}

}

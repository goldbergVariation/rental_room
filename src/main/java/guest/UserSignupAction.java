package guest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class UserSignupAction extends Action {

	

		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

			String LoginId = request.getParameter("loginid");
			String password= request.getParameter("password");
			String nickName= request.getParameter("nickname");
			System.out.println(1);
		

			User u = new User();
			u.setLoginId(LoginId);
			u.setPassword(password);
			u.setNickName(nickName);
			System.out.println(2);
			
			UserDao dao  = new UserDao();
			dao.insertUser(u);
			System.out.println(3);
			
			
			return "user_signup_finish.jsp";

		}

	}

	
	
	


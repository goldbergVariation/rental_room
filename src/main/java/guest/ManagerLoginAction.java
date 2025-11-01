package guest;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Manager;
import dao.ManagerDao;
import tool.Action;

public class ManagerLoginAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			System.out.println("1");

			String loginId = request.getParameter("login_id");
			String password = request.getParameter("password");

			int minlength = 4;
			int maxlength = 20;

			//ログインIDとパスワードが空文字かnullかのチェック
			if (loginId == null || loginId.trim().isEmpty() ||
					password == null || password.trim().isEmpty()) {

				request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/guest/manager_login.jsp	");	
				request.setAttribute("button", "管理者ログインへ");	
				return "/common/input_error.jsp";
				//入力画面に戻す

				//文字数・文字種のチェック
			} else if (!loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")
					|| !password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")) {

				request.setAttribute("error_message", "wrong");	
				request.setAttribute("forward_page", "/rental_room/guest/manager_login.jsp	");	
				request.setAttribute("button", "管理者ログインへ");	
				return "/common/input_error.jsp";
				//入力エラーページに戻す

			}

			ManagerDao dao = new ManagerDao();
			Manager manager = dao.getManager(loginId, password);
			System.out.println("2");

			//nullではない場合(照合できた場合)
			if (manager != null) {
				//セッション属性に設定
				manager.setPassword(null);
				session.setAttribute("account", manager);
				//フォワードファイル(login-out.jsp)を戻す
				System.out.println(manager.getNickName());
				System.out.println(manager.getId());
				System.out.println("3");
				return "/guest/top.jsp";
			}else {
				System.out.println("4");
				request.setAttribute("error_message", "wrong");	
				request.setAttribute("forward_page", "/rental_room/guest/manager_login.jsp	");	
				request.setAttribute("button", "管理者ログインへ");	
				return "/common/input_error.jsp";
				
				
			}
		

		} catch (SQLException e) {

			e.printStackTrace();
			request.setAttribute("message", "システムエラーです");
			request.setAttribute("forward_page", "/rental_room/guest/manager_login.jsp	");	
			request.setAttribute("button", "管理者ログインへ");	
			return "/common/system_error.jsp";

		} catch (Exception e) {

			e.printStackTrace();
			
			System.out.println("5");
			e.printStackTrace();
			request.setAttribute("message", "システムエラーです");
			request.setAttribute("forward_page", "/rental_room/guest/manager_login.jsp	");	
			request.setAttribute("button", "管理者ログインへ");	
			return "/common/system_error.jsp";
		
		}
	


	}

}

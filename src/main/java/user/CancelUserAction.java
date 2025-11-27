package user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

	public class CancelUserAction extends Action {

	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        // accountをセッションから取得
	        User account = (User) request.getSession().getAttribute("account");

	        if (account == null) {
	            request.setAttribute("error", "ログインが必要です。");
	            return "/guest/top.jsp";
	        }

	        int userId = account.getId();

	        UserDao dao = new UserDao();
	        boolean result = dao.cancelUser(userId);

	        if (result) {

	            // セッション破棄
	            request.getSession().invalidate();
	            
	            return "/guest/cancel_complete.jsp";
	        } else {
	            return "/common/system_error.jsp";
	        }
	    }
	}


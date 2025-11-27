package user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bean.User;
import tool.Action;

public class UserReviewCheckAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		HttpSession session = request.getSession();

		// フォームから受け取る	
		String review = request.getParameter("review");

		String nickName = request.getParameter("nickName");

		//ID

		String userId = request.getParameter("userId");
		String propertyId = request.getParameter("propertyId");
			
		int maxlength = 300;
		int minlength = 1;

		// 空チェック
		if (StringUtils.isBlank(review)) {
			request.setAttribute("error_message", "commenterror");
			request.setAttribute("forward_page", "/rental_room/user/user_review.jsp");
			request.setAttribute("button", "周辺地域の口コミ登録へ");
			return "/common/input_error.jsp";
		} else

		// 長さチェック+文字種チェック
		if (!(review.length() <= minlength || maxlength >= review.length())) {
			request.setAttribute("error_message", "wrong");
			request.setAttribute("forward_page", "/rental_room/user/user_review.jsp");
			request.setAttribute("button", "周辺地域の口コミ登録へ");
			return "/common/input_error.jsp";
		}

		// 正常処理
		User user = (User) session.getAttribute("account");
		request.setAttribute("account", user);

		request.setAttribute("review", review);
		session.setAttribute("nickName", nickName);
		session.setAttribute("userId", userId);
		session.setAttribute("propertyId", propertyId);
		
		return "user_review_confirm.jsp";
	}
}

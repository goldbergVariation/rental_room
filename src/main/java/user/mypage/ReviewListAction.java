package user.mypage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bean.Account;
import bean.Review;
import dao.ReviewDao;
import tool.Action;

public class ReviewListAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if(session == null) {
				request.setAttribute("message", "accountがありません");
				return "/common/system_error.jsp";
			}

			Account account = (Account)session.getAttribute("account");
			if(account == null) {
				request.setAttribute("message", "accountがありません");
				return "/common/system_error.jsp";
			}
			
			int userId = account.getId();

			ReviewDao reviewDao = new ReviewDao();
			List<Review> reviews = reviewDao.getReviewsByUserId(userId);

			// 口コミの有無での分岐
			if (reviews == null || reviews.isEmpty()) {
				request.setAttribute("reviews", new ArrayList<Review>());

				return "/user/mypage/reviews.jsp";
			} else {
				request.setAttribute("reviews", reviews);

				return "/user/mypage/reviews.jsp";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "入力に誤りがありました");
			return "/common/system_error.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "システムエラーが発生しました。<br>再度検索をお願いします。");
			return "/common/system_error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "システムエラーが発生しました。<br>再度検索をお願いします。");
			return "/common/system_error.jsp";
		}
	}
}
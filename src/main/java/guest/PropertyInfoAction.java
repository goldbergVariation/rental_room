package guest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Property;
import bean.Review;
import bean.User;
import dao.PropertyDao;
import dao.ReviewDao;
import dao.UserDao;
import tool.Action;

public class PropertyInfoAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idStr = request.getParameter("id");
			// idの無効な値を０に変換
			if (idStr == null || idStr.trim().isBlank()) {
				System.out.println("search info result null");
				request.setAttribute("message", "該当する物件が見つかりませんでした");
				return "/common/system_error.jsp";
			}
			int id = Integer.parseInt(idStr);

			PropertyDao propertyDao = new PropertyDao();
			Property property = propertyDao.getPropertyInfo(id);

			// 物件が見つからなかったとき
			if (property == null) {
				System.out.println("search info result null");
				request.setAttribute("message", "該当する物件が見つかりませんでした");
				return "/common/system_error.jsp";
			}

			System.out.println("search property info success");
			request.setAttribute("property", property);

			ReviewDao reviewDao = new ReviewDao();
			List<Review> reviews = reviewDao.getReviews(id);

			// 口コミの有無での分岐
			// ニックネームをArrayList<String>にて渡す
			if (reviews == null || reviews.isEmpty()) {
				System.out.println("reviews is null");
				request.setAttribute("nickNames", new ArrayList<String>());
				request.setAttribute("reviews", new ArrayList<Review>());

				return "/guest/property_info.jsp";
			} else {
				List<String> nickNames = new ArrayList<String>();
				UserDao userDao = new UserDao();
				for (Review review : reviews) {
					User user = userDao.getUser(review.getUserId());
					String userNickName = user.getNickName();
					nickNames.add(userNickName);
				}

				System.out.println("reviews get success");
				request.setAttribute("nickNames", nickNames);
				request.setAttribute("reviews", reviews);

				return "/guest/property_info.jsp";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("search error(NumberFormat)");
			request.setAttribute("message", "入力に誤りがありました");
			return "/common/system_error.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("search error(SQL)");
			request.setAttribute("message", "システムエラーが発生しました。<br>再度検索をお願いします。");
			return "/common/system_error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("search error");
			request.setAttribute("message", "システムエラーが発生しました。<br>再度検索をお願いします。");
			return "/common/system_error.jsp";
		}
	}
}
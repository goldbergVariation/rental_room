package user.mypage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import dao.ReviewDao;
import tool.Action;

public class ReviewDeleteAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String message = null; // 共通メッセージ置き場
		String forward = "/common/system_error.jsp"; // 失敗時の遷移先を統一

		// ① 入力チェック
		String reviewIdStr = request.getParameter("reviewId");
		if (StringUtils.isBlank(reviewIdStr)) {
			message = "入力に誤りがありました";
			request.setAttribute("message", message);
			return forward;
		}

		// ② 数値変換
		int reviewId;
		try {
			reviewId = Integer.parseInt(reviewIdStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			message = "入力に誤りがありました";
			request.setAttribute("message", message);
			return forward;
		}

		// ③ ID妥当性チェック
		if (reviewId <= 0) {
			message = "入力に誤りがありました";
			request.setAttribute("message", message);
			return forward;
		}

		// ④ DB削除処理
		try {
			ReviewDao dao = new ReviewDao();
			boolean result = dao.deleteReview(reviewId);

			if (result) {
				return "review_delete_complete.jsp";
			}

			// result が false → 削除対象無し
			message = "削除対象が存在しません";
		} catch (SQLException e) {
			e.printStackTrace();
			message = "SQLエラーが発生しました。";
		} catch (Exception e) {
			e.printStackTrace();
			message = "システムエラーが発生しました。";
		}

		// ⑤ エラーメッセージ設定してエラー画面へ
		request.setAttribute("message", message);
		return forward;
	}
}

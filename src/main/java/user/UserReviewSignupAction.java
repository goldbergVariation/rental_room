package user;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Review;
import bean.User;
import dao.ReviewDao;
import tool.Action;

public class UserReviewSignupAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	try {
        // フォームから値を受け取る
        String review = request.getParameter("review");
 
		int maxlength = 300;
		int minlength = 1;
        
		// 長さチェック+文字種チェック
		if (!(review.length() <= minlength || maxlength >= review.length())) {
			request.setAttribute("error_message", "wrong");
			request.setAttribute("forward_page", "/rental_room/user/user_review.jsp");
			request.setAttribute("button", "周辺地域の口コミ登録へ");
			return "/common/input_error.jsp";
		}

        // ログイン中のユーザー情報をセッションから取得
        User user = (User) request.getSession().getAttribute("account");
        if (user == null) {
            
            request.setAttribute("error_message", "empty_loginid");
            request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp");
            request.setAttribute("button", "利用者ログインへ");
            return "/common/system_error.jsp";// ログインしていない場合
        }

        int userId = user.getId();
        
        String propertyIdStr = request.getParameter("propertyId"); // ← 前画面からhiddenで送る想定
        if (propertyIdStr == null || propertyIdStr.isEmpty()) {
   
            request.setAttribute("error_message", "empty_propertyid");
            request.setAttribute("forward_page", "/rental_room/guest/top.jsp");
            request.setAttribute("button", "物件検索へ");	
            return "/common/system_error.jsp"; // propertyIdがない場合
        }
        
        int propertyId = Integer.parseInt(propertyIdStr);
        
        String comment = request.getParameter("review");
        if (comment == null || comment.trim().isEmpty()) {
        	
            request.setAttribute("error_message", "empty_comment");
            request.setAttribute("forward_page", "/rental_room/user/user_review.jsp");
            request.setAttribute("button", "周辺地域の口コミ登録へ");
            return "/common/system_error.jsp";// 入力画面に戻す
        }

        Review review2 = new Review();
        review2.setComment(comment);

        ReviewDao dao = new ReviewDao();
        dao.insertReview(review2, userId, propertyId);
        
    }catch(SQLIntegrityConstraintViolationException e) {
    	
		e.printStackTrace();
        request.setAttribute("error_message", "review_duplicate");
		request.setAttribute("forward_page", "/rental_room/guest/top.jsp");
		request.setAttribute("button", "トップページへ");
		return "/common/input_error.jsp";
    	
    }
    	catch (Exception e) {
		e.printStackTrace();
        request.setAttribute("error_message", "empty_propertyid");
		request.setAttribute("forward_page", "/rental_room/guest/top.jsp");
		request.setAttribute("button", "物件検索へ");
		return "/common/system_error.jsp";
	}

        //  完了画面へ
        return "user_review_complete.jsp";
    }
}


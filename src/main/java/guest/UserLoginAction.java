package guest;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class UserLoginAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            // セッション取得
            HttpSession session = request.getSession();

            // リクエストパラメータを取得
            String loginId = request.getParameter("login_id");
            String password = request.getParameter("password");

            int minlength = 4;
            int maxlength = 20;

            // ログインIDとパスワードが空文字かnullかのチェック
            if (loginId == null || loginId.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

                request.setAttribute("error_message", "empty");
                request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp");
                request.setAttribute("button", "利用者ログインへ");
                return "/common/input_error.jsp";
                //入力エラーページに戻す
            }

            // 文字数・文字種のチェック
            if (!loginId.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$") ||
                !password.matches("^[a-zA-Z0-9]{" + minlength + "," + maxlength + "}$")) {

                request.setAttribute("error_message", "wrong");
                request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp");
                request.setAttribute("button", "利用者ログインへ");
                return "/common/input_error.jsp";
                //入力エラーページに戻す
            }

            // DAOでユーザー検索
            UserDao dao = new UserDao();
            User user = dao.getUser(loginId, password);

            if (user != null) {
                // 退会済みユーザーはログイン不可
                if ("退会済".equals(user.getStatus())) {
                	request.setAttribute("error_message", "withdrawal"); // 退会済み用のエラーキー
                	request.setAttribute("forward_page", "/rental_room/guest/user_signup.jsp	");	
                	request.setAttribute("button", "利用者新規登録へ");
                    return "/common/input_error.jsp";
                }

                // ログイン成功処理
                user.setPassword(null); // パスワードはセッションに保存しない
                session.setAttribute("account", user);

                return "/guest/top.jsp";
            }else {
				request.setAttribute("error_message", "wrong");	
				request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp	");	
				request.setAttribute("button", "利用者ログインへ");	
				return "/common/input_error.jsp"; 
                // ユーザーが存在しない場合
                //request.setAttribute("error_message", "notfound");
                //request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp");
                //request.setAttribute("button", "利用者ログインへ");
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "システムエラーです");
            request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp");
            request.setAttribute("button", "利用者ログインへ");
            return "/common/system_error.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "システムエラーです");
            request.setAttribute("forward_page", "/rental_room/guest/user_login.jsp");
            request.setAttribute("button", "利用者ログインへ");
            return "/common/system_error.jsp";
        }
    }
}


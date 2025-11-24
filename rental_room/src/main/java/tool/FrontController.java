package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 画像をアップロードするために付与
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,     // 一時保存を使う閾値
	    maxFileSize = 1024 * 1024 * 2,       // アップロード1ファイル最大 2MB
	    maxRequestSize = 1024 * 1024 * 10    // リクエスト全体で最大 10MB
)
// .actionにマッチ
@WebServlet(urlPatterns = { "*.action" })
public class FrontController  extends HttpServlet{
    private static final long serialVersionUID = 1L;

    // .action を Actionに変更して実行する
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		String path = request.getServletPath().substring(1);
    		String name = path.replace(".action", "Action").replace("/", ".");
    		Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
    		String url = action.execute(request, response);
    		
    		if(url.startsWith("redirect:")) {
    			String reidrectPath = url.substring("redirect:".length());
    			response.sendRedirect(request.getContextPath() + reidrectPath);
    		} else {
    			request.getRequestDispatcher(url).forward(request, response);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		request.setAttribute("message", "不正なアクションが実行されました");
    		request.getRequestDispatcher("/common/system_error.jsp").forward(request, response);
		}
    }

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

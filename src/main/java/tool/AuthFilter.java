package tool;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Account;


public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getServletPath();

        // 公開ディレクトリはフリーパス
        if (path.startsWith("/css") || path.startsWith("/images")
                || path.startsWith("/guest") || path.startsWith("/common")) {
            chain.doFilter(req, res);
            return;
        }

		HttpSession session = request.getSession(false);
		Account account = (session != null) ? (Account) session.getAttribute("account") : null;
		
		if (account == null) {
		    response.sendRedirect(request.getContextPath() + "/guest/top.jsp");
		    return;
		}
		
		// 利用者専用
		if (path.startsWith("/user") && !"利用者".equals(account.getRole())) {
		    response.sendRedirect(request.getContextPath() + "/guest/top.jsp");
		    return;
		}
		
		// 管理者専用
		if (path.startsWith("/manager") && !"管理者".equals(account.getRole())) {
		    response.sendRedirect(request.getContextPath() + "/guest/top.jsp");
		    return;
		}
        chain.doFilter(req, res);
    }
}
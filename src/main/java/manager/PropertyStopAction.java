package manager;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import dao.PropertyDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class PropertyStopAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idStr = request.getParameter("id");
			if(StringUtils.isBlank(idStr)) {
				System.out.println("物件IDがない");
				return "/common/system_error.jsp";
			}
			int id = Integer.parseInt(idStr);

			PropertyDao propertyDao = new PropertyDao();
			boolean result = propertyDao.stopProperty(id);
			
			if(result) {
				System.out.println("物件掲載停止完了");
				return "/manager/property_stop_complete.jsp";
			}else {
				System.out.println("物件掲載停止失敗");
				return "/common/system_error.jsp";
			}
		} catch (SQLException e) {
			System.out.println("物件登録失敗　SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("物件登録失敗");
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

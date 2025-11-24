package manager;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import dao.PropertyDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class PropertyRepublishAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idStr = request.getParameter("id");
			if(StringUtils.isBlank(idStr)) {
				System.out.println("物件IDがない");
				return "/common/system_error.jsp";
			}
			int id = Integer.parseInt(idStr);

			PropertyDao propertyDao = new PropertyDao();
			boolean result = propertyDao.republishProperty(id);
			
			if(result) {
				System.out.println("物件掲載再開完了");
				return "/manager/property_republish_complete.jsp";
			}else {
				System.out.println("物件掲載再開失敗");
				return "/common/system_error.jsp";
			}
		} catch (SQLException e) {
			System.out.println("物件再掲載失敗　SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("物件再掲載失敗");
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

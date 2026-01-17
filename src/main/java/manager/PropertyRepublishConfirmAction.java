package manager;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import bean.Property;
import dao.PropertyDao;
import tool.Action;

public class PropertyRepublishConfirmAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idStr = request.getParameter("id");
			if(StringUtils.isBlank(idStr)) {
				return "/common/system_error.jsp";
			}
			int id = Integer.parseInt(idStr.trim());

			PropertyDao propertyDao = new PropertyDao();
			Property property = new Property();
			property = propertyDao.getPropertyInfo(id);
			
			if(property != null) {
				request.setAttribute("property", property);
				return "/manager/property_republish_confirm.jsp";
			}else {
				return "/common/system_error.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

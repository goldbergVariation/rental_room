package manager;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PropertyService;

import org.apache.commons.lang3.StringUtils;

import dao.PropertyDao;
import tool.Action;

public class PropertyDeleteAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idStr = request.getParameter("id");
			if(StringUtils.isBlank(idStr)) {
				return "/common/system_error.jsp";
			}
			int id = Integer.parseInt(idStr.trim());
			
			// 物件(物件コードid)についている口コミも含め削除する

			PropertyService propertyService = new PropertyService();
			propertyService.deleteProperty(id);
			return "/manager/property_delete_complete.jsp";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/system_error.jsp";
	}
}

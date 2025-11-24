package guest;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Account;
import bean.Property;
import dao.PropertyDao;
import tool.Action;

public class PropertySearchAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String priceNumStr = request.getParameter("priceNum");
			// 賃料の無効な値を０に変換
			if (priceNumStr == null || priceNumStr.trim().isBlank()) {
				priceNumStr = "0";
			}
			int priceNum = Integer.parseInt(priceNumStr);

			// 不正な賃料番号を排除
			List<Integer> priceNums = Arrays.asList(0, 1, 2, 3, 4);
			if (!priceNums.contains(priceNum)) {
				priceNum = 0;
			}

			String cityNumStr = request.getParameter("cityNum");
			// 地区の無効な値を０に変換
			if (cityNumStr == null || cityNumStr.trim().isBlank()) {
				cityNumStr = "0";
			}
			int cityNum = Integer.parseInt(cityNumStr);

			// 不正な地区番号を排除
			List<Integer> cityNums = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
			if (!cityNums.contains(cityNum)) {
				cityNum = 0;
			}

			String[] layouts = request.getParameterValues("layout");

			HttpSession session = request.getSession(false);

			PropertyDao propertyDao = new PropertyDao();
			List<Property> properties;

			if (session != null) {
				// 管理者は掲載停止、空室ともに閲覧可能
				Account account = (Account) session.getAttribute("account");
				if (account != null && account.getRole().equals("管理者")) {
					properties = propertyDao.searchPropertiesForManager(priceNum, layouts, cityNum);

					// 物件が見つからなかったとき
					if (properties == null || properties.isEmpty()) {
						System.out.println("search result null");
    					request.setAttribute("properties_all", properties);
						return "/guest/property_search_result.jsp";
					}

					request.setAttribute("properties_all", properties);
					System.out.println("search success(manager)");

					return "/guest/property_search_result.jsp";

				// ゲスト・利用者は掲載停止は閲覧不可
				} else {
					properties = propertyDao.searchPropertiesVacant(priceNum, layouts, cityNum);

					// 物件が見つからなかったとき
					if (properties == null || properties.isEmpty()) {
						System.out.println("search result null");
				        request.setAttribute("properties_vacant", properties);
						return "/guest/property_search_result.jsp";
					}

					System.out.println("search success(guest,user)");
					request.setAttribute("properties_vacant", properties);

					return "/guest/property_search_result.jsp";
				}
			// セッションがないとき
			} else {
				properties = propertyDao.searchPropertiesVacant(priceNum, layouts, cityNum);

				// 物件が見つからなかったとき
				if (properties == null || properties.isEmpty()) {
					System.out.println("search result null");
				    request.setAttribute("properties_vacant", properties);
					return "/guest/property_search_result.jsp";
				}

				System.out.println("search success(guest,user)");
				request.setAttribute("properties_vacant", properties);
				return "/guest/property_search_result.jsp";
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("search error(NumberFormat)");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("search error(SQL)");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("search error");
		}
		request.setAttribute("error_message", "empty_propertyid");				
		request.setAttribute("forward_page", "/rental_room/guest/top.jsp");	
		request.setAttribute("button", "検索画面へ");
		return "/common/system_error.jsp";
	}
}
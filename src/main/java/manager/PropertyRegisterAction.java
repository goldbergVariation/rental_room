package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import bean.Property;
import dao.PropertyDao;
import tool.Action;

public class PropertyRegisterAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String priceStr = request.getParameter("price");
			String info = request.getParameter("info");
			String layout = request.getParameter("layout");
			String pet = request.getParameter("pet");
			String city = request.getParameter("city");
			String address = request.getParameter("address");
			String fileName = request.getParameter("image");

			String[] var = { name, priceStr, info, layout, pet, city, address, fileName};

			System.out.println(1);

			List<String> list = new ArrayList<>(Arrays.asList(var));
			for (String str : list) {
				if(StringUtils.isBlank(str)) {
					System.out.println("未入力があります");
					request.setAttribute("error_message", "empty");
					request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
					return "/common/input_error.jsp";
				}
			}
			int price = Integer.parseInt(priceStr);
			
			if(name.length() > 50 || price > 10000000 || info.length() > 500 || pet.length() > 10 || city.length() > 10 || address.length() > 50) {
				System.out.println("入力文字数過多");
				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
				return "/common/input_error.jsp";
			}
			
			
			Property property = new Property();
			property.setName(name);
			property.setPrice(price);
			property.setInfo(info);
			property.setLayout(layout);
			property.setPet(pet);
			property.setImageName(fileName);
			property.setCity(city);
			property.setAddress(address);

			PropertyDao propertyDao = new PropertyDao();
			boolean result = propertyDao.insertProperty(property);
			
			if(result) {
				System.out.println("物件登録完了");
				return "redirect:/manager/property_register_complete.jsp";
			}else {
				System.out.println("物件登録失敗");
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

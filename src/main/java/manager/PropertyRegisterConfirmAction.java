package manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import bean.Property;
import tool.Action;

public class PropertyRegisterConfirmAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String priceStr = request.getParameter("price");
			String info = request.getParameter("info");
			String layout = request.getParameter("layout");
			String pet = request.getParameter("pet");
			String city = request.getParameter("city");
			String address = request.getParameter("address");
			
			Part filePart = request.getPart("image");
			if (filePart == null || filePart.getSize() == 0) {
			    request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
			    return "/common/input_error.jsp";
			}
			String contentType = filePart.getContentType();
			
			if (contentType == null || !(contentType.equals("image/jpeg"))) {
			    request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
			    return "/common/input_error.jsp";
			}

			String fileName = filePart.getSubmittedFileName();
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
				return "/common/input_error.jsp";
			}

			String savePath = request.getServletContext().getRealPath("/images/") + fileName;
			filePart.write(savePath);

			System.out.println(2);

			Property property = new Property();
			property.setName(name);
			property.setPrice(price);
			property.setInfo(info);
			property.setLayout(layout);
			property.setPet(pet);
			property.setImageName(fileName);
			property.setCity(city);
			property.setAddress(address);
			
			System.out.println("物件の確認画面へ");
			request.setAttribute("property", property);
			return "/manager/property_register_confirm.jsp";

		} catch (Exception e) {
			System.out.println("失敗");
			e.printStackTrace();
			return "/common/system_error.jsp";
		}
	}
}

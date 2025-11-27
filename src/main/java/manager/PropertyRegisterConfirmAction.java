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
			String contentType = filePart.getContentType();
			if (filePart == null || filePart.getSize() == 0 || contentType == null) {
			    request.setAttribute("error_message", "empty");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
				request.setAttribute("button", "物件登録へ");
			    return "/common/input_error.jsp";
			}
			
			if ( !(contentType.equals("image/jpeg"))) {
			   request.setAttribute("error_message", "not_jpeg");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
				request.setAttribute("button", "物件登録へ");
			   return "/common/input_error.jsp";
			}

			String fileName = filePart.getSubmittedFileName();
			String[] var = { name, priceStr, info, layout, pet, city, address, fileName};


			List<String> list = new ArrayList<>(Arrays.asList(var));
			for (String str : list) {
				if(StringUtils.isBlank(str)) {
					request.setAttribute("error_message", "empty");
					request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
					request.setAttribute("button", "物件登録へ");
					return "/common/input_error.jsp";
				}
			}
			int price = Integer.parseInt(priceStr);
			
			// 入力が小さいときのバリデーション
			if(name.length() <= 0 || price < 0 || info.length() <= 0 || pet.length() <= 0 || city.length() <= 0 || address.length() <= 0) {
				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
				return "/common/input_error.jsp";
			}
			
			// 入力が大きいときのバリデーション
			if(name.length() > 50 || price > 10000000 || info.length() > 500 || pet.length() > 10 || city.length() > 10 ||
					address.length() > 50 || layout.length() > 10 || fileName.length() > 100) {
				request.setAttribute("error_message", "wrong");
				request.setAttribute("forward_page", "/rental_room/manager/property_register.jsp");
				request.setAttribute("button", "物件登録へ");
				return "/common/input_error.jsp";
			}

			request.setAttribute("fileName", fileName);
			
			// 	画像ファイル名に現在時刻(ミリ秒単位)とアンダーバーを追加して名前の衝突を防ぐ
			fileName = System.currentTimeMillis() + "_" + fileName;

			String savePath = request.getServletContext().getRealPath("/images/") + fileName;
			filePart.write(savePath);


			Property property = new Property();
			property.setName(name);
			property.setPrice(price);
			property.setInfo(info);
			property.setLayout(layout);
			property.setPet(pet);
			property.setImageName(fileName);
			property.setCity(city);
			property.setAddress(address);
			
			request.setAttribute("property", property);
			return "/manager/property_register_confirm.jsp";

		} catch (Exception e) {
			e.printStackTrace();
			return "/common/system_error.jsp";
		}
	}
}

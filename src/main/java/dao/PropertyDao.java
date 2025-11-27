package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.Property;
import tool.Dao;

public class PropertyDao extends Dao{
	
	// 物件再開停止
	public boolean republishProperty(int id) throws Exception {
		String sql = "update properties set status='空室' where property_id=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);

			int line = st.executeUpdate();
			return line > 0 ? true : false;
		}
	}

	// 物件掲載停止
	public boolean stopProperty(int id) throws Exception {
		String sql = "update properties set status='掲載停止' where property_id=? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);

			int line = st.executeUpdate();
			return line > 0 ? true : false;
		}
	}

	// 物件登録
	public boolean insertProperty(Property property) throws Exception {
		String sql = "insert into properties (name, price, layout, pet, info, image_name, city, address) values (?,?,?,?,?,?,?,?) ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, property.getName());
			st.setInt(2, property.getPrice());
			st.setString(3, property.getLayout());
			st.setString(4, property.getPet());
			st.setString(5, property.getInfo());
			st.setString(6, property.getImageName());
			st.setString(7, property.getCity());
			st.setString(8, property.getAddress());

			int line = st.executeUpdate();

			return line > 0 ? true : false;
		}
	}
	
	// 物件詳細表示
	public Property getPropertyInfo(int id) throws Exception {
		String sql = "select * from properties where property_id = ? ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Property property = new Property();
					property.setId(rs.getInt("property_id"));
					property.setName(rs.getString("name"));
					property.setPrice(rs.getInt("price"));
					property.setLayout(rs.getString("layout"));
					property.setPet(rs.getString("pet"));
					property.setStatus(rs.getString("status"));
					property.setInfo(rs.getString("info"));
					property.setImageName(rs.getString("image_name"));
					property.setCity(rs.getString("city"));
					property.setAddress(rs.getString("address"));
					return property;
				}
				return null;
			}
		}
	}
	
	
	// 物件検索（物件が空室のみ取得）
	public List<Property> searchPropertiesVacant(int priceNum, String[] layouts, int cityNum) throws Exception {
		// 物件検索の基本SQL文
		String sql = "select * from properties where 1=1 and status='空室' ";

		// 価格の範囲を指定する追加文
		String priceRange = "";
		switch (priceNum) {
		case 0 -> priceRange = "";
		case 1 -> priceRange = " and price between 0 and 50000 ";
		case 2 -> priceRange = " and price between 50001 and 80000 ";
		case 3 -> priceRange = " and price between 80001 and 100000 ";
		case 4 -> priceRange = " and price >= 100001 ";
		}

		// 間取りの範囲を指定する追加文
		String layoutRange = "";
		if (layouts != null && layouts.length > 0) {
			List<String> list = new ArrayList<>(Arrays.asList(layouts));

			boolean hasother = list.remove("4R以上");

			if (!list.isEmpty() && hasother) {
				String joined = String.join("','", list);
				layoutRange += " AND (layout IN ('" + joined + "') " + " OR layout NOT IN ('1R','1K','1DK','1LDK',"
						+ "'2R','2K','2DK','2LDK'," + "'3R','3K','3DK','3LDK'))";
			} else if (!list.isEmpty()) {
				String joined = String.join("','", list);
				layoutRange += " AND layout IN ('" + joined + "')";
			} else if (hasother) {
				layoutRange += " AND layout NOT IN ('1R','1K','1DK','1LDK'," + "'2R','2K','2DK','2LDK',"
						+ "'3R','3K','3DK','3LDK')";
			}
		}

		if (layouts == null || layouts.length <= 0) {
			layoutRange = "";
		}

		// 地区の範囲を指定する追加文
		String cityRange = "";
		switch (cityNum) {
		case 0 -> cityRange = "";
		case 1 -> cityRange = " and city = '中央区' ";
		case 2 -> cityRange = " and city = '花見川区' ";
		case 3 -> cityRange = " and city = '稲毛区' ";
		case 4 -> cityRange = " and city = '若葉区' ";
		case 5 -> cityRange = " and city = '緑区' ";
		case 6 -> cityRange = " and city = '美浜区' ";
		}

		// 価格で昇順に並べ替え
		String order = " order by price ";
		// 価格、間取り、地区をAND条件にて検索するSQL文
		sql = sql + priceRange + layoutRange + cityRange + order;

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			List<Property> properties = new ArrayList<>();
			while (rs.next()) {
				Property property = new Property();
				property.setId(rs.getInt("property_id"));
				property.setName(rs.getString("name"));
				property.setPrice(rs.getInt("price"));
				property.setLayout(rs.getString("layout"));
				property.setPet(rs.getString("pet"));
				property.setStatus(rs.getString("status"));
				property.setInfo(rs.getString("info"));
				property.setImageName(rs.getString("image_name"));
				property.setCity(rs.getString("city"));
				property.setAddress(rs.getString("address"));
				properties.add(property);
			}
			return properties;
		}
	}
	// 物件検索（管理者用）
	public List<Property> searchPropertiesForManager(int priceNum, String[] layouts, int cityNum) throws Exception {
		// 物件検索の基本SQL文
		String sql = "select * from properties where 1=1 ";

		// 価格の範囲を指定する追加文
		String priceRange = "";
		switch (priceNum) {
		case 0 -> priceRange = "";
		case 1 -> priceRange = " and price between 0 and 50000 ";
		case 2 -> priceRange = " and price between 50001 and 80000 ";
		case 3 -> priceRange = " and price between 80001 and 100000 ";
		case 4 -> priceRange = " and price >= 100001 ";
		}

		// 間取りの範囲を指定する追加文
		String layoutRange = "";
		if (layouts != null && layouts.length > 0) {
			List<String> list = new ArrayList<>(Arrays.asList(layouts));

			boolean hasother = list.remove("4R以上");

			if (!list.isEmpty() && hasother) {
				String joined = String.join("','", list);
				layoutRange += " AND (layout IN ('" + joined + "') " + " OR layout NOT IN ('1R','1K','1DK','1LDK',"
						+ "'2R','2K','2DK','2LDK'," + "'3R','3K','3DK','3LDK'))";
			} else if (!list.isEmpty()) {
				String joined = String.join("','", list);
				layoutRange += " AND layout IN ('" + joined + "')";
			} else if (hasother) {
				layoutRange += " AND layout NOT IN ('1R','1K','1DK','1LDK'," + "'2R','2K','2DK','2LDK',"
						+ "'3R','3K','3DK','3LDK')";
			}
		}

		if (layouts == null || layouts.length <= 0) {
			layoutRange = "";
		}

		// 地区の範囲を指定する追加文
		String cityRange = "";
		switch (cityNum) {
		case 0 -> cityRange = "";
		case 1 -> cityRange = " and city = '中央区' ";
		case 2 -> cityRange = " and city = '花見川区' ";
		case 3 -> cityRange = " and city = '稲毛区' ";
		case 4 -> cityRange = " and city = '若葉区' ";
		case 5 -> cityRange = " and city = '緑区' ";
		case 6 -> cityRange = " and city = '美浜区' ";
		}

		// 価格で昇順に並べ替え
		String order = " order by price ";
		// 価格、間取り、地区をAND条件にて検索するSQL文
		sql = sql + priceRange + layoutRange + cityRange + order;

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			List<Property> properties = new ArrayList<>();
			while (rs.next()) {
				Property property = new Property();
				property.setId(rs.getInt("property_id"));
				property.setName(rs.getString("name"));
				property.setPrice(rs.getInt("price"));
				property.setLayout(rs.getString("layout"));
				property.setPet(rs.getString("pet"));
				property.setStatus(rs.getString("status"));
				property.setInfo(rs.getString("info"));
				property.setImageName(rs.getString("image_name"));
				property.setCity(rs.getString("city"));
				property.setAddress(rs.getString("address"));
				properties.add(property);
			}
			return properties;
		}
	}
}

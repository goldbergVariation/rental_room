package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Review;
import tool.Dao;

public class ReviewDao extends Dao{
	
	public List<Review> getReviews(int propertyId) throws Exception {
		String sql = "select * from reviews where property_id = ? order by created_at desc ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, propertyId);

			System.out.println("review dao内");
			try (ResultSet rs = st.executeQuery()) {
				List<Review> reviews = new ArrayList<Review>();
				while (rs.next()) {
					Review review = new Review();
					review.setId(rs.getInt("review_id"));
					review.setComment(rs.getString("comment"));
					review.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					review.setUserId(rs.getInt("user_id"));
					review.setPropertyId(rs.getInt("property_id"));
					reviews.add(review);
				}
				return reviews;
			}
		}
	}
	
}

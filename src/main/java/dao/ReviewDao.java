package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Review;
import tool.Dao;

public class ReviewDao extends Dao {

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

	public int insertReview(Review review, int userId, int propertyId) throws Exception {
		String sql = "insert into reviews(comment,user_id,property_id)values(?,?,?) ";
		int line = 0;

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {

			//どこまでIDが来ているか、コンソールに表示
	        System.out.println("ReviewDao");
			System.out.println("review=" + review);
			st.setString(1, review.getComment());
			System.out.println("userId=" + userId);
			st.setInt(2,userId);
			System.out.println("propertyId=" + propertyId);
			st.setInt(3,propertyId);
			
			line = st.executeUpdate();

			return line;

		}

	}

}

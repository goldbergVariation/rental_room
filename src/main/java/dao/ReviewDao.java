package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Review;
import tool.Dao;

public class ReviewDao extends Dao {

	// ユーザーが口コミ一覧を取得
	public List<Review> getReviewsByUserId(int userId) throws Exception {
		String sql = "select r.comment, r.created_at, r.property_id, r.user_id ,p.name as property_name "
					+ "from reviews r "
					+ "join properties p on r.property_id = p.property_id "
					+ "where r.user_id = ? order by r.created_at desc ";
		try(Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql); ){
			st.setInt(1, userId);
			
			try(ResultSet rs = st.executeQuery()){
				List<Review> reviews = new ArrayList<Review>();
				while(rs.next()) {
					Review review = new Review();
					review.setComment(rs.getString("comment"));
					review.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					review.setUserId(rs.getInt("user_id"));
					review.setPropertyId(rs.getInt("property_id"));	
					review.setPropertyName(rs.getString("property_name"));
					reviews.add(review);
				}
				return reviews;
			}
		}
	}

	// 物件コードにて口コミ一覧を取得
	public List<Review> getReviews(int propertyId) throws Exception {
		String sql = "select * from reviews where property_id = ? order by created_at desc ";

		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, propertyId);

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

			st.setString(1, review.getComment());
			st.setInt(2,userId);
			st.setInt(3,propertyId);
			
			line = st.executeUpdate();

			return line;
		}
	}

}

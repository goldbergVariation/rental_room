package service;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

import dao.PropertyDao;
import dao.ReviewDao;
import tool.Dao;

public class PropertyService extends Dao {

	public void deleteProperty(int propertyId) throws SQLException, NamingException {
		try (Connection con = getConnection()) {
			boolean originalAutoCommit = con.getAutoCommit();

			try {
				con.setAutoCommit(false);

				System.out.println("in PropertyService " + propertyId);
				new ReviewDao().deleteReviewByPropertyId(con, propertyId);

				int deleted = new PropertyDao().deleteProperty(con, propertyId);
				if (deleted == 0) {
					throw new SQLException("物件が存在しないため削除できませんでした。propertyId=" + propertyId);
				}
				con.commit();

			} catch (SQLException | NamingException e) {
				try {
					con.rollback();
				} catch (SQLException ignore) {
					throw e;
				}

			} finally {
				try {
					con.setAutoCommit(originalAutoCommit);
				} catch (SQLException e) {
				}
			}
		}
	}
}
package app.dao.billing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.model.BillingInfo;

public class SqlBillingDao implements BillingDao {
	
	private Connection connection;
	
	public SqlBillingDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(long userId, BillingInfo billingInfo) {
		String sql = "INSERT INTO billing_info (user_id, billing_address, credit_card_type, credit_card_number, "
				+ "exp_month, exp_year) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, userId);
			statement.setString(2, billingInfo.getBillingAddress());
			statement.setString(3, billingInfo.getCreditCardType().name());
			statement.setString(4, billingInfo.getCreditCardNumber());
			statement.setString(5, billingInfo.getExpirationMonth());
			statement.setInt(6, billingInfo.getExpirationYear());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

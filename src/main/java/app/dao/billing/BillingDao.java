package app.dao.billing;

import app.model.BillingInfo;

public interface BillingDao {
	
	public void insert(long userId, BillingInfo paymentInfo);

}

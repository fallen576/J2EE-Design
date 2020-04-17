package app.model;

public class BillingInfo {

	private String billingAddress;
	private CreditCardType creditCardType;
	private String creditCardNumber;
	private String expirationMonth;
	private int expirationYear;
	
	public BillingInfo() {}

	public BillingInfo(String billingAddress, CreditCardType creditCardType, String creditCardNumber,
			String expirationMonth, int expirationYear) {
		this.billingAddress = billingAddress;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

}

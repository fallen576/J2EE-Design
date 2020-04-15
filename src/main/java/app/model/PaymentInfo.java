package app.model;

public class PaymentInfo {

	private CreditCardType creditCardType;
	private String creditCardNumber;
	private String expirationMonth;
	private int expirationYear;

	public PaymentInfo(CreditCardType creditCardType, String creditCardNumber, String expirationMonth,
			int expirationYear) {
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

}

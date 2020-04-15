package app.model;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String billingAddress;
	private String emailAddress;
	private PaymentInfo paymentInfo;

	public User() {
	}

	public User(String firstName, String lastName, String billingAddress, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.billingAddress = billingAddress;
		this.emailAddress = emailAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}

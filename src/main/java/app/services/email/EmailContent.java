package app.services.email;

import java.text.DecimalFormat;

import app.html.Element;
import app.model.Reservation;
import app.model.User;

public class EmailContent {
	
	private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("$#,###.00");

	public static String generate(User user, Reservation reservation) {
		StringBuilder sb = new StringBuilder(Element.createHead());
		StringBuilder registrationBuilder = new StringBuilder();

		registrationBuilder.append(Element.create("h1", "Deals on Wheels Rental Car Confirmation"));
		registrationBuilder.append(Element.create("div", user.getFirstName().concat(" ").concat(user.getLastName()), "bold"));
		registrationBuilder.append(Element.create("div", "An e-mail confirmation has been sent to: " +
			Element.createLink("mailto:" + user.getEmailAddress(), user.getEmailAddress())
		));
		registrationBuilder.append(Element.create("div",
			"If you do not receive the e-mail confirmation or if you need to update your reservation "
			+ "information, please contact Deals on Wheels at "
			+ Element.createLink("mailto:dealsonwheels@gmail.com", "dealsonwheels@gmail.com") + "."
		));
		
		sb.append(Element.create("div", registrationBuilder.toString(), "reservation"));
		
		return sb.toString();
	}
	
}

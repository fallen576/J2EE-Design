package app.services.email;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import app.html.Element;
import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;

public class EmailContent {
	
	private static final DecimalFormat CURRENCY_FORMATTER = new DecimalFormat("$#,###.00");
	private static final DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd MMM yyyy hh:mm");

	public static String generate(User user, Reservation reservation) {
		StringBuilder sb = new StringBuilder(Element.createHead());
		StringBuilder reservationBuilder = new StringBuilder();

		reservationBuilder.append(
				Element.create("div",
						Element.create("h1", "Deals on Wheels") +
						Element.create("span",
								Element.create("div", "Confirmation Number", "title") +
								Element.create("div", "#" + reservation.getConfirmationNumber(), "number"),
								"confirmation-number"
						),
						"header-info"
				)
		);
		
		Vehicle vehicle = reservation.getVehicle();
		reservationBuilder.append(Element.create("div",
				Element.create("h3", "Reservation Details") +
				Element.create("div",
						Element.create("div",
								Element.create("span", "Location: ") +
								Element.create("span", reservation.getLocation())
						) +
						Element.create("div",
								Element.create("span", "Pickup Date: ") +
								Element.create("span", DATE_TIME_FORMATTER.format(reservation.getPickupDate()))
						) +
						Element.create("div",
								Element.create("span", "Dropoff Date: ") +
								Element.create("span", DATE_TIME_FORMATTER.format(reservation.getDropoffDate()))
						) +
						Element.create("div",
								Element.create("span", "Total Cost: ") +
								Element.create("span", getTotalCost(reservation))
						)
				)
		));
		
		reservationBuilder.append(Element.create("div",
				Element.create("h3", "Vehicle Details") +
				Element.create("div", 
						Element.create("div",
								Element.create("span", "Make: ") +
								Element.create("span", vehicle.getMake())
						) +
						Element.create("div",
								Element.create("span", "Model: ") +
								Element.create("span", vehicle.getModel())
						) +
						Element.create("div",
								Element.create("span", "Color: ") +
								Element.create("span", vehicle.getColor())
						) +
						Element.create("div",
								Element.create("span", "Category: ") +
								Element.create("span", vehicle.getCategory().name())
						)
				)
		));
		
		reservationBuilder.append(Element.createNewLine());
		reservationBuilder.append(Element.create("div", "Hello " + user.getFirstName().concat(" ").concat(user.getLastName() + ",")));
		reservationBuilder.append(Element.createNewLine());
		reservationBuilder.append(Element.create("div", "Thank you for confirming your registration with Deals On Wheels"));
		reservationBuilder.append(Element.create("div", "You can always modify your reservation prior to the pickup date by logging in to your account. However, once your pickup date has passed, there is no way to modify your reservation."));
		reservationBuilder.append(Element.createNewLine());
		reservationBuilder.append(Element.create("div", "We appreciate your business and hope to provide you high quality experience and great deals!"));
		
		sb.append(Element.create("div", reservationBuilder.toString(), "confirmation-email"));
		return sb.toString();
	}
	
	private static String getTotalCost(Reservation reservation) {
		double costPerDay = reservation.getVehicle().getCostPerDay();
		Date pickupDate = reservation.getPickupDate();
		Date dropoffDate = reservation.getDropoffDate();
		
		long totalHours = ChronoUnit.HOURS.between(pickupDate.toInstant(), dropoffDate.toInstant());
		double totalDays = Math.ceil(totalHours / 24);
		return CURRENCY_FORMATTER.format(totalDays * costPerDay);
	}
	
}

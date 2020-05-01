package app.services.email;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import app.html.Element;
import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;

public class EmailContent {
	
	private static final DecimalFormat CURRENCY_FORMATTER = new DecimalFormat("$#,###.00");
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

	public static String generate(User user, Reservation reservation) {
		StringBuilder sb = new StringBuilder(Element.createHead());
		StringBuilder reservationBuilder = new StringBuilder();

		reservationBuilder.append(
				Element.create("div",
						Element.create("h1", "Deals on Wheels") +
						Element.create("span",
								Element.create("span", "Confirmation Number:") +
								Element.create("span", reservation.getConfirmationNumber()),
								"confirmation-number"
						)
				)
		);
		
		Vehicle vehicle = reservation.getVehicle();
		reservationBuilder.append(Element.create(
				Element.create("div", "Reservation Details") +
				Element.create("div",
						Element.createImg(vehicle.getBase64Img(), vehicle.getMake() + " " + vehicle.getModel()) +
						Element.create("div",
								Element.create("span", "Pickup Location", "bold table-cell") +
								Element.create("span", reservation.getPickupLocation(), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Dropoff Location", "bold table-cell") +
								Element.create("span", reservation.getDropoffLocation(), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Pickup Date", "bold table-cell") +
								Element.create("span", DATE_TIME_FORMATTER.format(reservation.getPickupDate().toInstant()), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Dropoff Date", "bold table-cell") +
								Element.create("span", DATE_TIME_FORMATTER.format(reservation.getDropoffDate().toInstant()), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Total Cost", "bold table-cell") +
								Element.create("span", getTotalCost(reservation), "bold table-cell"),
								"table-row"),
						"table"
				),
				"reservation-details"
		));
		
		reservationBuilder.append(Element.create(
				Element.create("div", "Vehicle Details") +
				Element.create("div", 
						Element.create("div",
								Element.create("span", "Make", "bold table-cell") +
								Element.create("span", vehicle.getMake(), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Model", "bold table-cell") +
								Element.create("span", vehicle.getModel(), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Color", "bold table-cell") +
								Element.create("span", vehicle.getColor(), "table-cell"),
								"table-row") +
						Element.create("div",
								Element.create("span", "Category", "bold table-cell") +
								Element.create("span", vehicle.getCategory().name(), "table-cell"),
								"table-row"),
						"table"
				),
				"vehicle-details"
		));
		
		reservationBuilder.append(Element.create("div", "Hello " + user.getFirstName().concat(" ").concat(user.getLastName())));
		reservationBuilder.append(Element.create("div", "Thank you for confirming your registration with Deals On Wheels"));
		reservationBuilder.append(Element.create("div", "You can always modify your reservation prior to the pickup date by logging in to your account. However, once your pickup date has passed, there is no way to modify your reservation."));
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

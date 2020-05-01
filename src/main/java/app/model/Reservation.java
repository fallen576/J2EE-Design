package app.model;

import java.util.Date;

public class Reservation {

	private long reservationId;
	private String confirmationNumber;
	private String pickupLocation;
	private String dropoffLocation;
	private Date pickupDate;
	private Date dropoffDate;
	private boolean paid;
	private Vehicle vehicle;

	public Reservation() {
	}

	public Reservation(String pickupLocation, String dropoffLocation, Date pickupDate, Date dropoffDate) {
		this.pickupLocation = pickupLocation;
		this.dropoffLocation = dropoffLocation;
		this.pickupDate = pickupDate;
		this.dropoffDate = dropoffDate;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDropoffLocation() {
		return dropoffLocation;
	}

	public void setDropoffLocation(String dropoffLocation) {
		this.dropoffLocation = dropoffLocation;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getDropoffDate() {
		return dropoffDate;
	}

	public void setDropoffDate(Date dropoffDate) {
		this.dropoffDate = dropoffDate;
	}

	public boolean hasPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}

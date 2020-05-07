package app.model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {

	private static final long serialVersionUID = 178878282290705807L;
	
	private long reservationId;
	private String confirmationNumber;
	private String location;
	private Date pickupDate;
	private Date dropoffDate;
	private boolean paid;
	private Vehicle vehicle;

	public Reservation() {
	}

	public Reservation(String location, Date pickupDate, Date dropoffDate) {
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

package app.services.reservation;

import java.util.List;
import java.util.stream.Collectors;

import app.dao.reservation.ReservationDao;
import app.dao.user.reservation.UserReservationDao;
import app.dao.vehicle.VehicleDao;
import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;
import app.model.VehicleType;
import app.services.email.DefaultEmailService;
import app.services.email.EmailService;
import app.utils.ConfirmationNumberGenerator;

public class DefaultReservationService implements ReservationService {
	
	private ReservationDao reservationDao;
	private UserReservationDao userReservationDao;
	private VehicleDao vehicleDao;
	private EmailService emailService;

	public DefaultReservationService(ReservationDao reservationDao, UserReservationDao userReservationDao, VehicleDao vehicleDao) {
		this.reservationDao = reservationDao;
		this.userReservationDao = userReservationDao;
		this.vehicleDao = vehicleDao;
		this.emailService = new DefaultEmailService();
	}
	
	public void confirmReservation(User user, Reservation reservation, VehicleType vehicleType) {
		if (!reservation.getDropoffDate().after(reservation.getPickupDate())) {
			throw new RuntimeException("Cannot have the pickup date after the dropoff date");
		}

		boolean hasReservation = this.hasReservation(user, reservation);
		if (!hasReservation) {
			throw new RuntimeException("User has a reservation in the requested date range");
		}
		
		Vehicle availableVehicle = this.findAvailableVehicle(reservation, vehicleType);
		if (availableVehicle == null) {
			throw new RuntimeException("No " + vehicleType.name() + " vehicles available during the reservation period");
		}
		
		String confirmationNumber = ConfirmationNumberGenerator.generate();
		List<String> existingConfirmationNumbers = reservationDao.getConfirmationNumbers();
		while (existingConfirmationNumbers.contains(confirmationNumber)) {
			confirmationNumber = ConfirmationNumberGenerator.generate();
		}
		reservation.setConfirmationNumber(confirmationNumber);
		reservation.setVehicleId(availableVehicle.getId());
		
		long reservationId = reservationDao.insert(reservation);
		userReservationDao.insert(user.getId(), reservationId);
		emailService.send(user, reservation);
	}
	
	private Vehicle findAvailableVehicle(Reservation reservation, VehicleType vehicleType) {
		List<Vehicle> vehicles = vehicleDao.findByType(vehicleType);
		List<Long> vehicleIds = vehicles.stream().map(v -> v.getId()).collect(Collectors.toList());
		List<Reservation> conflictingVehicleReservations = reservationDao.findByVehicleIds(vehicleIds).stream()
				.filter(vr -> vr.getDropoffDate().after(reservation.getPickupDate()))
				.collect(Collectors.toList());
		return vehicles.stream()
				.filter(vehicle -> !conflictingVehicleReservations.stream().anyMatch(vr -> vr.getVehicleId() == vehicle.getId()))
				.findFirst().orElse(null);
	}
	
	private boolean hasReservation(User user, Reservation reservation) {
		List<Reservation> userReservations = reservationDao.findByUserId(user.getId());
		return userReservations.stream().anyMatch(r -> r.getDropoffDate().after(reservation.getPickupDate()));		
	}
	
}

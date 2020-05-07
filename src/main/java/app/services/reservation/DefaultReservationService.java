package app.services.reservation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import app.dao.reservation.ReservationDao;
import app.dao.user.reservation.UserReservationDao;
import app.dao.vehicle.VehicleDao;
import app.model.Reservation;
import app.model.User;
import app.model.Vehicle;
import app.model.VehicleCategory;
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
	
	@Override
	public Reservation confirmReservation(User user, Reservation reservation, Vehicle possibleVehicle, VehicleCategory category) {
		if (!reservation.getDropoffDate().after(reservation.getPickupDate())) {
			throw new RuntimeException("Cannot have the pickup date after the dropoff date");
		}

		boolean hasReservation = this.hasReservation(user, reservation);
		if (hasReservation) {
			throw new RuntimeException("User has a reservation in the requested date range");
		}
		
		Vehicle availableVehicle = new Vehicle(possibleVehicle);
		if (this.vehicleHasConflictingReservation(reservation, availableVehicle)) {
			availableVehicle = this.findAvailableVehicle(reservation, category);
		}
		
		if (availableVehicle == null) {
			throw new RuntimeException("No " + category.name() + " vehicles available during the reservation period");
		}
		
		String confirmationNumber = ConfirmationNumberGenerator.generate();
		List<String> existingConfirmationNumbers = reservationDao.getConfirmationNumbers();
		while (existingConfirmationNumbers.contains(confirmationNumber)) {
			confirmationNumber = ConfirmationNumberGenerator.generate();
		}
		reservation.setConfirmationNumber(confirmationNumber);
		reservation.setVehicle(availableVehicle);
		
		long reservationId = reservationDao.insert(reservation);
		reservation.setReservationId(reservationId);
		userReservationDao.insert(user.getId(), reservationId);
		
		emailService.send(user, reservation);
		
		return reservation;
	}

	@Override
	public List<Reservation> findUserReservations(User user) {
		return reservationDao.findByUserId(user.getId());
	}
	
	@Override
	public void update(Reservation reservation) {
		reservationDao.update(reservation);
	}
	
	@Override
	public void cancel(Long reservationId) {
		reservationDao.cancel(reservationId);
	}
	
	private Vehicle findAvailableVehicle(Reservation reservation, VehicleCategory vehicleType) {
		List<Vehicle> vehicles = vehicleDao.findByType(vehicleType);
		List<Long> vehicleIds = vehicles.stream().map(v -> v.getId()).collect(Collectors.toList());
		List<Reservation> conflictingVehicleReservations = reservationDao.findByVehicleIds(vehicleIds).stream()
				.filter(vr -> vr.getDropoffDate().after(reservation.getPickupDate()) || reservation.getDropoffDate().after(vr.getPickupDate()))
				.collect(Collectors.toList());

		return vehicles.stream()
				.filter(vehicle -> !conflictingVehicleReservations.stream().anyMatch(vr -> vr.getVehicle().getId() == vehicle.getId()))
				.findFirst().orElse(null);
	}
	
	private boolean hasReservation(User user, Reservation reservation) {
		List<Reservation> userReservations = reservationDao.findByUserId(user.getId());
		return userReservations.stream().anyMatch(r -> r.getDropoffDate().after(reservation.getPickupDate()));		
	}
	
	private boolean vehicleHasConflictingReservation(Reservation reservation, Vehicle vehicle) {
		List<Reservation> vehicleReservations = reservationDao.findByVehicleIds(Arrays.asList(vehicle.getId()));
		return vehicleReservations.stream().anyMatch(vr -> {
			return !vr.getDropoffDate().before(reservation.getPickupDate()) || reservation.getDropoffDate().after(vr.getPickupDate());
		});
	}
	
}

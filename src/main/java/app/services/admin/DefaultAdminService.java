package app.services.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import app.dao.reservation.ReservationDao;
import app.model.Reservation;
import app.model.Vehicle;
import app.services.vehicle.VehicleService;

public class DefaultAdminService implements AdminService {
	
	private VehicleService vehicleService;
	private ReservationDao reservationDao;

	public DefaultAdminService(VehicleService vehicleService, ReservationDao reservationDao) {
		this.vehicleService = vehicleService;
		this.reservationDao = reservationDao;
	}

	@Override
	public Vehicle insertVehicle(Vehicle vehicle) {
		return vehicleService.insert(vehicle);
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {
		if (!this.vehicleHasFutureReservation(vehicle.getId())) {
			vehicleService.update(vehicle);
		} else {
			throw new IllegalArgumentException("Cannot update a vehicle with a reservation in the future");
		}
	}

	@Override
	public void deleteVehicle(long vehicleId) {
		if (!this.vehicleHasFutureReservation(vehicleId)) {
			vehicleService.delete(vehicleId);
		} else {
			throw new IllegalArgumentException("Cannot delete a vehicle with a reservation in the future");
		}
	}
	
	private boolean vehicleHasFutureReservation(long vehicleId) {
		List<Reservation> reservations = reservationDao.findByVehicleIds(Arrays.asList(vehicleId));
		Date today = new Date();
		return reservations.stream().anyMatch(reservation -> reservation.getDropoffDate().after(today));
	}

}

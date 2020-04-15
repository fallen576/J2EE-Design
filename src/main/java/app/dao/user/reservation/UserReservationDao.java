package app.dao.user.reservation;

public interface UserReservationDao {

	void insert(long userId, long reservationId);
	
	void remove(long reservationId);
	
}

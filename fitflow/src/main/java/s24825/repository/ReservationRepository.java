package s24825.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24825.model.reservation.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
package s24825.repository;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import s24825.model.reservation.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[ReservationRepo] Check if a reservation exists for member and class") })
    boolean existsByMemberIdAndFitnessClassId(Long fitnessClassId, Long memberId);


}
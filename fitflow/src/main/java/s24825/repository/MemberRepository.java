package s24825.repository;


import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import s24825.model.person.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[MemberRepo] Find Member with all details for MyReservations page") })
    @Query("SELECT DISTINCT m FROM Member m " +
            "LEFT JOIN FETCH m.memberships " +
            "LEFT JOIN FETCH m.reservations r " +
            "LEFT JOIN FETCH r.fitnessClass fc " +
            "LEFT JOIN FETCH fc.trainer " +
            "LEFT JOIN FETCH fc.trainingRoom " +
            "WHERE m.id = :id")
    Optional<Member> findByIdWithDetails(@Param("id") Long id);

    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[MemberRepo] Find Member with Memberships for Reservation check") })
    @Query("SELECT DISTINCT m FROM Member m LEFT JOIN FETCH m.memberships WHERE m.id = :id")
    Optional<Member> findByIdWithMemberships(@Param("id") Long id);

    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[MemberRepo] Find Member by Email for Login") })
    Optional<Member> findByEmail(String email);
}

package s24825.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import s24825.model.person.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    @Query("SELECT DISTINCT m FROM Member m " +
            "LEFT JOIN FETCH m.memberships " +
            "LEFT JOIN FETCH m.reservations r " +
            "LEFT JOIN FETCH r.fitnessClass fc " +
            "LEFT JOIN FETCH fc.trainer " +
            "LEFT JOIN FETCH fc.trainingRoom " +
            "WHERE m.id = :id")
    Optional<Member> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT DISTINCT m FROM Member m LEFT JOIN FETCH m.memberships LEFT JOIN FETCH m.reservations WHERE m.id = :id")
    Optional<Member> findByIdWithMembershipsAndReservations(@Param("id") Long id);

    Optional<Member> findByEmail(String email);
}

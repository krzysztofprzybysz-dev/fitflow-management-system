package s24825.repository;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import s24825.model.classes.FitnessClass;

import java.util.List;
import java.util.Optional;

@Repository
public interface FitnessClassRepository extends JpaRepository<FitnessClass, Long> {

    @Query("SELECT fc FROM FitnessClass fc")
    @EntityGraph(value = "FitnessClass.withDetails")
    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[FitnessClassRepo] Find all classes with details (graph)") })
    List<FitnessClass> findAllWithDetails();

    // Nowa metoda do pobierania zajęć dla konkretnego trenera
    @Query("SELECT fc FROM FitnessClass fc WHERE fc.trainer.id = :trainerId ORDER BY fc.dateTime")
    @EntityGraph(value = "FitnessClass.withDetails")
    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[FitnessClassRepo] Find all classes for a specific trainer") })
    List<FitnessClass> findByTrainerIdWithDetails(@Param("trainerId") Long trainerId);

    // Nowa metoda do pobierania szczegółów jednych zajęć dla trenera (zabezpieczenie)
    @Query("SELECT fc FROM FitnessClass fc WHERE fc.id = :classId AND fc.trainer.id = :trainerId")
    @EntityGraph(attributePaths = {"reservations.member"}) // Dociągamy rezerwacje i ich członków
    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[FitnessClassRepo] Find specific class details for a trainer") })
    Optional<FitnessClass> findByIdAndTrainerIdWithParticipants(@Param("classId") Long classId, @Param("trainerId") Long trainerId);
}

package s24825.repository;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import s24825.model.classes.FitnessClass;

import java.util.List;

@Repository
public interface FitnessClassRepository extends JpaRepository<FitnessClass, Long> {

    @Query("SELECT fc FROM FitnessClass fc")
    @EntityGraph(value = "FitnessClass.withDetails")
    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[FitnessClassRepo] Find all classes with details (graph)") })
    List<FitnessClass> findAllWithDetails();

}

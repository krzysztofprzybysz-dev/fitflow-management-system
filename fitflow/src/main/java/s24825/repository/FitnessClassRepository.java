package s24825.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24825.model.classes.FitnessClass;

import java.util.List;

@Repository
public interface FitnessClassRepository extends JpaRepository<FitnessClass, Long> {

    @Override
    @EntityGraph(value = "FitnessClass.withReservations")
    List<FitnessClass> findAll();

}

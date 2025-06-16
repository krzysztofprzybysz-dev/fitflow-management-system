package s24825.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24825.model.person.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
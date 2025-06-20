package s24825.repository;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import s24825.model.person.Trainer;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @QueryHints({ @QueryHint(name = "org.hibernate.comment", value = "[TrainerRepo] Find trainer by email") })
    Optional<Trainer> findByEmail(String email);
}
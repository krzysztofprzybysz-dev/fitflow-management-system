package s24825.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24825.model.other.TrainingRoom;

@Repository
public interface TrainingRoomRepository extends JpaRepository<TrainingRoom, Long> {
}
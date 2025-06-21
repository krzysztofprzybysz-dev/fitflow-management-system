package s24825.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24825.model.other.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
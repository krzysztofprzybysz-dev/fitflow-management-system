package s24825.model.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a group fitness class, a specific type of FitnessClass.
 */
@Entity
@Table(name = "group_classes")
@Getter
@Setter
@NoArgsConstructor
public class GroupClass extends FitnessClass {

    private int participantLimit;

    @Override
    public int getCapacity() {
        return getParticipantLimit();
    }
}
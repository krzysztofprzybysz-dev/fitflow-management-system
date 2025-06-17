package s24825.model.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import s24825.model.reservation.Reservation;
import s24825.model.reservation.ReservationStatus;
import s24825.model.other.TrainingRoom;
import s24825.model.person.Trainer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "fitness_classes")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(
        name = "FitnessClass.withDetails",
        attributeNodes = {
                @NamedAttributeNode("reservations"),
                @NamedAttributeNode("trainer"),
                @NamedAttributeNode("trainingRoom")
        }
)
public abstract class FitnessClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime dateTime;
    private int durationInMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_room_id")
    private TrainingRoom trainingRoom;

    @OneToMany(mappedBy = "fitnessClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

    public abstract int getCapacity();

    public int getNumberOfReservations() {
        return (int) reservations.stream()
                .filter(r -> r.getStatus() == ReservationStatus.CONFIRMED)
                .count();
    }
}

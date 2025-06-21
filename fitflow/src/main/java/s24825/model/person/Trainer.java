package s24825.model.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a trainer at the fitness club.
 */
@Entity
@Table(name = "trainers")
@Getter
@Setter
@NoArgsConstructor
public class Trainer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String licenseNumber;

    @ElementCollection
    @CollectionTable(name = "trainer_specializations", joinColumns = @JoinColumn(name = "trainer_id"))
    @Column(name = "specialization", nullable = false)
    private List<String> specializations = new ArrayList<>();
}
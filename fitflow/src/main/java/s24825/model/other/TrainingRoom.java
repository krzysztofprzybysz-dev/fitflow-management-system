package s24825.model.other;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Entity
@Table(name = "training_rooms")
@Getter
@Setter
@NoArgsConstructor
public class TrainingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    private String name;
    private int capacity;

    @OneToMany(mappedBy = "trainingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "serialNumber")
    private Map<String, Equipment> equipment = new HashMap<>();
}

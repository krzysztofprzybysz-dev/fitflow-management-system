package s24825.service.auth;

import org.springframework.stereotype.Component;
import s24825.model.person.Person;
import s24825.repository.TrainerRepository;
import java.util.Optional;

@Component
public class TrainerFinder implements UserFinder {
    private final TrainerRepository trainerRepository;

    public TrainerFinder(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return trainerRepository.findByEmail(email).map(person -> person);
    }
}
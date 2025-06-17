package s24825.service;

import org.springframework.stereotype.Service;
import s24825.model.classes.FitnessClass;
import s24825.repository.FitnessClassRepository;
import java.util.List;

@Service
public class FitnessClassService {

    private final FitnessClassRepository fitnessClassRepository;

    public FitnessClassService(FitnessClassRepository fitnessClassRepository) {
        this.fitnessClassRepository = fitnessClassRepository;
    }

    public List<FitnessClass> getAllClassesWithDetails() {
        return fitnessClassRepository.findAllWithDetails();
    }
}

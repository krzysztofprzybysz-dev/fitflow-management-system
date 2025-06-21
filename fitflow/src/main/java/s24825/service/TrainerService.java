package s24825.service;

import org.springframework.stereotype.Service;
import s24825.exception.ResourceNotFoundException;
import s24825.model.classes.FitnessClass;
import s24825.repository.FitnessClassRepository;
import java.util.List;

@Service
public class TrainerService {

    private final FitnessClassRepository fitnessClassRepository;

    public TrainerService(FitnessClassRepository fitnessClassRepository) {
        this.fitnessClassRepository = fitnessClassRepository;
    }

    public List<FitnessClass> getClassesForTrainer(Long trainerId) {
        return fitnessClassRepository.findByTrainerIdWithDetails(trainerId);
    }

    public FitnessClass getClassDetailsForTrainer(Long classId, Long trainerId) {
        return fitnessClassRepository.findByIdAndTrainerIdWithParticipants(classId, trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono zajęć o ID: " + classId + " lub nie jesteś do nich przypisany."));
    }
}
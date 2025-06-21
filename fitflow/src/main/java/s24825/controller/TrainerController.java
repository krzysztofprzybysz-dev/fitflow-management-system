package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import s24825.model.classes.FitnessClass;
import s24825.service.SessionService;
import s24825.service.TrainerService;
import java.util.List;

@Controller
public class TrainerController {

    private final TrainerService trainerService;
    private final SessionService sessionService;

    public TrainerController(TrainerService trainerService, SessionService sessionService) {
        this.trainerService = trainerService;
        this.sessionService = sessionService;
    }

    @GetMapping("/my-trainer-classes")
    public String showTrainerClasses(Model model, HttpSession session) {
        // Zabezpieczenie: Sprawdzamy, czy użytkownik jest zalogowany
        Long trainerId = sessionService.getLoggedInUserId(session);
        List<FitnessClass> classes = trainerService.getClassesForTrainer(trainerId);
        model.addAttribute("classes", classes);
        return "trainer-classes";
    }

    @GetMapping("/my-trainer-classes/{id}")
    public String showClassDetails(@PathVariable("id") Long classId, Model model, HttpSession session) {
        // Zabezpieczenie: Sprawdzamy, czy użytkownik jest zalogowany
        Long trainerId = sessionService.getLoggedInUserId(session);
        FitnessClass fitnessClass = trainerService.getClassDetailsForTrainer(classId, trainerId);
        model.addAttribute("fitnessClass", fitnessClass);
        return "trainer-class-details";
    }
}
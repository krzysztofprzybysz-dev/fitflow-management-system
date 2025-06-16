package s24825.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s24825.model.classes.FitnessClass;
import s24825.repository.FitnessClassRepository;
import s24825.service.ReservationService;

import java.util.List;

/**
 * Controller for handling requests related to the class schedule and making reservations.
 */
@Controller
public class ClassScheduleController {

    public static final long HARDCODED_MEMBER_ID = 1L;

    private final FitnessClassRepository fitnessClassRepository;
    private final ReservationService reservationService;

    public ClassScheduleController(FitnessClassRepository fitnessClassRepository, ReservationService reservationService) {
        this.fitnessClassRepository = fitnessClassRepository;
        this.reservationService = reservationService;
    }


    @GetMapping({"/", "/class-schedule"})
    public String showClassSchedule(Model model) {

        List<FitnessClass> classes = fitnessClassRepository.findAll();

        model.addAttribute("classes", classes);
        model.addAttribute("memberId", HARDCODED_MEMBER_ID);

        return "class-schedule";
    }

    @PostMapping("/reservations")
    public String makeReservation(@RequestParam("classId") Long classId,
                                  @RequestParam("memberId") Long memberId,
                                  RedirectAttributes redirectAttributes) {
        try {
            reservationService.createReservation(memberId, classId);
            redirectAttributes.addFlashAttribute("successMessage", "Rezerwacja zakończona pomyślnie!");

        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd rezerwacji: " + e.getMessage());
        }

        return "redirect:/class-schedule";
    }
}

package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s24825.service.FitnessClassService;
import s24825.service.ReservationService;
import s24825.service.SessionService;

@Controller
public class ClassScheduleController {

    private final FitnessClassService fitnessClassService;
    private final ReservationService reservationService;
    private final SessionService sessionService;

    public ClassScheduleController(FitnessClassService fitnessClassService, ReservationService reservationService, SessionService sessionService) {
        this.fitnessClassService = fitnessClassService;
        this.reservationService = reservationService;
        this.sessionService = sessionService;
    }

    @GetMapping({"/", "/class-schedule"})
    public String showClassSchedule(Model model) {
        // Ta strona jest publiczna, więc nie sprawdzamy sesji
        model.addAttribute("classes", fitnessClassService.getAllClassesWithDetails());
        return "class-schedule";
    }

    @PostMapping("/reservations")
    public String makeReservation(@RequestParam("classId") Long classId, HttpSession session, RedirectAttributes redirectAttributes) {
        // Zabezpieczenie: tylko zalogowany użytkownik może dokonać rezerwacji
        Long memberId = sessionService.getLoggedInUserId(session);
        reservationService.createReservation(memberId, classId);
        redirectAttributes.addFlashAttribute("successMessage", "Rezerwacja zakończona pomyślnie!");
        return "redirect:/class-schedule";
    }
}
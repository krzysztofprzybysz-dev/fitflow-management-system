package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s24825.model.person.Member;
import s24825.model.reservation.Reservation;
import s24825.service.MemberService;
import s24825.service.ReservationService;
import s24825.service.SessionService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyReservationsController {

    private final MemberService memberService;
    private final SessionService sessionService;
    private final ReservationService reservationService;

    public MyReservationsController(MemberService memberService,
                                    SessionService sessionService,
                                    ReservationService reservationService) {
        this.memberService = memberService;
        this.sessionService = sessionService;
        this.reservationService = reservationService;
    }

    @GetMapping("/my-reservations")
    public String showMyReservations(Model model, HttpSession session) {

        Long memberId = sessionService.getLoggedInMemberId(session);
        Member member = memberService.getMemberWithDetails(memberId);

        List<Reservation> sortedReservations = member.getReservations().stream()
                .sorted(Comparator.comparing(r -> r.getFitnessClass().getDateTime()))
                .collect(Collectors.toList());

        model.addAttribute("member", member);

        model.addAttribute("reservations", sortedReservations);

        model.addAttribute("member", member);
        return "my-reservations";
    }

    @DeleteMapping("/my-reservations/remove/{id}")
    public String removeReservation (RedirectAttributes redirectAttributes,
                                     @PathVariable Long id) {

        reservationService.removeReservation(id);
        redirectAttributes.addFlashAttribute("successMessage", "Rezerwacja została pomyślnie usunięta!");
        return "redirect:/my-reservations";

    }
}

package s24825.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import s24825.model.person.Member;
import s24825.model.reservation.Reservation;
import s24825.repository.MemberRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for displaying a member's reservations.
 */
@Controller
public class MyReservationsController {

    private final MemberRepository memberRepository;

    public MyReservationsController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Displays the reservations for a hardcoded member.
     * @param model the UI model
     * @return the name of the Thymeleaf template
     */
    @GetMapping("/my-reservations")
    public String showMyReservations(Model model) {
        // Using hardcoded member ID for demonstration
        Member member = memberRepository.findById(ClassScheduleController.HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new IllegalStateException("Test member not found"));

        List<Reservation> sortedReservations = member.getReservations().stream()
                .sorted(Comparator.comparing(r -> r.getFitnessClass().getDateTime()))
                .collect(Collectors.toList());

        model.addAttribute("reservations", sortedReservations);
        model.addAttribute("member", member);
        return "my-reservations";
    }
}

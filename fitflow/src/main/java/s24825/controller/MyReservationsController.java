package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import s24825.model.person.Member;
import s24825.model.reservation.Reservation;
import s24825.service.MemberService;
import s24825.service.SessionService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyReservationsController {

    private final MemberService memberService;
    private final SessionService sessionService;

    public MyReservationsController(MemberService memberService, SessionService sessionService) {
        this.memberService = memberService;
        this.sessionService = sessionService;
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
}

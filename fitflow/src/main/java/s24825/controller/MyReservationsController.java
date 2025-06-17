package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import s24825.model.person.Member;
import s24825.model.reservation.Reservation;
import s24825.repository.MemberRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MyReservationsController {

    private final MemberRepository memberRepository;

    public MyReservationsController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @GetMapping("/my-reservations")
    public String showMyReservations(Model model, HttpSession session) {

        Long memberId = (Long) session.getAttribute("loggedInMemberId");

        if (memberId == null) {
            return "redirect:/login";
        }

        Member member = memberRepository.findByIdWithDetails(memberId)
                .orElseThrow(() -> new IllegalStateException("Zalogowany użytkownik nie został znaleziony w bazie danych."));

        List<Reservation> sortedReservations = member.getReservations().stream()
                .sorted(Comparator.comparing(r -> r.getFitnessClass().getDateTime()))
                .collect(Collectors.toList());

        model.addAttribute("reservations", sortedReservations);
        model.addAttribute("member", member);
        return "my-reservations";
    }
}

package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s24825.service.MemberService;
import s24825.service.SessionService;

@Controller
public class MembershipController {

    private final SessionService sessionService;
    private final MemberService memberService;

    public MembershipController(SessionService sessionService, MemberService memberService) {
        this.sessionService = sessionService;
        this.memberService = memberService;
    }

    @PostMapping("/my-memberships/upgrade-to-premium")
    public String upgradeToPremium(HttpSession session, RedirectAttributes redirectAttributes) {
        Long memberId = sessionService.getLoggedInUserId(session);
        memberService.upgradeMembershipToPremium(memberId);
        redirectAttributes.addFlashAttribute("successMessage", "Gratulacje! Twój karnet został ulepszony do wersji Premium!");
        return "redirect:/my-reservations"; // Przekieruj na stronę, gdzie widać zmiany
    }
}
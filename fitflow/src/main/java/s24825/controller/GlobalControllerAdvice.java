package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import s24825.model.membership.Membership;
import s24825.repository.MemberRepository;
import s24825.service.SessionService;

/**
 * This class provides global attributes to the model for all controllers.
 * It's used here to add membership details to the model for every request,
 * making them available in the navbar fragment.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private final SessionService sessionService;
    private final MemberRepository memberRepository;

    public GlobalControllerAdvice(SessionService sessionService, MemberRepository memberRepository) {
        this.sessionService = sessionService;
        this.memberRepository = memberRepository;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpSession session) {
        try {
            if ("MEMBER".equals(session.getAttribute("loggedInUserRole"))) {
                Long memberId = (Long) session.getAttribute("loggedInUserId");
                if (memberId != null) {
                    // Fetch the member and their active pass
                    memberRepository.findByIdWithMemberships(memberId).ifPresent(member -> {
                        member.getMemberships().stream()
                                .filter(Membership::isActive)
                                .findFirst()
                                .ifPresent(activePass -> {
                                    // Add details of the active pass to the model
                                    model.addAttribute("membershipTypeName", activePass.getTypeName());
                                    model.addAttribute("membershipEntriesUsed", activePass.getEntriesUsed());
                                });
                    });
                }
            }
        } catch (Exception e) {
            // If session is invalid or user is not a member, do nothing.
        }
    }
}
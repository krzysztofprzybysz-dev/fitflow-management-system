package s24825.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s24825.model.person.Member;
import s24825.repository.MemberRepository;

import java.util.Optional;

@Controller
public class LoginController {

    private final MemberRepository memberRepository;

    public LoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            if (password.equals(member.getPassword())) {
                session.setAttribute("loggedInMemberId", member.getId());
                session.setAttribute("loggedInMemberFirstName", member.getFirstName());
                return "redirect:/class-schedule";
            }

        }
        redirectAttributes.addFlashAttribute("error", "Nieprawidłowy e-mail lub hasło.");
        return "redirect:/login";
    }


}

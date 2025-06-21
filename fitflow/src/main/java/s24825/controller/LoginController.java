package s24825.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import s24825.model.person.Person;
import s24825.model.person.Trainer;
import s24825.service.LoginService;
import s24825.service.SessionService;

@Controller
public class LoginController {

    private final LoginService loginService;
    private final SessionService sessionService;

    public LoginController(LoginService loginService, SessionService sessionService) {
        this.loginService = loginService;
        this.sessionService = sessionService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Person person = loginService.login(email, password);
        sessionService.loginUser(session, person);

        if (person instanceof Trainer) {
            return "redirect:/my-trainer-classes";
        }

        return "redirect:/class-schedule";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        loginService.logout(session);
        return "redirect:/login";
    }
}
package s24825.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import s24825.exception.InvalidCredentialsException;
import s24825.model.person.Member;
import s24825.repository.MemberRepository;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Nieprawidłowy e-mail lub hasło."));

        if (password.equals(member.getPassword())) {
            return member;
        } else {
            throw new InvalidCredentialsException("Nieprawidłowy e-mail lub hasło.");
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
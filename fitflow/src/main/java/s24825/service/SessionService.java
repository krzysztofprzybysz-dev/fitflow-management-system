package s24825.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import s24825.exception.SessionExpiredException;
import s24825.model.person.Member;

@Service
public class SessionService {

    private static final String MEMBER_ID_ATTRIBUTE = "loggedInMemberId";
    private static final String MEMBER_NAME_ATTRIBUTE = "loggedInMemberFirstName";

    public Long getLoggedInMemberId(HttpSession session) {
        Long memberId = (Long) session.getAttribute(MEMBER_ID_ATTRIBUTE);
        if (memberId == null) {
            throw new SessionExpiredException("Sesja wygasła. Proszę się zalogować ponownie.");
        }
        return memberId;
    }

    public void loginUser(HttpSession session, Member member) {
        session.setAttribute(MEMBER_ID_ATTRIBUTE, member.getId());
        session.setAttribute(MEMBER_NAME_ATTRIBUTE, member.getFirstName());
    }
}
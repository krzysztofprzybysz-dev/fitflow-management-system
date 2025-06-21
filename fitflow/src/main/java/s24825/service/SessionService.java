package s24825.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import s24825.exception.SessionExpiredException;
import s24825.model.person.Member;
import s24825.model.person.Person;
import s24825.model.person.Trainer;

@Service
public class SessionService {

    private static final String USER_ID_ATTRIBUTE = "loggedInUserId";
    private static final String USER_NAME_ATTRIBUTE = "loggedInUserFirstName";
    private static final String USER_ROLE_ATTRIBUTE = "loggedInUserRole";

    public Long getLoggedInUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
        if (userId == null) {
            throw new SessionExpiredException("Sesja wygasła. Proszę się zalogować ponownie.");
        }
        return userId;
    }

    public String getLoggedInUserRole(HttpSession session) {
        return (String) session.getAttribute(USER_ROLE_ATTRIBUTE);
    }

    public void loginUser(HttpSession session, Person person) {
        session.setAttribute(USER_ID_ATTRIBUTE, person.getId());
        session.setAttribute(USER_NAME_ATTRIBUTE, person.getFirstName());

        if (person instanceof Member) {
            session.setAttribute(USER_ROLE_ATTRIBUTE, "MEMBER");
        } else if (person instanceof Trainer) {
            session.setAttribute(USER_ROLE_ATTRIBUTE, "TRAINER");
        }
    }
}
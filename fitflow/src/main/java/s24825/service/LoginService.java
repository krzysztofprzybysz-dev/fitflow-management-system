package s24825.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import s24825.exception.InvalidCredentialsException;
import s24825.model.person.Person;
import s24825.service.auth.UserFinder;
import java.util.List;
import java.util.Optional;


@Service
public class LoginService {


    private final List<UserFinder> finders;

    public LoginService(List<UserFinder> finders) {
        this.finders = finders;
    }


    public Person login(String email, String password) {

        return finders.stream()
                .map(finder -> finder.findByEmail(email)) // Spróbuj znaleźć użytkownika używając każdego "findera"
                .filter(Optional::isPresent)             // Odsiej puste wyniki
                .map(Optional::get)                      // Wyciągnij użytkownika z Optional
                .findFirst()                             // Weź pierwszego znalezionego
                .filter(person -> person.getPassword().equals(password)) // Sprawdź jego hasło
                .orElseThrow(() -> new InvalidCredentialsException("Nieprawidłowy e-mail lub hasło.")); // Jeśli go nie ma lub hasło jest złe, rzuć wyjątek
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
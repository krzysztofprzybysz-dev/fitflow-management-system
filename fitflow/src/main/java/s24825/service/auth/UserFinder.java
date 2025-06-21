package s24825.service.auth;

import s24825.model.person.Person;
import java.util.Optional;

/**
 * Interfejs strategii do wyszukiwania różnych typów użytkowników.
 * Każda implementacja będzie odpowiedzialna za przeszukanie jednego repozytorium.
 */
public interface UserFinder {
    Optional<Person> findByEmail(String email);
}
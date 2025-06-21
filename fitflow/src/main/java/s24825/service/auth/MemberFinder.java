package s24825.service.auth;

import org.springframework.stereotype.Component;
import s24825.model.person.Person;
import s24825.repository.MemberRepository;
import java.util.Optional;

@Component
public class MemberFinder implements UserFinder {

    private final MemberRepository memberRepository;

    public MemberFinder(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return memberRepository.findByEmail(email).map(person -> person);
    }
}
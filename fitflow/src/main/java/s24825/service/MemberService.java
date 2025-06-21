package s24825.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s24825.exception.ResourceNotFoundException;
import s24825.exception.ReservationException;
import s24825.model.membership.Membership;
import s24825.model.person.Member;
import s24825.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberWithDetails(Long memberId) {
        return memberRepository.findByIdWithDetails(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono danych użytkownika."));
    }

    // NOWA METODA
    @Transactional
    public void upgradeMembershipToPremium(Long memberId) {
        Member member = memberRepository.findByIdWithMemberships(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono członka o ID: " + memberId));

        Membership activePass = member.getMemberships().stream()
                .filter(Membership::isActive)
                .findFirst()
                .orElseThrow(() -> new ReservationException("Brak aktywnego karnetu do ulepszenia."));

        // Wywołujemy metodę, którą zaimplementowaliśmy w ramach wzorca Stan
        activePass.upgradeToPremium();

        // Zapisujemy zmiany. Dzięki kaskadzie, zmiany w karnecie zostaną utrwalone.
        memberRepository.save(member);
    }
}
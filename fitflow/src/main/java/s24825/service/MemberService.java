package s24825.service;

import org.springframework.stereotype.Service;
import s24825.exception.ResourceNotFoundException;
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
}
package s24825.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s24825.exception.ReservationException;
import s24825.exception.ResourceNotFoundException;
import s24825.model.classes.FitnessClass;
import s24825.model.membership.Membership;
import s24825.model.person.Member;
import s24825.model.reservation.Reservation;
import s24825.repository.FitnessClassRepository;
import s24825.repository.MemberRepository;
import s24825.repository.ReservationRepository;

import java.util.Optional;

@Service
public class ReservationService {

    private final MemberRepository memberRepository;
    private final FitnessClassRepository fitnessClassRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(MemberRepository memberRepository,
                              FitnessClassRepository fitnessClassRepository,
                              ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.fitnessClassRepository = fitnessClassRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void createReservation(Long memberId, Long fitnessClassId) {

        if (reservationRepository.existsByMemberIdAndFitnessClassId(memberId, fitnessClassId)) {
            throw new ReservationException("Już posiadasz rezerwację na te zajęcia.");
        }

        Member member = memberRepository.findByIdWithMemberships(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono członka o ID: " + memberId));

        Optional<Membership> activePass = member.getMemberships().stream()
                .filter(Membership::isActive)
                .findFirst();

        if (activePass.isEmpty() || !activePass.get().canBook()) {
            throw new ReservationException("Członek nie posiada aktywnego karnetu lub wyczerpał limit wejść.");
        }

        FitnessClass fitnessClass = fitnessClassRepository.findById(fitnessClassId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono zajęć o ID: " + fitnessClassId));

        Reservation reservation = new Reservation(member, fitnessClass);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void removeReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono rezerwacji o ID: " + reservationId));

        reservationRepository.delete(reservation);

    }
}

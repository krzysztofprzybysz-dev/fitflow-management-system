package s24825.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s24825.model.classes.FitnessClass;
import s24825.model.person.Member;
import s24825.model.membership.Membership;
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

        Member member = memberRepository.findByIdWithMembershipsAndReservations(memberId)
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono członka o ID: " + memberId));

        FitnessClass fitnessClass = fitnessClassRepository.findById(fitnessClassId)
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono zajęć o ID: " + fitnessClassId));

        if (fitnessClass.getNumberOfReservations() >= fitnessClass.getCapacity()) {
            throw new IllegalStateException("Brak wolnych miejsc na te zajęcia.");
        }


        Optional<Membership> activePass = member.getMemberships().stream()
                .filter(Membership::isActive)
                .findFirst();

        if (activePass.isEmpty() || !activePass.get().canBook()) {
            throw new IllegalStateException("Członek nie posiada aktywnego karnetu lub wyczerpał limit wejść.");
        }

        boolean alreadyReserved = member.getReservations().stream()
                .anyMatch(r -> r.getFitnessClass().getId().equals(fitnessClassId));
        if (alreadyReserved) {
            throw new IllegalStateException("Już posiadasz rezerwację na te zajęcia.");
        }

        Reservation reservation = new Reservation(member, fitnessClass);
        reservationRepository.save(reservation);
    }
}

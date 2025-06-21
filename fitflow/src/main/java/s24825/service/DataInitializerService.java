package s24825.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import s24825.model.classes.GroupClass;
import s24825.model.membership.Membership;
import s24825.model.other.Address;
import s24825.model.other.TrainingRoom;
import s24825.model.person.Member;
import s24825.model.person.Trainer;
import s24825.model.reservation.Reservation;
import s24825.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Service to initialize the database with sample data for demonstration purposes.
 */
@Service
public class DataInitializerService {

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingRoomRepository trainingRoomRepository;
    private final FitnessClassRepository fitnessClassRepository;
    private final ReservationRepository reservationRepository; // <-- DODANE REPOZYTORIUM

    public DataInitializerService(MemberRepository memberRepository, TrainerRepository trainerRepository,
                                  TrainingRoomRepository trainingRoomRepository, FitnessClassRepository fitnessClassRepository,
                                  ReservationRepository reservationRepository) { // <-- DODANY PARAMETR
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
        this.trainingRoomRepository = trainingRoomRepository;
        this.fitnessClassRepository = fitnessClassRepository;
        this.reservationRepository = reservationRepository; // <-- DODANE PRZYPISANIE
    }

    @PostConstruct
    @org.springframework.transaction.annotation.Transactional
    public void initData() {

        // 1. CREATE TRAINING ROOMS
        TrainingRoom roomA = new TrainingRoom();
        roomA.setName("Sala A (Siłownia)");
        roomA.setNumber("A1");
        roomA.setCapacity(20);
        trainingRoomRepository.save(roomA);

        TrainingRoom roomB = new TrainingRoom();
        roomB.setName("Sala B (Fitness)");
        roomB.setNumber("B2");
        roomB.setCapacity(25);
        trainingRoomRepository.save(roomB);

        TrainingRoom roomC = new TrainingRoom();
        roomC.setName("Sala C (Cardio)");
        roomC.setNumber("C3");
        roomC.setCapacity(30);
        trainingRoomRepository.save(roomC);

        // 2. CREATE TRAINERS
        Trainer trainer1 = new Trainer();
        trainer1.setFirstName("Anna");
        trainer1.setLastName("Nowak");
        trainer1.setEmail("anna.nowak@fitflow.com");
        trainer1.setPassword("password123");
        trainer1.setDateOfBirth(LocalDate.of(1990, 5, 15));
        trainer1.setPhone("123456789");
        trainer1.setLicenseNumber("T-123456");
        Address address1 = new Address();
        address1.setStreet("Słoneczna");
        address1.setHouseNumber("10");
        address1.setCity("Warszawa");
        address1.setPostalCode("01-234");
        trainer1.setAddress(address1);
        trainer1.setSpecializations(Arrays.asList("Joga", "Pilates", "Zdrowy kręgosłup"));
        trainerRepository.save(trainer1);

        Trainer trainer2 = new Trainer();
        trainer2.setFirstName("Piotr");
        trainer2.setLastName("Wiśniewski");
        trainer2.setEmail("piotr.wisniewski@fitflow.com");
        trainer2.setPassword("password123");
        trainer2.setDateOfBirth(LocalDate.of(1985, 10, 20));
        trainer2.setPhone("987654321");
        trainer2.setLicenseNumber("T-654321");
        Address address2 = new Address();
        address2.setStreet("Księżycowa");
        address2.setHouseNumber("5A");
        address2.setApartmentNumber("12");
        address2.setCity("Kraków");
        address2.setPostalCode("30-002");
        trainer2.setAddress(address2);
        trainer2.setSpecializations(Arrays.asList("Crossfit", "Trening siłowy"));
        trainerRepository.save(trainer2);

        // 3. CREATE MEMBERS AND MEMBERSHIPS

        // Member 1: Standard Active Pass
        Member member1 = new Member();
        member1.setFirstName("Jan");
        member1.setLastName("Kowalski");
        member1.setMemberNumber("M001");
        member1.setEmail("jan.kowalski@example.com");
        member1.setPassword("password123");
        member1.setDateOfBirth(LocalDate.of(1995, 3, 25));
        member1.setPhone("555666777");
        member1.setRegistrationDate(LocalDate.now().minusMonths(6));
        Address memberAddress1 = new Address();
        memberAddress1.setStreet("Główna");
        memberAddress1.setHouseNumber("1");
        memberAddress1.setCity("Warszawa");
        memberAddress1.setPostalCode("02-456");
        member1.setAddress(memberAddress1);

        Membership pass1 = new Membership();
        pass1.setMembershipType("STANDARD");
        pass1.setPurchaseDate(LocalDate.now());
        pass1.setExpirationDate(LocalDate.now().plusMonths(1));
        pass1.setEntriesUsed(0);
        member1.addPass(pass1);
        memberRepository.save(member1);

        // Member 2: Premium Active Pass
        Member member2 = new Member();
        member2.setFirstName("Anna");
        member2.setLastName("Zielińska");
        member2.setMemberNumber("M002");
        member2.setEmail("anna.zielinska@example.com");
        member2.setPassword("password123");
        member2.setDateOfBirth(LocalDate.of(1998, 8, 20));
        member2.setPhone("222333444");
        Address memberAddress2 = new Address();
        memberAddress2.setStreet("Polna");
        memberAddress2.setHouseNumber("2");
        memberAddress2.setCity("Gdańsk");
        memberAddress2.setPostalCode("80-123");
        member2.setAddress(memberAddress2);

        Membership pass2 = new Membership();
        pass2.setMembershipType("PREMIUM");
        pass2.setPurchaseDate(LocalDate.now().minusDays(10));
        pass2.setExpirationDate(LocalDate.now().plusMonths(2));
        member2.addPass(pass2);
        memberRepository.save(member2);

        // Member 3: Standard Expired Pass
        Member member3 = new Member();
        member3.setFirstName("Tomasz");
        member3.setLastName("Wójcik");
        member3.setMemberNumber("M003");
        member3.setEmail("tomasz.wojcik@example.com");
        member3.setPassword("password123");
        member3.setDateOfBirth(LocalDate.of(1991, 1, 1));
        member3.setPhone("333444555");
        Address memberAddress3 = new Address();
        memberAddress3.setStreet("Krótka");
        memberAddress3.setHouseNumber("3");
        memberAddress3.setCity("Poznań");
        memberAddress3.setPostalCode("60-456");
        member3.setAddress(memberAddress3);

        Membership pass3 = new Membership();
        pass3.setMembershipType("STANDARD");
        pass3.setPurchaseDate(LocalDate.now().minusMonths(2));
        pass3.setExpirationDate(LocalDate.now().minusMonths(1)); // Expired last month
        pass3.setEntriesUsed(5);
        member3.addPass(pass3);
        memberRepository.save(member3);

        // 4. CREATE FITNESS CLASSES
        GroupClass yogaClass = new GroupClass();
        yogaClass.setName("Poranna Joga");
        yogaClass.setDescription("Odświeżająca sesja jogi na dobry początek dnia.");
        yogaClass.setDateTime(LocalDateTime.now().plusDays(2).withHour(8).withMinute(0));
        yogaClass.setDurationInMinutes(60);
        yogaClass.setTrainer(trainer1);
        yogaClass.setTrainingRoom(roomB);
        yogaClass.setParticipantLimit(15);
        fitnessClassRepository.save(yogaClass);

        GroupClass crossfitClass = new GroupClass();
        crossfitClass.setName("Crossfit");
        crossfitClass.setDescription("Intensywny trening funkcjonalny.");
        crossfitClass.setDateTime(LocalDateTime.now().plusDays(2).withHour(18).withMinute(0));
        crossfitClass.setDurationInMinutes(50);
        crossfitClass.setTrainer(trainer2);
        crossfitClass.setTrainingRoom(roomA);
        crossfitClass.setParticipantLimit(10);
        fitnessClassRepository.save(crossfitClass);

        GroupClass pilatesClass = new GroupClass();
        pilatesClass.setName("Pilates dla każdego");
        pilatesClass.setDescription("Wzmacniający pilates dla każdego.");
        pilatesClass.setDateTime(LocalDateTime.now().plusDays(3).withHour(17).withMinute(0));
        pilatesClass.setDurationInMinutes(55);
        pilatesClass.setTrainer(trainer1);
        pilatesClass.setTrainingRoom(roomB);
        pilatesClass.setParticipantLimit(2); // Low limit to test full class scenario
        fitnessClassRepository.save(pilatesClass);

        GroupClass hiitClass = new GroupClass();
        hiitClass.setName("HIIT");
        hiitClass.setDescription("Trening interwałowy o wysokiej intensywności.");
        hiitClass.setDateTime(LocalDateTime.now().plusDays(4).withHour(19).withMinute(0));
        hiitClass.setDurationInMinutes(45);
        hiitClass.setTrainer(trainer2);
        hiitClass.setTrainingRoom(roomC);
        hiitClass.setParticipantLimit(20);
        fitnessClassRepository.save(hiitClass);


        // 5. CREATE RESERVATIONS TO POPULATE THE SYSTEM

        // Member 1 reserves Yoga. His pass usage should increment.
        Reservation res1 = new Reservation(member1, yogaClass);
        reservationRepository.save(res1);
        pass1.handleBooking(); // Update entriesUsed for the standard pass
        memberRepository.save(member1); // Save the updated state of the member/pass

        // Member 2 reserves Crossfit.
        Reservation res2 = new Reservation(member2, crossfitClass);
        reservationRepository.save(res2);
        pass2.handleBooking(); // For premium pass, this does nothing, which is correct
        memberRepository.save(member2);

        // Member 1 and 2 reserve the Pilates class to make it full
        Reservation res3 = new Reservation(member1, pilatesClass);
        reservationRepository.save(res3);
        pass1.handleBooking();
        memberRepository.save(member1);

        Reservation res4 = new Reservation(member2, pilatesClass);
        reservationRepository.save(res4);
        pass2.handleBooking();
        memberRepository.save(member2);

        // We do not create a reservation for member3, as his pass is expired.
        // This allows testing the scenario where a user cannot book a class.
    }
}
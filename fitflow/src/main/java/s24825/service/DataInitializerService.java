package s24825.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import s24825.model.classes.GroupClass;
import s24825.model.membership.StandardMembership;
import s24825.model.other.TrainingRoom;
import s24825.model.person.Member;
import s24825.model.person.Trainer;
import s24825.repository.FitnessClassRepository;
import s24825.repository.MemberRepository;
import s24825.repository.TrainerRepository;
import s24825.repository.TrainingRoomRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Service to initialize the database with sample data for demonstration purposes.
 */
@Service
public class DataInitializerService {

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingRoomRepository trainingRoomRepository;
    private final FitnessClassRepository fitnessClassRepository;

    public DataInitializerService(MemberRepository memberRepository, TrainerRepository trainerRepository, TrainingRoomRepository trainingRoomRepository, FitnessClassRepository fitnessClassRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
        this.trainingRoomRepository = trainingRoomRepository;
        this.fitnessClassRepository = fitnessClassRepository;
    }

    @PostConstruct
    public void initData() {
        // 1. Create Trainers with all required fields
        Trainer trainer1 = new Trainer();
        trainer1.setFirstName("Anna");
        trainer1.setLastName("Nowak");
        trainer1.setEmail("anna.nowak@fitflow.com");
        trainer1.setPassword("password123");
        trainer1.setDateOfBirth(LocalDate.of(1990, 5, 15));
        trainer1.setPhone("123456789"); // Dodano numer telefonu
        trainer1.setLicenseNumber("T-123456");
        trainerRepository.save(trainer1);

        Trainer trainer2 = new Trainer();
        trainer2.setFirstName("Piotr");
        trainer2.setLastName("Wiśniewski");
        trainer2.setEmail("piotr.wisniewski@fitflow.com");
        trainer2.setPassword("password123");
        trainer2.setDateOfBirth(LocalDate.of(1985, 10, 20));
        trainer2.setPhone("987654321"); // Dodano numer telefonu
        trainer2.setLicenseNumber("T-654321");
        trainerRepository.save(trainer2);

        // 2. Create Training Halls
        TrainingRoom salaA = new TrainingRoom();
        salaA.setName("Sala A");
        salaA.setNumber("A1");
        salaA.setCapacity(20);
        trainingRoomRepository.save(salaA);

        TrainingRoom hallB = new TrainingRoom();
        hallB.setName("Sala B");
        hallB.setNumber("B2");
        hallB.setCapacity(15);
        trainingRoomRepository.save(hallB);

        // 3. Create Fitness Classes
        GroupClass yogaClass = new GroupClass();
        yogaClass.setName("Poranna Joga");
        yogaClass.setDescription("Odświeżająca sesja jogi na dobry początek dnia.");
        yogaClass.setDateTime(LocalDateTime.now().plusDays(1).withHour(8).withMinute(0));
        yogaClass.setDurationInMinutes(60);
        yogaClass.setTrainer(trainer1);
        yogaClass.setTrainingRoom(salaA);
        yogaClass.setParticipantLimit(20);
        fitnessClassRepository.save(yogaClass);

        GroupClass crossfitClass = new GroupClass();
        crossfitClass.setName("Crossfit");
        crossfitClass.setDescription("Intensywny trening funkcjonalny.");
        crossfitClass.setDateTime(LocalDateTime.now().plusDays(1).withHour(18).withMinute(0));
        crossfitClass.setDurationInMinutes(50);
        crossfitClass.setTrainer(trainer2);
        crossfitClass.setTrainingRoom(hallB);
        crossfitClass.setParticipantLimit(15);
        fitnessClassRepository.save(crossfitClass);

        GroupClass fullClass = new GroupClass();
        fullClass.setName("Pilates (Pełna)");
        fullClass.setDescription("Wzmacniający pilates dla każdego.");
        fullClass.setDateTime(LocalDateTime.now().plusDays(2).withHour(17).withMinute(0));
        fullClass.setDurationInMinutes(55);
        fullClass.setTrainer(trainer1);
        fullClass.setTrainingRoom(salaA);
        fullClass.setParticipantLimit(0); // For testing "no seats" scenario
        fitnessClassRepository.save(fullClass);

        // 4. Create a Member with a Pass
        Member member = new Member();
        member.setFirstName("Jan");
        member.setLastName("Kowalski");
        member.setMemberNumber("M001");
        member.setActive(true);
        member.setEmail("jan.kowalski@example.com");
        member.setPassword("password123");
        member.setDateOfBirth(LocalDate.of(1995, 3, 25));
        member.setPhone("555666777"); // Dodano numer telefonu
        member.setRegistrationDate(LocalDate.now().minusMonths(6));

        StandardMembership pass = new StandardMembership();
        pass.setPurchaseDate(LocalDate.now());
        pass.setExpirationDate(LocalDate.now().plusMonths(1));
        pass.setActive(true);
        pass.setEntryLimit(10);
        pass.setEntriesUsed(0);

        member.addPass(pass);
        memberRepository.save(member);
    }
}

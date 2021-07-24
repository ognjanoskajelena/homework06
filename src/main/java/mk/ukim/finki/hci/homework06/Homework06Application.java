package mk.ukim.finki.hci.homework06;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;
import mk.ukim.finki.hci.homework06.model.enums.Role;
import mk.ukim.finki.hci.homework06.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class Homework06Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Homework06Application.class, args);

        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);

        UserRepository participantRepository =
                configurableApplicationContext.getBean(ParticipantRepository.class);

        User initiator = new User("John", "Doe", "john_doe", "jd", "john.doe@yahoo.com", LocalDate.now(), Role.ROLE_INITIATOR);

        User admin = new User("Diana", "Sanchez", "dianna.s", "ds", "diana.s@outlook.com", LocalDate.now(), Role.ROLE_ADMIN);

        User participant = new Participant("Jelena", "Ognjanoska", "jelena.o", "jo", "j.ognjanoska@yahoo.com", LocalDate.now());

        userRepository.save(initiator);
        userRepository.save(admin);
        participantRepository.save(participant);

        PollQuestionRepository pollQuestionRepo =
                configurableApplicationContext.getBean(PollQuestionRepository.class);

        SingleChoiceQuestionRepository singleChoiceQuestionRepo =
                configurableApplicationContext.getBean(SingleChoiceQuestionRepository.class);

        MultipleChoiceQuestionRepository multipleChoiceQuestionRepo =
                configurableApplicationContext.getBean(MultipleChoiceQuestionRepository.class);

        OpenQuestionRepository openQuestionRepo =
                configurableApplicationContext.getBean(OpenQuestionRepository.class);

        PollQuestion pollQuestion = new PollQuestion("base", PollQuestionType.OPEN, null);

        OpenQuestion openQuestion = new OpenQuestion("open_q", null, "response");

        SingleChoiceQuestion singleChoiceQuestion = new SingleChoiceQuestion("single_q", null);

        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("multiple_q", null);

        pollQuestionRepo.save(pollQuestion);
        openQuestionRepo.save(openQuestion);
        singleChoiceQuestionRepo.save(singleChoiceQuestion);
        multipleChoiceQuestionRepo.save(multipleChoiceQuestion);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}

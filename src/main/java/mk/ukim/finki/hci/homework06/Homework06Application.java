package mk.ukim.finki.hci.homework06;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;
import mk.ukim.finki.hci.homework06.model.enums.Role;
import mk.ukim.finki.hci.homework06.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Homework06Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Homework06Application.class, args);

        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);

        UserRepository initiatorRepository =
                configurableApplicationContext.getBean(InitiatorRepository.class);

        UserRepository participantRepository =
                configurableApplicationContext.getBean(ParticipantRepository.class);

        UserRepository administratorRepository =
                configurableApplicationContext.getBean(AdministratorRepository.class);

        User user = new User("name", "surname", "username", "password", "email", Role.ROLE_PARTICIPANT);

        User initiator = new Initiator("name", "surname", "username", "password", "email", LocalDate.now(), "123456789");

        User participant = new Participant("name", "surname", "username", "password", "email", LocalDate.now());

        User administrator = new Administrator("name", "surname", "username", "password", "email");

        userRepository.save(user);
        initiatorRepository.save(initiator);
        participantRepository.save(participant);
        administratorRepository.save(administrator);

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
}

package mk.ukim.finki.hci.homework06;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.enums.Role;
import mk.ukim.finki.hci.homework06.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class Homework06Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Homework06Application.class, args);

        // Users with different roles
        UserRepository userRepository =
                configurableApplicationContext.getBean(UserRepository.class);
        UserRepository participantRepository =
                configurableApplicationContext.getBean(ParticipantRepository.class);
        User initiator = new User("John", "Doe", "john_doe", "jd",
                "john.doe@yahoo.com", LocalDate.now(), Role.ROLE_INITIATOR);
        User admin = new User("Admin", "Admin", "admin.a", "aa",
                "admin.a@outlook.com", LocalDate.now(), Role.ROLE_ADMIN);
        User participant = new Participant("TestName", "TestSurname", "test", "tt",
                "test@test.com", LocalDate.now());
        participantRepository.save(participant);
        participant = new Participant("Jelena", "Ognjanoska", "jelena.o", "jo",
                "j.ognjanoska@yahoo.com", LocalDate.now());
        userRepository.save(initiator);
        userRepository.save(admin);
        participantRepository.save(participant);

        // Initiatives
        InitiativeRepository initiativeRepository = configurableApplicationContext.getBean(InitiativeRepository.class);
        Initiative initiative = new Initiative("New children park in Maleardi",
                "Dear citizens, especially parents, the goal of this initiative is and will be along the way, to get new clean and beautiful park for our children. Every support is extremely important and valuable.",
                initiator);
        initiativeRepository.save(initiative);
        initiative = new Initiative("Landfill problem in Banjica", "The problem with the Rusino landfill is just getting worse. Polluting our food, air and water, this needs to be solved as fast as possible. And we can't let the control out of our hands. No one cares more about our families and friends than we do ourselves.",
                initiator);
        initiativeRepository.save(initiative);

        Participant participant1 = (Participant) participant;
        participant1.addToInitiatives(initiative);
        participantRepository.save(participant1);

        PollRepository pollRepository = configurableApplicationContext
                .getBean(PollRepository.class);
        Poll poll = new Poll("Progress and protest day", true, initiative);
        pollRepository.save(poll);

        //Open question
        OpenQuestionRepository openQuestionRepository = configurableApplicationContext
                .getBean(OpenQuestionRepository.class);
        PollQuestion openQuestion = new OpenQuestion("Is the course of this initiative going in the right direction so far?", poll);
        openQuestionRepository.save(openQuestion);

        //Single choice question
        SingleChoiceQuestionRepository singleChoiceQuestionRepo =
                configurableApplicationContext.getBean(SingleChoiceQuestionRepository.class);
        PollQuestion singleChoiceQuestion = new SingleChoiceQuestion("Do you think that it is the right time to go for a protest?", poll);
        singleChoiceQuestionRepo.save(singleChoiceQuestion);

        //Multiple choice question
        MultipleChoiceQuestionRepository multipleChoiceQuestionRepo =
                configurableApplicationContext.getBean(MultipleChoiceQuestionRepository.class);
        PollQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("Date of the next protest", poll);
        multipleChoiceQuestionRepo.save(multipleChoiceQuestion);

        //more polls
        poll = new Poll("First steps", false, initiative);
        pollRepository.save(poll);
        poll = new Poll("Further notice", false, initiative);
        pollRepository.save(poll);
        poll = new Poll("Need for legal entities", false, initiative);
        pollRepository.save(poll);
        poll = new Poll("Protest or not?", false, initiative);
        pollRepository.save(poll);

        //Choices
        ChoiceRepository choiceRepository = configurableApplicationContext.getBean(ChoiceRepository.class);
        Choice choice = new Choice("Yes", false, singleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("No", false, singleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("September 12, 2021", false, multipleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("September 14, 2021", false, multipleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("September 09, 2021", false, multipleChoiceQuestion);
        choiceRepository.save(choice);

        //Webinars
        WebinarRepository webinarRepository = configurableApplicationContext
                .getBean(WebinarRepository.class);
        Webinar webinar = new Webinar("Initial interest for ecological action in our city park",
                "The main purpose of this webinar will be to discuss ideas and rate the initial interest for an ecological action. Our park has been damaged and dirty for the whole summer, and we know that we are responsible for that, i.e. the one-month long park festival. Together we can arrange our park again for the incoming autumn.",
                "https://web.zoom.us/some34zoom7723meeting0098linkKJFKW=example", LocalDate.parse("2021-08-12"), LocalTime.parse("09:00:00"), initiator);
        webinarRepository.save(webinar);

        webinar = new Webinar("Donation of school reads for first, second, third and fourth graders",
                "The kids in the Edinstvo middle school struggle with copies of school reads. Any samples are welcomed, as long as they are in a readable state. Any needed repairments will be made after the collecting. That activity will also require recruiting volunteers, so if you are interested, stay tuned :)",
                "https://web.zoom.us/some34zoom7723meeting0098linkKJFKW=example", LocalDate.parse("2021-09-22"), LocalTime.parse("20:00:00"), initiator);
        webinarRepository.save(webinar);

        //Discussions
        DiscussionRepository discussionRepository = configurableApplicationContext.getBean(DiscussionRepository.class);

        Discussion discussion = new Discussion("Air polluting concerns", LocalDate.parse("2021-07-08"), initiative);
        discussionRepository.save(discussion);
        discussion = new Discussion("No responses from municipality", LocalDate.parse("2021-07-24"), initiative);
        discussionRepository.save(discussion);
        discussion = new Discussion("Lack of volunteers", LocalDate.parse("2021-07-25"), initiative);
        discussionRepository.save(discussion);

        discussion = new Discussion("Lack of legal entities for the scheduled municipality-meeting", LocalDate.parse("2021-09-12"), initiative);
        discussionRepository.save(discussion);

        //Commments
        CommentRepository commentRepository = configurableApplicationContext.getBean(CommentRepository.class);
        Comment comment = new Comment("I think that we need more qualified lawyers, because they are trying to avoid all the bad circumstances.", participant, discussion);
        commentRepository.save(comment);
        comment = new Comment("I totally agree with Mr. Charles's comment (above), also i am a lawyer with 17 years of experience, so i will be happy to contribute in this matter.", participant, discussion);
        commentRepository.save(comment);

        //Events
        EventRepository eventRepository = configurableApplicationContext.getBean(EventRepository.class);
        Event event = new Event("Protest for landfill Rusino",
                "The problem with the Rusino landfill is just getting worse. Polluting our food, air and water, this needs to be solved as fast as possible. And we can't let the control out of our hands.",
                LocalDate.parse("2021-08-12"), LocalTime.parse("10:00:00"), initiative);
        event.addToGoingParticipants((Participant) participant);
        eventRepository.save(event);

        event = new Event("Last consultations before the municipality meeting",
                "Event that will cover the last legal - related concerns before the municipality meeting. Also, there will be another voting related with the incoming meeting.",
                LocalDate.parse("2021-07-12"), LocalTime.parse("08:00:00"), initiative);
        event.close();
        eventRepository.save(event);

        //Volunteer opportunities
        VolunteerOpportunityRepository volunteerOpportunityRepository = configurableApplicationContext
                .getBean(VolunteerOpportunityRepository.class);
        VolunteerOpportunity volunteerOpportunity = new VolunteerOpportunity("Donation of school reads for first, second, third and fourth graders",
                "The kids in the \"Edinstvo\" middle school struggle with copies of school reads. Any samples are welcomed, as long as they are in a readable state. Any needed repairments will be made after the collecting. That activity will also require recruiting volunteers, so if you are interested, stay tuned :)",
                admin);
        volunteerOpportunityRepository.save(volunteerOpportunity);
        volunteerOpportunity = new VolunteerOpportunity("Ecological action in our city park",
                "Our park has been damaged and dirty for the whole summer, and we know that we are responsible for that, i.e. the one-month long park festival. Together we can arrange our park again for the incoming autumn.",
                admin);
        volunteerOpportunityRepository.save(volunteerOpportunity);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

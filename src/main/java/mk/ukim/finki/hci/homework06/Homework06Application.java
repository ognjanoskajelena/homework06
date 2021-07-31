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
        User john = new User("John", "Doe", "john_doe", "jd",
                "john.doe@outlook.com", LocalDate.of(1985, 12, 4), Role.ROLE_INITIATOR);
        User jenna = new User("Jenna", "Clarke", "jenna_clarke", "jc",
                "jenna.clarke@gmail.com", LocalDate.of(1990, 8, 14), Role.ROLE_INITIATOR);
        userRepository.save(john);
        userRepository.save(jenna);

        User jelena = new Participant("TestName", "TestSurname", "test", "tt",
                "test@test.com", LocalDate.of(1988, 2, 28));
        participantRepository.save(jelena);
        User test = new Participant("Jelena", "Ognjanoska", "jelena.o", "jo",
                "j.ognjanoska@yahoo.com", LocalDate.of(1999, 4, 8));
        participantRepository.save(jelena);
        participantRepository.save(test);

        User admin = new User("Admin", "Admin", "admin", "aa",
                "admin@admin.com", LocalDate.of(1992, 12, 9), Role.ROLE_ADMIN);
        userRepository.save(admin);

        // Initiatives
        InitiativeRepository initiativeRepository = configurableApplicationContext.getBean(InitiativeRepository.class);
        Initiative childrenPark = new Initiative("New children park in Maleardi",
                "Dear citizens, especially parents, the goal of this initiative is and will be along the way, to get new clean and beautiful park for our children. Every support is extremely important and valuable.",
                john);
        initiativeRepository.save(childrenPark);
        Initiative saveTheEnvironment = new Initiative("Save the environment while being active", "This initiative focuses on our and earth's health. Therefore, we need to raise the awareness for how beneficial riding bicycles can be. Join us on our mission in saving the planet and staying in physical health.",
                jenna);
        initiativeRepository.save(saveTheEnvironment);
//        Initiative stopForPlasticBags = new Initiative("Stop for plastic bags!", "Modern needs require modern alternatives. Not just that we do not do no good to our planet with (this) extensive use of plastic bags, we do enormous bad. Everyone who thinks alike will contribute so much with any ideas, everyone who does not, can participate to give us a chance to change that way of thinking.",
//                john);
//        initiativeRepository.save(stopForPlasticBags);
        Initiative landfillProblem = new Initiative("Landfill problem in Banjica", "The problem with the Rusino landfill is just getting worse. Polluting our food, air and water, this needs to be solved as fast as possible. And we can't let the control out of our hands. No one cares more about our families and friends than we do ourselves.",
                jenna);
        initiativeRepository.save(landfillProblem);

        Participant jelenaParticipant = (Participant) jelena;
        jelenaParticipant.addToInitiatives(landfillProblem);
        participantRepository.save(jelenaParticipant);
        Participant testParticipant = (Participant) test;
        testParticipant.addToInitiatives(childrenPark);
        participantRepository.save(testParticipant);

        PollRepository pollRepository = configurableApplicationContext
                .getBean(PollRepository.class);
        Poll pollForLandfillProblem = new Poll("Progress and protest day", true, landfillProblem);
        pollRepository.save(pollForLandfillProblem);

        Poll pollForSaveTheEarth = new Poll("Tour around town and the \"Rent a bike\" suggestion", true, saveTheEnvironment);
        pollRepository.save(pollForSaveTheEarth);

        Poll pollForChildrenPark = new Poll("New municipality meeting", true, childrenPark);
        pollRepository.save(pollForChildrenPark);

        //Open question
        OpenQuestionRepository openQuestionRepository = configurableApplicationContext
                .getBean(OpenQuestionRepository.class);
        PollQuestion openQuestion = new OpenQuestion("Is the course of this initiative going in the right direction so far?", pollForLandfillProblem);
        openQuestionRepository.save(openQuestion);
        PollQuestion openQuestion1 = new OpenQuestion("Your thoughts on the suggestion \"Rent a bike\"?", pollForSaveTheEarth);
        openQuestionRepository.save(openQuestion1);
        PollQuestion openQuestion2 = new OpenQuestion("Do you think that the course of this initiative is going in the right direction?", pollForChildrenPark);
        openQuestionRepository.save(openQuestion2);

        //Single choice question
        SingleChoiceQuestionRepository singleChoiceQuestionRepo =
                configurableApplicationContext.getBean(SingleChoiceQuestionRepository.class);
        PollQuestion singleChoiceQuestion = new SingleChoiceQuestion("Do you think now is the right time to go with a protest?", pollForLandfillProblem);
        singleChoiceQuestionRepo.save(singleChoiceQuestion);
        PollQuestion singleChoiceQuestion1 = new SingleChoiceQuestion("Do you think that we should do a bicycling tour around town?", pollForSaveTheEarth);
        singleChoiceQuestionRepo.save(singleChoiceQuestion1);
        PollQuestion singleChoiceQuestion2 = new SingleChoiceQuestion("Do you think that we should ask for another municipality meeting this month?", pollForChildrenPark);
        singleChoiceQuestionRepo.save(singleChoiceQuestion2);

        //Multiple choice question
        MultipleChoiceQuestionRepository multipleChoiceQuestionRepo =
                configurableApplicationContext.getBean(MultipleChoiceQuestionRepository.class);
        PollQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("Potential date of the next protest:", pollForLandfillProblem);
        multipleChoiceQuestionRepo.save(multipleChoiceQuestion);
        PollQuestion multipleChoiceQuestion1 = new MultipleChoiceQuestion("Potential date of the tour:", pollForSaveTheEarth);
        multipleChoiceQuestionRepo.save(multipleChoiceQuestion1);
        PollQuestion multipleChoiceQuestion2 = new MultipleChoiceQuestion("Potential date of the meeting:", pollForChildrenPark);
        multipleChoiceQuestionRepo.save(multipleChoiceQuestion2);

        //Closed polls
        Poll closedPoll = new Poll("First steps", false, landfillProblem);
        pollRepository.save(closedPoll);
        closedPoll = new Poll("Further notice", false, landfillProblem);
        pollRepository.save(closedPoll);
        closedPoll = new Poll("Need for legal entities", false, landfillProblem);
        pollRepository.save(closedPoll);
        closedPoll = new Poll("Protest or not?", false, landfillProblem);
        pollRepository.save(closedPoll);

        closedPoll = new Poll("First steps", false, saveTheEnvironment);
        pollRepository.save(closedPoll);
        closedPoll = new Poll("Further actions", false, saveTheEnvironment);
        pollRepository.save(closedPoll);

        closedPoll = new Poll("First steps", false, childrenPark);
        pollRepository.save(closedPoll);
        closedPoll = new Poll("Your opinions", false, childrenPark);
        pollRepository.save(closedPoll);

        //Choices
        ChoiceRepository choiceRepository = configurableApplicationContext.getBean(ChoiceRepository.class);
        Choice choice = new Choice("Yes", false, singleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("Yes", false, singleChoiceQuestion1);
        choiceRepository.save(choice);
        choice = new Choice("No", false, singleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("No", false, singleChoiceQuestion1);
        choiceRepository.save(choice);
        choice = new Choice("Later", false, singleChoiceQuestion1);
        choiceRepository.save(choice);

        choice = new Choice("Yes", false, singleChoiceQuestion2);
        choiceRepository.save(choice);
        choice = new Choice("No", false, singleChoiceQuestion2);
        choiceRepository.save(choice);
        choice = new Choice("Later", false, singleChoiceQuestion2);
        choiceRepository.save(choice);

        choice = new Choice("September 12, 2021", false, multipleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("September 12, 2021", false, multipleChoiceQuestion1);
        choiceRepository.save(choice);
        choice = new Choice("September 14, 2021", false, multipleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("September 14, 2021", false, multipleChoiceQuestion1);
        choiceRepository.save(choice);
        choice = new Choice("September 09, 2021", false, multipleChoiceQuestion);
        choiceRepository.save(choice);
        choice = new Choice("September 09, 2021", false, multipleChoiceQuestion1);
        choiceRepository.save(choice);

        choice = new Choice("September 22, 2021", false, multipleChoiceQuestion2);
        choiceRepository.save(choice);
        choice = new Choice("September 25, 2021", false, multipleChoiceQuestion2);
        choiceRepository.save(choice);

        //Webinars
        WebinarRepository webinarRepository = configurableApplicationContext
                .getBean(WebinarRepository.class);
        Webinar webinar = new Webinar("Initial interest for ecological action in our city park",
                "The main purpose of this webinar will be to discuss ideas and rate the initial interest for an ecological action. Our park has been damaged and dirty for the whole summer, and we know that we are responsible for that, i.e. the one-month long park festival. Together we can arrange our park again for the incoming autumn.",
                "https://web.zoom.us/some34zoom7723meeting0098linkKJFKW=example", LocalDate.parse("2021-08-12"), LocalTime.parse("09:00:00"), john);
        webinarRepository.save(webinar);

        webinar = new Webinar("Donation of school reads for first, second, third and fourth graders",
                "The kids in the Edinstvo middle school struggle with copies of school reads. Any samples are welcomed, as long as they are in a readable state. Any needed repairments will be made after the collecting. That activity will also require recruiting volunteers, so if you are interested, stay tuned :)",
                "https://web.zoom.us/some34zoom7723meeting0098linkKJFKW=example", LocalDate.parse("2021-09-22"), LocalTime.parse("20:00:00"), jenna);
        webinarRepository.save(webinar);

        //Discussions
        DiscussionRepository discussionRepository = configurableApplicationContext.getBean(DiscussionRepository.class);

        Discussion discussion = new Discussion("Air polluting concerns", LocalDate.parse("2021-07-08"), landfillProblem);
        discussionRepository.save(discussion);
        discussion = new Discussion("No responses from municipality", LocalDate.parse("2021-07-24"), landfillProblem);
        discussionRepository.save(discussion);
        discussion = new Discussion("Lack of volunteers", LocalDate.parse("2021-07-25"), landfillProblem);
        discussionRepository.save(discussion);
        discussion = new Discussion("Lack of legal entities for the scheduled municipality-meeting", LocalDate.parse("2021-09-12"), landfillProblem);
        discussionRepository.save(discussion);

        Discussion discussion1 = new Discussion("Air polluting concerns", LocalDate.parse("2021-07-08"), saveTheEnvironment);
        discussionRepository.save(discussion1);
        discussion1 = new Discussion("\"Rent a bike\"", LocalDate.parse("2021-12-08"), saveTheEnvironment);
        discussionRepository.save(discussion1);

        Discussion discussion2 = new Discussion("Exact location of the park", LocalDate.parse("2021-07-14"), childrenPark);
        discussionRepository.save(discussion2);
        discussion2 = new Discussion("Latest concerns on the meeting", LocalDate.parse("2021-10-08"), childrenPark);
        discussionRepository.save(discussion2);

        //Comments
        CommentRepository commentRepository = configurableApplicationContext.getBean(CommentRepository.class);
        Comment comment = new Comment("I think that we need more qualified lawyers, because they are trying to avoid all the bad circumstances.", jelena, discussion);
        commentRepository.save(comment);
        comment = new Comment("I totally agree with Mr. Charles's comment (above), also i am a lawyer with 17 years of experience, so i will be happy to contribute in this matter.", test, discussion);
        commentRepository.save(comment);

        comment = new Comment("I think that our town desperately needs this kind of service.", jelena, discussion1);
        commentRepository.save(comment);

        comment = new Comment("I think that our neighborhood really needs this.", test, discussion2);
        commentRepository.save(comment);

        //Events
        EventRepository eventRepository = configurableApplicationContext.getBean(EventRepository.class);
        Event event = new Event("Protest for landfill Rusino",
                "The problem with the Rusino landfill is just getting worse. Polluting our food, air and water, this needs to be solved as fast as possible. And we can't let the control out of our hands.",
                LocalDate.parse("2021-10-12"), LocalTime.parse("10:00:00"), landfillProblem);
        event.addToGoingParticipants((Participant) jelena);
        eventRepository.save(event);

        event = new Event("Last consultations before the municipality meeting",
                "Event that will cover the last legal - related concerns before the municipality meeting. Also, there will be another voting related with the incoming meeting.",
                LocalDate.parse("2021-07-12"), LocalTime.parse("09:00:00"), landfillProblem);
        event.close();
        eventRepository.save(event);

        event = new Event("Tour around town",
                "We need to raise the awareness for how beneficial riding bicycles can be. Join us on our mission in saving the planet and staying in physical health.",
                LocalDate.parse("2021-09-12"), LocalTime.parse("11:00:00"), saveTheEnvironment);
        eventRepository.save(event);
        event = new Event("Last consultations before the municipality meeting",
                "Event that will cover the last legal - related concerns before the municipality meeting. Also, there will be another voting related with the incoming meeting.",
                LocalDate.parse("2021-06-12"), LocalTime.parse("14:00:00"), saveTheEnvironment);
        event.close();
        eventRepository.save(event);

        event = new Event("Last consultations before the municipality meeting",
                "Event that will cover the last legal - related concerns before the municipality meeting. Also, there will be another voting related with the incoming meeting.",
                LocalDate.parse("2021-07-20"), LocalTime.parse("14:00:00"), childrenPark);
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

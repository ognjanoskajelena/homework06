package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiatorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.ParticipantNotFoundException;
import mk.ukim.finki.hci.homework06.repository.InitiativeRepository;
import mk.ukim.finki.hci.homework06.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InitiativeServiceImpl implements InitiativeService {

    private final InitiativeRepository initiativeRepository;
    private final InitiatorService initiatorService;
    private final EventService eventService;
    private final PollService pollService;
    private final DiscussionService discussionService;
    private final ParticipantService participantService;

    public InitiativeServiceImpl(InitiativeRepository initiativeRepository, InitiatorService initiatorService,
                                 EventService eventService, PollService pollService,
                                 DiscussionService discussionService, ParticipantService participantService) {
        this.initiativeRepository = initiativeRepository;
        this.initiatorService = initiatorService;
        this.eventService = eventService;
        this.pollService = pollService;
        this.discussionService = discussionService;
        this.participantService = participantService;
    }

    @Override
    public Optional<Initiative> findById(Long id) {
        return this.initiativeRepository.findById(id);
    }

    @Override
    public Optional<Initiative> save(String title, String description, String dateOpened, Long initiatorId) {
        Optional<Initiator> initiator = this.initiatorService.findById(initiatorId);
        if(initiator.isEmpty())
            throw new InitiatorNotFoundException(initiatorId);

        Initiative initiative = new Initiative(title, description, LocalDate.parse(dateOpened), initiator.get());
        return Optional.of(this.initiativeRepository.save(initiative));
    }

    @Override
    public Optional<Initiative> update(Long id, String title, String description, String dateOpened) {
        Optional<Initiative> initiative = this.findById(id);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(id);

        Initiative updatedInitiative = initiative.get();
        updatedInitiative.setTitle(title);
        updatedInitiative.setDescription(description);
        updatedInitiative.setDateOpened(LocalDate.parse(dateOpened));

        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> deleteById(Long id) {
        Optional<Initiative> initiative = this.findById(id);
        if(initiative.isPresent()) {
            this.initiativeRepository.deleteById(id);
            return initiative;
        }
        throw new InitiativeNotFoundException(id);
    }

    @Override
    public Optional<Initiative> addEvent(Long initiativeId, Event event) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        this.eventService.save(event.getTitle(), event.getDescription(), event.getDate(), event.getTime(),
                initiativeId);
        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToEvents(event);
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> addPoll(Long initiativeId, Poll poll) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        this.pollService.save(poll.getTopic(), poll.isOpen(), initiativeId, poll.getQuestions());
        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToPolls(poll);
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> addDiscussion(Long initiativeId, Discussion discussion) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        this.discussionService.save(discussion.getTopic(), discussion.getCloseDate(), initiativeId);
        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToDiscussions(discussion);
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> addParticipant(Long initiativeId, Long participantId) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Optional<Participant> participant = this.participantService.findById(participantId);
        if(participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToParticipants(participant.get());
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> removeParticipant(Long initiativeId, Long participantId) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Optional<Participant> participant = this.participantService.findById(participantId);
        if(participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Initiative updatedInitiative = initiative.get();
        updatedInitiative.removeFromParticipants(participant.get());
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }
}

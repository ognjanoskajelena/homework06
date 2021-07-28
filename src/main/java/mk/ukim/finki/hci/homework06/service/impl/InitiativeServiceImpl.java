package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiatorNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.ParticipantNotFoundException;
import mk.ukim.finki.hci.homework06.repository.DiscussionRepository;
import mk.ukim.finki.hci.homework06.repository.EventRepository;
import mk.ukim.finki.hci.homework06.repository.InitiativeRepository;
import mk.ukim.finki.hci.homework06.repository.PollRepository;
import mk.ukim.finki.hci.homework06.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InitiativeServiceImpl implements InitiativeService {

    private final InitiativeRepository initiativeRepository;
    private final UserService userService;
    private final EventRepository eventRepository;
    private final PollRepository pollRepository;
    private final DiscussionRepository discussionRepository;
    private final ParticipantService participantService;

    public InitiativeServiceImpl(InitiativeRepository initiativeRepository, UserService userService,
                                 EventRepository eventRepository, PollRepository pollRepository,
                                 DiscussionRepository discussionRepository, ParticipantService participantService) {
        this.initiativeRepository = initiativeRepository;
        this.userService = userService;
        this.eventRepository = eventRepository;
        this.pollRepository = pollRepository;
        this.discussionRepository = discussionRepository;
        this.participantService = participantService;
    }

    @Override
    public List<Initiative> findAll() {
        return this.initiativeRepository.findAll();
    }

    @Override
    public Optional<Initiative> findById(Long id) {
        return this.initiativeRepository.findById(id);
    }

    @Override
    public Optional<Initiative> save(String title, String description, Long initiatorId) {
        Optional<User> initiator = this.userService.findById(initiatorId);
        if(initiator.isEmpty())
            throw new InitiatorNotFoundException(initiatorId);

        Initiative initiative = new Initiative(title, description, initiator.get());
        return Optional.of(this.initiativeRepository.save(initiative));
    }

    @Override
    public Optional<Initiative> update(Long id, String title, String description) {
        Optional<Initiative> initiative = this.findById(id);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(id);

        Initiative updatedInitiative = initiative.get();
        updatedInitiative.setTitle(title);
        updatedInitiative.setDescription(description);

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

        this.eventRepository.save(event);
        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToEvents(event);
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> addPoll(Long initiativeId, Poll poll) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        this.pollRepository.save(poll);
        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToPolls(poll);
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }

    @Override
    public Optional<Initiative> addDiscussion(Long initiativeId, Discussion discussion) {
        Optional<Initiative> initiative = this.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        this.discussionRepository.save(discussion);
        Initiative updatedInitiative = initiative.get();
        updatedInitiative.addToDiscussions(discussion);
        return Optional.of(this.initiativeRepository.save(updatedInitiative));
    }
}

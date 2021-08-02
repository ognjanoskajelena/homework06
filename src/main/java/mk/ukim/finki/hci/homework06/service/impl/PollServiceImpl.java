package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.UserNotFoundException;
import mk.ukim.finki.hci.homework06.repository.PollRepository;
import mk.ukim.finki.hci.homework06.service.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final InitiativeService initiativeService;
    private final ParticipantService participantService;

    public PollServiceImpl(PollRepository pollRepository,
                           InitiativeService initiativeService,
                           ParticipantService participantService) {
        this.pollRepository = pollRepository;
        this.initiativeService = initiativeService;
        this.participantService = participantService;
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return this.pollRepository.findById(id);
    }

    @Override
    public Optional<Poll> save(String topic, boolean isOpen, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if (initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Poll poll = new Poll(topic, isOpen, initiative.get());
        return Optional.of(this.pollRepository.save(poll));
    }

    @Override
    public Optional<Poll> fill(Long pollId, String participantUsername) {
        Optional<Poll> poll = this.findById(pollId);
        if (poll.isEmpty())
            throw new PollNotFoundException(pollId);

        Optional<Participant> participant = this.participantService.findByUsername(participantUsername);
        if (participant.isEmpty())
            throw new UserNotFoundException(participantUsername);

        Poll updatedPoll = poll.get();
        updatedPoll.addToParticipants(participant.get());
        return Optional.of(this.pollRepository.save(updatedPoll));
    }
}

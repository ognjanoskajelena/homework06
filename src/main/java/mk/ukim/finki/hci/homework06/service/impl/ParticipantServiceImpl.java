package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.ParticipantNotFoundException;
import mk.ukim.finki.hci.homework06.repository.ParticipantRepository;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import mk.ukim.finki.hci.homework06.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final InitiativeService initiativeService;

    public ParticipantServiceImpl(ParticipantRepository participantRepository,
                                  InitiativeService initiativeService) {
        this.participantRepository = participantRepository;
        this.initiativeService = initiativeService;
    }

    @Override
    public Optional<Participant> findById(Long id) {
        Optional<User> participant = this.participantRepository.findById(id);
        return participant.map(user -> (Participant) user);
    }

    @Override
    public Optional<Participant> participate(Long participantId, Long initiativeId) {
        Optional<Participant> participant = this.findById(participantId);
        if(participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Participant updatedParticipant = participant.get();
        // todo: Check status
        updatedParticipant.addToInitiatives(initiative.get());
        return Optional.of(this.participantRepository.save(updatedParticipant));
    }
}

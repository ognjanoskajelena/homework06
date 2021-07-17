package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.repository.ParticipantRepository;
import mk.ukim.finki.hci.homework06.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public Optional<Participant> findById(Long id) {
        Optional<User> participant = this.participantRepository.findById(id);
        return participant.map(user -> (Participant) user);
    }
}

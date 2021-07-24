package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Participant;

import java.util.Optional;

public interface ParticipantService {
    Optional<Participant> findById(Long id);

    Optional<Participant> participate(Long participantId, Long initiativeId);

    Optional<Participant> notParticipate(Long participantId, Long initiativeId);
}

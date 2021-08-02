package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Poll;

import java.util.Optional;

public interface PollService {
    Optional<Poll> findById(Long id);

    Optional<Poll> save(String topic, boolean isOpen, Long initiativeId);

    Optional<Poll> fill(Long pollId, String participantUsername);
}

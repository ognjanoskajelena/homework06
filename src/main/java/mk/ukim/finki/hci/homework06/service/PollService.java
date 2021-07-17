package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<Poll> findById(Long id);

    Optional<Poll> save(String topic, boolean isOpen, Long initiativeId);

    Optional<Poll> save(String topic, boolean isOpen, Long initiativeId, List<Long> questionsIds);

    Optional<Poll> update(Long id, String topic, boolean isOpen);

    Optional<Poll> deleteById(Long id);
}

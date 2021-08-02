package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.PollQuestion;

import java.util.Optional;

public interface PollQuestionService {
    Optional<PollQuestion> findById(Long id);

    Optional<PollQuestion> save(PollQuestion question);
}

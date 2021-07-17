package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import java.util.Optional;

public interface PollQuestionService {
    Optional<PollQuestion> findById(Long id);

    Optional<PollQuestion> save(String content, PollQuestionType type, Long pollId);

    Optional<PollQuestion> update(Long id, String content, PollQuestionType type);

    Optional<PollQuestion> deleteById(Long id);
}

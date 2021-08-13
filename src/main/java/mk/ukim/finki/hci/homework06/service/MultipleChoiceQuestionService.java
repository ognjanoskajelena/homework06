package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.MultipleChoiceQuestion;
import mk.ukim.finki.hci.homework06.model.PollQuestion;

import java.util.Optional;

public interface MultipleChoiceQuestionService {
    Optional<MultipleChoiceQuestion> findById(Long id);

    Optional<PollQuestion> save(String content, Long pollId);
}

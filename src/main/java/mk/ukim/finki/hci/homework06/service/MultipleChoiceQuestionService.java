package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.MultipleChoiceQuestion;

import java.util.List;
import java.util.Optional;

public interface MultipleChoiceQuestionService {
    Optional<MultipleChoiceQuestion> findById(Long id);

    Optional<MultipleChoiceQuestion> save(String content, Long pollId);

    Optional<MultipleChoiceQuestion> save(String content, Long pollId, List<Long> choicesIds);
}

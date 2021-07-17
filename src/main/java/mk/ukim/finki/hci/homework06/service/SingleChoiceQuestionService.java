package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.SingleChoiceQuestion;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import java.util.List;
import java.util.Optional;

public interface SingleChoiceQuestionService {
    Optional<SingleChoiceQuestion> findById(Long id);

    Optional<SingleChoiceQuestion> save(String content, Long pollId);

    Optional<SingleChoiceQuestion> save(String content, Long pollId, List<Long> choicesIds);
}

package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Choice;
import mk.ukim.finki.hci.homework06.model.MultipleChoiceQuestion;
import mk.ukim.finki.hci.homework06.model.PollQuestion;

import java.util.List;
import java.util.Optional;

public interface MultipleChoiceQuestionService {
    Optional<MultipleChoiceQuestion> findById(Long id);

    Optional<PollQuestion> save(String content, Long pollId);

    Optional<PollQuestion> save(String content, Long pollId, List<Long> choicesIds);

    Optional<PollQuestion> addChoice(Long questionId, Choice choice);
}

package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.SingleChoiceQuestion;

import java.util.Optional;

public interface SingleChoiceQuestionService {
    Optional<SingleChoiceQuestion> findById(Long id);

    Optional<PollQuestion> save(String content, Long pollId);
}

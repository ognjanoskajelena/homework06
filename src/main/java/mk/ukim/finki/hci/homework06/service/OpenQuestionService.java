package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.OpenQuestion;
import mk.ukim.finki.hci.homework06.model.PollQuestion;

import java.util.Optional;

public interface OpenQuestionService {
    Optional<OpenQuestion> findById(Long id);

    Optional<PollQuestion> save(String content, Long pollId, String response);
}

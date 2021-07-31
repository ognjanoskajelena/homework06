package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Choice;

import java.util.Optional;

public interface ChoiceService {
    Optional<Choice> findById(Long id);

    Optional<Choice> save(String content, boolean selected, Long pollQuestionId);

    Optional<Choice> save(String content, Long pollQuestionId);

    Optional<Choice> deleteById(Long id);

    Optional<Choice> changeSelection(Long choiceId);
}

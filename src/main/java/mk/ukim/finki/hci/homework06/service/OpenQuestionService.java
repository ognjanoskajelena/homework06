package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.OpenQuestion;

import java.util.Optional;

public interface OpenQuestionService {
    Optional<OpenQuestion> findById(Long id);
}

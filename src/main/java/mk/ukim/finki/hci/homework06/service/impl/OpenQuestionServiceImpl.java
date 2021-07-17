package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.OpenQuestion;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.repository.OpenQuestionRepository;
import mk.ukim.finki.hci.homework06.service.OpenQuestionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpenQuestionServiceImpl implements OpenQuestionService {

    private final OpenQuestionRepository openQuestionRepository;

    public OpenQuestionServiceImpl(OpenQuestionRepository openQuestionRepository) {
        this.openQuestionRepository = openQuestionRepository;
    }

    @Override
    public Optional<OpenQuestion> findById(Long id) {
        Optional<PollQuestion> pollQuestion = this.openQuestionRepository.findById(id);
        return pollQuestion.map(question -> (OpenQuestion) question);
    }
}

package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.repository.PollQuestionRepository;
import mk.ukim.finki.hci.homework06.service.PollQuestionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollQuestionServiceImpl implements PollQuestionService {

    private final PollQuestionRepository pollQuestionRepository;

    public PollQuestionServiceImpl(PollQuestionRepository pollQuestionRepository) {
        this.pollQuestionRepository = pollQuestionRepository;
    }

    @Override
    public Optional<PollQuestion> findById(Long id) {
        return this.pollQuestionRepository.findById(id);
    }

    @Override
    public Optional<PollQuestion> save(PollQuestion question) {
        return Optional.of(this.pollQuestionRepository.save(question));
    }
}

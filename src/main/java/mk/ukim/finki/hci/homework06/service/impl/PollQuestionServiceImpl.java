package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollQuestionNotFoundException;
import mk.ukim.finki.hci.homework06.repository.PollQuestionRepository;
import mk.ukim.finki.hci.homework06.service.PollQuestionService;
import mk.ukim.finki.hci.homework06.service.PollService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollQuestionServiceImpl implements PollQuestionService {

    private final PollQuestionRepository pollQuestionRepository;
    private final PollService pollService;

    public PollQuestionServiceImpl(PollQuestionRepository pollQuestionRepository, PollService pollService) {
        this.pollQuestionRepository = pollQuestionRepository;
        this.pollService = pollService;
    }

    @Override
    public Optional<PollQuestion> findById(Long id) {
        return this.pollQuestionRepository.findById(id);
    }

    @Override
    public Optional<PollQuestion> save(String content, PollQuestionType type, Long pollId) {
        Optional<Poll> poll = this.pollService.findById(pollId);
        if(poll.isEmpty())
            throw new PollNotFoundException(pollId);

        PollQuestion pollQuestion = new PollQuestion(content, type, poll.get());
        return Optional.of(this.pollQuestionRepository.save(pollQuestion));
    }

    @Override
    public Optional<PollQuestion> update(Long id, String content, PollQuestionType type) {
        Optional<PollQuestion> pollQuestion = this.pollQuestionRepository.findById(id);
        if(pollQuestion.isEmpty())
            throw new PollQuestionNotFoundException(id);

        PollQuestion updatePollQuestion = pollQuestion.get();
        updatePollQuestion.setContent(content);
        updatePollQuestion.setType(type);
        return Optional.of(this.pollQuestionRepository.save(updatePollQuestion));
    }

    @Override
    public Optional<PollQuestion> deleteById(Long id) {
        Optional<PollQuestion> pollQuestion = this.findById(id);
        if(pollQuestion.isPresent()) {
            this.pollQuestionRepository.deleteById(id);
            return pollQuestion;
        }
        throw new PollQuestionNotFoundException(id);
    }
}

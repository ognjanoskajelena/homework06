package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.OpenQuestion;
import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.repository.OpenQuestionRepository;
import mk.ukim.finki.hci.homework06.service.OpenQuestionService;
import mk.ukim.finki.hci.homework06.service.PollService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpenQuestionServiceImpl implements OpenQuestionService {

    private final OpenQuestionRepository openQuestionRepository;
    private final PollService pollService;

    public OpenQuestionServiceImpl(OpenQuestionRepository openQuestionRepository, PollService pollService) {
        this.openQuestionRepository = openQuestionRepository;
        this.pollService = pollService;
    }

    @Override
    public Optional<OpenQuestion> findById(Long id) {
        Optional<PollQuestion> pollQuestion = this.openQuestionRepository.findById(id);
        return pollQuestion.map(question -> (OpenQuestion) question);
    }

    @Override
    public Optional<PollQuestion> save(String content, Long pollId, String response) {
        Optional<Poll> poll = this.pollService.findById(pollId);
        if(poll.isEmpty())
            throw new PollNotFoundException(pollId);

        PollQuestion openQuestion = new OpenQuestion(content, poll.get(), response);
        return Optional.of(this.openQuestionRepository.save(openQuestion));
    }
}

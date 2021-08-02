package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.SingleChoiceQuestion;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.repository.SingleChoiceQuestionRepository;
import mk.ukim.finki.hci.homework06.service.PollService;
import mk.ukim.finki.hci.homework06.service.SingleChoiceQuestionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingleChoiceQuestionServiceImpl implements SingleChoiceQuestionService {

    private final SingleChoiceQuestionRepository singleChoiceQuestionRepository;
    private final PollService pollService;

    public SingleChoiceQuestionServiceImpl(SingleChoiceQuestionRepository singleChoiceQuestionRepository,
                                           PollService pollService) {
        this.singleChoiceQuestionRepository = singleChoiceQuestionRepository;
        this.pollService = pollService;
    }

    @Override
    public Optional<SingleChoiceQuestion> findById(Long id) {
        Optional<PollQuestion> singleChoiceQuestion = this.singleChoiceQuestionRepository.findById(id);
        return singleChoiceQuestion.map(question -> (SingleChoiceQuestion) question);
    }

    @Override
    public Optional<PollQuestion> save(String content, Long pollId) {
        Optional<Poll> poll = this.pollService.findById(pollId);
        if(poll.isEmpty())
            throw new PollNotFoundException(pollId);

        PollQuestion singleChoiceQuestion = new SingleChoiceQuestion(content, poll.get());
        return Optional.of(this.singleChoiceQuestionRepository.save(singleChoiceQuestion));
    }
}

package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.MultipleChoiceQuestion;
import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.repository.MultipleChoiceQuestionRepository;
import mk.ukim.finki.hci.homework06.service.MultipleChoiceQuestionService;
import mk.ukim.finki.hci.homework06.service.PollService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {

    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final PollService pollService;

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository,
                                             PollService pollService) {
        this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
        this.pollService = pollService;
    }

    @Override
    public Optional<MultipleChoiceQuestion> findById(Long id) {
        Optional<PollQuestion> pollQuestion = this.multipleChoiceQuestionRepository.findById(id);
        return pollQuestion.map(question -> (MultipleChoiceQuestion) question);
    }

    @Override
    public Optional<PollQuestion> save(String content, Long pollId) {
        Optional<Poll> poll = this.pollService.findById(pollId);
        if(poll.isEmpty())
            throw new PollNotFoundException(pollId);

        PollQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(content, poll.get());
        return Optional.of(this.multipleChoiceQuestionRepository.save(multipleChoiceQuestion));
    }
}

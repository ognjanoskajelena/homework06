package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Choice;
import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.SingleChoiceQuestion;
import mk.ukim.finki.hci.homework06.model.exception.ChoiceNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollQuestionNotFoundException;
import mk.ukim.finki.hci.homework06.repository.SingleChoiceQuestionRepository;
import mk.ukim.finki.hci.homework06.service.ChoiceService;
import mk.ukim.finki.hci.homework06.service.PollService;
import mk.ukim.finki.hci.homework06.service.SingleChoiceQuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SingleChoiceQuestionServiceImpl implements SingleChoiceQuestionService {

    private final SingleChoiceQuestionRepository singleChoiceQuestionRepository;
    private final PollService pollService;
    private final ChoiceService choiceService;

    public SingleChoiceQuestionServiceImpl(SingleChoiceQuestionRepository singleChoiceQuestionRepository,
                                           PollService pollService,
                                           ChoiceService choiceService) {
        this.singleChoiceQuestionRepository = singleChoiceQuestionRepository;
        this.pollService = pollService;
        this.choiceService = choiceService;
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

    @Override
    public Optional<PollQuestion> save(String content, Long pollId, List<Long> choicesIds) {
        Optional<Poll> poll = this.pollService.findById(pollId);
        if(poll.isEmpty())
            throw new PollNotFoundException(pollId);

        List<Choice> choiceList = new ArrayList<>();
        for (Long cId : choicesIds) {
            Optional<Choice> choice = this.choiceService.findById(cId);
            if(choice.isEmpty())
                throw new ChoiceNotFoundException(cId);

            choiceList.add(choice.get());
        }

        PollQuestion singleChoiceQuestion = new SingleChoiceQuestion(content, poll.get(), choiceList);
        return Optional.of(this.singleChoiceQuestionRepository.save(singleChoiceQuestion));
    }

    @Override
    public Optional<PollQuestion> addChoice(Long questionId, Choice choice) {
        Optional<PollQuestion> question = this.singleChoiceQuestionRepository.findById(questionId);
        if(question.isEmpty())
            throw new PollQuestionNotFoundException(questionId);

        PollQuestion choiceQuestion = question.get();
        choiceQuestion.addToChoices(choice);
        return Optional.of(this.singleChoiceQuestionRepository.save(choiceQuestion));
    }
}

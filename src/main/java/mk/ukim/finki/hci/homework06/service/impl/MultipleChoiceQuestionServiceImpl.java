package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.model.exception.ChoiceNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollQuestionNotFoundException;
import mk.ukim.finki.hci.homework06.repository.MultipleChoiceQuestionRepository;
import mk.ukim.finki.hci.homework06.service.ChoiceService;
import mk.ukim.finki.hci.homework06.service.MultipleChoiceQuestionService;
import mk.ukim.finki.hci.homework06.service.PollService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {

    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final PollService pollService;
    private final ChoiceService choiceService;

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository,
                                             PollService pollService,
                                             ChoiceService choiceService) {
        this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
        this.pollService = pollService;
        this.choiceService = choiceService;
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

        PollQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(content, poll.get(), choiceList);
        return Optional.of(this.multipleChoiceQuestionRepository.save(multipleChoiceQuestion));
    }

    @Override
    public Optional<MultipleChoiceQuestion> addChoice(Long questionId, Choice choice) {
        Optional<PollQuestion> question = this.multipleChoiceQuestionRepository.findById(questionId);
        if(question.isEmpty())
            throw new PollQuestionNotFoundException(questionId);

        MultipleChoiceQuestion choiceQuestion = (MultipleChoiceQuestion) question.get();
        choiceQuestion.addToChoices(choice);
        // todo: Check return type
        return Optional.of(this.multipleChoiceQuestionRepository.save(choiceQuestion));
    }
}

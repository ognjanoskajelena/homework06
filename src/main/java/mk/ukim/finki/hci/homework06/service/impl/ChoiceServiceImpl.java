package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Choice;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.exception.ChoiceNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollQuestionNotFoundException;
import mk.ukim.finki.hci.homework06.repository.ChoiceRepository;
import mk.ukim.finki.hci.homework06.service.ChoiceService;
import mk.ukim.finki.hci.homework06.service.PollQuestionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final PollQuestionService pollQuestionService;

    public ChoiceServiceImpl(ChoiceRepository choiceRepository, PollQuestionService pollQuestionService) {
        this.choiceRepository = choiceRepository;
        this.pollQuestionService = pollQuestionService;
    }

    @Override
    public Optional<Choice> findById(Long id) {
        return this.choiceRepository.findById(id);
    }

    @Override
    public Optional<Choice> save(String content, boolean selected, Long pollQuestionId) {
        Optional<PollQuestion> pollQuestion = this.pollQuestionService.findById(pollQuestionId);
        if(pollQuestion.isEmpty())
            throw new PollQuestionNotFoundException(pollQuestionId);

        Choice choice = new Choice(content, selected, pollQuestion.get());
        return Optional.of(this.choiceRepository.save(choice));
    }

    @Override
    public Optional<Choice> save(String content, Long pollQuestionId) {
        Optional<PollQuestion> pollQuestion = this.pollQuestionService.findById(pollQuestionId);
        if(pollQuestion.isEmpty())
            throw new PollQuestionNotFoundException(pollQuestionId);

        Choice choice = new Choice(content, pollQuestion.get());
        return Optional.of(this.choiceRepository.save(choice));
    }

    @Override
    public Optional<Choice> update(Long id, String content, boolean selected) {
        Optional<Choice> choice = this.findById(id);
        if(choice.isEmpty())
            throw new ChoiceNotFoundException(id);

        Choice updatedChoice = choice.get();
        updatedChoice.setContent(content);
        updatedChoice.setSelected(selected);

        return Optional.of(this.choiceRepository.save(updatedChoice));
    }

    @Override
    public Optional<Choice> deleteById(Long id) {
        Optional<Choice> choice = this.findById(id);
        if(choice.isEmpty())
            throw new ChoiceNotFoundException(id);
        this.choiceRepository.deleteById(id);
        return choice;
    }
}

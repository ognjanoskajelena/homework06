package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollQuestionNotFoundException;
import mk.ukim.finki.hci.homework06.repository.PollRepository;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import mk.ukim.finki.hci.homework06.service.PollQuestionService;
import mk.ukim.finki.hci.homework06.service.PollService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final InitiativeService initiativeService;
    private final PollQuestionService pollQuestionService;

    public PollServiceImpl(PollRepository pollRepository,
                           InitiativeService initiativeService,
                           PollQuestionService pollQuestionService) {
        this.pollRepository = pollRepository;
        this.initiativeService = initiativeService;
        this.pollQuestionService = pollQuestionService;
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return this.pollRepository.findById(id);
    }

    @Override
    public Optional<Poll> save(String topic, boolean isOpen, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Poll poll = new Poll(topic, isOpen, initiative.get());
        return Optional.of(this.pollRepository.save(poll));
    }

    @Override
    public Optional<Poll> save(String topic, boolean isOpen, Long initiativeId, List<Long> questionsIds) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        List<PollQuestion> pollQuestionList = new ArrayList<>();
        for (Long qId : questionsIds) {
            Optional<PollQuestion> pollQuestion = this.pollQuestionService.findById(qId);
            if(pollQuestion.isEmpty())
                throw new PollQuestionNotFoundException(qId);

            pollQuestionList.add(pollQuestion.get());
        }

        Poll poll = new Poll(topic, isOpen, pollQuestionList, initiative.get());
        return Optional.of(this.pollRepository.save(poll));
    }

    @Override
    public Optional<Poll> update(Long id, String topic, boolean isOpen) {
        Optional<Poll> poll = this.findById(id);
        if(poll.isEmpty())
            throw new PollNotFoundException(id);

        Poll updatedPoll = poll.get();
        updatedPoll.setTopic(topic);
        updatedPoll.setOpen(isOpen);

        return Optional.of(this.pollRepository.save(updatedPoll));
    }

    @Override
    public Optional<Poll> deleteById(Long id) {
        Optional<Poll> poll = this.findById(id);
        if(poll.isEmpty())
            throw new PollNotFoundException(id);
        this.pollRepository.deleteById(id);
        return poll;
    }
}

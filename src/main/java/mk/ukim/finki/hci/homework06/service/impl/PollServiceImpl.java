package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.PollQuestion;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.ParticipantNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.repository.PollRepository;
import mk.ukim.finki.hci.homework06.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final InitiativeService initiativeService;
    private final ParticipantService participantService;
    private final PollQuestionService pollQuestionService;

    public PollServiceImpl(PollRepository pollRepository, InitiativeService initiativeService,
                           ParticipantService participantService, PollQuestionService pollQuestionService) {
        this.pollRepository = pollRepository;
        this.initiativeService = initiativeService;
        this.participantService = participantService;
        this.pollQuestionService = pollQuestionService;
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return this.pollRepository.findById(id);
    }

    @Override
    public Optional<Poll> save(String topic, boolean isOpen, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if (initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Poll poll = new Poll(topic, isOpen, initiative.get());
        return Optional.of(this.pollRepository.save(poll));
    }

    @Override
    public Optional<Poll> save(String topic, boolean isOpen, Long initiativeId, List<PollQuestion> questions) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if (initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);
        /*
        List<PollQuestion> pollQuestionList = new ArrayList<>();
        for (Long qId : questionsIds) {
            Optional<PollQuestion> pollQuestion = this.pollQuestionService.findById(qId);
            if(pollQuestion.isEmpty())
                throw new PollQuestionNotFoundException(qId);

            pollQuestionList.add(pollQuestion.get());
        }
        */
        Poll poll = new Poll(topic, isOpen, questions, initiative.get());
        return Optional.of(this.pollRepository.save(poll));
    }

    @Override
    public Optional<Poll> update(Long id, String topic, boolean isOpen) {
        Optional<Poll> poll = this.findById(id);
        if (poll.isEmpty())
            throw new PollNotFoundException(id);

        Poll updatedPoll = poll.get();
        updatedPoll.setTopic(topic);
        updatedPoll.setOpen(isOpen);

        return Optional.of(this.pollRepository.save(updatedPoll));
    }

    @Override
    public Optional<Poll> deleteById(Long id) {
        Optional<Poll> poll = this.findById(id);
        if (poll.isEmpty())
            throw new PollNotFoundException(id);
        this.pollRepository.deleteById(id);
        return poll;
    }

    @Override
    public Optional<Poll> fill(Long pollId, Long participantId) {
        Optional<Poll> poll = this.findById(pollId);
        if (poll.isEmpty())
            throw new PollNotFoundException(pollId);

        Optional<Participant> participant = this.participantService.findById(participantId);
        if (participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Poll updatedPoll = poll.get();
        updatedPoll.addToParticipants(participant.get());
        return Optional.of(this.pollRepository.save(updatedPoll));
    }

    @Override
    public Optional<Poll> addQuestion(Long pollId, PollQuestion question) {
        Optional<Poll> poll = this.findById(pollId);
        if (poll.isEmpty())
            throw new PollNotFoundException(pollId);

        // todo: Check flow
        this.pollQuestionService.save(question);

        Poll updatedPoll = poll.get();
        updatedPoll.addToQuestions(question);
        return Optional.of(this.pollRepository.save(updatedPoll));
    }
}

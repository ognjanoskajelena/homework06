package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Event;
import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.exception.EventNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.ParticipantNotFoundException;
import mk.ukim.finki.hci.homework06.repository.EventRepository;
import mk.ukim.finki.hci.homework06.service.EventService;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import mk.ukim.finki.hci.homework06.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final InitiativeService initiativeService;
    private final ParticipantService participantService;

    public EventServiceImpl(EventRepository eventRepository,
                            InitiativeService initiativeService,
                            ParticipantService participantService) {
        this.eventRepository = eventRepository;
        this.initiativeService = initiativeService;
        this.participantService = participantService;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Optional<Event> save(String title, String description, String date, String time, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Event event = new Event(title, description, LocalDate.parse(date), LocalTime.parse(time), initiative.get());
        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> save(String title, String description, LocalDate date, LocalTime time, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Event event = new Event(title, description, date, time, initiative.get());
        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> save(Event event) {
        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> update(Long id, String title, String description, String date, String time) {
        Optional<Event> event = this.eventRepository.findById(id);
        if(event.isEmpty())
            throw new EventNotFoundException(id);

        Event updatedEvent = event.get();
        updatedEvent.setTitle(title);
        updatedEvent.setDescription(description);
        updatedEvent.setDate(LocalDate.parse(date));
        updatedEvent.setTime(LocalTime.parse(time));

        return Optional.of(this.eventRepository.save(updatedEvent));
    }

    @Override
    public Optional<Event> deleteById(Long id) {
        Optional<Event> event = this.eventRepository.findById(id);
        if(event.isEmpty())
            throw new EventNotFoundException(id);
        this.eventRepository.deleteById(id);
        return event;
    }

    @Override
    public Optional<Event> going(Long participantId, Long eventId) {
        Optional<Participant> participant = this.participantService.findById(participantId);
        if(participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Optional<Event> event = this.findById(eventId);
        if(event.isEmpty())
            throw new EventNotFoundException(eventId);

        Event updatedEvent = event.get();
        updatedEvent.addToGoingParticipants(participant.get());
        return Optional.of(this.eventRepository.save(updatedEvent));
    }

    @Override
    public Optional<Event> notGoing(Long participantId, Long eventId) {
        Optional<Participant> participant = this.participantService.findById(participantId);
        if(participant.isEmpty())
            throw new ParticipantNotFoundException(participantId);

        Optional<Event> event = this.findById(eventId);
        if(event.isEmpty())
            throw new EventNotFoundException(eventId);

        Event updatedEvent = event.get();
        updatedEvent.removeFromGoingParticipants(participant.get());
        return Optional.of(this.eventRepository.save(updatedEvent));
    }
}

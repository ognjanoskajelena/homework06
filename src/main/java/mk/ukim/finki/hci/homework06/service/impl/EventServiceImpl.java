package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Event;
import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.exception.EventNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.repository.EventRepository;
import mk.ukim.finki.hci.homework06.service.EventService;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final InitiativeService initiativeService;

    public EventServiceImpl(EventRepository eventRepository, InitiativeService initiativeService) {
        this.eventRepository = eventRepository;
        this.initiativeService = initiativeService;
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
}

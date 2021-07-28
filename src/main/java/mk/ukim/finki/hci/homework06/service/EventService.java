package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface EventService {
    Optional<Event> findById(Long id);

    Optional<Event> save(String title, String description, String date, String time, Long initiativeId);

    Optional<Event> save(String title, String description, LocalDate date, LocalTime time, Long initiativeId);

    Optional<Event> save(Event event);

    Optional<Event> update(Long id, String title, String description, String date, String time);

    Optional<Event> deleteById(Long id);

    Optional<Event> going(Long participantId, Long eventId);

    Optional<Event> notGoing(Long participantId, Long eventId);
}

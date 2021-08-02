package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Event;

import java.util.Optional;

public interface EventService {
    Optional<Event> findById(Long id);

    Optional<Event> save(Event event);
}

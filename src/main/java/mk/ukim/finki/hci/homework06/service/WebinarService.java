package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Webinar;

import java.util.Optional;

public interface WebinarService {
    Optional<Webinar> findById(Long id);

    Optional<Webinar> save(String topic, String description, String link, String date, String time, Long initiatorId);

    Optional<Webinar> update(Long id, String topic, String description, String link, String date, String time);

    Optional<Webinar> deleteById(Long id);

    Optional<Webinar> interested(Long webinarId, Long participantId);
}

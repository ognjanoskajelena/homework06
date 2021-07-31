package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Webinar;

import java.util.List;
import java.util.Optional;

public interface WebinarService {
    Optional<Webinar> findById(Long id);

    List<Webinar> findAll();

    Optional<Webinar> save(String topic, String description, String link, String date, String time, Long initiatorId);

    Optional<Webinar> deleteById(Long id);
}

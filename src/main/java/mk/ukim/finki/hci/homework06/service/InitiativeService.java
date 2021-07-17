package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.Initiator;

import java.time.LocalDate;
import java.util.Optional;

public interface InitiativeService {
    Optional<Initiative> findById(Long id);

    Optional<Initiative> save(String title, String description, String dateOpened, Long initiatorId);

    Optional<Initiative> update(Long id, String title, String description, String dateOpened);

    Optional<Initiative> deleteById(Long id);
}

package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.*;

import java.util.List;
import java.util.Optional;

public interface InitiativeService {
    List<Initiative> findAll();

    Optional<Initiative> findById(Long id);

    Optional<Initiative> save(String title, String description, Long initiatorId);

    Optional<Initiative> deleteById(Long id);
}

package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Initiator;

import java.util.Optional;

public interface InitiatorService {
    Optional<Initiator> findById(Long id);
}

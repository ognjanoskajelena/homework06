package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.*;

import java.util.Optional;

public interface InitiativeService {
    Optional<Initiative> findById(Long id);

    Optional<Initiative> save(String title, String description, String dateOpened, Long initiatorId);

    Optional<Initiative> update(Long id, String title, String description, String dateOpened);

    Optional<Initiative> deleteById(Long id);

    Optional<Initiative> addEvent(Long initiativeId, Event event);

    Optional<Initiative> addPoll(Long initiativeId, Poll poll);

    Optional<Initiative> addDiscussion(Long initiativeId, Discussion discussion);

    Optional<Initiative> addParticipant(Long initiativeId, Long participantId);

    Optional<Initiative> removeParticipant(Long initiativeId, Long participantId);
}

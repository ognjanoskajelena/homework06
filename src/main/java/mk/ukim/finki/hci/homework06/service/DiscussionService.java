package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Discussion;

import java.util.Optional;

public interface DiscussionService {
    Optional<Discussion> findById(Long id);

    Optional<Discussion> save(String topic, String closeDate, Long initiativeId);

    Optional<Discussion> update(Long id, String topic, String closeDate);

    Optional<Discussion> deleteById(Long id);
}

package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Discussion;

import java.util.Optional;

public interface DiscussionService {
    Optional<Discussion> findById(Long id);

    Optional<Discussion> save(Discussion discussion);
}

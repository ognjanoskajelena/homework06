package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Comment;
import mk.ukim.finki.hci.homework06.model.Discussion;

import java.time.LocalDate;
import java.util.Optional;

public interface DiscussionService {
    Optional<Discussion> findById(Long id);

    Optional<Discussion> save(String topic, String closeDate, Long initiativeId);

    Optional<Discussion> save(String topic, LocalDate closeDate, Long initiativeId);

    Optional<Discussion> save(Discussion discussion);

    Optional<Discussion> update(Long id, String topic, String closeDate);

    Optional<Discussion> deleteById(Long id);

    Optional<Discussion> addComment(Long discussionId, Comment comment);
}

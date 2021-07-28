package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findByDiscussionId(Long discussionId);

    Optional<Comment> findById(Long id);

    Optional<Comment> save(String content, String authorUsername, Long discussionId);

    Optional<Comment> deleteById(Long id);
}

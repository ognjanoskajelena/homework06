package mk.ukim.finki.hci.homework06.service;

import mk.ukim.finki.hci.homework06.model.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);

    Optional<Comment> save(String content, String datePosted, String timePosted, String authorUsername, Long discussionId);

    Optional<Comment> save(Comment comment);

    Optional<Comment> deleteById(Long id);
}

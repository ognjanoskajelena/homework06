package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Comment;
import mk.ukim.finki.hci.homework06.model.Discussion;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.model.exception.CommentNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.DiscussionNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.UserNotFoundException;
import mk.ukim.finki.hci.homework06.repository.CommentRepository;
import mk.ukim.finki.hci.homework06.service.CommentService;
import mk.ukim.finki.hci.homework06.service.DiscussionService;
import mk.ukim.finki.hci.homework06.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final DiscussionService discussionService;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserService userService,
                              DiscussionService discussionService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.discussionService = discussionService;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public Optional<Comment> save(String content, String datePosted, String timePosted, String authorUsername,
                                  Long discussionId) {
        Optional<User> author = this.userService.findByUsername(authorUsername);
        if(author.isEmpty())
            throw new UserNotFoundException(authorUsername);

        Optional<Discussion> discussion = this.discussionService.findById(discussionId);
        if(discussion.isEmpty())
            throw new DiscussionNotFoundException(discussionId);

        Comment comment = new Comment(content, author.get(), discussion.get());
        return Optional.of(this.commentRepository.save(comment));
    }

    @Override
    public Optional<Comment> save(Comment comment) {
        return Optional.of(this.commentRepository.save(comment));
    }

    @Override
    public Optional<Comment> deleteById(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(comment.isEmpty())
            throw new CommentNotFoundException(id);
        this.commentRepository.deleteById(id);
        return comment;
    }
}

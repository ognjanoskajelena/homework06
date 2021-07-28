package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Comment;
import mk.ukim.finki.hci.homework06.model.Discussion;
import mk.ukim.finki.hci.homework06.model.Initiative;
import mk.ukim.finki.hci.homework06.model.exception.DiscussionNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.InitiativeNotFoundException;
import mk.ukim.finki.hci.homework06.repository.CommentRepository;
import mk.ukim.finki.hci.homework06.repository.DiscussionRepository;
import mk.ukim.finki.hci.homework06.service.DiscussionService;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final InitiativeService initiativeService;
    private final CommentRepository commentRepository;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository,
                                 InitiativeService initiativeService,
                                 CommentRepository commentRepository) {
        this.discussionRepository = discussionRepository;
        this.initiativeService = initiativeService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Discussion> findById(Long id) {
        return this.discussionRepository.findById(id);
    }

    @Override
    public Optional<Discussion> save(String topic, String closeDate, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Discussion discussion = new Discussion(topic, LocalDate.parse(closeDate), initiative.get());
        return Optional.of(this.discussionRepository.save(discussion));
    }

    @Override
    public Optional<Discussion> save(String topic, LocalDate closeDate, Long initiativeId) {
        Optional<Initiative> initiative = this.initiativeService.findById(initiativeId);
        if(initiative.isEmpty())
            throw new InitiativeNotFoundException(initiativeId);

        Discussion discussion = new Discussion(topic, closeDate, initiative.get());
        return Optional.of(this.discussionRepository.save(discussion));
    }

    @Override
    public Optional<Discussion> save(Discussion discussion) {
        return Optional.of(this.discussionRepository.save(discussion));
    }

    @Override
    public Optional<Discussion> update(Long id, String topic, String closeDate) {
        Optional<Discussion> discussion = this.findById(id);
        if(discussion.isEmpty())
            throw new DiscussionNotFoundException(id);

        Discussion updatedDiscussion = discussion.get();
        updatedDiscussion.setTopic(topic);
        updatedDiscussion.setCloseDate(LocalDate.parse(closeDate));

        return Optional.of(this.discussionRepository.save(updatedDiscussion));
    }

    @Override
    public Optional<Discussion> deleteById(Long id) {
        Optional<Discussion> discussion = this.findById(id);
        if(discussion.isEmpty())
            throw new DiscussionNotFoundException(id);
        this.initiativeService.deleteById(id);
        return discussion;
    }

    @Override
    public Optional<Discussion> addComment(Long discussionId, Comment comment) {
        Optional<Discussion> discussion = this.findById(discussionId);
        if(discussion.isEmpty())
            throw new DiscussionNotFoundException(discussionId);

        this.commentRepository.save(comment);
        Discussion updatedDiscussion = discussion.get();
        updatedDiscussion.addToComments(comment);
        return Optional.of(this.discussionRepository.save(updatedDiscussion));
    }
}

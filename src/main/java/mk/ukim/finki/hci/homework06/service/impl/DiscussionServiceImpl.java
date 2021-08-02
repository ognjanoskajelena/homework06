package mk.ukim.finki.hci.homework06.service.impl;

import mk.ukim.finki.hci.homework06.model.Discussion;
import mk.ukim.finki.hci.homework06.repository.DiscussionRepository;
import mk.ukim.finki.hci.homework06.service.DiscussionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    @Override
    public Optional<Discussion> findById(Long id) {
        return this.discussionRepository.findById(id);
    }

    @Override
    public Optional<Discussion> save(Discussion discussion) {
        return Optional.of(this.discussionRepository.save(discussion));
    }
}

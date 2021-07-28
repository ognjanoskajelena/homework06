package mk.ukim.finki.hci.homework06.web.rest;

import mk.ukim.finki.hci.homework06.model.Comment;
import mk.ukim.finki.hci.homework06.model.dto.CommentDto;
import mk.ukim.finki.hci.homework06.model.exception.DiscussionNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.UserNotFoundException;
import mk.ukim.finki.hci.homework06.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionRestController {

    private final CommentService commentService;

    public DiscussionRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}/all-comments")
    public List<Comment> getAllComments(@PathVariable Long id) {
        return this.commentService.findByDiscussionId(id);
    }

    @PostMapping("/{id}/add-comment")
    public void addComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        try {
            this.commentService.save(commentDto.getContent(), commentDto.getAuthor(), id);
        } catch (UserNotFoundException | DiscussionNotFoundException e) {
        }
    }
}

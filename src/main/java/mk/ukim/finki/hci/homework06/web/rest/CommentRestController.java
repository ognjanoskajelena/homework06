package mk.ukim.finki.hci.homework06.web.rest;

import mk.ukim.finki.hci.homework06.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        return this.commentService.deleteById(id)
                .map(comment -> ResponseEntity.ok().body(comment))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

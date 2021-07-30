package mk.ukim.finki.hci.homework06.web.rest;

import mk.ukim.finki.hci.homework06.model.Poll;
import mk.ukim.finki.hci.homework06.model.dto.OpenQuestionDto;
import mk.ukim.finki.hci.homework06.model.dto.PollDto;
import mk.ukim.finki.hci.homework06.model.exception.ChoiceNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.PollQuestionNotFoundException;
import mk.ukim.finki.hci.homework06.model.exception.UserNotFoundException;
import mk.ukim.finki.hci.homework06.service.ChoiceService;
import mk.ukim.finki.hci.homework06.service.OpenQuestionService;
import mk.ukim.finki.hci.homework06.service.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls/")
public class PollRestController {

    private final OpenQuestionService openQuestionService;
    private final ChoiceService choiceService;
    private final PollService pollService;

    public PollRestController(OpenQuestionService openQuestionService,
                              ChoiceService choiceService,
                              PollService pollService) {
        this.openQuestionService = openQuestionService;
        this.choiceService = choiceService;
        this.pollService = pollService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return this.pollService.findById(id)
                .map(poll -> ResponseEntity.ok().body(poll))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/fill")
    public ResponseEntity fillPoll(@PathVariable Long id, @RequestBody PollDto pollDto) {
        try {
            this.pollService.fill(id, pollDto.getUsername());
            for (OpenQuestionDto o : pollDto.getOpen()) {
                this.openQuestionService.respond(o.getId(), o.getResponse());
            }
            for (Long s : pollDto.getSingle()) {
                this.choiceService.changeSelection(s);
            }
            for (Long m : pollDto.getMultiple()) {
                this.choiceService.changeSelection(m);
            }
            return ResponseEntity.ok().build();
        } catch (PollNotFoundException | UserNotFoundException |
                PollQuestionNotFoundException | ChoiceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

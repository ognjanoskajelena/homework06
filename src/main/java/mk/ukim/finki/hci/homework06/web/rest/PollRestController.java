package mk.ukim.finki.hci.homework06.web.rest;

import mk.ukim.finki.hci.homework06.model.dto.OpenQuestionDto;
import mk.ukim.finki.hci.homework06.model.dto.PollDto;
import mk.ukim.finki.hci.homework06.service.ChoiceService;
import mk.ukim.finki.hci.homework06.service.OpenQuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls/")
public class PollRestController {

    private final OpenQuestionService openQuestionService;
    private final ChoiceService choiceService;

    public PollRestController(OpenQuestionService openQuestionService,
                              ChoiceService choiceService) {
        this.openQuestionService = openQuestionService;
        this.choiceService = choiceService;
    }

    @PostMapping("/fill")
    public void fillPoll(@RequestBody PollDto pollDto) {
        for (OpenQuestionDto o : pollDto.getOpen()) {
            this.openQuestionService.respond(o.getId(), o.getResponse());
        }
        for (Long s : pollDto.getSingle()) {
            this.choiceService.changeSelection(s);
        }
        for (Long m : pollDto.getMultiple()) {
            this.choiceService.changeSelection(m);
        }
    }
}

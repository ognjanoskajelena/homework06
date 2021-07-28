package mk.ukim.finki.hci.homework06.web;

import mk.ukim.finki.hci.homework06.model.*;
import mk.ukim.finki.hci.homework06.service.CommentService;
import mk.ukim.finki.hci.homework06.service.DiscussionService;
import mk.ukim.finki.hci.homework06.service.InitiativeService;
import mk.ukim.finki.hci.homework06.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/initiatives"})
public class InitiativeController {

    private final InitiativeService initiativeService;
    private final UserService userService;
    private final DiscussionService discussionService;

    public InitiativeController(InitiativeService initiativeService,
                                UserService userService,
                                DiscussionService discussionService) {
        this.initiativeService = initiativeService;
        this.userService = userService;
        this.discussionService = discussionService;
    }

    @GetMapping
    public String getAllInitiativesPage(Model model) {
        model.addAttribute("headTitle", "Initiatives");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "initiatives.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "initiatives");

        model.addAttribute("initiatives", this.initiativeService.findAll());
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getInitiativeDetails(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Initiative> initiative = this.initiativeService.findById(id);
        if (initiative.isPresent()) {
            LocalDate today = LocalDate.now();
            for (Discussion d : initiative.get().getDiscussions()) {
                if (d.getCloseDate().isBefore(today)) {
                    d.close();
                    this.discussionService.save(d);
                }
            }

            Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
            if (optionalUser.isPresent()) {
                Participant participant = (Participant) optionalUser.get();
                model.addAttribute("user", participant);
            }

            model.addAttribute("initiative", this.initiativeService.findById(id).get());
            model.addAttribute("headTitle", "Initiative details");
            model.addAttribute("style1", "header.css");
            model.addAttribute("style2", "jumbotron.css");
            model.addAttribute("style3", "initiative.css");
            model.addAttribute("style4", "footer.css");
            model.addAttribute("bodyContent", "initiative");
            return "master-template";
        }
        return "not_found";
    }

    @GetMapping("/{id}/take-part")
    public String addInitiativeToParticipant(@PathVariable Long id, HttpServletRequest request) {
        Optional<Initiative> initiative = this.initiativeService.findById(id);
        if (initiative.isPresent()) {
            Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
            if (optionalUser.isPresent()) {
                Participant participant = (Participant) optionalUser.get();
                if (participant.getInitiatives().stream().noneMatch(i -> i.getId().equals(id))) {
                    participant.addToInitiatives(initiative.get());
                    this.userService.save(participant);
                }
            }
            return "redirect:/dashboard";
        }
        return "not_found";
    }

    @GetMapping("/{id}/remove-part")
    public String removeInitiativeFromParticipant(@PathVariable Long id, HttpServletRequest request) {
        Optional<Initiative> initiative = this.initiativeService.findById(id);
        if (initiative.isPresent()) {
            Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
            if (optionalUser.isPresent()) {
                Participant participant = (Participant) optionalUser.get();
                participant.removeFromInitiatives(initiative.get());
                this.userService.save(participant);
            }
            return "redirect:/dashboard";
        }
        return "not_found";
    }
}

package mk.ukim.finki.hci.homework06.web;

import mk.ukim.finki.hci.homework06.model.Event;
import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.service.EventService;
import mk.ukim.finki.hci.homework06.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/events"})
public class EventController {

    private final UserService userService;
    private final EventService eventService;

    public EventController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/{id}/going")
    public String goingParticipant(@PathVariable Long id, HttpServletRequest request) {
        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            Participant participant = (Participant) optionalUser.get();
            Optional<Event> event = this.eventService.findById(id);
            if (event.isPresent()) {
                if (event.get().getGoingParticipants().stream().noneMatch(p -> p.getId().equals(participant.getId()))) {
                    event.get().addToGoingParticipants(participant);
                    this.eventService.save(event.get());
                }
                return "redirect:/dashboard";
            }
            return "redirect:/not-found";
        }
        return "redirect:/login";
    }

    @GetMapping("/{id}/not-going")
    public String notGoingParticipant(@PathVariable Long id, HttpServletRequest request) {
        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            Participant participant = (Participant) optionalUser.get();
            Optional<Event> event = this.eventService.findById(id);
            if (event.isPresent()) {
                if (event.get().getGoingParticipants().stream().anyMatch(p -> p.getId().equals(participant.getId()))) {
                    event.get().removeFromGoingParticipants(participant);
                    this.eventService.save(event.get());
                }
                return "redirect:/dashboard";
            }
            return "redirect:/not-found";
        }
        return "redirect:/login";
    }
}

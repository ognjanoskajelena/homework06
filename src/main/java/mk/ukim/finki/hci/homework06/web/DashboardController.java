package mk.ukim.finki.hci.homework06.web;

import mk.ukim.finki.hci.homework06.model.Participant;
import mk.ukim.finki.hci.homework06.model.User;
import mk.ukim.finki.hci.homework06.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/dashboard"})
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getDashboardPage(HttpServletRequest request, Model model) {
        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            model.addAttribute("headTitle", "Dashboard");
            model.addAttribute("style1", "header.css");
            model.addAttribute("style2", "jumbotron.css");
            model.addAttribute("style3", "dashboard.css");
            model.addAttribute("style4", "footer.css");
            model.addAttribute("bodyContent", "dashboard");

            Participant participant = (Participant) optionalUser.get();
            model.addAttribute("user", participant);
            model.addAttribute("initiatives", participant.getInitiatives());
            model.addAttribute("events", participant.getEvents());
            return "master-template";
        }
        return "redirect:/login";
    }
}

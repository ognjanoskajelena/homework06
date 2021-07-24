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
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("headTitle", "Home");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "home.css");
        model.addAttribute("style3", "footer.css");
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping(value = {"/dashboard"})
    public String getDashboardPage(HttpServletRequest request, Model model) {
        model.addAttribute("headTitle", "Dashboard");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "dashboard.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "dashboard");

        Optional<User> optionalUser = this.userService.findByUsername(request.getRemoteUser());
        if (optionalUser.isPresent()) {
            Participant participant = (Participant) optionalUser.get();
            model.addAttribute("user", participant);
        }
        return "master-template";
    }

    @GetMapping(value = {"/initiatives"})
    public String getInitiativesPage(Model model) {
        model.addAttribute("headTitle", "Initiatives");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "initiatives.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "initiatives");
        return "master-template";
    }

    @GetMapping(value = {"/volunteer"})
    public String getVolunteerPage(Model model) {
        model.addAttribute("headTitle", "Volunteer opportunities");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "volunteer.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "volunteer");
        return "master-template";
    }

    @GetMapping(value = {"/webinars"})
    public String getWebinarsPage(Model model) {
        model.addAttribute("headTitle", "Webinars");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "footer.css");
        model.addAttribute("bodyContent", "webinars");
        return "master-template";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage() {
        return "access_denied";
    }
}

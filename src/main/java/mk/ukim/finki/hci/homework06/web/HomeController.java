package mk.ukim.finki.hci.homework06.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

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
    public String getDashboardPage(Model model) {
        model.addAttribute("headTitle", "Dashboard");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "dashboard.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "dashboard");
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
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "webinars");
        return "master-template";
    }
}

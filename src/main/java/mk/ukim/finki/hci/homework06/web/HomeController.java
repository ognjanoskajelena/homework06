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

    @GetMapping("/faq")
    public String getFaqPage(Model model) {
        model.addAttribute("headTitle", "Home");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "faq.css");
        model.addAttribute("style3", "footer.css");
        model.addAttribute("bodyContent", "faq");
        return "master-template";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {
        return "access_denied";
    }

    @GetMapping("/not-found")
    public String getNotFoundPage() {
        return "not_found";
    }
}

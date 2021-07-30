package mk.ukim.finki.hci.homework06.web;

import mk.ukim.finki.hci.homework06.service.WebinarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webinars")
public class WebinarsController {

    private final WebinarService webinarService;

    public WebinarsController(WebinarService webinarService) {
        this.webinarService = webinarService;
    }

    @GetMapping
    public String getWebinarsPage(Model model) {
        model.addAttribute("headTitle", "Webinars");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "webinars.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "webinars");
        model.addAttribute("webinars", this.webinarService.findAll());
        return "master-template";
    }
}

package mk.ukim.finki.hci.homework06.web;

import mk.ukim.finki.hci.homework06.service.VolunteerOpportunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/volunteer")
public class VolunteerOpportunityController {

    private final VolunteerOpportunityService volunteerOpportunityService;

    public VolunteerOpportunityController(VolunteerOpportunityService volunteerOpportunityService) {
        this.volunteerOpportunityService = volunteerOpportunityService;
    }

    @GetMapping
    public String getVolunteerPage(Model model) {
        model.addAttribute("headTitle", "Volunteer opportunities");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "jumbotron.css");
        model.addAttribute("style3", "volunteer.css");
        model.addAttribute("style4", "footer.css");
        model.addAttribute("bodyContent", "volunteer");
        model.addAttribute("opportunities", this.volunteerOpportunityService.findAll());
        return "master-template";
    }
}

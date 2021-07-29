package mk.ukim.finki.hci.homework06.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("headTitle", "Login Page");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "login.css");
        model.addAttribute("style3", "footer.css");
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }
}

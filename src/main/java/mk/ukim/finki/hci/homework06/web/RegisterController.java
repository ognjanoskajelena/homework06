package mk.ukim.finki.hci.homework06.web;

import mk.ukim.finki.hci.homework06.model.exception.PasswordMismatchException;
import mk.ukim.finki.hci.homework06.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.hci.homework06.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error + " Please try again.");
        }
        model.addAttribute("headTitle", "Register Page");
        model.addAttribute("style1", "header.css");
        model.addAttribute("style2", "login.css");
        model.addAttribute("style3", "footer.css");
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String email,
                           @RequestParam String birthDate) {
        try {
            this.authService.register(name, surname, username, password, repeatedPassword, email, birthDate);
            return "redirect:/login";
        } catch (UsernameAlreadyExistsException | PasswordMismatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

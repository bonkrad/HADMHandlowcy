package pl.radekbonk.HADMHandlowcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.radekbonk.HADMHandlowcy.model.User;
import pl.radekbonk.HADMHandlowcy.service.SecurityServiceImpl;
import pl.radekbonk.HADMHandlowcy.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SecurityServiceImpl securityService;

    @GetMapping(value = "/registration")
    public String registration(Model model, String errorUser, String errorPass) {
        model.addAttribute("userForm", new User());
        if (errorUser != null) {
            model.addAttribute("errorUser", "Username already exists");
        }
        if (errorPass != null) {
            model.addAttribute("errorPass", "Password Confirmation Error");
        }

        return "registration";
    }

    @PostMapping(value = "registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return ("registration");
        }
        try {
            userService.saveUser(userForm);
            securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
            return ("redirect:/");
        } catch (Exception e) {
            if (String.valueOf(e).equals("UserExist"))
                return ("redirect:/registration?errorUser");
            else
                return ("redirect:/registration?errorPass");
        }

        /*userService.saveUser(userForm);*/

       /* securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";*/
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }

        return "login";
    }


    @GetMapping(value = {"/", "handlowcy"})
    public String welcome(Model model, HttpServletRequest request) {
        User user = userService.findByUsername(request.getRemoteUser());
        model.addAttribute("User", user.getName() + " " + user.getSurname());
        return "handlowcy";
    }
}

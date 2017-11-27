package ru.ivmiit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ivmiit.forms.UserForm;
import ru.ivmiit.services.RegistrationService;
import ru.ivmiit.services.RegistrationServiceImpl;


@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping(value = "/registration")
    public String registerPage(){
        return "registration";
    }

    @PostMapping(value = "/users")
    public String registerUser(@ModelAttribute UserForm userForm){
        registrationService.registration(userForm);
        return "redirect:/success";
    }

    @GetMapping(value = "/success")
    public static String getSuccessPage(){
        return "success";
    }

    @GetMapping(value = "login")
    public static String getLoginPage(){
        return "login";
    }

    @PostMapping(value = "login")
    public static String login(){
        return "login";
    }
}

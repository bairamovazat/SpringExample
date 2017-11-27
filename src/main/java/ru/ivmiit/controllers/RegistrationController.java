package ru.ivmiit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.forms.UserForm;
import ru.ivmiit.services.RegistrationService;

import javax.mail.MessagingException;


@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @GetMapping(value = "/registration")
    public String registerPage(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registerUser(@ModelAttribute UserForm userForm){
        try {
            registrationService.registrationAndSendConfirmMail(userForm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "registration_success";
    }

    @GetMapping(value = "/confirm/{uuid}" )
    public String confirmAccount(@PathVariable String uuid){
        registrationService.confirmUser(uuid);
        return "confirm_success";
    }

//    @GetMapping(value = "/success")
//    public static String getSuccessPage(){
//        return "success";
//    }

//    @GetMapping(value = "login")
//    public static String getLoginPage(){
//        return "login";
//    }
//
//    @PostMapping(value = "login")
//    public static String login(){
//        return "login";
//    }
}

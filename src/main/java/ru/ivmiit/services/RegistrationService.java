package ru.ivmiit.services;

import ru.ivmiit.forms.UserForm;

import javax.mail.MessagingException;

public interface RegistrationService {
    void registrationAndSendConfirmMail(UserForm userForm) throws MessagingException;
    void confirmUser(String uuid) throws IllegalArgumentException;
    String sendSmsToUser(String phone, String text);
}

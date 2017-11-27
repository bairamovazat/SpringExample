package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.forms.UserForm;
import ru.ivmiit.models.User;
import ru.ivmiit.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private  UserRepository userRepository;

    @Override
    public void registration(UserForm userForm) {
        User user = User.builder()
                .name(userForm.getName())
                .login(userForm.getLogin())
                .hashPassword(encoder.encode(userForm.getPassword()))
                .email(userForm.getEmail())
                .uuid(UUID.randomUUID())
                .build();
        userRepository.save(user);

        String text = "hello";
        String email = user.getEmail();


        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setContent(text, "text/html");
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(email);
            messageHelper.setSubject("Подвтерждение регистрации в чате");
            messageHelper.setText("http://localhost:8080/confirm/" + user.getUuid().toString(), true);
        } catch (MessagingException e) {
            throw new IllegalArgumentException(e);
        }

        javaMailSender.send(message);
    }
}

package ru.ivmiit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    private UserRepository userRepository;

    @Override
    public void registrationAndSendConfirmMail(UserForm userForm) throws MessagingException {
        User user = User.builder()
                .name(userForm.getName())
                .login(userForm.getLogin())
                .hashPassword(encoder.encode(userForm.getPassword()))
                .email(userForm.getEmail())
                .uuid(UUID.randomUUID())
                .status("NOT_CONFIRMED")
                .phone(userForm.getPhone())
                .build();
        userRepository.save(user);

        String text = "Здравствуйте! Пожалуйста перейдите по ссылке, чтобы закончить регистрацию в нашем чате. "
                + "http://localhost:8080/confirm/"
                + user.getUuid().toString();
        String email = user.getEmail();
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setContent(text, "text/html");
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(email);
        messageHelper.setSubject("Подвтерждение регистрации в чате");
        messageHelper.setText(text + "Где я ?", true);

        javaMailSender.send(message);
    }

    @Override
    public void confirmUser(String id){
        UUID uuid = UUID.fromString(id);
        User user = userRepository.findOneByUuid(uuid);
        user.setStatus("CONFIRMED");
        if(user!=null) {
            userRepository.save(user);
        }
    }

    @Override
    public String sendSmsToUser(String phone, String text) {
        String login = "bairamovazat@gmail.com";
        String passwordMD5 = "2508c2f61f27a3deb80aeb092398fa8a";
        String to = phone;
        String from = "biznes";
        String type = "7";

        String request = "https://gate.smsaero.ru/send/?user=" + login +
                "&password=" + passwordMD5 +
                "&to=" + to +
                "&text=" + text +
                "&from=" + from +
                "&type=" + type;

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity = template.getForEntity(request, String.class);
        return responseEntity.toString();
    }

}

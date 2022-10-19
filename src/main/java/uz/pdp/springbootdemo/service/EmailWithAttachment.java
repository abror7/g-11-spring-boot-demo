package uz.pdp.springbootdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import uz.pdp.springbootdemo.dto.EmailDto;
import uz.pdp.springbootdemo.payload.ApiResponse;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;

//2.  XABAR BN BIRGALIKDA ATTACHMENT (FILE) YUBORADI
@Service
@RequiredArgsConstructor
public class EmailWithAttachment implements EmailService {

    private final JavaMailSender mailSender;


    @Override
    public ApiResponse sendEmail(EmailDto emailDto) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("7abror7@gmail.com");
            helper.setTo(emailDto.getTo());
            helper.setSubject(emailDto.getSubject());
            helper.setText(emailDto.getMessage(), true);
            File file = ResourceUtils.getFile("classpath:static/java.jpeg");
            helper.addAttachment("image.jpeg", file);
            Thread thread = new Thread(() -> {
                System.out.println("new thread ishladi!!!");
                mailSender.send(message);
            });
            thread.start();
            System.out.println("Asosiy thread ishini tugatdi");
            return new ApiResponse("Email with attachment sent!!", true, null);
        } catch (MessagingException e) {
            e.printStackTrace();
            // TODO: 09/09/22 throw conflict exception
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

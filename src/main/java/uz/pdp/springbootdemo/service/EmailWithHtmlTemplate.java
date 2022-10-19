package uz.pdp.springbootdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uz.pdp.springbootdemo.dto.EmailDto;
import uz.pdp.springbootdemo.payload.ApiResponse;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//3.  TEMPLATE EMAIL YUBORISH (HTML PAGE)
@Service
@RequiredArgsConstructor
@Primary
public class EmailWithHtmlTemplate implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public ApiResponse sendEmail(EmailDto emailDto) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String, Object> model = new HashMap<>();
            model.put("ism", emailDto.getName());
            model.put("message", emailDto.getMessage());

            Context context = new Context();
            context.setVariables(model);

            String html = templateEngine.process("email-template-2", context);

            helper.setFrom("7abror7@gmail.com");
            helper.setTo(emailDto.getTo());
            helper.setSubject(emailDto.getSubject());
            helper.setText(html, true);
            // If you want you can also include attachment file with html template
//            File file = ResourceUtils.getFile("classpath:static/java.jpeg");
//            helper.addAttachment("image.jpeg", file);
            
            mailSender.send(message);

            return new ApiResponse("Email with html template sent!!!", true, null);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new IllegalStateException("Some went wrong!!!");
        }
    }
}

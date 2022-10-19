package uz.pdp.springbootdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import uz.pdp.springbootdemo.dto.EmailDto;
import uz.pdp.springbootdemo.payload.ApiResponse;

// 1. ODDIY TEXT XABAR YUBORADI
@Service
@RequiredArgsConstructor
public class SimpleTextEmailService implements EmailService {

    private final MailSender mailSender;

    @Override
    public ApiResponse sendEmail(EmailDto emailDto) {
        SimpleMailMessage simpleMsg = new SimpleMailMessage();
        simpleMsg.setFrom("7abror7@gmail.com");
        simpleMsg.setTo(emailDto.getTo());
        simpleMsg.setSubject(emailDto.getSubject());
        simpleMsg.setText(emailDto.getMessage());
        mailSender.send(simpleMsg);
        return new ApiResponse("Email successfully sent!!!", true, null);
    }
}

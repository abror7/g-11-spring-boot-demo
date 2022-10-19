package uz.pdp.springbootdemo.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import uz.pdp.springbootdemo.dto.SmsDto;
import uz.pdp.springbootdemo.payload.ApiResponse;


@Service
public class SmsService {
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

    public ApiResponse sendSms(SmsDto smsDto) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber(smsDto.getTo()), // phone number to
                        "", // message servicing sid
                        smsDto.getMessage())
                .create();
        System.out.println(message.getSid());
        return new ApiResponse("Sms sent!!!", true, null);
    }
}
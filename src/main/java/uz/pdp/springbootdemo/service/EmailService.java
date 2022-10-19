package uz.pdp.springbootdemo.service;


import uz.pdp.springbootdemo.dto.EmailDto;
import uz.pdp.springbootdemo.payload.ApiResponse;

public interface EmailService {

    ApiResponse sendEmail(EmailDto emailDto);
}

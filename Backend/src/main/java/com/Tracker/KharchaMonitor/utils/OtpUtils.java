package com.Tracker.KharchaMonitor.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtils {

    @Autowired
    private JavaMailSender mailSender;

    // Generate 6 digit OTP
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Send OTP to Email
    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("KharchaMonitor OTP verification");
        message.setText("Your OTP is: "+otp);
        mailSender.send(message);
    }
}

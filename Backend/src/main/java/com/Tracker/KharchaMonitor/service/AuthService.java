package com.Tracker.KharchaMonitor.service;

import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.dto.DTO;
import com.Tracker.KharchaMonitor.utils.EmailValidator;
import com.Tracker.KharchaMonitor.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpUtils otpUtils;


    //Check if username Exists
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    //Check if Email Exists
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // find user by username
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


    // Register User with OTP verification
    public DTO register(User user) {
        if(!EmailValidator.isValidEmail(user.getEmail())) {
            return new DTO("Invalid email format.",false);
        }

        if (usernameExists(user.getUsername())) {
            return new DTO( "Username already exists.", false);
        }

        if (emailExists(user.getEmail())) {
            return new DTO("Email already exists.", false);
        }


        String otp = otpUtils.generateOtp();
        user.setOtp(otp);
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        otpUtils.sendOtp(user.getEmail(),otp);
        return new DTO("User registered. Please verify with OTP sent to your registered email.", true);
    }

    //Verify OTP
    public DTO verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return new DTO("Email not found",false);
        }

        if(!otp.equals(user.getOtp())) {
            return new DTO("Invalid OTP",false);
        }

        user.setVerified(true);
        user.setOtp(null);
        userRepository.save(user);
        return new DTO("User verified successfully.",true);
    }

    // Forgot Password (Generate Reset Token)
    public DTO forgotPassword(String email) {
        User user =userRepository.findByEmail(email);
        if(user == null){
            return new DTO("Email not found",false);
        }

        String resetToken = otpUtils.generateOtp();
        user.setResetToken(resetToken);
        userRepository.save(user);
        otpUtils.sendOtp(email, resetToken);
        return new DTO("Reset token sent to registered email.",true);
    }

    // Reset Password using reset token
    public DTO resetPassword(String email, String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if(user == null || !user.getEmail().equals(email)) {
            return new DTO("Invalid reset token",false);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);
        return new DTO("Password reset successfully.",true);
    }

    // Login user with email and password
    public DTO login(User user) {
        User foundUser = findByUsername(user.getUsername());

        if (foundUser == null || !passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return new DTO("Invalid username or password!", false);
        }
        if(!foundUser.isVerified()) {
            return new DTO("Account is not verified. Please verify via OTP.",false);
        }
        return new DTO<>("Login successful",true);
    }

}


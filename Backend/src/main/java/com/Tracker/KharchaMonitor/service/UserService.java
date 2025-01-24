package com.Tracker.KharchaMonitor.service;

import com.Tracker.KharchaMonitor.mapper.DTOMapper;
import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.dto.DTO;
import com.Tracker.KharchaMonitor.utils.EmailValidator;
import com.Tracker.KharchaMonitor.utils.OtpUtils;
import com.Tracker.KharchaMonitor.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpUtils otpUtils;

    @Autowired
    private DTOMapper dtoMapper;

    // Fetch user profile details
    public DTO<UserProfileDTO> getProfileDetails(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return new DTO<>("User not found!",false,null);
        }

        UserProfileDTO userProfile = dtoMapper.mapToUserProfileDTO(user);

        return new DTO<>("User profile fetched successfully",true,userProfile);
    }

    // Change Username
    public DTO<String> changeUsername(String currentUsername, String newUsername) {
        User user = userRepository.findByUsername(currentUsername);
        if(user == null) {
            return new DTO<>("User not found",false);
        }
        if(userRepository.findByUsername(newUsername) != null) {
            return new DTO<>("New username already taken",false);
        }
        user.setUsername(newUsername);
        userRepository.save(user);
        return new DTO<>("Username updated successfully",true);
    }

    // Change Email
    public DTO<String> changeEmail(String currentEmail, String newEmail) {
        User user = userRepository.findByEmail(currentEmail);
        if(user == null) {
            return new DTO<>("User not found",false);
        }

        if(!EmailValidator.isValidEmail(newEmail)) {
            return new DTO<>("Invalid email format.",false);
        }

        if(userRepository.findByEmail(newEmail) != null) {
            return new DTO<>("New email already in use",false);
        }

        String otp = otpUtils.generateOtp();
        user.setEmail(newEmail);
        user.setVerified(false);
        user.setOtp(otp);
        userRepository.save(user);
        otpUtils.sendOtp(newEmail,otp);
        return new DTO<>("Email updated successfully. Please verify with the OTP sent to the new email.",true);
    }

    // Change Password
    public DTO<String> changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return new DTO<>("User not found",false);
        }
        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return new DTO<>("Old password is incorrect",false);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return new DTO<>("Password Updated successfully",true);
    }



    //================== Private Helper Method ============


}

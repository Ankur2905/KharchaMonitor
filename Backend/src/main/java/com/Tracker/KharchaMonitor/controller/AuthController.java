package com.Tracker.KharchaMonitor.controller;

import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.service.AuthService;
import com.Tracker.KharchaMonitor.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    // Register user and send OTP
    @PostMapping("/signup")
    public ResponseEntity<DTO> register(@RequestBody User user) {
        DTO result = authService.register(user);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Verify OTP
    @PostMapping("/verify")
    public ResponseEntity<DTO> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        DTO result = authService.verifyOtp(email, otp);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Forgot password (Send Reset Token)
    @PostMapping("/forgot-password")
    public ResponseEntity<DTO> forgotPassword(@RequestParam String email) {
        DTO result = authService.forgotPassword(email);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Reset Password using Resent Token
    @PostMapping("/reset-password")
    public ResponseEntity<DTO> resetPassword(@RequestParam String email, @RequestParam String resetToken, @RequestParam String newPassword) {
        DTO result = authService.resetPassword(email, resetToken, newPassword);
        if(!result.success){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Login with username and Password
    @PostMapping("/login")
    public ResponseEntity<DTO<User>> login(@RequestBody User user) {
        DTO<User> result = authService.login(user);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }
}

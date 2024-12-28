package com.Tracker.KharchaMonitor.controller;

import com.Tracker.KharchaMonitor.service.UserService;
import com.Tracker.KharchaMonitor.dto.DTO;
import com.Tracker.KharchaMonitor.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    // Get User Details
    @GetMapping("/details/{username}")
    public ResponseEntity<DTO<UserProfileDTO>> fetUserDetails(@PathVariable String username) {
        DTO<UserProfileDTO> result = userService.getProfileDetails(username);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Change Username
    @PutMapping("/change-username")
    public ResponseEntity<DTO> changeUsername(@RequestParam String currentUsername, @RequestParam String newUsername) {
        DTO result = userService.changeUsername(currentUsername, newUsername);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }

    // Change Email
    @PutMapping("change-email")
    public ResponseEntity<DTO> changeEmail(@RequestParam String currentEmail, @RequestParam String newEmail) {
        DTO result = userService.changeEmail(currentEmail, newEmail);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }

    // Change Password
    @PutMapping("/change-password")
    public ResponseEntity<DTO> changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        DTO result = userService.changePassword(email, oldPassword, newPassword);
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }
}

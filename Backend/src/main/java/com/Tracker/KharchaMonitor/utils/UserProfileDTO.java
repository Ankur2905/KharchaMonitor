package com.Tracker.KharchaMonitor.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private String username;
    private String email;
    private boolean verified;

    // Constructor
    public UserProfileDTO(String username, String email, boolean verified) {
        this.username = username;
        this.email = email;
        this.verified = verified;
    }
}

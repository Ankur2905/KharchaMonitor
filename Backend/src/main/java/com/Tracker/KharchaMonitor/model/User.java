package com.Tracker.KharchaMonitor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    @Indexed(unique = true)
    @NonNull
    private String email;

    // for OTP sending
    private String otp;
    private boolean verified;
    private String resetToken;

    // Reference to Budget collection
    @DBRef(lazy = false)
    private Budget budget;

    @DBRef(lazy = false)
    private List<Transaction> transactions = new ArrayList<>();

    // Constructor
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.verified = false;
    }

}

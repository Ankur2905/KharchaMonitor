package com.Tracker.KharchaMonitor.model;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@Document(collection = "transactions")
public class Transaction {
    @Id
    private ObjectId id;
    private String username;
    private String category;
    private String type;  // income or expense
    private double amount;
    private String description;
    private LocalDateTime date;
}

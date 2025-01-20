package com.Tracker.KharchaMonitor.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NonNull;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NonNull
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;

    private String category;

    private String type;  // income or expense

    private double amount;

    private String description;

    private LocalDateTime date;
}

package com.Tracker.KharchaMonitor.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@Document(collection = "budgets")
public class Budget {

    @Id
    private ObjectId id;

    @NonNull
    private ObjectId userId;

    @NonNull
    private Double amount;

    private String description;

    @NonNull
    private LocalDateTime startDate;

    @NonNull
    private LocalDateTime endDate;
}

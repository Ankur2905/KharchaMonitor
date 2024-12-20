package com.Tracker.KharchaMonitor.utils;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
public class BudgetDTO {

    private ObjectId id;
    private String category;
    private Double amount;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

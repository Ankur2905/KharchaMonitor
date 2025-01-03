package com.Tracker.KharchaMonitor.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
public class BudgetDTO {

    private ObjectId id;
    private Double amount;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

package com.Tracker.KharchaMonitor.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Getter
@Setter
public class BudgetDTO {

    private ObjectId id;
    private Double amount;
    private LocalDate startDate;
    private LocalDate endDate;
}

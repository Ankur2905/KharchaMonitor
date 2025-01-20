package com.Tracker.KharchaMonitor.service;

import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.utils.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetAlertService {

    @Autowired
    private EmailContent emailContent;

    // Send an alert email when the spending exceeds the budget
    public void sendBudgetExceededAlert(User user, double totalSpending) {
        System.out.println("Sending budget exceeds alert to: " + user.getEmail());
        String subject = "Budget Exceeded Alert!";
        String content = emailContent.generateBudgetExceededContent(user, totalSpending);

        // Send email to the user
        emailContent.sendEmailToUser(user.getEmail(), subject, content);
    }
}

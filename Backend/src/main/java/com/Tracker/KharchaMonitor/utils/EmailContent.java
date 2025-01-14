package com.Tracker.KharchaMonitor.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailContent {

    @Autowired
    private JavaMailSender mailSender;

    // Generate the email content for the monthly summary
    public String generateMonthlySummaryContent(String username, double totalSpending, Map<String, Double> categorySummary) {
        if (totalSpending == 0) {
            return "Dear "+ username + ",\n\nNo transactions were recorded for the current month.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Dear ").append(username).append(",\n\n")
                .append("Here is your Monthly Spending Summary:\n\n")
                .append("Total Spending: ").append(totalSpending).append("\n\n")
                .append("Category-wise BreakDown:\n");

        categorySummary.forEach((category, amount) ->
                report.append(category).append(": ").append(amount).append("\n"));

        report.append("\nThank you for using KharchaMonitor!");
        return report.toString();
    }

    // Send the email with the generated content
    public void sendMonthlySummaryEmail(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}

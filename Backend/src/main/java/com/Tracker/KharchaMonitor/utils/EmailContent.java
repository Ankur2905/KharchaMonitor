package com.Tracker.KharchaMonitor.utils;

import com.Tracker.KharchaMonitor.enums.TransactionCategory;
import com.Tracker.KharchaMonitor.model.User;
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
    public String generateMonthlySummaryContent(String username, double totalSpending, Map<TransactionCategory, Double> categorySummary) {
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

    // Generate the content for the budget exceeded alert
    public String generateBudgetExceededContent(User user, double totalSpending) {
        return "Dear "+user.getUsername()+".\n\n"+
                "Your total spending has exceeded your budget.\n"+
                "Total Spending: "+ totalSpending + "\n"+
                "Your Budget: "+ user.getBudget().getAmount() +"\n\n"+
                "Please review your transactions and adjust accordingly.";
    }

    // Send the email with the generated content
    public void sendEmailToUser(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}

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
    public String generateMonthlySummaryContent(String username, double totalSpending, double totalEarnings, Map<TransactionCategory, Double> categorySummary) {
        if (totalSpending == 0 && totalEarnings == 0) {
            return "Hi " + username + ",\n\n"
                    + "We hope you're doing well!\n\n"
                    + "It looks like there were no recorded transactions for this month.\n"
                    + "Feel free to use KharchaMonitor anytime to keep track of your finances.\n\n"
                    + "Thank you for using KharchaMonitor.\n\n"
                    + "Best regards,\n"
                    + "Team KharchaMonitor";
        }

        StringBuilder report = new StringBuilder();
        report.append("Hi ").append(username).append(",\n\n")
                .append("We hope this message finds you well!\n\n")
                .append("Here's a summary of your financial activity for this month:\n\n");

        report.append("Total Income: ₹").append(String.format("%.2f", totalEarnings)).append("\n");

        // Spending
        report.append("Total Spending: ₹").append(String.format("%.2f", totalSpending)).append("\n\n");

        report.append("Spending Breakdown by Category:\n");
        categorySummary.forEach((category, amount) ->
                report.append("• ").append(category).append(": ₹").append(String.format("%.2f", amount)).append("\n"));

        report.append("\n");

        report.append("Keep tracking your finances and stay in control of your spending.\n")
                .append("Thank you for choosing KharchaMonitor!\n\n")
                .append("Warm regards,\n")
                .append("Team KharchaMonitor");

        return report.toString();
    }

    // Generate the content for the budget exceeded alert
    public String generateBudgetExceededContent(User user, double totalSpending) {
        return "Hi " + user.getUsername() + ",\n\n"
                + "We wanted to let you know that your spending for this month has exceeded your set budget.\n\n"
                + "Total Spending: ₹" + String.format("%.2f", totalSpending) + "\n"
                + "Your Budget: ₹" + String.format("%.2f", user.getBudget().getAmount()) + "\n\n"
                + "We recommend reviewing your recent transactions to stay on top of your financial goals.\n\n"
                + "If needed, you can adjust your budget settings in the KharchaMonitor app.\n\n"
                + "Thank you for using KharchaMonitor.\n\n"
                + "Best regards,\n"
                + "Team KharchaMonitor";
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

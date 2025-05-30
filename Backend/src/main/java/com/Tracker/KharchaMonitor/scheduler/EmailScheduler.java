package com.Tracker.KharchaMonitor.scheduler;

import com.Tracker.KharchaMonitor.enums.TransactionCategory;
import com.Tracker.KharchaMonitor.model.Transaction;
import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.service.TransactionService;
import com.Tracker.KharchaMonitor.utils.TransactionSummary;
import com.Tracker.KharchaMonitor.utils.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class EmailScheduler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionSummary transactionSummary;

    @Autowired
    private EmailContent emailContent;

    // Schedule task to send monthly summaries
    @Scheduled(cron = "0 0 9 1 * ?") // At 9 AM on the 1st of each month
    public void sendMonthlySummariesForAllUsers() {
        Iterable<User> users = userRepository.findAll();

        for (User user : users) {
            sendMonthlySummary(user.getUsername(), user.getEmail());
        }
    }

    // Generate and send the monthly summary for each user
    public void sendMonthlySummary(String username, String email) {
        User user = userRepository.findByUsername(username);


        List<Transaction> transactions = transactionService.getTransactionsForPastMonth(user);

        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        int year = lastMonth.getYear();
        int month = lastMonth.getMonthValue();

        double totalSpending = transactionSummary.calculateTotalMonthlySpending(transactions, year, month);
        double totalEarnings = transactionSummary.calculateTotalMonthlyIncome(transactions, year, month);
        Map<TransactionCategory, Double> categorySummary = transactionSummary.calculateCategoryWiseBreakdown(transactions, year, month);

        String content = emailContent.generateMonthlySummaryContent(username, totalSpending, totalEarnings, categorySummary);
        emailContent.sendEmailToUser(email, "Monthly Spending Summary for "+username, content);
    }

}

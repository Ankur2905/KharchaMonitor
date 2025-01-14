package com.Tracker.KharchaMonitor.utils;

import com.Tracker.KharchaMonitor.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionSummary {

    // Calculate total spending for the given transactions
    public double calculateTotalSpending(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Calculate category-wise breakdown of spending
    public Map<String, Double> calculateCategoryWiseBreakdown(List<Transaction> transactions) {
        Map<String, Double> categorySummary = new HashMap<>();

        for (Transaction transaction : transactions) {
            String category = transaction.getCategory();
            categorySummary.put(category, categorySummary.getOrDefault(category,0.0) + transaction.getAmount());
        }

        return categorySummary;
    }


}

package com.Tracker.KharchaMonitor.utils;

import com.Tracker.KharchaMonitor.enums.TransactionCategory;
import com.Tracker.KharchaMonitor.enums.TransactionType;
import com.Tracker.KharchaMonitor.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionSummary {

    // Calculate total spending for the given transactions
    public double calculateTotalMonthlySpending(List<Transaction> transactions, Integer year, Integer month) {
        LocalDate now = LocalDate.now();
        int targetYear = (year != null) ? year : now.getYear();
        int targetMonth = (month != null) ? month : now.getMonthValue();
        return transactions.stream()
                .filter(transaction -> TransactionType.EXPENSE.equals(transaction.getType()))
                .filter(transaction -> {
                    LocalDate date = transaction.getDate();
                    return date.getYear() == targetYear && date.getMonthValue() == targetMonth;
                })
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateTotalMonthlySpending(List<Transaction> transactions) {
        return calculateTotalMonthlySpending(transactions, null, null);
    }

    // Calculate total income for the given transactions
    public double calculateTotalMonthlyIncome(List<Transaction> transactions, Integer year, Integer month) {
        LocalDate now = LocalDate.now();
        int targetYear = (year != null) ? year : now.getYear();
        int targetMonth = (month != null) ? month : now.getMonthValue();

        return transactions.stream()
                .filter(transaction -> TransactionType.INCOME.equals(transaction.getType()))
                .filter(transaction -> {
                    LocalDate date = transaction.getDate();
                    return date.getYear() == targetYear && date.getMonthValue() == targetMonth;
                })
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateTotalMonthlyIncome(List<Transaction> transactions) {
        return calculateTotalMonthlyIncome(transactions, null, null);
    }

    // Calculate category-wise breakdown of spending
    public Map<TransactionCategory, Double> calculateCategoryWiseBreakdown(List<Transaction> transactions, Integer year, Integer month) {
        Map<TransactionCategory, Double> categorySummary = new HashMap<>();

        LocalDate now = LocalDate.now();
        int targetYear = (year != null) ? year : now.getYear();
        int targetMonth = (month != null) ? month : now.getMonthValue();

        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();
            if (date.getYear() == targetYear && date.getMonthValue() == targetMonth) {
                TransactionCategory category = transaction.getCategory();
                categorySummary.put(category, categorySummary.getOrDefault(category, 0.0) + transaction.getAmount());
            }
        }

        return categorySummary;
    }


}

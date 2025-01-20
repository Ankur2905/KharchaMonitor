package com.Tracker.KharchaMonitor.service;


import com.Tracker.KharchaMonitor.model.Transaction;
import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.repository.TransactionRepository;
import com.Tracker.KharchaMonitor.dto.DTO;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.utils.TransactionSummary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionSummary transactionSummary;

    @Autowired
    private BudgetAlertService budgetAlertService;

    // Create a new Transaction
    public DTO<Transaction> createTransaction(Transaction transaction) {
        Optional<User> userOptional = userRepository.findById(transaction.getUserId());

        if (userOptional.isEmpty()) {
            return  new DTO<>("User not found",false,null);
        }

        User user = userOptional.get();

        transaction.setId(new ObjectId());
        transaction.setDate(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);

        user.getTransactions().add(savedTransaction);
        userRepository.save(user);

        checkBudgetAndSendAlert(user);
        return new DTO<>("Transaction created successfully", true, savedTransaction);
    }

    // Retrieve a transaction by ID
    public DTO<Transaction> getTransactionById(ObjectId id) {
        return transactionRepository.findById(id)
                .map(transaction -> new DTO<>("Transaction found",true,transaction))
                .orElse(new DTO<>("Transaction not found", false, null));
    }

    // Retrieve all transaction for a user
    public DTO<List<Transaction>> getAllTransaction(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new DTO<>("User not found",false,null);
        }

        List<Transaction> transactions = transactionRepository.findByUserId(user.getId());

        if(transactions.isEmpty()) {
            return new DTO<>("No transaction found",false,null);
        }
        return new DTO<>("Transaction found",true,transactions);
    }

    // Update a transaction
    public DTO<Transaction> updateTransaction(ObjectId id, Transaction updatedTransaction) {
        Optional<Transaction> existingTransactionOptional = transactionRepository.findById(id);
        if (existingTransactionOptional.isEmpty()) {
            return  new DTO<>("Transaction not found",false,null);
        }

        Transaction existingTransaction = existingTransactionOptional.get();
        existingTransaction.setCategory(updatedTransaction.getCategory());
        existingTransaction.setType(updatedTransaction.getType());
        existingTransaction.setAmount(updatedTransaction.getAmount());
        existingTransaction.setDescription(updatedTransaction.getDescription());
        existingTransaction.setDate(updatedTransaction.getDate());
        Transaction savedTransaction = transactionRepository.save(existingTransaction);

        Optional<User> userOptional = userRepository.findById(existingTransaction.getUserId());
        userOptional.ifPresent(this::checkBudgetAndSendAlert);

        return new DTO<>("Transaction updated successfully",true,savedTransaction);

    }

    // Delete a transaction
    public DTO<Void> deleteTransaction(ObjectId id) {
        if (!transactionRepository.existsById(id)) {
            return new DTO<>("Transaction not found",false,null);
        }

        transactionRepository.deleteById(id);
        return new DTO<>("Transaction deleted Successfully",true,null);
    }

    // Filter transactions
    public DTO<List<Transaction>> filterTransaction(String username, String category, String type, LocalDateTime startDate, LocalDateTime endDate) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new DTO<>("User not found",false,null);
        }

        List<Transaction> transactions;

        if (category != null && type != null && startDate != null && endDate != null) {
            transactions = transactionRepository.findByUserIdAndCategoryAndTypeAndDateBetween(user.getId(), category, type, startDate, endDate);
        } else if (category != null && type != null) {
            transactions = transactionRepository.findByUserIdAndCategoryAndType(user.getId(), category, type);
        } else if (startDate != null && endDate != null) {
            transactions = transactionRepository.findByUserIdAndDateBetween(user.getId(), startDate, endDate);
        } else if (category != null) {
            transactions = transactionRepository.findByUserIdAndCategory(user.getId(), category);
        } else if (type != null) {
            transactions = transactionRepository.findByUserIdAndType(user.getId(), type);
        } else {
            transactions = transactionRepository.findByUserId(user.getId());
        }

        if(transactions.isEmpty()) {
            return new DTO<>("No transactions found for the given filters",false,null);
        }
        return new DTO<>("Transactions found",true,transactions);
    }

    // Fetch transactions for the past month
    public List<Transaction> getTransactionsForPastMonth(User user) {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);

        return transactionRepository.findByUserIdAndDateBetween(user.getId(), startOfMonth, endOfMonth);
    }

    // Check if the total spending exceeds the user's budget
    private void checkBudgetAndSendAlert(User user) {
        if(user != null && user.getBudget() != null) {
            // Fetch the user's budget
            double userBudget = user.getBudget().getAmount();

            // Fetch al transactions for the user
            List<Transaction> transactions = transactionRepository.findByUserId(user.getId());

            // Calculate total spending
            double totalSpending = transactionSummary.calculateTotalSpending(transactions);

            if (totalSpending > userBudget) {
                // Send budget alert if the spending exceeds the budget
                budgetAlertService.sendBudgetExceededAlert(user, totalSpending);
            }
        }
    }
}
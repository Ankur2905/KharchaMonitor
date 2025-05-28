package com.Tracker.KharchaMonitor.service;


import com.Tracker.KharchaMonitor.enums.TransactionType;
import com.Tracker.KharchaMonitor.model.Transaction;
import com.Tracker.KharchaMonitor.model.User;
import com.Tracker.KharchaMonitor.repository.TransactionRepository;
import com.Tracker.KharchaMonitor.dto.DTO;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.utils.TransactionSummary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        User user = findUserById(transaction.getUserId());

        if (user == null) {
            return  new DTO<>("User not found",false,null);
        }

        transaction.setId(new ObjectId());
        if(transaction.getDate() == null) {
            transaction.setDate(LocalDate.now());
        }
        Transaction savedTransaction = transactionRepository.save(transaction);

        user.getTransactions().add(savedTransaction);
        userRepository.save(user);

        checkBudgetAndSendAlert(user, transaction);
        return new DTO<>("Transaction created successfully", true, savedTransaction);
    }

    // Retrieve a transaction by ID
    public DTO<Transaction> getTransactionById(ObjectId id) {
        return transactionRepository.findById(id)
                .map(transaction -> new DTO<>("Transaction found",true,transaction))
                .orElse(new DTO<>("Transaction not found", false, null));
    }

    // Retrieve all transaction for a user
    public DTO<List<Transaction>> getAllTransaction(String username, int page, int size) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new DTO<>("User not found",false,null);
        }

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Transaction> transactionsPage = transactionRepository.findByUserId(user.getId(), pageRequest);

        if(transactionsPage.isEmpty()) {
            return new DTO<>("No transaction found",false,null);
        }
        return new DTO<>("Transaction found",true,transactionsPage.getContent());
    }

    // Update a transaction
    public DTO<Transaction> updateTransaction(ObjectId id, Transaction updatedTransaction) {
        Transaction existingTransaction = findTransactionById(id);
        if (existingTransaction == null) {
            return  new DTO<>("Transaction not found",false,null);
        }

        updateTransactionDetails(existingTransaction, updatedTransaction);
        Transaction savedTransaction = transactionRepository.save(existingTransaction);

        User user = findUserById(existingTransaction.getUserId());
        if (user != null) {
            checkBudgetAndSendAlert(user, updatedTransaction);
        }

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

    // Filter transactions with Pagination
    public DTO<List<Transaction>> filterTransaction(String username, String category, String type, LocalDate startDate, LocalDate endDate, int page, int size) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new DTO<>("User not found",false,null);
        }

        // Create PageRequest for pagination
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Transaction> transactionsPage = filterTransactions(user.getId(), category, type, startDate, endDate, pageRequest);

        if(transactionsPage.isEmpty()) {
            return new DTO<>("No transactions found for the given filters",false,null);
        }
        return new DTO<>("Transactions found",true,transactionsPage.getContent());
    }

    // Get total income and total spending
    public double[] getDetails(String username) {
        User user = userRepository.findByUsername(username);

        // Fetch al transactions for the user
        List<Transaction> transactions = transactionRepository.findByUserId(user.getId());

        double userBudget = user.getBudget().getAmount();

        // Calculate total spending
        double totalSpending = transactionSummary.calculateTotalMonthlySpending(transactions);

        // Calculate total income
        double totalIncome = transactionSummary.calculateTotalMonthlyIncome(transactions);

        return new double[]{userBudget, totalIncome, totalSpending};
    }

    // Fetch transactions for the past month
    public List<Transaction> getTransactionsForPastMonth(User user) {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1);

        return transactionRepository.findByUserIdAndDateBetween(user.getId(), startOfMonth, endOfMonth);
    }

    // Check if the total spending exceeds the user's budget
    private void checkBudgetAndSendAlert(User user, Transaction newTransaction) {
        if(user != null && user.getBudget() != null && newTransaction.getType() == TransactionType.EXPENSE) {
            // Fetch the user's budget
            double userBudget = user.getBudget().getAmount();

            // Fetch al transactions for the user
            List<Transaction> transactions = transactionRepository.findByUserId(user.getId());

            // Calculate total spending
            double totalSpending = transactionSummary.calculateTotalMonthlySpending(transactions);

            if (totalSpending > userBudget) {
                // Send budget alert if the spending exceeds the budget
                budgetAlertService.sendBudgetExceededAlert(user, totalSpending);
            }
        }
    }


    //=============Private Helper Method============

    private User findUserById(ObjectId userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    private Transaction findTransactionById(ObjectId transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        return transactionOptional.orElse(null);
    }

    private void updateTransactionDetails(Transaction existingTransaction, Transaction updatedTransaction) {
        existingTransaction.setCategory(updatedTransaction.getCategory());
        existingTransaction.setType(updatedTransaction.getType());
        existingTransaction.setAmount(updatedTransaction.getAmount());
        existingTransaction.setDescription(updatedTransaction.getDescription());
        existingTransaction.setDate(updatedTransaction.getDate());
    }

    private Page<Transaction> filterTransactions(ObjectId userId, String category, String type, LocalDate startDate, LocalDate endDate, PageRequest pageRequest) {
        if (category != null && type != null && startDate != null && endDate != null) {
            return transactionRepository.findByUserIdAndCategoryAndTypeAndDateBetween(userId, category, type, startDate, endDate, pageRequest);
        } else if (category != null && type != null) {
            return transactionRepository.findByUserIdAndCategoryAndType(userId, category, type, pageRequest);
        } else if (startDate != null && endDate != null) {
            return transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate, pageRequest);
        } else if (category != null) {
            return transactionRepository.findByUserIdAndCategory(userId, category, pageRequest);
        } else if (type != null) {
            return transactionRepository.findByUserIdAndType(userId, type, pageRequest);
        } else {
            return transactionRepository.findByUserId(userId, pageRequest);
        }
    }
}
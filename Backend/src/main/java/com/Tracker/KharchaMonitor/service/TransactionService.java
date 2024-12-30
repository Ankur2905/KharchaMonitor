package com.Tracker.KharchaMonitor.service;


import com.Tracker.KharchaMonitor.model.Transaction;
import com.Tracker.KharchaMonitor.repository.TransactionRepository;
import com.Tracker.KharchaMonitor.dto.DTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired TransactionRepository transactionRepository;

    // Create a new Transaction
    public DTO<Transaction> createTransaction(Transaction transaction) {
        transaction.setId(new ObjectId());
        transaction.setDate(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return new DTO<>("Transaction created successfully", true, savedTransaction);
    }

    // Retrieve a transaction by ID
    public DTO<Transaction> getTransactionById(ObjectId id) {
        return transactionRepository.findById(id)
                .map(transaction -> new DTO<>("Transaction found",true,transaction))
                .orElse(new DTO<>("Transaction not found", false, null));
    }

    // Retrieve all transaction for a user
    public List<Transaction> getAllTransaction(String username) {
        return transactionRepository.findByUsername(username);
    }

    // Update a transaction
    public DTO<Transaction> updateTransaction(ObjectId id, Transaction updatedTransaction) {
        if (!transactionRepository.existsById(id)) {
            return new DTO<>("Transaction not found",false,null);
        }

        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if(existingTransaction != null) {
            existingTransaction.setCategory(updatedTransaction.getCategory());
            existingTransaction.setType(updatedTransaction.getType());
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setDescription(updatedTransaction.getDescription());
            existingTransaction.setDate(updatedTransaction.getDate());
            Transaction savedTransaction = transactionRepository.save(existingTransaction);
            return new DTO<>("Transaction updated successfully",true,savedTransaction);
        }

        return  new DTO<>("Transaction update failed",false,null);
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
    public List<Transaction> filterTransaction(String username, String category, String type, LocalDateTime startDate, LocalDateTime endDate) {
        if (category != null && type != null && startDate != null && endDate != null) {
            return transactionRepository.findByUsernameAndCategoryAndTypeAndDateBetween(username, category, type, startDate, endDate);
        }
        else if (category != null && type != null) {
            return transactionRepository.findByUsernameAndCategoryAndType(username, category, type);
        }
        else if (startDate != null && endDate != null) {
            return transactionRepository.findByUsernameAndDateBetween(username, startDate, endDate);
        }
        else if (category != null) {
            return transactionRepository.findByUsernameAndCategory(username, category);
        }
        else if (type != null) {
            return transactionRepository.findByUsernameAndType(username, type);
        }

        return transactionRepository.findByUsername(username);
    }
}

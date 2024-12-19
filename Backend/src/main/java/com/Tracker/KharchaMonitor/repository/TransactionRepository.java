package com.Tracker.KharchaMonitor.repository;

import com.Tracker.KharchaMonitor.model.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {

    List<Transaction> findByUsername(String username);

    List<Transaction> findByUsernameAndCategory(String username, String category);

    List<Transaction> findByUsernameAndType(String username, String type);

    List<Transaction> findByUsernameAndDateBetween(String username, LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByUsernameAndCategoryAndType(String username, String category, String type);

    List<Transaction> findByUsernameAndCategoryAndTypeAndDateBetween(String username, String category, String type, LocalDateTime startDate, LocalDateTime endDate);
}

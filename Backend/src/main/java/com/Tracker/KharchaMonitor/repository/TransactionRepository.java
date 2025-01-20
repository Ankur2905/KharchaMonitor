package com.Tracker.KharchaMonitor.repository;

import com.Tracker.KharchaMonitor.model.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {

    List<Transaction> findByUserId(ObjectId userId);

    List<Transaction> findByUserIdAndCategory(ObjectId userId, String category);

    List<Transaction> findByUserIdAndType(ObjectId userId, String type);

    List<Transaction> findByUserIdAndDateBetween(ObjectId userId, LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByUserIdAndCategoryAndType(ObjectId userId, String category, String type);

    List<Transaction> findByUserIdAndCategoryAndTypeAndDateBetween(ObjectId userId, String category, String type, LocalDateTime startDate, LocalDateTime endDate);

}

package com.Tracker.KharchaMonitor.repository;

import com.Tracker.KharchaMonitor.model.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {

    List<Transaction> findByUserId(ObjectId userId);

    Page<Transaction> findByUserId(ObjectId userId, Pageable pageable);

    Page<Transaction> findByUserIdAndCategory(ObjectId userId, String category, Pageable pageable);

    Page<Transaction> findByUserIdAndType(ObjectId userId, String type, Pageable pageable);

    Page<Transaction> findByUserIdAndDateBetween(ObjectId userId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<Transaction> findByUserIdAndDateBetween(ObjectId userId, LocalDate startDate, LocalDate endDate);

    Page<Transaction> findByUserIdAndCategoryAndType(ObjectId userId, String category, String type, Pageable pageable);

    Page<Transaction> findByUserIdAndCategoryAndTypeAndDateBetween(ObjectId userId, String category, String type, LocalDate startDate, LocalDate endDate, Pageable pageable);

}

package com.Tracker.KharchaMonitor.repository;

import com.Tracker.KharchaMonitor.model.Budget;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BudgetRepository extends MongoRepository<Budget, ObjectId> {
    List<Budget> findByUserId(ObjectId userId);
}

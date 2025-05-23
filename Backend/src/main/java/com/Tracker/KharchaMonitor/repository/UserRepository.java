package com.Tracker.KharchaMonitor.repository;

import com.Tracker.KharchaMonitor.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByResetToken(String resetToken);
}

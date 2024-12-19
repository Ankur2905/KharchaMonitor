package com.Tracker.KharchaMonitor.controller;

import com.Tracker.KharchaMonitor.model.Transaction;
import com.Tracker.KharchaMonitor.service.TransactionService;
import com.Tracker.KharchaMonitor.utils.DTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping
    public ResponseEntity<DTO<Transaction>> createTransaction(@RequestBody Transaction transaction) {
        DTO<Transaction> result = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DTO<Transaction>> getTransactionById(@PathVariable String id) {
        DTO<Transaction> result = transactionService.getTransactionById(new ObjectId(id));
        if (!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable String username) {
        return ResponseEntity.ok(transactionService.getAllTransaction(username));
    }


    @PutMapping("/{id}")
    public ResponseEntity<DTO<Transaction>> updateTransaction(@PathVariable String id, @RequestBody Transaction updatedTransaction) {
        DTO<Transaction> result = transactionService.updateTransaction(new ObjectId(id), updatedTransaction);
        if (!result.success) {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DTO<Void>> deleteTransaction(@PathVariable String id) {
        DTO<Void> result = transactionService.deleteTransaction(new ObjectId(id));
        if(!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/filter/{username}")
    public ResponseEntity<List<Transaction>> filterTransactions(@PathVariable String username, @RequestParam(required = false) String category, @RequestParam(required = false) String type, @RequestParam(required = false) LocalDateTime startDate, @RequestParam(required = false) LocalDateTime endDate) {
        return ResponseEntity.ok(transactionService.filterTransaction(username, category, type, startDate, endDate));
    }

}

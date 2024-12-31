package com.Tracker.KharchaMonitor.controller;


import com.Tracker.KharchaMonitor.model.Budget;
import com.Tracker.KharchaMonitor.service.BudgetService;
import com.Tracker.KharchaMonitor.dto.BudgetDTO;
import com.Tracker.KharchaMonitor.dto.DTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    //Create Budget
    @PostMapping
    public ResponseEntity<DTO> createBudget(@RequestBody Budget budget) {
        DTO result = budgetService.createBudget(budget);
        if (!result.success) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    // Get Budget for a User by User ID
    @GetMapping("/{userId}")
    public ResponseEntity<DTO<List<BudgetDTO>>> getBudgetByUserId(@PathVariable String userId) {
        ObjectId userObjectId = new ObjectId(userId); // Convert String ID to ObjectId
        DTO<List<BudgetDTO>> result = budgetService.getBudgetByUserId(userObjectId);

        if(!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Update Budget
    @PutMapping("/{id}")
    public ResponseEntity<DTO> updateBudget(@PathVariable String id, @RequestBody Budget updatedBudget) {
        ObjectId budgetId = new ObjectId(id); // Convert String ID to ObjectId
        DTO result = budgetService.updateBudget(budgetId, updatedBudget);
        if (!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


    // Delete Budget
    @DeleteMapping("/{id}")
    public ResponseEntity<DTO> deleteBudget(@PathVariable String id) {
        ObjectId budgetId = new ObjectId(id);
        DTO result = budgetService.deleteBudget(budgetId);
        if (!result.success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}

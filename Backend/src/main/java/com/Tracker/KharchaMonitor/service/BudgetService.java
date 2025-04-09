package com.Tracker.KharchaMonitor.service;

import com.Tracker.KharchaMonitor.mapper.DTOMapper;
import com.Tracker.KharchaMonitor.model.Budget;
import com.Tracker.KharchaMonitor.repository.BudgetRepository;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.dto.BudgetDTO;
import com.Tracker.KharchaMonitor.dto.DTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOMapper dtoMapper;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }


    // Create Default Budget
    public Budget createDefaultBudget(ObjectId userId) {
        validateUserExists(userId);

        Budget defaultBudget = new Budget();
        defaultBudget.setUserId(userId);
        defaultBudget.setAmount(0.0);
        defaultBudget.setStartDate(LocalDate.now());

        //set end date to the last day of the current month
        YearMonth currentMonth = YearMonth.now();
        LocalDate endMonth = currentMonth.atEndOfMonth();
        defaultBudget.setEndDate(endMonth);

        return budgetRepository.save(defaultBudget);
    }


    // Get Budgets by User ID
    public DTO<BudgetDTO> getBudgetByUserId(ObjectId userId) {
        validateUserExists(userId);

        Optional<Budget> optionalBudget = budgetRepository.findByUserId(userId).stream().findFirst();
        if (optionalBudget.isEmpty()) {
            return new DTO<>("No budget found for the given user",false,null);
        }

        Budget budget = optionalBudget.get();
        BudgetDTO budgetDTO = dtoMapper.mapToBudgetDTO(budget);

        return new DTO<>("Budget retrieved successfully",true,budgetDTO);
    }


    // Update Budget
    public DTO<String> updateBudget(ObjectId budgetId, Budget updatedBudget) {
        if (updatedBudget == null) {
            throw new IllegalArgumentException("Updated budget data cannot be null");
        }

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        validateBudgetDates(updatedBudget);

        budget.setAmount(updatedBudget.getAmount());
        budget.setStartDate(updatedBudget.getStartDate());
        budget.setEndDate(updatedBudget.getEndDate());
        budgetRepository.save(budget);

        return new DTO<>("Budget updated successfully.", true);
    }


    // Delete Budget
    public DTO<String> deleteBudget(ObjectId budgetId) {
        if (!budgetRepository.existsById(budgetId)) {
            return new DTO<>("Budget not found",false);
        }

        budgetRepository.deleteById(budgetId);
        return new DTO<>("Budget deleted successfully",true);
    }



    // ============== Private Helper Methods ===============

    // Validates that the user ID exists
    private  void validateUserExists(ObjectId userId) {
        if (userId == null || !userRepository.existsById(userId)) {
            throw  new IllegalArgumentException("Invalid or non-existing user ID.");
        }
    }

    // Validates the Budget dates
    private void validateBudgetDates(Budget budget) {
        if (budget.getEndDate().isBefore(budget.getStartDate())) {
            throw new IllegalArgumentException("End date must be after the start date.");
        }
    }
}

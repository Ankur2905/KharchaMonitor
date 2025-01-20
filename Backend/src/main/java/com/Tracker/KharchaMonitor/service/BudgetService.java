package com.Tracker.KharchaMonitor.service;

import com.Tracker.KharchaMonitor.model.Budget;
import com.Tracker.KharchaMonitor.repository.BudgetRepository;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.dto.BudgetDTO;
import com.Tracker.KharchaMonitor.dto.DTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;


    // Create Default Budget
    public Budget createDefaultBudget(ObjectId userId) {
        validateUserExists(userId);

        Budget defaultBudget = new Budget();
        defaultBudget.setUserId(userId);
        defaultBudget.setAmount(0.0);
        defaultBudget.setStartDate(LocalDateTime.now());

        //set end date to the last day of the current month
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime endMonth = currentMonth.atEndOfMonth().atTime(23,59,59);
        defaultBudget.setEndDate(endMonth);

        return budgetRepository.save(defaultBudget);
    }


    // Get Budgets by User ID
    public DTO<BudgetDTO> getBudgetByUserId(ObjectId userId, int page, int size) {
        validateUserExists(userId);

        Optional<Budget> optionalBudget = budgetRepository.findByUserId(userId).stream().findFirst();
        if (optionalBudget.isEmpty()) {
            return new DTO<>("No budget found for the given user",false,null);
        }

        Budget budget = optionalBudget.get();
        BudgetDTO budgetDTO = mapToBudgetDTO(budget);

        return new DTO<>("Budget retrieved successfully",true,budgetDTO);
    }


    // Update Budget
    public DTO updateBudget(ObjectId budgetId, Budget updatedBudget) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("Budget not found"));

        validateBudgetDates(updatedBudget);

        budget.setAmount(updatedBudget.getAmount());
        budget.setDescription(updatedBudget.getDescription());
        budget.setStartDate(updatedBudget.getStartDate());
        budget.setEndDate(updatedBudget.getEndDate());
        budgetRepository.save(budget);

        return new DTO<>("Budget updated successfully.", true);
    }


    // Delete Budget
    public DTO deleteBudget(ObjectId budgetId) {
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

    // Maps a Budget entity to a BudgetDTO
    private BudgetDTO mapToBudgetDTO(Budget budget) {
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setId(budget.getId());
        budgetDTO.setAmount(budget.getAmount());
        budgetDTO.setDescription(budget.getDescription());
        budgetDTO.setStartDate(budget.getStartDate());
        budgetDTO.setEndDate(budget.getEndDate());
        return budgetDTO;
    }
}

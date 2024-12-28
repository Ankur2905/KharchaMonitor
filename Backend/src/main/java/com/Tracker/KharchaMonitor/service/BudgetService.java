package com.Tracker.KharchaMonitor.service;

import com.Tracker.KharchaMonitor.model.Budget;
import com.Tracker.KharchaMonitor.repository.BudgetRepository;
import com.Tracker.KharchaMonitor.repository.UserRepository;
import com.Tracker.KharchaMonitor.dto.BudgetDTO;
import com.Tracker.KharchaMonitor.dto.DTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;


    // Create Budget
    public com.Tracker.KharchaMonitor.dto.DTO createBudget(Budget budget) {
        if (!ObjectId.isValid(String.valueOf(budget.getUserId())) || !userRepository.existsById(new ObjectId(String.valueOf(budget.getUserId())))) {
            return new DTO<>("Invalid or non- existing userId.", false);
        }

        if(budget.getEndDate().isBefore(budget.getStartDate())) {
            return new DTO<>("End date must be after start date",false);
        }

        budgetRepository.save(budget);
        return new DTO<>("Budget created successfully.",true);
    }


    // Get Budgets by User ID
    public List<BudgetDTO> getBudgetByUserId(ObjectId userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);
        return budgets.stream()
                .map(b -> {
                    BudgetDTO dto = new BudgetDTO();
                    dto.setId(b.getId());
                    dto.setCategory(b.getCategory());
                    dto.setAmount(b.getAmount());
                    dto.setDescription(b.getDescription());
                    dto.setStartDate(b.getStartDate());
                    dto.setEndDate(b.getEndDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    // Update Budget
    public com.Tracker.KharchaMonitor.dto.DTO updateBudget(ObjectId id, Budget updatedBudget) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            budget.setCategory(updatedBudget.getCategory());
            budget.setAmount(updatedBudget.getAmount());
            budget.setDescription(updatedBudget.getDescription());
            budget.setStartDate(updatedBudget.getStartDate());
            budget.setEndDate(updatedBudget.getEndDate());
            budgetRepository.save(budget);
            return new DTO<>("Budget updated successfully",true);
        }
        return new DTO<>("Budget not found",false);
    }



    // Delete Budget
    public com.Tracker.KharchaMonitor.dto.DTO deleteBudget(ObjectId id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return  new DTO<>("Budget deleted successfully",true);
        }
        return new DTO<>("Budget not found",false);
    }
}

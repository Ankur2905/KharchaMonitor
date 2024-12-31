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
    public DTO createBudget(Budget budget) {
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
    public DTO<List<BudgetDTO>> getBudgetByUserId(ObjectId userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);

        if(budgets.isEmpty()) {
            return new DTO<>("No budgets found for the given user.",false,null);
        }

        List<BudgetDTO> budgetDTOS = budgets.stream()
                .map(b -> {
                    BudgetDTO dto = new BudgetDTO();
                    dto.setId(b.getId());
                    dto.setAmount(b.getAmount());
                    dto.setDescription(b.getDescription());
                    dto.setStartDate(b.getStartDate());
                    dto.setEndDate(b.getEndDate());
                    return dto;
                })
                .collect(Collectors.toList());

        return new DTO<>("Budgets retrieved successfully.",true,budgetDTOS);
    }


    // Update Budget
    public DTO updateBudget(ObjectId id, Budget updatedBudget) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
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
    public DTO deleteBudget(ObjectId id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return  new DTO<>("Budget deleted successfully",true);
        }
        return new DTO<>("Budget not found",false);
    }
}

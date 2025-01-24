package com.Tracker.KharchaMonitor.mapper;

import com.Tracker.KharchaMonitor.dto.BudgetDTO;
import com.Tracker.KharchaMonitor.dto.UserProfileDTO;
import com.Tracker.KharchaMonitor.model.Budget;
import com.Tracker.KharchaMonitor.model.User;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    public BudgetDTO mapToBudgetDTO(Budget budget) {
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setId(budget.getId());
        budgetDTO.setAmount(budget.getAmount());
        budgetDTO.setDescription(budget.getDescription());
        budgetDTO.setStartDate(budget.getStartDate());
        budgetDTO.setEndDate(budget.getEndDate());
        return budgetDTO;
    }

    public UserProfileDTO mapToUserProfileDTO(User user) {
        return new UserProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.isVerified()
        );
    }
}

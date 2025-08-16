package com.Tracker.KharchaMonitor.dto;

import com.Tracker.KharchaMonitor.model.Transaction;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class PaginatedTransactionDTO {
    private List<Transaction> transactions;
    private long totalElements;
    private int totalPage;
    private int activePage;

    public PaginatedTransactionDTO(List<Transaction> transactions, long totalElements, int totalPage, int activePage) {
        this.transactions = transactions;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
        this.activePage = activePage;
    }

    public int getActivePage() {
        return activePage + 1;
    }
}

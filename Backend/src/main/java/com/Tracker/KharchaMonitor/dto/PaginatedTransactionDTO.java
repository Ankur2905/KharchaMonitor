package com.Tracker.KharchaMonitor.dto;

import com.Tracker.KharchaMonitor.model.Transaction;

import java.util.List;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getActivePage() {
        return activePage + 1;
    }
}

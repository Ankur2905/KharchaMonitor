package com.Tracker.KharchaMonitor.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTO<T> {
    public String message;
    public Boolean success;
    private T data;

    public DTO(String message, Boolean success){
        this.message = message;
        this.success = success;
    }

    public DTO(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
}


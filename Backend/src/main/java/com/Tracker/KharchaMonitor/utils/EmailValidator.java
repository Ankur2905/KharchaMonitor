package com.Tracker.KharchaMonitor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    // Regular expression to validate email format
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+([._%+-]*[A-Za-z0-9])*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


    // Method  to check if the email matches the regex
    public static boolean isValidEmail(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

package com.example.logisticcompany.util;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidNegativeOrZeroValue;
import com.example.logisticcompany.exceptions.InvalidPasswordCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class Validation {

    public static boolean validateEmail(String email) throws InvalidEmailCharacters {
        Pattern pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.find();

        if (matchFound) {
            return true;
        }

        throw new InvalidEmailCharacters("The typed email has invalid characters!");
    }

    public static boolean validatePositiveValue(BigDecimal bd) throws InvalidNegativeOrZeroValue {
        if (bd.compareTo(BigDecimal.valueOf(0)) > 0) {
            return true;
        }

        throw new InvalidNegativeOrZeroValue("The typed value is invalid because it is either negative or zero!");
    }

    public static boolean validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberCharacters {
        Pattern pattern = Pattern.compile("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean matchFound = matcher.find();

        if (matchFound) {
            return true;
        }

        throw new InvalidPhoneNumberCharacters("The typed phone number has invalid characters!");
    }

    public static boolean validatePassword(String password) throws InvalidPasswordCharacters {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        boolean matchFound = matcher.find();

        if (matchFound) {
            return true;
        }

        throw new InvalidPasswordCharacters("The typed password has invalid symbols!");
    }

    public static boolean checkIfNullOrEmpty(String input) {
        return input == null || input.trim().equals("");
    }
}

package com.example.jsfcrud.utils;

import com.example.jsfcrud.entities.UserEntity;
import com.example.jsfcrud.exception.InvalidAgeException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class UserValidationUtils {
    public static boolean isUserAbove18(UserEntity userEntity) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = userEntity.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();

        if (age <= 18) {
            throw new InvalidAgeException("User must be above 18 years old.");
        }

        return true;
    }
}

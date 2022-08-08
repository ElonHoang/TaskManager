package com.example.taskmanager.model.ulti;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterValid implements ConstraintValidator<CharacterAnno, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        try {
            if(value.length() == 0){
                return true;
            }
            if (!matcher.matches()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(CharacterAnno constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}

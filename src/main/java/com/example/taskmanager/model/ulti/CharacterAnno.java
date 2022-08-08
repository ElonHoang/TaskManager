package com.example.taskmanager.model.ulti;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CharacterValid.class)
@Documented
public @interface CharacterAnno {
    String message() default "{CharacterValid.isValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

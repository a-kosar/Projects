package tpo.project.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ValidColoursValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidColours {
    String message() default "Invalid colours.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

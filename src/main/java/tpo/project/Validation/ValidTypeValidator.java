package tpo.project.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import tpo.project.Services.TypeService;

public class ValidTypeValidator implements ConstraintValidator<ValidType, Long> {

    @Autowired
    private TypeService typeService;

    @Override
    public boolean isValid(Long typeId, ConstraintValidatorContext context) {
        return typeService.exists(typeId);
    }
}

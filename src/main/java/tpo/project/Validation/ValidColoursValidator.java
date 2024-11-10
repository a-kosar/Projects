package tpo.project.Validation;

import jakarta.validation.ConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;
import tpo.project.Repositories.ColourRepository;

import java.util.ArrayList;
import java.util.List;

public class ValidColoursValidator implements ConstraintValidator<ValidColours, String> {
    private ColourRepository repository;

    public ValidColoursValidator(ColourRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(ValidColours constraintAnnotation) {
    }

    @Override
    public boolean isValid(String coloursStr, ConstraintValidatorContext context) {
        List<Long> colourIds = new ArrayList<>();
        String[] coloursSplit = coloursStr.split("\\s+");
        for (String colour : coloursSplit) {
            colourIds.add(Long.parseLong(colour));
        }
        for (Long colourId : colourIds) {
            if (!repository.existsById(colourId)) {
                return false;
            }
        }
        return true;
    }
}


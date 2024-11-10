package tpo.project.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tpo.project.Repositories.UserRepository;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

private UserRepository userRepository;

   public UniqueUsernameValidator(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !userRepository.existsByUsername(username);
    }
}

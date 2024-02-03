package realworld.user.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;
import realworld.user.User;
import realworld.user.repository.UserRepository;

import java.util.Optional;

public class NotDuplicatedEmailValidator implements ConstraintValidator<NotDuplicatedEmail, String> {
    private UserRepository userRepository;

    public NotDuplicatedEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(NotDuplicatedEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> userOptional = userRepository.findUserByEmail(s);
        return userOptional.isPresent();
    }
}

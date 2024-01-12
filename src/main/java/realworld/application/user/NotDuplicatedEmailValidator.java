package realworld.application.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;

public class NotDuplicatedEmailValidator implements ConstraintValidator<NotDuplicatedEmail, String> {
    @Override
    public void initialize(NotDuplicatedEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        todo: when finish repository.findUser, then finish this;
        return true;
    }
}

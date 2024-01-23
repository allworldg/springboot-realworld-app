package realworld.user.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotDuplicatedEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotDuplicatedEmail {
    String message() default "Email is duplicated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package realworld.article.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import realworld.article.repository.ArticleRepository;

import java.util.Optional;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {

    private ArticleRepository repository;

    public UniqueTitleValidator(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueTitle constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return repository.getArticleByTitle(s).isEmpty();
    }
}

package edu.school21.app;

import edu.school21.models.Hero;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class InputValidation {

    private final Validator validator;

    public InputValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public boolean HeroValidation(Hero hero) {
        Set<ConstraintViolation<Hero>> violations = validator.validate(hero);

        if (violations.size() == 0) {
            return true;
        }

        for (ConstraintViolation<Hero> violation : violations) {
            System.err.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }

        return false;
    }
}

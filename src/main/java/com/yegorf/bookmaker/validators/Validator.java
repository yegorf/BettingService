package com.yegorf.bookmaker.validators;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validator {
    private ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public void validate(String text) throws Exception {
        validationStrategy.check(text);
    }
}

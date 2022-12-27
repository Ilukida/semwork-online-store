package ru.itis.services.validation;

import ru.itis.dto.SignUpForm;

import java.util.Optional;

public interface Validator {
    Optional<ErrorEntity> validateRegistration(SignUpForm form);
}

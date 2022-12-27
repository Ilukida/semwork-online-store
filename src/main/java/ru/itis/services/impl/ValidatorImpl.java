package ru.itis.services.impl;

import ru.itis.dao.UserRepository;
import ru.itis.dto.SignUpForm;
import ru.itis.services.validation.ErrorEntity;
import ru.itis.services.validation.Validator;

import java.util.Optional;

public class ValidatorImpl implements Validator {

    private final UserRepository userRepository;

    public ValidatorImpl(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @Override
    public Optional<ErrorEntity> validateRegistration(SignUpForm form) {
        if(form.getEmail() == null) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        } else if(userRepository.findByEmail(form.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}

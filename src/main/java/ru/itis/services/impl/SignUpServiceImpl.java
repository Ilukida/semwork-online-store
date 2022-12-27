package ru.itis.services.impl;

import ru.itis.dao.UserRepository;
import ru.itis.dto.SignUpForm;
import ru.itis.excptions.ValidationException;
import ru.itis.models.User;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.SignUpService;
import ru.itis.services.validation.ErrorEntity;
import ru.itis.services.validation.Validator;

import java.util.Optional;

public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public void signUp(SignUpForm form) {
        Optional<ErrorEntity> optionalError = validator.validateRegistration(form);
        if(optionalError.isPresent()) {
            throw new ValidationException(optionalError.get());
        }

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .phoneNumber(form.getPhoneNumber())
                .build();

        userRepository.save(user);
    }
}

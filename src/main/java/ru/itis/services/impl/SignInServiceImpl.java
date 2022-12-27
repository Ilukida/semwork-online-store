package ru.itis.services.impl;

import ru.itis.dao.UserRepository;
import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;
import ru.itis.excptions.ValidationException;
import ru.itis.models.User;
import ru.itis.services.PasswordEncoder;
import ru.itis.services.SignInService;
import ru.itis.services.validation.ErrorEntity;

public class SignInServiceImpl implements SignInService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signIn(SignInForm form) {
        User user = userRepository.findByEmail(form.getEmail())
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));

        if (!passwordEncoder.matches(form.getPassword(), user.getHashPassword())) {
            throw new ValidationException(ErrorEntity.INCORRECT_PASSWORD);
        }

        return UserDto.from(user);
    }

    @Override
    public UserDto signIn(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException(ErrorEntity.NOT_FOUND));
        return UserDto.from(user);
    }
}

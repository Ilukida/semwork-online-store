package ru.itis.services;

import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;

public interface SignInService {
    UserDto signIn(SignInForm signInForm);
    UserDto signIn(String email);
}

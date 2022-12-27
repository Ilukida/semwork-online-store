package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}

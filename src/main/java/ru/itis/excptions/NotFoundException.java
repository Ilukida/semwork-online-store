package ru.itis.excptions;

import ru.itis.services.validation.ErrorEntity;

public class NotFoundException extends ValidationException {
    public NotFoundException(String message) {
        super(ErrorEntity.NOT_FOUND, message);
    }
}
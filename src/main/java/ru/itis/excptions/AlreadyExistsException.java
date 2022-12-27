package ru.itis.excptions;

import ru.itis.services.validation.ErrorEntity;

public class AlreadyExistsException extends ValidationException {
    public AlreadyExistsException(String message) {
        super(ErrorEntity.NOT_FOUND, message);
    }
}
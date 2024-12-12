package com.bibliotech.bibliotech.exception;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {
    private List<String> errorMessages;

    public ValidationException(BindingResult result) {
        super("Erro de validação");
        this.errorMessages = new ArrayList<>();
        result.getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
    }

    public ValidationException(String s) {
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}

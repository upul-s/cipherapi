package com.test.cipherapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


/**
 * App errors exception.
 */
@Getter
@AllArgsConstructor
public class AppErrorsException extends RuntimeException {
    private final AppErrors appErrors;

    public AppErrorsException(List<AppErrors.ErrorMessage> errors) {
        appErrors = new AppErrors().withErrors(errors);
    }

    public AppErrorsException(String key, String message) {
        appErrors = new AppErrors(key, message);
    }

    @Override
    public String getMessage() {
        return appErrors.toString();
    }
}

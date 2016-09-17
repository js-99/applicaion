package com.example.rest.exception;

/**
 * Exception class if user data in not valid
 */
public class UserValidationException extends Exception {

    private static final long serialVersionUID = -5334497922648078399L;

    public UserValidationException(final String message) {
        super(message);
    }

}

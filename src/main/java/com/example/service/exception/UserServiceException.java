package com.example.service.exception;

/**
 * Service exception thrown when user operation fails
 */
public class UserServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public UserServiceException(final String message) {
        super(message);
    }

}

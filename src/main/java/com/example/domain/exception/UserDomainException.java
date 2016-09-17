package com.example.domain.exception;

/**
 * Domain exception thrown when user is not created
 */
public class UserDomainException extends Exception {

    private static final long serialVersionUID = 1063227844360851132L;

    /**
     * @param message
     */
    public UserDomainException(final String message) {
        super(message);
    }

}

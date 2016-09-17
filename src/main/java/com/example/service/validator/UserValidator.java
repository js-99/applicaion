package com.example.service.validator;

import java.util.logging.Logger;

import org.springframework.util.StringUtils;

import com.example.rest.exception.UserValidationException;

/**
 * Class to validate User requests
 */
public class UserValidator {

    private static final int MAX_SIZE_256 = 256;

    private static final Logger logger = Logger.getLogger(UserValidator.class.getName());

    /**
     * Validates first name and last name of a user
     *
     * @param firstName first name
     * @param lastName last name
     * @throws UserValidationException in case of validation error
     */
    public static void validate(final String firstName, final String lastName) throws UserValidationException {

        if (StringUtils.isEmpty(firstName)) {
            logger.severe("First name cannot be empty.");
            throw new UserValidationException("First name cannot be empty.");
        }
        if (StringUtils.isEmpty(lastName)) {
            logger.severe("Last name cannot be empty.");
            throw new UserValidationException("Last name cannot be empty.");
        }
        if (firstName.length() > MAX_SIZE_256) {
            logger.severe("First name length cannot be more than 256 characters");
            throw new UserValidationException("First name length cannot be more than 256 characters");
        }
        if (lastName.length() > MAX_SIZE_256) {
            logger.severe("Last name length cannot be more than 256 characters");
            throw new UserValidationException("Last name length cannot be more than 256 characters");
        }
    }
}

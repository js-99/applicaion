package com.example.service;

import com.example.domain.entity.UserEntity;
import com.example.rest.exception.UserValidationException;
import com.example.service.exception.UserServiceException;

/**
 * Service Interface that exposes various operations that can be performed on user
 */
public interface UserService {

    /**
     * Creates a user
     *
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @throws UserServiceException thrown when user creation failed
     * @throws UserValidationException in case of validation errors
     */
    public void createUser(String firstName, String lastName) throws UserServiceException, UserValidationException;

    /**
     * query a user
     *
     * @param firstName first name
     * @param lastName last name
     * @return UserEntity
     * @throws UserServiceException thrown when user query failed
     * @throws UserValidationException in case of validation errors
     */
    public UserEntity queryUser(final String firstName, String lastName) throws UserServiceException, UserValidationException;

    /**
     * Query a user
     *
     * @param id user identifier
     * @return UserEntity
     * @throws UserServiceException thrown when user query failed
     */
    public UserEntity queryUser(long id) throws UserServiceException;

    /**
     * Query a user
     *
     * @param id user identifier
     * @param firstName first name
     * @param lastName last name
     * @throws UserServiceException throws when user update failed
     * @throws UserValidationException in case of validation errors
     */
    public void updateUser(long id, String firstName, String lastName) throws UserServiceException, UserValidationException;

    /**
     * Deletes a user
     *
     * @param id user identifier
     * @throws UserServiceException thrown if user deletion failed
     */
    public void deleteUser(final long id) throws UserServiceException;

}

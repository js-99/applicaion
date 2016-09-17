package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.UserRepository;
import com.example.domain.UserRepositoryImpl;
import com.example.domain.entity.UserEntity;
import com.example.domain.exception.UserDomainException;
import com.example.rest.exception.UserValidationException;
import com.example.service.exception.UserServiceException;
import com.example.service.validator.UserValidator;

/**
 * User service implementaion
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepositoryImpl userRepositoryImpl) {
        this.userRepository = userRepositoryImpl;
    }

    @Override
    public void createUser(final String firstName, final String lastName) throws UserValidationException, UserServiceException {
        try {
            UserValidator.validate(firstName, lastName);
            if (userRepository.createUser(firstName, lastName)) {
                return;
            }
            logger.severe("Unable to create the user with firstname: " + firstName + "and lastname :" + lastName);
            throw new UserServiceException("Unable to create the user with firstname: " + firstName + "and lastname :" + lastName);
        } catch (final UserDomainException e) {
            logger.severe("Unable to create the user with firstname: " + firstName + "and lastname :" + lastName + " Exception: " + e.getMessage() + e);
            throw new UserServiceException("Unable to create the user with firstname: " + firstName + "and lastname :" + lastName);
        }

    }

    @Override
    public UserEntity queryUser(final String firstName, final String lastName) throws UserValidationException, UserServiceException {
        try {
            UserValidator.validate(firstName, lastName);
            final List<UserEntity> userEntities = userRepository.queryUser(firstName, lastName);
            if (userEntities == null || userEntities.isEmpty()) {
                logger.severe("Unable to find the user with firstname: " + firstName + "and lastname :" + lastName);
                throw new UserServiceException("Unable to find the user with firstname: " + firstName + "and lastname :" + lastName);
            } else if (userEntities.size() == 1) {
                return userEntities.get(0);
            } else {
                logger.severe("Some error occurred while querying the user with firstname: " + firstName + "and lastname :" + lastName);
                throw new UserServiceException("Some error occurred while querying the user with firstname: " + firstName + "and lastname :" + lastName);
            }
        } catch (final UserDomainException e) {
            logger.severe("Unable to find the user with firstname: " + firstName + "and lastname :" + lastName + " Exception: " + e.getMessage() + e);
            throw new UserServiceException("Unable to find the user with firstname: " + firstName + "and lastname :" + lastName);
        }
    }

    @Override
    public UserEntity queryUser(final long id) throws UserServiceException {
        try {
            final List<UserEntity> userEntities = userRepository.queryUser(id);
            if (userEntities == null || userEntities.isEmpty()) {
                logger.severe("Unable to find the user with id: " + id);
                throw new UserServiceException("Unable to find the user with id: " + id);
            } else if (userEntities.size() == 1) {
                return userEntities.get(0);
            } else {
                logger.severe("Some error occurred while querying the user with id: " + id);
                throw new UserServiceException("Some error occurred while querying the user with id: " + id);
            }
        } catch (final UserDomainException e) {
            logger.severe("Unable to query user with id: " + id + " Exception: " + e.getMessage() + e);
            throw new UserServiceException("Unable to q user with id: " + id);
        }
    }

    @Override
    public void deleteUser(final long id) throws UserServiceException {
        try {
            if (userRepository.deleteUser(id)) {
                return;
            }
            logger.severe("Unable to delete user with id: " + id);
            throw new UserServiceException("Unable to delete user with id: " + id);
        } catch (final UserDomainException e) {
            logger.severe("Unable to delete user with id: " + id + " Exception: " + e.getMessage() + e);
            throw new UserServiceException("Unable to delete user with id: " + id);
        }
    }

    @Override
    public void updateUser(final long id, final String firstName, final String lastName) throws UserValidationException, UserServiceException {
        try {
            UserValidator.validate(firstName, lastName);
            final UserEntity userEntity = queryUser(id);
            userEntity.setFirstName(firstName);
            userEntity.setLastName(lastName);
            userEntity.setUpdatedDate(new Date());
            if (userRepository.updateUser(userEntity)) {
                return;
            }
            logger.severe("Unable to update user with id: " + id);
            throw new UserServiceException("Unable to update user with id: " + id);
        } catch (final UserDomainException e) {
            logger.severe("Unable to update user with id: " + id + " Exception: " + e.getMessage() + e);
            throw new UserServiceException("Unable to update user with id: " + id);
        }

    }
}

package com.example.domain;

import java.util.List;

import com.example.domain.entity.UserEntity;
import com.example.domain.exception.UserDomainException;

/**
 * Interface for user repository
 */
public interface UserRepository {

    /**
     * create a user
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @return true is user creation was successful
     * @throws UserDomainException if user creation was not successful
     */
    public boolean createUser(String firstName, String lastName) throws UserDomainException;

    /**
     * query user
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @return list of users
     * @throws UserDomainException if user query was not successful
     */
    public List<UserEntity> queryUser(String firstName, String lastName) throws UserDomainException;

    /**
     * query user
     *
     * @param id user id
     * @return list of users
     * @throws UserDomainException if user query was not successful
     */
    public List<UserEntity> queryUser(final long id) throws UserDomainException;

    /**
     * delete a user
     *
     * @param id user id
     * @return true id delete user was successful
     * @throws UserDomainException if user deletion was not successful
     */
    public boolean deleteUser(long id) throws UserDomainException;

    /**
     * update an existing user
     *
     * @param userEntity user details
     * @return true if update user was not successful
     * @throws UserDomainException if user deletion was not successful
     */
    public boolean updateUser(UserEntity userEntity) throws UserDomainException;

}

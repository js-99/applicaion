package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.entity.UserEntity;
import com.example.domain.exception.UserDomainException;

/**
 * Implementaion of user repository
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(final JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @Override
    public boolean createUser(final String firstName, final String lastName) throws UserDomainException {
        try {
            final String sql = "insert into user (first_Name, last_Name, created_date, updated_date) values (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            final int rows = jdbcTemplate.update(sql, firstName, lastName);
            return rows == 1;
        } catch (final DataAccessException e) {
            logger.severe("User creation failed with: " + e.getMessage() + e);
            throw new UserDomainException(e.getMessage());
        }
    }

    @Override
    public List<UserEntity> queryUser(final String firstName, final String lastName) throws UserDomainException {
        try {
            final List<UserEntity> userEntities = new ArrayList<UserEntity>();
            jdbcTemplate
                .query(
                    "select id, first_name, last_name, created_date, updated_date  from user where first_name = ? and last_name = ?",
                    new Object[] { firstName, lastName },
                    (rs, rowNum) -> new UserEntity(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getTimestamp("created_date"),
                        rs.getTimestamp("updated_date")))
                .forEach(e -> userEntities.add(e));
            return userEntities;
        } catch (final DataAccessException e) {
            logger.severe("User query failed with: " + e.getMessage() + e);
            throw new UserDomainException(e.getMessage());
        }
    }

    @Override
    public List<UserEntity> queryUser(final long id) throws UserDomainException {
        try {
            final List<UserEntity> userEntities = new ArrayList<UserEntity>();
            jdbcTemplate
                .query(
                    "select id, first_name, last_name, created_date, updated_date  from user where id = ?",
                    new Object[] { id },
                    (rs, rowNum) -> new UserEntity(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getTimestamp("created_date"),
                        rs.getTimestamp("updated_date")))
                .forEach(e -> userEntities.add(e));
            return userEntities;
        } catch (final DataAccessException e) {
            logger.severe("User query failed with: " + e.getMessage() + e);
            throw new UserDomainException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(final long id) throws UserDomainException {
        try {
            final int rows = jdbcTemplate.update("delete from user where id = ?", id);
            return rows == 1;

        } catch (final DataAccessException e) {
            logger.severe("User deletion failed with: " + e.getMessage() + e);
            throw new UserDomainException(e.getMessage());
        }
    }

    @Override
    public boolean updateUser(final UserEntity userEntity) throws UserDomainException {
        try {
            final String query = "update user set first_name=?,last_name=?, updated_date=? where id=?";
            final int rows = jdbcTemplate
                .update(query, new Object[] { userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUpdatedDate(), userEntity.getId() });
            return rows == 1;
        } catch (final DataAccessException e) {
            logger.severe("User update failed with: " + e.getMessage() + e);
            throw new UserDomainException(e.getMessage());
        }
    }
}

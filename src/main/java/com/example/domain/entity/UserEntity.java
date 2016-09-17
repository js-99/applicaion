package com.example.domain.entity;

import java.util.Date;

/**
 * User entity
 */
public class UserEntity {

    private long id;

    private String firstName;

    private String lastName;

    private Date createdDate;

    private Date updatedDate;

    public UserEntity(final long id, final String firstName, final String lastName, final Date createdDate, final Date updatedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public UserEntity(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedDate() {
        if (createdDate != null) {
            return new Date(createdDate.getTime());
        }
        return null;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        if (updatedDate != null) {
            return new Date(updatedDate.getTime());
        }
        return null;
    }

    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}

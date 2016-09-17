package com.example.rest.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Data object for User request
 */
public class UserRequest {

    private final String firstName;

    private final String lastName;

    public UserRequest(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private UserRequest() {
        this.firstName = null;
        this.lastName = null;
        // for json
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

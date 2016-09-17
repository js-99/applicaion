package com.example.rest.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Response for create subscription operation
 */
public class CreateSubscriptionResponse extends SubscriptionResponse {

    private final String accountIdentifier;

    public CreateSubscriptionResponse(final String accountIdentifier) {
        super(true, null);
        this.accountIdentifier = accountIdentifier;
    }

    public CreateSubscriptionResponse(final String accountIdentifier, final String message) {
        super(true, message);
        this.accountIdentifier = accountIdentifier;
    }

    /**
     * @return the accountIdentifier
     */
    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

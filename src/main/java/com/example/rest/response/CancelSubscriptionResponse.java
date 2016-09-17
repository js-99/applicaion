package com.example.rest.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Response for cancel subscription operation
 */
public class CancelSubscriptionResponse extends SubscriptionResponse {

    public CancelSubscriptionResponse() {
        super(true, null);
    }

    public CancelSubscriptionResponse(final String message) {
        super(true, message);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

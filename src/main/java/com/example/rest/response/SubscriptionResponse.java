package com.example.rest.response;

/**
 * Abstract Response class for subscription operations
 */
public abstract class SubscriptionResponse {

    private final boolean success;

    private final String message;

    /**
     * If the response is successful
     */
    public SubscriptionResponse(final boolean success, final String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}

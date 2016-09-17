package com.example.rest.response;

/**
 * Abstract Response class for subscription operations
 */
public abstract class SubscriptionResponse {

    private boolean success;

    private String message;

    /**
     * If the response is successful
     */
    public SubscriptionResponse(final boolean success, final String message) {
        this.success = success;
        this.message = message;
    }

    protected SubscriptionResponse() {
        // For json
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

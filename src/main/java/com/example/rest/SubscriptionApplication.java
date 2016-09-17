package com.example.rest;

import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.rest.response.CancelSubscriptionResponse;
import com.example.rest.response.CreateSubscriptionResponse;
import com.example.rest.response.ErrorCode;
import com.example.rest.response.ErrorResponse;
import com.example.rest.response.SubscriptionResponse;

/**
 * Application class to perform various operations on subscription
 */
@Path(value = "/api/notification/subscription")
@Component
public class SubscriptionApplication {

    private static final String EVENT_URL = "eventUrl";

    private static final Logger logger = Logger.getLogger(SubscriptionApplication.class.getName());

    /**
     * Creates a subscription
     * 
     * @param url eventUrl
     * @return
     */
    @POST
    @Produces("application/json")
    @Path(value = "/create")
    public Response createSubscription(@QueryParam(EVENT_URL) final String url) {
        logger.info("Create subscription called with eventUrl: " + url);
        if (StringUtils.isEmpty(url)) {
            // Mocking a scenario to return USER_NOT_FOUND
            final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_FOUND, "URL not passed.");
            return buildResponse(Status.BAD_REQUEST, errorResponse);
        }
        //TODO need enhancement, for now having a NO-OP
        final CreateSubscriptionResponse response = new CreateSubscriptionResponse(UUID.randomUUID().toString(), "Subscription successfully created.");
        return buildResponse(Status.CREATED, response);
    }

    /**
     * Cancels a subscription
     * 
     * @param url eventUrl
     * @return
     */
    @POST
    @Produces("application/json")
    @Path(value = "/cancel")
    public Response cancelSubscription(@QueryParam(EVENT_URL) final String url) {
        logger.info("Cancel subscription called with eventUrl: " + url);
        if (StringUtils.isEmpty(url)) {
            // Mocking a scenario to return USER_NOT_FOUND
            final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_FOUND, "URL not passed.");
            return buildResponse(Status.BAD_REQUEST, errorResponse);
        }
        //TODO need enhancement, for now having a NO-OP
        final CancelSubscriptionResponse response = new CancelSubscriptionResponse("Subscription successfully canceled.");
        return buildResponse(Status.OK, response);
    }

    private Response buildResponse(final Status status, final SubscriptionResponse response) {
        ResponseBuilder builder;
        builder = Response.status(status);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(response);
        return builder.build();
    }

}

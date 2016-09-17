package com.example.rest;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.entity.UserEntity;
import com.example.rest.exception.UserValidationException;
import com.example.rest.request.UserRequest;
import com.example.service.UserServiceImpl;
import com.example.service.exception.UserServiceException;

/**
 * Application class to perform various CRUD operations on user
 */
@Path(value = "/myApp")
@Component
public class UserApplication {

    private static final Logger logger = Logger.getLogger(UserApplication.class.getName());

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserApplication(final UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Adds a user in the application
     * 
     * @param userRequest user details
     * @return
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/users/add")
    public Response add(final UserRequest userRequest) {
        logger.info("Add user called with request: " + userRequest);
        if (userRequest == null) {
            return buildResponse(Status.BAD_REQUEST, new String("Request cannot be empty"));
        }
        try {
            userServiceImpl.createUser(userRequest.getFirstName(), userRequest.getLastName());
            return buildResponse(Status.CREATED, new String("User created."));
        } catch (final UserValidationException e) {
            return buildResponse(Status.BAD_REQUEST, new String(e.getMessage()));
        } catch (final UserServiceException e) {
            return buildResponse(Status.INTERNAL_SERVER_ERROR, new String("User creation failed."));
        }

    }

    /**
     * Query a user by firstName and lastName
     * 
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @return
     */
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/users")
    public Response query(@QueryParam("firstName") final String firstName, @QueryParam("lastName") final String lastName) {
        logger.info("Query user called for firstName: " + firstName + " and lastName :" + lastName);
        try {
            final UserEntity userEntity = userServiceImpl.queryUser(firstName, lastName);
            return buildResponse(Status.OK, userEntity);

        } catch (final UserValidationException e) {
            return buildResponse(Status.BAD_REQUEST, new String(e.getMessage()));
        } catch (final UserServiceException e) {
            return buildResponse(Status.INTERNAL_SERVER_ERROR, new String("User query failed."));
        }
    }

    /**
     * Query a user by its id
     * 
     * @param id user identifier
     * @return
     */
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/users/id/{id}")
    public Response query(@PathParam("id") final long id) {
        logger.info("Query user called for id: " + id);
        try {
            final UserEntity userEntity = userServiceImpl.queryUser(id);
            return buildResponse(Status.OK, userEntity);

        } catch (final UserServiceException e) {
            return buildResponse(Status.INTERNAL_SERVER_ERROR, new String("User query failed."));
        }
    }

    /**
     * Update an existing user
     * 
     * @param id user identifier
     * @param userRequest details of the user
     * @return
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/users/id/{id}")
    public Response update(@PathParam("id") final long id, final UserRequest userRequest) {
        logger.info("Update user called for id: " + id);
        if (userRequest == null) {
            return buildResponse(Status.BAD_REQUEST, new String("Request cannot be empty"));
        }
        try {
            userServiceImpl.updateUser(id, userRequest.getFirstName(), userRequest.getLastName());
            return buildResponse(Status.OK, new String("User updated successfully."));

        } catch (final UserValidationException e) {
            return buildResponse(Status.BAD_REQUEST, new String(e.getMessage()));
        } catch (final UserServiceException e) {
            return buildResponse(Status.INTERNAL_SERVER_ERROR, new String("Update user failed."));
        }
    }

    /**
     * Delete an existing user
     * 
     * @param id user identifier
     * @return
     */
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    @Path(value = "/users/id/{id}")
    public Response delete(@PathParam("id") final long id) {
        logger.info("Delete user called for id: " + id);
        try {
            userServiceImpl.deleteUser(id);
            return buildResponse(Status.OK, new String("User deleted successfully."));

        } catch (final UserServiceException e) {
            return buildResponse(Status.INTERNAL_SERVER_ERROR, new String("Delete user failed."));
        }
    }

    private Response buildResponse(final Status status, final Object response) {
        ResponseBuilder builder;
        builder = Response.status(status);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(response);
        return builder.build();
    }

}

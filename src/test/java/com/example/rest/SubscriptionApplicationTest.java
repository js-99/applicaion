package org.example.rest;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import org.springframework.web.client.RestTemplate;

import com.example.Main;
import com.example.rest.response.CancelSubscriptionResponse;
import com.example.rest.response.CreateSubscriptionResponse;
import com.example.rest.response.ErrorCode;
import com.example.rest.response.ErrorResponse;

import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests for SubscriptionApplication
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest
public class SubscriptionApplicationTest {

    private static String CREATE_URL = "http://localhost:8888/subscription/create";
    
    private static String CANCEL_URL = "http://localhost:8888/subscription/cancel";

    private RestTemplate restTemplate = new TestRestTemplate();

    /**
     * Test create subscription by passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCreateSubscription() throws Exception {
        CreateSubscriptionResponse response = restTemplate.getForObject(CREATE_URL + "?eventUrl=http://abc.com", CreateSubscriptionResponse.class);
        assertNotNull("Create Subscription Response cannot be null.", response);
        assertEquals("Subscription successfully created.", response.getMessage());
        assertTrue(response.isSuccess());
        assertNotNull("AccountIdentifier cannot be null.", response.getAccountIdentifier());
    }

    /**
     * Test create subscription by not passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCreateSubscriptionWithoutUrl() throws Exception {
        ErrorResponse response = restTemplate.getForObject(CREATE_URL, ErrorResponse.class);
        assertNotNull("Create Subscription Response cannot be null.", response);
        assertEquals("URL not passed.", response.getMessage());
        assertFalse(response.isSuccess());
        assertEquals(ErrorCode.USER_NOT_FOUND, response.getErrorCode());
    }
    
    /**
     * Test cancel subscription by passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCancelSubscription() throws Exception {
        CancelSubscriptionResponse response = restTemplate.getForObject(CANCEL_URL+ "?eventUrl=http://abc.com", CancelSubscriptionResponse.class);
        assertNotNull("Cancel Subscription Response cannot be null.", response);
        assertEquals("Subscription successfully canceled.", response.getMessage());
        assertTrue(response.isSuccess());
    }

    /**
     * Test cancel subscription by not passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCancelSubscriptionWithoutUrl() throws Exception {
        ErrorResponse response = restTemplate.getForObject(CANCEL_URL, ErrorResponse.class);
        assertNotNull("Cancel Subscription Response cannot be null.", response);
        assertEquals("URL not passed.", response.getMessage());
        assertFalse(response.isSuccess());
        assertEquals(ErrorCode.USER_NOT_FOUND, response.getErrorCode());
    }
}

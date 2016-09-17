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
import com.example.rest.response.ErrorCode;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests for SubscriptionApplication
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest
public class SubscriptionApplicationTest {

    private static String CREATE_URL = "http://localhost:8888/api/notification/subscription/create";
    
    private static String CANCEL_URL = "http://localhost:8888/api/notification/subscription/cancel";

    private RestTemplate restTemplate = new TestRestTemplate();

    /**
     * Test create subscription by passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCreateSubscription() throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, requestHeaders);
        Map<String, Object> response = restTemplate.postForObject(CREATE_URL + "?eventUrl=http://abc.com", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertNotNull("Create Subscription Response cannot be null.", response);
        String message = response.get("message").toString();
        assertEquals("Subscription successfully created.", message);
        String success = response.get("success").toString();
        assertEquals("true", success);
        String accountId = response.get("accountIdentifier").toString();
        assertNotNull("AccountIdentifier cannot be null.", accountId);
    }

    /**
     * Test create subscription by not passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCreateSubscriptionWithoutUrl() throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, requestHeaders);
        Map<String, Object> response = restTemplate.postForObject(CREATE_URL, httpEntity, Map.class, Collections.EMPTY_MAP);
        assertNotNull("Create Subscription Response cannot be null.", response);
        String message = response.get("message").toString();
        assertEquals("URL not passed.", message);
        String success = response.get("success").toString();
        assertEquals("false", success);
        Object accountId = response.get("accountId");
        assertNull("AccountIdentifier should be null.", accountId);
        String errorCode = response.get("errorCode").toString();
        assertEquals(ErrorCode.USER_NOT_FOUND.toString(), errorCode);
    }
    
    /**
     * Test cancel subscription by passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCancelSubscription() throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, requestHeaders);
        Map<String, Object> response = restTemplate.postForObject(CANCEL_URL + "?eventUrl=http://abc.com", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertNotNull("Cancel Subscription Response cannot be null.", response);
        String message = response.get("message").toString();
        assertEquals("Subscription successfully canceled.", message);
        String success = response.get("success").toString();
        assertEquals("true", success);
    }

    /**
     * Test cancel subscription by not passing a url
     * 
     * @throws Exception
     */
    @Test
    public void testCancelSubscriptionWithoutUrl() throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, requestHeaders);
        Map<String, Object> response = restTemplate.postForObject(CANCEL_URL, httpEntity, Map.class, Collections.EMPTY_MAP);
        assertNotNull("Cancel Subscription Response cannot be null.", response);
        String message = response.get("message").toString();
        assertEquals("URL not passed.", message);
        String success = response.get("success").toString();
        assertEquals("false", success);
        String errorCode = response.get("errorCode").toString();
        assertEquals(ErrorCode.USER_NOT_FOUND.toString(), errorCode);
    }
}

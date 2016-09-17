package org.example.rest;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert.*;
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
import com.example.domain.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests for UserApplication 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest
public class UserApplicationTest {

    private static String CREATE_URL = "http://localhost:8888/myApp/users";

    private ObjectMapper objectMapper = new ObjectMapper();

    private RestTemplate restTemplate = new TestRestTemplate();

    /**
     * Test create user by passing firstname and lastname
     * 
     * @throws Exception
     */
    @Test
    public void testCreateUser() throws Exception {
        // Add user
        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("firstName", "Bob");
        requestBody.put("lastName", "Builder");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(objectMapper.writeValueAsString(requestBody), requestHeaders);
        String addResponse = restTemplate.postForObject(CREATE_URL + "/add", httpEntity, String.class);
        assertNotNull("Add user response has to be non-null.", addResponse);
        assertEquals("User created.", addResponse);
        
        // query user by id
        UserEntity getResponse = restTemplate.getForObject(CREATE_URL + "/id/1", UserEntity.class);
        assertNotNull("Get user response cannot be null.", getResponse);
        assertEquals(1, getResponse.getId());
        assertEquals("Bob", getResponse.getFirstName());
        assertEquals("Builder", getResponse.getLastName());
        
        
        // query user by first name and last name
        UserEntity getResponse1 = restTemplate.getForObject(CREATE_URL + "?firstName=Bob&lastName=Builder", UserEntity.class);
        assertNotNull("Get user response cannot be null.", getResponse1);
        assertEquals(getResponse, getResponse1);

        // update user
        Map<String, Object> updateBody = new HashMap<String, Object>();
        updateBody.put("firstName", "BobNew");
        updateBody.put("lastName", "BuilderNew");
        restTemplate.put(CREATE_URL + "/id/1", updateBody, String.class);
        
        // query user by id
        UserEntity getResponse2 = restTemplate.getForObject(CREATE_URL + "/id/1", UserEntity.class);
        assertNotNull("Get user response cannot be null.", getResponse);
        assertEquals(1, getResponse2.getId());
        assertEquals("BobNew", getResponse2.getFirstName());
        assertEquals("BuilderNew", getResponse2.getLastName());

        // delete user
        restTemplate.delete(CREATE_URL + "/id/1");
        
        // query user by id
        String getResponse3 = restTemplate.getForObject(CREATE_URL + "/id/1", String.class);
        assertEquals("User query failed.", getResponse3);
    }
}












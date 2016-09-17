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

    //TODO work in progress. Add more tests
    private static String CREATE_URL = "http://localhost:8888/users";

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
        Map<String, Object> response = restTemplate.postForObject(CREATE_URL + "/add", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertNull("Add user response has to be null.", response);
    }
}












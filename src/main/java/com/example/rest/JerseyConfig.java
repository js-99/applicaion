package com.example.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Config class which registers various applications
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(SubscriptionApplication.class);
        register(UserApplication.class);
    }
}

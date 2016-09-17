package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class
 */
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        new Main().configure(new SpringApplicationBuilder(Main.class)).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }
}

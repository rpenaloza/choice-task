package com.encora.choice.webapp.config;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WebConfigTest {

    WebConfig config = new WebConfig();
    @Test
    void configureMessageConverters() {
        assertDoesNotThrow(()->config.configureMessageConverters(new ArrayList<>()));
    }

    @Test
    void methodValidationPostProcessor() {
        assertNotNull(config.methodValidationPostProcessor());
    }
}
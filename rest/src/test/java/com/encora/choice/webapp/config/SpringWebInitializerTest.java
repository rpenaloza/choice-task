package com.encora.choice.webapp.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpringWebInitializerTest {

    SpringWebInitializer initializer = new SpringWebInitializer();

    @Test
    void getServletConfigClasses() {
        assertNotNull(initializer.getServletConfigClasses());
    }

    @Test
    void getServletMappings() {
        assertNotNull(initializer.getServletMappings());
    }

    @Test
    void getRootConfigClasses() {
        assertNotNull(initializer.getRootConfigClasses());
    }
}
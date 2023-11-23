package com.encora.choice.webapp.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ModelMapperConfigTest {

    @Test
    void mapper() {
        ModelMapperConfig config = new ModelMapperConfig();
        ModelMapper mapper = config.mapper();
        assertNotNull(mapper);

    }
}
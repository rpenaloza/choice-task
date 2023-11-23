package com.encora.choice.webapp.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WSClientConfigTest {

    WSClientConfig config = new WSClientConfig();
    @Test
    void wsClient() {
        assertNotNull(config.wsClient());
    }

    @Test
    void faultCodeResponseMap() {
        assertNotNull(config.wsClient());
    }
}
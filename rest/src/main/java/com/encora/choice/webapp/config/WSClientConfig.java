package com.encora.choice.webapp.config;

import com.encora.choice.webapp.ws.client.HotelCatalogPort;
import com.encora.choice.webapp.ws.client.HotelCatalogPortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WSClientConfig {

    @Bean
    public HotelCatalogPort wsClient(){
        HotelCatalogPortService port = new HotelCatalogPortService();
        return port.getHotelCatalogPortSoap11();
    }
}

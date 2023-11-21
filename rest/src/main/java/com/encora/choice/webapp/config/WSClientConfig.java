package com.encora.choice.webapp.config;

import com.encora.choice.webapp.ws.client.HotelCatalogPort;
import com.encora.choice.webapp.ws.client.HotelCatalogPortService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for SOAP service Client
 */
@Configuration
public class WSClientConfig {

    @Value("${ws.client.fault.hotel.notFound}")
    private String hotelNotFoundFaultMessage;

    @Value("${ws.client.fault.amenity.notFound}")
    private String amenityNotFoundMessage;

    @Bean
    public HotelCatalogPort wsClient(){
        HotelCatalogPortService port = new HotelCatalogPortService();
        return port.getHotelCatalogPortSoap11();
    }

    @Bean
    @Qualifier("faultCodeResponseMap")
    public Map<String, HttpStatus> faultCodeResponseMap() {
        Map<String, HttpStatus> m = new HashMap<>();
        m.put(hotelNotFoundFaultMessage, HttpStatus.NOT_FOUND);
        m.put(amenityNotFoundMessage, HttpStatus.NOT_FOUND);
        return m;
    }
}

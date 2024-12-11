package com.WebServices_Reservation_System.soap;

import com.WebServices_Reservation_System.services.ReservationService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    private final Bus bus;
    private final ReservationService reservationService;

    public CxfConfig(Bus bus, ReservationService reservationService) {
        this.bus = bus;
        this.reservationService = reservationService;
    }

    @Bean
    public Endpoint reservationEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, reservationService);
        endpoint.publish("/ReservationService");  // This is where the service is exposed
        return endpoint;
    }
}

package com.WebServices_Reservation_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.WebServices_Reservation_System", "com.WebServices_Reservation_System.services", "com.WebServices_Reservation_System.soap"})
public class WebServicesReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServicesReservationSystemApplication.class, args);
	}

}

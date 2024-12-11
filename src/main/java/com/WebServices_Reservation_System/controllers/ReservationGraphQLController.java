package com.WebServices_Reservation_System.controllers;

import com.WebServices_Reservation_System.dto.ReservationDTO;
import com.WebServices_Reservation_System.services.ReservationGraphQLService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ReservationGraphQLController {

    private final ReservationGraphQLService reservationGraphQLService;

    @Autowired
    public ReservationGraphQLController(ReservationGraphQLService reservationGraphQLService) {
        this.reservationGraphQLService = reservationGraphQLService;
    }

    @QueryMapping
    public List<ReservationDTO> allReservations() {
        return reservationGraphQLService.getAllReservations();
    }

    @QueryMapping
    public ReservationDTO reservationById(Long id) {
        Optional<ReservationDTO> reservation = reservationGraphQLService.getReservationById(id);
        return reservation.orElse(null);
    }

    @MutationMapping
    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        return reservationGraphQLService.addReservation(reservationDTO);
    }

    @MutationMapping
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        return reservationGraphQLService.updateReservation(id, reservationDTO);
    }

    @MutationMapping
    public String deleteReservation(Long id) {
        return reservationGraphQLService.deleteReservation(id);
    }
}

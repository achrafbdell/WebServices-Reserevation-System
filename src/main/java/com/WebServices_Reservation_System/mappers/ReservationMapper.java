package com.WebServices_Reservation_System.mappers;

import com.WebServices_Reservation_System.dto.ReservationDTO;
import com.WebServices_Reservation_System.models.Reservation;

import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation reservation) {

        return new ReservationDTO(
                reservation.getClientName(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getRoomPreferences()
        );
    }

    public Reservation toEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setClientName(reservationDTO.getClientName());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setRoomPreferences(reservationDTO.getRoomPreferences());

        return reservation;
    }


}

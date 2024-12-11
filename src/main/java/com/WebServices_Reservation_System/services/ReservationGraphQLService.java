package com.WebServices_Reservation_System.services;

import com.WebServices_Reservation_System.models.Reservation;
import com.WebServices_Reservation_System.dto.ReservationDTO;
import com.WebServices_Reservation_System.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationGraphQLService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationGraphQLService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDTO> getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        // Map DTO to Entity
        reservation.setClientName(reservationDTO.getClientName());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setRoomPreferences(reservationDTO.getRoomPreferences());

        reservation = reservationRepository.save(reservation);
        return convertToDTO(reservation);
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            reservation.setClientName(reservationDTO.getClientName());
            reservation.setStartDate(reservationDTO.getStartDate());
            reservation.setEndDate(reservationDTO.getEndDate());
            reservation.setRoomPreferences(reservationDTO.getRoomPreferences());

            reservation = reservationRepository.save(reservation);
            return convertToDTO(reservation);
        }
        return null; // Or throw exception
    }

    public String deleteReservation(Long id) {
        reservationRepository.deleteById(id);
        return "Reservation deleted successfully";
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getClientName(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getRoomPreferences()
        );
    }
}

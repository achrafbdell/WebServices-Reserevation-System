package com.WebServices_Reservation_System.services;

import com.WebServices_Reservation_System.mappers.ReservationMapper;
import com.WebServices_Reservation_System.models.Reservation;
import com.WebServices_Reservation_System.repositories.ReservationRepository;
import com.WebServices_Reservation_System.dto.ReservationDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebService(serviceName = "ReservationService")
@Component
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReservationRepository reservationRepository;

    @WebMethod
    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDTO(savedReservation);
    }

    @WebMethod
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @WebMethod
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);

        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            if (reservationDTO.getClientName() != null) {
                reservation.setClientName(reservationDTO.getClientName());
            }
            if (reservationDTO.getStartDate() != null) {
                reservation.setStartDate(reservationDTO.getStartDate());
            }
            if (reservationDTO.getEndDate() != null) {
                reservation.setEndDate(reservationDTO.getEndDate());
            }
            if (reservationDTO.getRoomPreferences() != null) {
                reservation.setRoomPreferences(reservationDTO.getRoomPreferences());
            }
            Reservation updatedReservation = reservationRepository.save(reservation);
            return reservationMapper.toDTO(updatedReservation);
        }
        throw new RuntimeException("Reservation not found");
    }

    @WebMethod
    public String deleteReservation(Long id) {
        reservationRepository.deleteById(id);
        return "Reservation supprimer avec succes";
    }
}
